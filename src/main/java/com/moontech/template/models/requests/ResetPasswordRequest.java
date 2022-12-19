package com.moontech.template.models.requests;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * Clase que contienen las propiedades para solicitar el restablecimiento de una contraseña
 * olvidada.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 15 dic., 2022
 */
@Data
public class ResetPasswordRequest {
  /** Propiedad para usuario. */
  @NotEmpty private String username;
}
