package com.moontech.library.models.responses;

import com.moontech.library.models.requests.Author;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Respuesta de la API AUTHORS-RETRIEVE.
 *
 * @author Felipe Monzón
 * @since Jan 30, 2022
 */
@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class AuthorResponse extends Pagination {
  /** Lista de autores. */
  List<Author> authors;
}
