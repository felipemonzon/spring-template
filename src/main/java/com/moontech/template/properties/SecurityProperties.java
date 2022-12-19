package com.moontech.template.properties;

import lombok.Builder;
import lombok.Data;

/**
 * Propiedades externalizadas de seguridad.
 *
 * @author Felipe Monzón
 * @since 11 ene., 2022
 */
@Data
@Builder
public class SecurityProperties {
  /** Llave del JWT. */
  private final String jwtKey;
  /** Tiempo de vida del token. */
  private final int jwtLifeTime;
  /** Ruta para la autenticación de usuarios. */
  private final String userAuthenticationPath;
  /** Ruta para la confirmación de usuarios. */
  private String userConfirmTokenPath;
  /** Ruta para olvidar contraseña. */
  private String forgotPasswordPath;
}
