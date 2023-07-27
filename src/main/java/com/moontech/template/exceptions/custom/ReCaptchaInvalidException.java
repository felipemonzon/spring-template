package com.moontech.template.exceptions.custom;
/**
 * Excepción para validar el captcha.
 *
 * @author Felipe Monzón
 * @since 24 jun., 2023
 * @enterprise moontech
 */
public class ReCaptchaInvalidException extends RuntimeException {
  public ReCaptchaInvalidException() {
    super();
  }

  public ReCaptchaInvalidException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ReCaptchaInvalidException(final String message) {
    super(message);
  }
}
