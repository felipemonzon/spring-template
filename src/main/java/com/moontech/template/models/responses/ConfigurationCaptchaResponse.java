package com.moontech.template.models.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Respuesta para la configuración del captcha.
 *
 * @author Felipe Monzón
 * @since 26 jun., 2023
 * @enterprise moontech
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ConfigurationCaptchaResponse {
  /** Propiedad para el site del captcha. */
  private String site;
  /** Propiedad para el secret. */
  private String secret;
}
