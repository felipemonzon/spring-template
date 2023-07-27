package com.moontech.template.services;

import com.moontech.template.models.responses.ConfigurationCaptchaResponse;

/**
 * Configuraciones del sistema.
 *
 * @author Felipe Monzón
 * @since 26 jun., 2023
 * @enterprise moontech
 */
public interface ConfigurationService {
  /**
   * Consulta la configuración para el captcha.
   *
   * @return configuración necesaria para el captcha
   */
  ConfigurationCaptchaResponse getCaptcha();
}
