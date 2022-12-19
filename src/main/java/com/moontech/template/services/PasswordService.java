package com.moontech.template.services;

import com.moontech.template.models.requests.ResetPasswordRequest;

/**
 * reglas de negocio para la contraseña.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 15 dic., 2022
 */
public interface PasswordService {
  /**
   * Solicita un cambio para la contraseña del usuario.
   *
   * @param request {@code ForgotPasswordRequest|}
   */
  void resetPassword(ResetPasswordRequest request);
}
