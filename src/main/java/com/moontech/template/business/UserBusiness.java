package com.moontech.template.business;

import com.moontech.template.constants.ErrorConstant;
import com.moontech.template.constants.LogConstant;
import com.moontech.template.entities.ConfirmationTokenEntity;
import com.moontech.template.entities.RoleEntity;
import com.moontech.template.entities.UserEntity;
import com.moontech.template.enums.EmailTemplate;
import com.moontech.template.enums.Status;
import com.moontech.template.exceptions.custom.BusinessException;
import com.moontech.template.models.requests.UserRequest;
import com.moontech.template.models.responses.UserResponse;
import com.moontech.template.notifications.Notification;
import com.moontech.template.repositories.UserRepository;
import com.moontech.template.security.utilities.SecurityUtilities;
import com.moontech.template.services.CaptchaService;
import com.moontech.template.services.UserService;
import com.moontech.template.utilities.EmailUtilities;
import com.moontech.template.utilities.Utilities;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de las reglas de negocio.
 *
 * @author Felipe Monzón
 * @since 01 Dic, 2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserBusiness implements UserService {
  /** Repositorio de usuarios. */
  private final UserRepository userRepository;
  /** Servicio para el token de confirmación. */
  private final ConfirmationTokenBusiness confirmationTokenBusiness;
  /** Servicio para mensajería. */
  private final Notification notification;
  /** Servicio de captcha. */
  private final CaptchaService captchaService;
  /** Puerto de la aplicación. */
  @Value("${api.uri.data.confirm}")
  private String confirmationPath;

  /** {@inheritDoc}. */
  @Override
  @Transactional(readOnly = true)
  public Page<UserResponse> retrieve(Pageable pageable) {
    int currentPage = Utilities.getCurrentPage(pageable);
    log.info(LogConstant.USERS_RETRIEVE, currentPage);
    return this.userRepository
        .findAll(PageRequest.of(currentPage, pageable.getPageSize()))
        .map(this::mapping);
  }

  /** {@inheritDoc}. */
  @Override
  @Transactional
  public void save(UserRequest request) {
    log.info("Guarda los datos del usuario {}", request);
    Optional<UserEntity> user = this.userRepository.findByUsername(request.getUsername());

    if (user.isPresent()) {
      throw new BusinessException(ErrorConstant.DATA_EXIST_CODE, ErrorConstant.USERNAME_EXIST);
    } else {
      this.captchaService.validate(request.getReCaptcha());
      request.setStatus(Status.PENDING_TO_CONFIRM);
      request.setPassword(SecurityUtilities.passwordEncoder(request.getPassword()));
      UserEntity userEntity = this.mapping(request);
      userEntity.setRoles(
          request.getProfiles().stream().map(this::mapping).collect(Collectors.toSet()));
      this.userRepository.save(userEntity);
      this.generateConfirmationToken(userEntity);
    }
  }

  /** {@inheritDoc}. */
  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void confirm(String token) {
    log.debug("Confirma el usuario con el token {}", token);
    ConfirmationTokenEntity confirmToken =
        this.confirmationTokenBusiness.getConfirmationByToken(token);
    Optional<UserEntity> user = this.userRepository.findById(confirmToken.getUser().getId());

    if (user.isPresent()) {
      user.get().setStatus(Status.ACTIVE);
      this.userRepository.save(user.get());
      this.confirmationTokenBusiness.save(confirmToken, Status.USED);
    } else {
      throw new BusinessException(ErrorConstant.ACCESS_DENIED_CODE, ErrorConstant.INVALID_TOKEN);
    }
  }

  /**
   * Genera y guarda el token de confirmación.
   *
   * @param userEntity datos del usuario
   */
  private void generateConfirmationToken(final UserEntity userEntity) {
    ConfirmationTokenEntity confirmationToken = this.confirmationTokenBusiness.save(userEntity);
    this.sendNotification(userEntity, confirmationToken.getConfirmToken());
  }

  /**
   * Envía una notificación al usuario.
   *
   * @param userEntity datos de usuario
   * @param randomToken token generado
   */
  private void sendNotification(final UserEntity userEntity, final String randomToken) {
    log.info("Sending email");
    this.notification.sendMail(
        EmailUtilities.getUserRegisterEmail(
            userEntity, SecurityUtilities.getConfirmPath(this.confirmationPath, randomToken)),
        EmailTemplate.USER);
  }

  /**
   * Convierte genera una entidad de tipo {@code RoleEntity}
   *
   * @param idProfile identificador del perfil
   * @return entidad para perfiles
   */
  private RoleEntity mapping(Long idProfile) {
    RoleEntity role = new RoleEntity();
    role.setId(idProfile);
    return role;
  }

  /**
   * Convierte una entidad {@code UserEntity} a uno de tipo {@code UserResponse}
   *
   * @param entity objeto de tipo {@link UserEntity}
   * @return objeto de salida de la api de usuarios
   */
  private UserResponse mapping(UserEntity entity) {
    return new ModelMapper().map(entity, UserResponse.class);
  }

  /**
   * Convierte un objeto {@code UserRequest} a uno de tipo {@code UserEntity}
   *
   * @param request objeto de tipo {@link UserRequest}
   * @return entidad de usuarios
   */
  private UserEntity mapping(UserRequest request) {
    return new ModelMapper().map(request, UserEntity.class);
  }
}
