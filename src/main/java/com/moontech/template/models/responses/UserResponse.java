package com.moontech.template.models.responses;

import com.moontech.template.enums.Genre;
import lombok.*;

/**
 * Respuesta para la api de usuarios.
 *
 * @author Felipe Monzón
 * @since 31/12/21
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
  /** Identificador del usuario. */
  private long id;
  /** nombre del usuario. */
  private String username;
  /** Propiedad primer nombre. */
  private String firstName;
  /** Propiedad segundo nombre. */
  private String lastName;
  /** Propiedad para el correo. */
  private String email;
  /** Propiedad para el celular. */
  private String cel;
  /** Propiedad para el género. */
  private Genre genre;
}
