package com.moontech.library.business;

import com.moontech.library.constants.ErrorConstant;
import com.moontech.library.entities.CategoryEntity;
import com.moontech.library.exceptions.custom.NotDataFoundException;
import com.moontech.library.models.requests.Category;
import com.moontech.library.models.responses.CategoryResponse;
import com.moontech.library.repositories.CategoryRepository;
import com.moontech.library.services.CategoryService;
import com.moontech.library.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de las APIS de categoría.
 *
 * @author Felipe Monzón
 * @since Jan 31, 2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryBusiness implements CategoryService {
  /** Repositorio de categorías. */
  private final CategoryRepository categoryRepository;

  /** {@inheritDoc}. */
  @Override
  @Transactional
  public void save(Category request) {
    log.debug("Datos de la categoría a guardar {}", request);
    this.categoryRepository.save(this.mapping(request));
  }

  /** {@inheritDoc}. */
  @Override
  @Transactional(readOnly = true)
  public CategoryResponse retrieve(Pageable pageable) {
    int currentPage = Utilities.getCurrentPage(pageable);
    log.info("Consulta categoría por página {}", currentPage);
    Page<Category> categories =
        this.categoryRepository
            .findAll(PageRequest.of(currentPage, pageable.getPageSize()))
            .map(this::mapping);
    return CategoryResponse.builder()
        .categories(categories.getContent())
        .totalPages(categories.getTotalPages())
        .currentPage(currentPage)
        .firstPage(categories.isFirst())
        .lastPage(categories.isLast())
        .build();
  }

  /** {@inheritDoc}. */
  @Override
  @Modifying
  @Transactional
  public void replaceCategory(Category request, int id) {
    log.debug("Datos de la categoría a actualizar {}", request);
    this.validateCategory(id);
    request.setId(id);
    this.categoryRepository.save(this.mapping(request));
  }

  /**
   * Valida si una categoría existe.
   *
   * @param id identificador de la categoría
   */
  private void validateCategory(int id) {
    this.categoryRepository
        .findById(id)
        .<NotDataFoundException>orElseThrow(
            () -> {
              throw new NotDataFoundException(ErrorConstant.RECORD_NOT_FOUND_MESSAGE);
            });
  }

  /**
   * Convierte un objeto {@code Category} a una entidad de tipo {@code CategoryEntity}
   *
   * @param request objeto de tipo {@link Category}
   * @return una entidad de categoría
   */
  private CategoryEntity mapping(Category request) {
    return new ModelMapper().map(request, CategoryEntity.class);
  }

  /**
   * Convierte una entidad {@code CategoryEntity} a uno de tipo {@code Publisher}
   *
   * @param entity objeto de tipo {@link Category}
   * @return objeto de salida de la api de categorías
   */
  private Category mapping(CategoryEntity entity) {
    return new ModelMapper().map(entity, Category.class);
  }
}
