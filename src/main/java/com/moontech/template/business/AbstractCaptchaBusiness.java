package com.moontech.template.business;

import com.moontech.template.exceptions.custom.ReCaptchaInvalidException;
import com.moontech.template.security.captcha.CaptchaAttemptBusiness;
import com.moontech.template.security.utilities.SecurityUtilities;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * Clase auxiliar para el captcha.
 *
 * @author Felipe Monzón
 * @since 24 jun., 2023
 * @enterprise moontech
 */
@Slf4j
public abstract class AbstractCaptchaBusiness {
  /** Datos de la petición. */
  @Autowired private HttpServletRequest request;
  /** Servicio para reintentos de captcha. */
  @Autowired protected CaptchaAttemptBusiness reCaptchaAttemptService;

  /**
   * Validación de seguridad del captcha.
   *
   * @param captcha datos del captcha
   */
  protected void securityCheck(final String captcha) {
    log.debug("Attempting to validate response {}", captcha);

    if (this.reCaptchaAttemptService.isBlocked(this.getClientIP())) {
      throw new ReCaptchaInvalidException("Client exceeded maximum number of failed attempts");
    }

    if (!responseSanityCheck(captcha)) {
      throw new ReCaptchaInvalidException("Response contains invalid characters");
    }
  }

  /**
   * Valida si el captcha no esta vacío o cumple con el patron indicado.
   *
   * @param captcha datos del captcha
   * @return verdadero si no cumple con la condición falso caso contrario
   */
  protected boolean responseSanityCheck(String captcha) {
    return StringUtils.hasLength(captcha)
        && SecurityUtilities.RESPONSE_PATTERN.matcher(captcha).matches();
  }

  /**
   * Obtiene la IP del cliente.
   *
   * @return ip del cliente
   */
  protected String getClientIP() {
    final String xfHeader = request.getHeader("X-Forwarded-For");
    String response;
    if (xfHeader == null || xfHeader.isEmpty() || !xfHeader.contains(request.getRemoteAddr())) {
      response = request.getRemoteAddr();
    } else {
      response = xfHeader.split(",")[0];
    }
    return response;
  }
}
