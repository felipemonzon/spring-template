package com.moontech.template.exceptions.custom;

/**
 * @author Felipe Monzón
 * @since 25 jun., 2023
 * @enterprise
 */
public class ReCaptchaUnavailableException extends RuntimeException {
  public ReCaptchaUnavailableException() {
    super();
  }

  public ReCaptchaUnavailableException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ReCaptchaUnavailableException(final String message) {
    super(message);
  }
}
