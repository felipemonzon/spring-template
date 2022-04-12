package com.moontech.library.models.responses;

import com.moontech.library.models.requests.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Respuesta para la api de usuarios.
 *
 * @author Felipe Monzón
 * @since 31/12/21
 */
@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends Pagination {
  /** Lista de usuarios. */
  List<User> users;
}
