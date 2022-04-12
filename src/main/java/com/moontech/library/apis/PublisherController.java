package com.moontech.library.apis;

import com.moontech.library.constants.RoutesConstant;
import com.moontech.library.models.requests.Publisher;
import com.moontech.library.models.responses.PublisherResponse;
import com.moontech.library.services.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * APIS de Editoriales.
 *
 * @author Felipe Monzón
 * @since Dec 20. 2021
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = RoutesConstant.PUBLISHERS_BASE_PATH)
public class PublisherController {
  /** Servicio de editoriales. */
  private final PublisherService publisherService;

  /**
   * API que consulta todas las editoriales.
   *
   * @return lista de todas las editoriales
   */
  @GetMapping(path = RoutesConstant.DATA_RETRIEVE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PublisherResponse> retrieve(@PageableDefault() Pageable pageable) {
    return ResponseEntity.ok(this.publisherService.retrieve(pageable));
  }

  /**
   * API para guardar una editorial.
   *
   * @param request entrada de la API {@code Publisher}
   * @return HttpStatus CREATED si se guardo con éxito
   */
  @PostMapping(
      path = RoutesConstant.DATA_CREATE_PATH,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> save(@RequestBody @Valid Publisher request) {
    this.publisherService.save(request);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * API para actualizar una editorial.
   *
   * @param request entrada de la API {@code Publisher}
   * @param id identificador de la editorial
   * @return HttpStatus NO_CONTENT si se actualizo con éxito
   */
  @PutMapping(
      path = RoutesConstant.DATA_MODIFIED_PATH,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> update(@RequestBody @Valid Publisher request, @PathVariable int id) {
    this.publisherService.replacePublisher(request, id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
