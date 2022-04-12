package com.moontech.library.services;

import com.moontech.library.models.requests.Author;
import com.moontech.library.models.responses.AuthorResponse;
import org.springframework.data.domain.Pageable;

/**
 * Servicio de autores.
 *
 * @author Felipe Monzón
 * @since Jan 31, 2022
 */
public interface AuthorService {
  /**
   * Consulta un listado de autores.
   *
   * @param pageable objeto de paginación {@code Pageable}
   * @return una lista de autores.
   */
  AuthorResponse retrieve(Pageable pageable);

  /**
   * Actualiza un autor.
   *
   * @param request DTO de editoriales {@code Author}
   * @param id identificador de un autor.
   */
  void replaceAuthor(Author request, int id);

  /**
   * Guarda un autor.
   *
   * @param request DTO de editoriales {@code Author}
   */
  void saveAuthor(Author request);
}
