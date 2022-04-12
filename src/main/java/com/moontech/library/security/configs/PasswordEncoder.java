 package com.moontech.library.security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuración para el encriptado de la contraseña.
 *
 * @author Felipe Monzón
 * @since Jan. 05, 2022
 */
@Configuration
public class PasswordEncoder {
  /**
   * Encriptado de la contraseña.
   *
   * @return {@code BCryptPasswordEncoder}
   */
  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }
}
