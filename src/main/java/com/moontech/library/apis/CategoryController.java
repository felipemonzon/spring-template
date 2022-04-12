package com.moontech.library.apis;

import com.moontech.library.constants.RoutesConstant;
import com.moontech.library.models.requests.Category;
import com.moontech.library.models.responses.CategoryResponse;
import com.moontech.library.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Apis para el modulo de categorías.
 *
 * @author Felipe Monzón
 * @version 1.0.0
 * @since Dec 20,xs 2020
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = RoutesConstant.CATEGORIES_BASE_PATH)
public class CategoryController {
  /** Servicio de categorías. */
  private final CategoryService categoryService;

  /**
   * API para guardar una categoría.
   *
   * @param request objeto {@code Category} de la API de categorías
   * @return HttpStatus CREATED cuando la categoría se guardó correctamente
   */
  @PostMapping(
      path = RoutesConstant.DATA_CREATE_PATH,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> save(@RequestBody @Valid Category request) {
    this.categoryService.save(request);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * API para listar todas las categorías.
   *
   * @param pageable objeto de paginación {@code Pageable}
   * @return {@code Page} paginación de las categorías
   */
  @GetMapping(path = RoutesConstant.DATA_RETRIEVE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CategoryResponse> retrieve(@PageableDefault() Pageable pageable) {
    return ResponseEntity.ok(this.categoryService.retrieve(pageable));
  }

  /**
   * API para actualizar una categoría.
   *
   * @param request objeto {@code Category} de la API de categorías
   * @param id identificador de la categoría
   */
  @PutMapping(
      path = RoutesConstant.DATA_MODIFIED_PATH,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> replaceCategory(
      @RequestBody @Valid Category request, @PathVariable int id) {
    this.categoryService.replaceCategory(request, id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
