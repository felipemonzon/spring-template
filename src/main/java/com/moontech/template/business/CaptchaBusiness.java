package com.moontech.template.business;

import com.moontech.template.exceptions.custom.ReCaptchaInvalidException;
import com.moontech.template.exceptions.custom.ReCaptchaUnavailableException;
import com.moontech.template.models.responses.CaptchaResponse;
import com.moontech.template.properties.CaptchaSetting;
import com.moontech.template.services.CaptchaService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

/**
 * Reglas de negocio para el captcha.
 *
 * @author Felipe Monzón
 * @since 24 jun., 2023
 * @enterprise moontech
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaBusiness extends AbstractCaptchaBusiness implements CaptchaService {
  /** Propiedades del captcha. */
  private final CaptchaSetting captchaSettings;
  /** Consumidor de servicios. */
  private final RestOperations restTemplate;
  /** Acción de registro. */
  private static final String REGISTER_ACTION = "register";

  /** {@inheritDoc}. */
  @Override
  public void validate(String captcha) {
    this.securityCheck(captcha);

    final URI verifyUri =
        URI.create(
            String.format(
                this.captchaSettings.getUrl(),
                this.captchaSettings.getSecret(),
                captcha,
                this.getClientIP()));
    try {
      final CaptchaResponse response =
          this.restTemplate.getForObject(verifyUri, CaptchaResponse.class);
      log.debug("captcha response: {} ", response);

      if (response == null
          || !response.isSuccess()
          || !response.getAction().equals(REGISTER_ACTION)
          || response.getScore() < captchaSettings.getThreshold()) {
        if (response.hasClientError()) {
          this.reCaptchaAttemptService.reCaptchaFailed(getClientIP());
        }
        throw new ReCaptchaInvalidException("reCaptcha was not successfully validated");
      }
    } catch (RestClientException rce) {
      throw new ReCaptchaUnavailableException(
          "Registration unavailable at this time.  Please try again later.", rce);
    }
    this.reCaptchaAttemptService.reCaptchaSucceeded(this.getClientIP());
  }
}
