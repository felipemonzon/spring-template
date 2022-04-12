package com.moontech.library.repositories;

import com.moontech.library.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad de categoría.
 *
 * @author Felipe Monzón
 * @since Jan 20. 2022
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {}
