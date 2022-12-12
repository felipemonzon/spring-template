package com.moontech.template.services;

import com.moontech.template.models.requests.UserRequest;
import com.moontech.template.models.responses.UserResponse;
import org.springframework.data.domain.Page;
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
  Page<UserResponse> retrieve(Pageable pageable);

  /**
   * Guarda los datos de un usuario y manda correo de verificación.
   *
   * @param request datos del usuario
   */
  void save(UserRequest request);

  /**
   * Confirma el token del usuario y actualiza su estatus a activo.
   *
   * @param token token del usuario
   */
  void confirm(String token);
}
