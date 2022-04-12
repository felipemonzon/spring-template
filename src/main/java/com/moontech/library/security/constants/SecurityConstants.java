package com.moontech.library.security.constants;

/**
 * Constantes para la configuración de seguridad del servicio.
 *
 * @author Felipe Monzón
 * @since Jan 05. 2022
 */
public abstract class SecurityConstants {
  /** LLava e de autorización. */
  public static final String AUTHORITIES_KEY = "CLAIM_TOKEN";
  /** Token del propietario. */
  public static final String ISSUER_TOKEN = "ISSUER";
  /** Token bearer. */
  public static final String TOKEN_BEARER_PREFIX = "Bearer";
  /** Permite solo perfil cliente. */
  public static final String CUSTOMER_ALLOWED = "hasRole('CUSTOMER')";
  /** Permite solo perfil administrador. */
  public static final String ADMIN_ALLOWED = "hasRole('ADMIN')";
}
