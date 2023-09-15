package com.moontech.template.apis;

import com.moontech.template.models.responses.ConfigurationCaptchaResponse;
import com.moontech.template.services.ConfigurationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Configuraciones de la aplicación.
 *
 * @author Felipe Monzón
 * @since 25 jun., 2023
 * @enterprise moontech
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/configuration")
public class ConfigurationController {
  /** Propiedades del captcha. */
  private final ConfigurationService configurationService;

  @GetMapping(path = "/reCaptcha", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ConfigurationCaptchaResponse> getCaptcha() {
    return ResponseEntity.ok(this.configurationService.getCaptcha());
  }
}
