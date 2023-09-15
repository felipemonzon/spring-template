package com.moontech.template.business;

import com.moontech.template.models.responses.ConfigurationCaptchaResponse;
import com.moontech.template.properties.CaptchaSetting;
import com.moontech.template.services.ConfigurationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Implementación de las reglas de negocio.
 *
 * @author Felipe Monzón
 * @since 25 jun., 2023
 * @enterprise moontech
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigurationBusiness implements ConfigurationService {
  /** Propiedades de captcha. */
  private final CaptchaSetting captchaSetting;

  /** {@inheritDoc}. */
  @Override
  public ConfigurationCaptchaResponse getCaptcha() {
    log.info("consulta la configuración para el reCaptcha");
    return this.mapping(this.captchaSetting);
  }

  /**
   * Convierte una entidad {@code CaptchaSetting} a uno de tipo {@code ConfigurationCaptchaResponse}
   *
   * @param captchaSetting objeto de tipo {@link CaptchaSetting}
   * @return objeto de salida para las configuraciones del captcha
   */
  private ConfigurationCaptchaResponse mapping(CaptchaSetting captchaSetting) {
    return new ModelMapper().map(captchaSetting, ConfigurationCaptchaResponse.class);
  }
}
