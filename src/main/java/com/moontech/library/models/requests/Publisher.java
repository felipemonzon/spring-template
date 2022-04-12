package com.moontech.library.models.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * DTO para la api de editoriales.
 *
 * @author Felipe Monzón
 * @since Jan. 29, 2022
 */
@Data
public class Publisher {
  /** Identificador de editoriales. */
  @NotNull private int id;
  /** Nombre de la editorial. */
  @NotEmpty private String name;
}
