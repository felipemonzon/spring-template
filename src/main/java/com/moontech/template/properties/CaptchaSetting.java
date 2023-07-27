package com.moontech.template.properties;

import com.moontech.template.constants.ApiConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Propiedades para el re-captcha.
 *
 * @author Felipe Monzón
 * @since 24 jun., 2023
 * @enterprise moontech
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = ApiConstant.CAPTCHA_PROPERTIES)
public class CaptchaSetting {
  /** Propiedad para el site del captcha. */
  private String site;
  /** Propiedad para el secret. */
  private String secret;
  /** Propiedad para reintentos. */
  private float threshold;
  /** URL para verificar captcha. */
  private String url;
}
