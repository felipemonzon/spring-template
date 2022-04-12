package com.moontech.library.repositories;

import com.moontech.library.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio de usuarios.
 *
 * @author Felipe Monzón
 * @since 31/12/21
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  /**
   * Consulta un usuario por su nombre de usuario.
   *
   * @param username nombre de usuario
   * @return usuario encontrado o vacío sino se encontró nada
   */
  Optional<UserEntity> findByUsername(String username);
}
