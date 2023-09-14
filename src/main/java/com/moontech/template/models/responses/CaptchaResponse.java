package com.moontech.template.models.responses;

import com.fasterxml.jackson.annotation.*;
import com.moontech.template.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Respuesta para el captcha.
 *
 * @author Felipe Monzón
 * @since 25 jun., 2023
 * @enterprise moontech
 */
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"success", "score", "action", "challenge_ts", "hostname", "error-codes"})
public class CaptchaResponse {
  /** Propiedad para éxito. */
  @JsonProperty("success")
  private boolean success;
  /** Propiedad para el timestamp del reto. */
  @JsonProperty("challenge_ts")
  private String challengeTimeStamp;
  /** Dirección del host. */
  @JsonProperty("hostname")
  private String hostname;
  /** Propiedad para la puntuación. */
  @JsonProperty("score")
  private float score;
  /** Propiedad para ala acción. */
  @JsonProperty("action")
  private String action;
  /** Códigos de error. */
  @JsonProperty("error-codes")
  private ErrorCode[] errorCodes;

  @JsonIgnore
  public boolean hasClientError() {
    final ErrorCode[] errors = getErrorCodes();
    if (errors == null) {
      return false;
    }
    for (final ErrorCode error : errors) {
      switch (error) {
        case INVALID_RESPONSE:
        case MISSING_RESPONSE:
        case BAD_REQUEST:
          return true;
        default:
          break;
      }
    }
    return false;
  }
}
