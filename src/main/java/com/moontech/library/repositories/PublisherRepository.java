package com.moontech.library.repositories;

import com.moontech.library.entities.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para editoriales.
 *
 * @author Felipe Monzón
 * @since Jan 20. 2022
 */
@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Integer> {}
