package com.moontech.library.models.responses;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Objeto de paginación.
 *
 * @author Felipe Monzón
 * @since Jan 30, 2022
 */
@Getter
@SuperBuilder(toBuilder = true)
public class Pagination {
  /** Página actual. */
  private int currentPage;
  /** Páginas totales. */
  private int totalPages;
  /** Página final. */
  private boolean lastPage;
  /** Página inicial. */
  private boolean firstPage;
}
