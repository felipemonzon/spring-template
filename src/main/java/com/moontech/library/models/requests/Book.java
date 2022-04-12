package com.moontech.library.models.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * Solicitud para el api de libros.
 *
 * @author Felipe Monzón
 * @since Jan. 29, 2022
 */
@Data
public class Book {
  /** Identificador del libro. */
  private long id;
  /** Nombre del libro. */
  @NotEmpty private String title;
  /** ISBN. */
  @NotEmpty private String isbn;
  /** Estatus del libro. */
  private String status;
  /** Autores del libro. */
  private Set<Author> authors;
  /** Categorías del libro. */
  private Set<Category> categories;
  /** Editorial del libro. */
  private Publisher publisher;
}
