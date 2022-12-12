package com.moontech.template.services;

import com.moontech.template.entities.ConfirmationTokenEntity;
import com.moontech.template.entities.UserEntity;

/**
 * Reglas de negocio para la confirmación del token.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 11 dic., 2022
 */
public interface ConfirmationTokenService {
  /**
   * Genera y guarda el token de confirmación
   *
   * @param userEntity entidad de usuario
   */
  ConfirmationTokenEntity save(final UserEntity userEntity);

  /**
   * Consulta el token de confirmación y válida si está activo aun.
   *
   * @param token token de confirmación
   * @return datos del token
   */
  ConfirmationTokenEntity getConfirmationByToken(final String token);

  /**
   * Guarda los datos del token con status inactivo
   *
   * @param entity datos dle token
   */
  void save(ConfirmationTokenEntity entity);
}
