package com.moontech.library.business;

import com.moontech.library.entities.BookEntity;
import com.moontech.library.models.requests.Book;
import com.moontech.library.repositories.BookRepository;
import com.moontech.library.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de las reglas de negocio.
 *
 * @author Felipe Monzón
 * @since Jan 31, 2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookBusiness implements BookService {
  /** Repositorio de libros. */
  private final BookRepository bookRepository;

  /**
   * Guarda un libro.
   *
   * @param request {@link Book}
   */
  @Override
  public void saveBook(Book request) {
    log.debug("Datos del libro a guardar {}", request);
    this.bookRepository.save(this.bookMapping(request));
  }

  /** {@inheritDoc}. */
  @Override
  public List<Book> retrieve() {
    log.info("Consulta todos los libros");
    return this.bookRepository.findAll().stream()
        .map(this::bookMapping)
        .collect(Collectors.toList());
  }

  /**
   * Convierte un objeto {@code BookDto} a una entidad de tipo {@code BookEntity}
   *
   * @param request objeto de tipo {@link Book}
   * @return una entidad de libros
   */
  private BookEntity bookMapping(Book request) {
    return new ModelMapper().map(request, BookEntity.class);
  }

  /**
   * Convierte una entidad {@code BookEntity} a uno de tipo {@code BookDto}
   *
   * @param entity objeto de tipo {@link BookEntity}
   * @return objeto de salida de la api de libros
   */
  private Book bookMapping(BookEntity entity) {
    return new ModelMapper().map(entity, Book.class);
  }
}
