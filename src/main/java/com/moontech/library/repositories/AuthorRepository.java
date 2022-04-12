package com.moontech.library.repositories;

import com.moontech.library.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de autores.:
 *
 * @author Felipe Monzón
 * @since Jan 20. 2022
 */
@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
  AuthorEntity findByFirstNameContainsAndLastNameContains(String firstname, String lastname);
}
