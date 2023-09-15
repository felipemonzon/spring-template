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
  MISSING_SECRET,
  INVALID_SECRET,
  MISSING_RESPONSE,
  INVALID_RESPONSE,
  BAD_REQUEST,
  TIMEOUT_OR_DUPLICATE;

  private static final Map<String, ErrorCode> ERROR_MAP = new HashMap<>(6);

  static {
    ERROR_MAP.put("missing-input-secret", MISSING_SECRET);
    ERROR_MAP.put("invalid-input-secret", INVALID_SECRET);
    ERROR_MAP.put("missing-input-response", MISSING_RESPONSE);
    ERROR_MAP.put("invalid-input-response", INVALID_RESPONSE);
    ERROR_MAP.put("bad-request", BAD_REQUEST);
    ERROR_MAP.put("timeout-or-duplicate", TIMEOUT_OR_DUPLICATE);
  }

  @JsonCreator
  public static ErrorCode forValue(final String value) {
    return ERROR_MAP.get(value.toLowerCase());
  }
}
