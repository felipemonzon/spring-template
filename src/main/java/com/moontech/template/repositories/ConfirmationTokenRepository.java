package com.moontech.template.repositories;

import com.moontech.template.entities.ConfirmationTokenEntity;
import com.moontech.template.enums.Status;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la tabla "confirmation_token"
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 11 dic., 2022
 */
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity, Long> {
  /**
   * Consulta los datos del token por uuid.
   *
   * @param confirmToken token generado previamente
   * @param status estatus del token
   * @return datos del token
   */
  Optional<ConfirmationTokenEntity> findByConfirmTokenAndStatus(
      final String confirmToken, Status status);
}
