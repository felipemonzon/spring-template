package com.moontech.template.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Configuración para el restemplate.
 *
 * @author Felipe Monzón
 * @since 27 jun., 2023
 * @enterprise moontech
 */
@Configuration
public class RestOperationConfig {
  /**
   * Configuración para consumir servicios.
   *
   * @return instancia nueva de rest-template
   */
  @Bean
  public RestOperations restTemplate() {
    return new RestTemplate();
  }
}
