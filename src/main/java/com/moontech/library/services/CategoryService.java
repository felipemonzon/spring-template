package com.moontech.library.services;

import com.moontech.library.models.requests.Category;
import com.moontech.library.models.responses.CategoryResponse;
import org.springframework.data.domain.Pageable;

/**
 * Servicio para las APIS de categorías
 *
 * @author Felipe Monzón
 * @since Dec 20, 2020
 */
public interface CategoryService {
  /**
   * Guarda una categoría.
   *
   * @param request objeto {@code Category} de la API de categorías
   */
  void save(Category request);

  /**
   * Actualiza una categoría.
   *
   * @param request objeto {@code Category} de la API de categorías
   * @param id identificador de la categoría
   */
  void replaceCategory(Category request, int id);

  /**
   * Consulta las categorías.
   *
   * @param pageable objeto de paginación {@code Pageable}
   * @return {@code Page} paginación de las categorías
   */
  CategoryResponse retrieve(Pageable pageable);
}
