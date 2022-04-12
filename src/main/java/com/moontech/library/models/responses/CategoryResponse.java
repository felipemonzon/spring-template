package com.moontech.library.models.responses;

import com.moontech.library.models.requests.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Respuesta de la API de CATEGORY-RETRIEVE
 *
 * @author Felipe Monzón
 * @since Jan 30, 2022
 */
@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class CategoryResponse extends Pagination {
  /** Lista de categorías. */
  private List<Category> categories;
}
