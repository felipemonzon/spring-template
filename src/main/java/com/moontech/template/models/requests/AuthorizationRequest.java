package com.moontech.template.models.requests;

import java.io.Serializable;
import lombok.Data;

/**
 * Objeto para autenticación de usuarios.
 *
 * @author Felipe Monzón
 * @since Jan. 04, 2022
 */
@Data
public class AuthorizationRequest implements Serializable {
  /** Serial. */
  private static final long serialVersionUID = 1L;
  /** Nombre de usuario. */
  private String username;
  /** Contraseña. */
  private String password;
}
