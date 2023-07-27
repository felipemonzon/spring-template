package com.moontech.template.models.requests;

import com.moontech.template.enums.Genre;
import com.moontech.template.enums.Status;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Objeto de entrada para la api de usuarios.
 *
 * @author Felipe Monzón
 * @since 31/12/21
 */
@Data
public class UserRequest {
  /** Identificador del usuario. */
  private long id;
  /** nombre del usuario. */
  @NotBlank private String username;
  /** Propiedad primer nombre. */
  @NotBlank private String firstName;
  /** Propiedad segundo nombre. */
  @NotBlank private String lastName;
  /** Propiedad para el correo. */
  @NotBlank private String email;
  /** Contraseña del usuario. */
  @NotBlank private String password;
  /** Propiedad para el celular. */
  @NotBlank
  @Size(min = 10, max = 10)
  private String cel;
  /** Propiedad para el género. */
  @NotNull private Genre genre;
  /** Estatus del usuario. */
  private Status status;
  /** Roles del usuario. */
  private Set<Long> profiles;
  /** Datos del captcha. */
  private String reCaptcha;
}
