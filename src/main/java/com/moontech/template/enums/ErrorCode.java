package com.moontech.template.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.HashMap;
import java.util.Map;

/**
 * Códigos de error del captcha.
 *
 * @author Felipe Monzón
 * @since 25 jun., 2023
 * @enterprise moontech
 */
public enum ErrorCode {
  MissingSecret,
  InvalidSecret,
  MissingResponse,
  InvalidResponse,
  BadRequest,
  TimeoutOrDuplicate;

  private static final Map<String, ErrorCode> ERROR_MAP = new HashMap<>(6);

  static {
    ERROR_MAP.put("missing-input-secret", MissingSecret);
    ERROR_MAP.put("invalid-input-secret", InvalidSecret);
    ERROR_MAP.put("missing-input-response", MissingResponse);
    ERROR_MAP.put("invalid-input-response", InvalidResponse);
    ERROR_MAP.put("bad-request", BadRequest);
    ERROR_MAP.put("timeout-or-duplicate", TimeoutOrDuplicate);
  }

  @JsonCreator
  public static ErrorCode forValue(final String value) {
    return ERROR_MAP.get(value.toLowerCase());
  }
}
