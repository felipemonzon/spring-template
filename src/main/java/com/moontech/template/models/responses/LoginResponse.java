package com.moontech.template.models.responses;

import com.moontech.template.enums.Genre;
import com.moontech.template.enums.Status;
import java.io.Serializable;
import lombok.Data;

/**
 * Clase para retornar un login exitoso.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 17 jul., 2022
 */
@Data
public class LoginResponse implements Serializable {
  /** Identificador del usuario. */
  private Long id;
  /** Nombre de usuario. */
  private String username;
  /** Correo del usuario. */
  private String email;
  /** Propiedad primer nombre. */
  private String firstName;
  /** Propiedad segundo nombre. */
  private String lastName;
  /** Propiedad para el celular. */
  private String cel;
  /** Propiedad para el género. */
  private Genre genre;
  /** Nombre completo. */
  private String displayName;
  /** Estatus del empleado. */
  private Status status;
}
