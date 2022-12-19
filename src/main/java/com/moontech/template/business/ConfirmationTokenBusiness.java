package com.moontech.template.business;

import com.moontech.template.constants.ErrorConstant;
import com.moontech.template.entities.ConfirmationTokenEntity;
import com.moontech.template.entities.UserEntity;
import com.moontech.template.enums.Status;
import com.moontech.template.exceptions.custom.BusinessException;
import com.moontech.template.repositories.ConfirmationTokenRepository;
import com.moontech.template.security.utilities.SecurityUtilities;
import com.moontech.template.services.ConfirmationTokenService;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * Implementación de las reglas de negocio para la confirmación del token.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 11 dic., 2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfirmationTokenBusiness implements ConfirmationTokenService {
  /** Repositorio para guardar el token. */
  private final ConfirmationTokenRepository confirmationTokenRepository;
  /** Días para expirar el token. */
  @Value("${api.token.expiration}")
  private long expirationToken;

  /** {@inheritDoc}. */
  @Override
  @Transactional
  public ConfirmationTokenEntity save(final UserEntity userEntity) {
    ConfirmationTokenEntity token = new ConfirmationTokenEntity();
    token.setUser(userEntity);
    token.setCreatedDate(new Date());
    token.setConfirmToken(SecurityUtilities.getToken());
    token.setExpirationDate(LocalDateTime.now().plusDays(this.expirationToken));
    token.setStatus(Status.ACTIVE);
    return this.confirmationTokenRepository.save(token);
  }

  /** {@inheritDoc}. */
  @Override
  @Transactional
  public ConfirmationTokenEntity getConfirmationByToken(final String token) {
    ConfirmationTokenEntity entity =
        this.confirmationTokenRepository
            .findByConfirmTokenAndStatus(token, Status.ACTIVE)
            .orElseGet(ConfirmationTokenEntity::new);
    if (!ObjectUtils.isEmpty(entity.getConfirmToken())) {
      if (entity.getExpirationDate().isAfter(LocalDateTime.now())) {
        log.info("Consulta token con éxito.");
        return entity;
      } else {
        log.debug("token expirado");
        this.save(entity, Status.EXPIRED);
        throw new BusinessException(ErrorConstant.ACCESS_DENIED_CODE, ErrorConstant.INVALID_TOKEN);
      }
    } else {
      log.debug("No existe el token.");
      throw new BusinessException(ErrorConstant.ACCESS_DENIED_CODE, ErrorConstant.INVALID_TOKEN);
    }
  }

  /** {@inheritDoc}. */
  @Override
  @Transactional
  public void save(ConfirmationTokenEntity entity, Status status) {
    entity.setStatus(status);
    this.confirmationTokenRepository.save(entity);
  }
}
