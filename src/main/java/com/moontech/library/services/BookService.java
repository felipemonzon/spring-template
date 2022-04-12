package com.moontech.library.services;

import com.moontech.library.models.requests.Book;

import java.util.List;

/**
 * Servicio de libros.
 *
 * @author Felipe Monzón
 * @since Jan 30, 2022
 */
public interface BookService {
  /**
   * Guarda un libro.
   *
   * @param request {@link Book}
   */
  void saveBook(Book request);

  /**
   * Consulta los libros.
   *
   * @return una lista de tipo {@code BookDto}
   */
  List<Book> retrieve();
}
