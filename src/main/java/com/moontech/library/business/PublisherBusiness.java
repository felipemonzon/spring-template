package com.moontech.library.business;

import com.moontech.library.constants.ErrorConstant;
import com.moontech.library.entities.PublisherEntity;
import com.moontech.library.exceptions.custom.NotDataFoundException;
import com.moontech.library.models.requests.Publisher;
import com.moontech.library.models.responses.PublisherResponse;
import com.moontech.library.repositories.PublisherRepository;
import com.moontech.library.services.PublisherService;
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
 * Implementación de las reglas de negocio.
 *
 * @author Felipe Monzón
 * @since Jan 31, 2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PublisherBusiness implements PublisherService {
  /** Repositorio para editoriales. */
  private final PublisherRepository publisherRepository;

  /** {@inheritDoc}. */
  @Override
  @Transactional
  public void save(Publisher request) {
    log.debug("Guarda los datos de una editorial {}", request.toString());
    this.publisherRepository.save(this.mapping(request));
  }

  /** {@inheritDoc}. */
  @Override
  @Modifying
  @Transactional
  public void replacePublisher(Publisher request, int id) {
    this.validatePublisher(id);
    request.setId(id);
    log.debug("Actualiza los datos de una editorial {}", request);
    this.publisherRepository.save(this.mapping(request));
  }

  /** {@inheritDoc}. */
  @Override
  @Transactional(readOnly = true)
  public PublisherResponse retrieve(Pageable pageable) {
    int page = Utilities.getCurrentPage(pageable);
    log.info("Consulta editoriales por página {}", page);
    Page<Publisher> publishers =
        this.publisherRepository
            .findAll(PageRequest.of(page, pageable.getPageSize()))
            .map(this::mapping);
    return PublisherResponse.builder()
        .publishers(publishers.getContent())
        .currentPage(page)
        .firstPage(publishers.isFirst())
        .lastPage(publishers.isLast())
        .totalPages(publishers.getTotalPages())
        .build();
  }

  /**
   * Válida que exista una editorial.
   *
   * @param id identificador de la editorial
   */
  private void validatePublisher(int id) {
    this.publisherRepository
        .findById(id)
        .<NotDataFoundException>orElseThrow(
            () -> {
              throw new NotDataFoundException(ErrorConstant.RECORD_NOT_FOUND_MESSAGE);
            });
  }

  /**
   * Convierte un objeto {@code Publisher} a una entidad de tipo {@code PublisherEntity}
   *
   * @param request objeto de tipo {@link Publisher}
   * @return una entidad de editorial
   */
  private PublisherEntity mapping(Publisher request) {
    return new ModelMapper().map(request, PublisherEntity.class);
  }

  /**
   * Convierte una entidad {@code PublisherEntity} a uno de tipo {@code Publisher}
   *
   * @param entity objeto de tipo {@link Publisher}
   * @return objeto de salida de la api de editorial
   */
  private Publisher mapping(PublisherEntity entity) {
    return new ModelMapper().map(entity, Publisher.class);
  }
}
