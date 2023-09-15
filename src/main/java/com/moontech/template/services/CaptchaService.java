package com.moontech.template.services;

/**
 * Regla de negocio del captcha.
 *
 * @author Felipe Monzón
 * @since 25 jun., 2023
 * @enterprise moontech
 */
public interface CaptchaService {
  /**
   * Valida el captcha recibido.
   *
   * @param captcha datos del captcha
   */
  void validate(String captcha);
}
