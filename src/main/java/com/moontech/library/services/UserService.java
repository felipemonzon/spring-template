package com.moontech.library.services;

import com.moontech.library.models.responses.UserResponse;
import org.springframework.data.domain.Pageable;

/**
 * Reglas de negocio de usuarios.
 *
 * @author Felipe Monzón
 * @since 31/12/21
 */
public interface UserService {
  /**
   * Consulta los usuarios por página.
   *
   * @param pageable página a consultar
   * @return lista de usuarios
   */
  UserResponse retrieve(Pageable pageable);
}
