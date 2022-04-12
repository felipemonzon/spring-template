package com.moontech.library.models.requests;

import com.moontech.library.enums.Status;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Petición para las APIS de autores.
 *
 * @author Felipe Monzón
 * @since Jan. 29, 2022
 */
@Data
public class Author {
  /** Identificador de la tabla. */
  private int id;
  /** Propiedad para el primer nombre del autor. */
  @NotEmpty private String firstName;
  /** Segundo nombre. */
  @NotEmpty private String lastName;
  /** Status del autor. */
  private Status status;
}
