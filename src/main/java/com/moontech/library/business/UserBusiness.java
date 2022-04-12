package com.moontech.library.business;

import com.moontech.library.constants.LogConstant;
import com.moontech.library.entities.UserEntity;
import com.moontech.library.models.requests.User;
import com.moontech.library.models.responses.UserResponse;
import com.moontech.library.repositories.UserRepository;
import com.moontech.library.services.UserService;
import com.moontech.library.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de las reglas de negocio.
 *
 * @author Felipe Monzón
 * @since 31/12/21
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserBusiness implements UserService {
  /** Repositorio de usuarios. */
  private final UserRepository userRepository;

  /** {@inheritDoc}. */
  @Override
  @Transactional(readOnly = true)
  public UserResponse retrieve(Pageable pageable) {
    int currentPage = Utilities.getCurrentPage(pageable);
    log.info(LogConstant.USERS_RETRIEVE, currentPage);
    Page<User> authors =
        this.userRepository
            .findAll(PageRequest.of(currentPage, pageable.getPageSize()))
            .map(this::mapping);
    return UserResponse.builder()
        .users(authors.getContent())
        .totalPages(authors.getTotalPages())
        .currentPage(currentPage)
        .firstPage(authors.isFirst())
        .lastPage(authors.isLast())
        .build();
  }

  /**
   * Convierte una entidad {@code UserEntity} a uno de tipo {@code User}
   *
   * @param entity objeto de tipo {@link UserEntity}
   * @return objeto de salida de la api de usuarios
   */
  private User mapping(UserEntity entity) {
    return new ModelMapper().map(entity, User.class);
  }
}
