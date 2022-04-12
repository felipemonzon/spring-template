package com.moontech.library.business;

import com.moontech.library.constants.ApiConstant;
import com.moontech.library.constants.ErrorConstant;
import com.moontech.library.entities.AuthorEntity;
import com.moontech.library.enums.Status;
import com.moontech.library.exceptions.custom.BusinessException;
import com.moontech.library.exceptions.custom.NotDataFoundException;
import com.moontech.library.models.requests.Author;
import com.moontech.library.models.responses.AuthorResponse;
import com.moontech.library.repositories.AuthorRepository;
import com.moontech.library.services.AuthorService;
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
import org.springframework.util.ObjectUtils;

/**
 * Implementación de las APIS de autores.
 *
 * @author Felipe Monzón
 * @since Jan 31, 2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorBusiness implements AuthorService {
  /** Repositorio para autores. */
  private final AuthorRepository authorRepository;

  /** {@inheritDoc}. */
  @Override
  @Transactional(readOnly = true)
  public AuthorResponse retrieve(Pageable pageable) {
    int currentPage = Utilities.getCurrentPage(pageable);
    log.info("Consulta autores por página {}", currentPage);
    Page<Author> authors =
        this.authorRepository
            .findAll(PageRequest.of(currentPage, pageable.getPageSize()))
            .map(this::mapping);
    return AuthorResponse.builder()
        .authors(authors.getContent())
        .totalPages(authors.getTotalPages())
        .currentPage(currentPage)
        .firstPage(authors.isFirst())
        .lastPage(authors.isLast())
        .build();
  }

  /** {@inheritDoc}. */
  @Override
  @Transactional
  public void saveAuthor(Author request) {
    log.debug("Datos del autor a guardar {}", request);
    AuthorEntity entity =
        this.authorRepository.findByFirstNameContainsAndLastNameContains(
            request.getFirstName(), request.getLastName());
    if (!ObjectUtils.isEmpty(entity)) {
      throw new BusinessException(
          ErrorConstant.DATA_EXIST_CODE,
          ErrorConstant.DATA_EXIST_MESSAGE
              + request.getFirstName()
              + ApiConstant.WHITE_SPACE
              + request.getLastName());
    }
    request.setStatus(Status.ACTIVE);
    this.authorRepository.save(this.mapping(request));
  }

  /** {@inheritDoc}. */
  @Override
  @Modifying
  @Transactional
  public void replaceAuthor(Author request, int id) {
    log.debug("Datos del autor a actualizar {}", request);
    this.validateAuthor(id);
    request.setId(id);
    this.authorRepository.save(this.mapping(request));
  }

  /**
   * Valida si el autor existe.
   *
   * @param id identificador del autor
   */
  private void validateAuthor(int id) {
    this.authorRepository
        .findById(id)
        .<NotDataFoundException>orElseThrow(
            () -> {
              throw new NotDataFoundException(ErrorConstant.RECORD_NOT_FOUND_MESSAGE);
            });
  }

  /**
   * Convierte un objeto {@code Author} a una entidad de tipo {@code AuthorEntity}
   *
   * @param request objeto de tipo {@link Author}
   * @return una entidad de autores
   */
  private AuthorEntity mapping(Author request) {
    return new ModelMapper().map(request, AuthorEntity.class);
  }

  /**
   * Convierte una entidad {@code AuthorEntity} a uno de tipo {@code Author}
   *
   * @param entity objeto de tipo {@link AuthorEntity}
   * @return objeto de salida de la api de autores
   */
  private Author mapping(AuthorEntity entity) {
    return new ModelMapper().map(entity, Author.class);
  }
}
