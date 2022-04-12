package com.moontech.library.repositories;

import com.moontech.library.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad de libros.
 *
 * @author Felipe Monzón
 * @since Jan 20. 2022
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {}
