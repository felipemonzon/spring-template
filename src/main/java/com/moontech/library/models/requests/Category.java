package com.moontech.library.models.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Petición de la API categorías.
 *
 * @author Felipe Monzón
 * @since Jan. 29, 2022
 */
@Data
public class Category {
  /** Identificador de la categoría. */
  private Integer id;
  /** Nombre de la categoría. */
  @NotEmpty private String name;
  /** Estatus de la categorías. */
  @NotEmpty private String status;
}
