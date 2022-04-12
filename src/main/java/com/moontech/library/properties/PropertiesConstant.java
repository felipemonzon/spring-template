package com.moontech.library.properties;

import com.moontech.library.constants.ApiConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Propiedades externalizadas de la aplicación.
 *
 * @author Felipe Monzón
 * @since 17/03/21
 */
@Data
@Configuration
@ConfigurationProperties(prefix = ApiConstant.PROPERTY_PREFIX)
public class PropertiesConstant {
  /** Ruta para la intercepción de la petición. */
  private String interceptorPath;
}
