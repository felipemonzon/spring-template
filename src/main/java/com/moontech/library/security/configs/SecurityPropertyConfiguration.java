package com.moontech.library.security.configs;

import com.moontech.library.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Propiedades externalizadas de seguridad.
 *
 * @author Felipe Monzón
 * @since 11 ene., 2022
 */
@Configuration
public class SecurityPropertyConfiguration {
  /** Llave del JWT. */
  @Value("${security.jwt.key}")
  private String signingKey;
  /** Tiempo de vida del token. */
  @Value("${security.jwt.lifeTime}")
  private int validity;
  /** Ruta para la autenticación de usuarios. */
  @Value("${api.uri.data.authentication}")
  private String userAuthenticationPath;

  /**
   * Carga las variables externalizadas en modelo.
   *
   * @return {@code SecurityProperties}
   */
  @Bean
  public SecurityProperties loadSecurityProperties() {
    return SecurityProperties.builder()
        .userAuthenticationPath(this.userAuthenticationPath)
        .jwtKey(this.signingKey)
        .jwtLifeTime(this.validity)
        .build();
  }
}
