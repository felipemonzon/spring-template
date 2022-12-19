package com.moontech.template.business;

import com.moontech.template.constants.ErrorConstant;
import com.moontech.template.entities.UserEntity;
import com.moontech.template.enums.EmailTemplate;
import com.moontech.template.exceptions.custom.BusinessException;
import com.moontech.template.models.requests.ResetPasswordRequest;
import com.moontech.template.notifications.Notification;
import com.moontech.template.repositories.UserRepository;
import com.moontech.template.services.PasswordService;
import com.moontech.template.utilities.EmailUtilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * Implementación de las reglas de negocio para la contraseña.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 15 dic., 2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordBusiness implements PasswordService {
  /** Repositorio de usuarios. */
  private final UserRepository userRepository;
  /** Servicio para mensajería. */
  private final Notification notification;
  /** Puerto de la aplicación. */
  @Value("${api.uri.data.passwordForgot}")
  private String forgotPasswordPath;

  /** {@inheritDoc}. */
  public void resetPassword(ResetPasswordRequest request) {
    log.info("Solicitud para el cambio de la contraseña del usuario {}", request.getUsername());
    UserEntity user =
        this.userRepository.findByUsername(request.getUsername()).orElseGet(UserEntity::new);
    if (ObjectUtils.isEmpty(user.getUsername())) {
      throw new BusinessException(
          ErrorConstant.DATA_EXIST_CODE, ErrorConstant.GENERIC_ERROR_MESSAGE);
    } else {
      this.notification.sendMail(
          EmailUtilities.getResetPassword(user, this.forgotPasswordPath),
          EmailTemplate.FORGOTPASSWORD);
    }
  }
}
