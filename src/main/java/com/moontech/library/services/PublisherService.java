package com.moontech.library.services;

import com.moontech.library.models.requests.Publisher;
import com.moontech.library.models.responses.PublisherResponse;
import org.springframework.data.domain.Pageable;

/**
 * Servicio de editorial.
 *
 * @author Felipe Monzón
 * @since Jan 30, 2022
 */
public interface PublisherService {
  /**
   * Guarda una editorial.
   *
   * @param request DTO de editoriales {@code Publisher}
   */
  void save(Publisher request);

  /**
   * Actualiza una editorial.
   *
   * @param request DTO de editoriales {@code Publisher}
   * @param id identificador de la editorial.
   */
  void replacePublisher(Publisher request, int id);

  /**
   * Consulta todas las editoriales.
   *
   * @param pageable objeto de paginación {@code Pageable}
   * @return una lista de editoriales.
   */
  PublisherResponse retrieve(Pageable pageable);
}
