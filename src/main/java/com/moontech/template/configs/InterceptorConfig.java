package com.moontech.template.configs;

import com.moontech.template.interceptors.TimeInterceptor;
import com.moontech.template.properties.PropertiesConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración para añadir el interceptor.
 *
 * @author Felipe Monzón
 * @since Dec 17, 2021
 */
@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
  /** Componente para medir el tiempo de la petición. */
  private final TimeInterceptor timeInterceptor;
  /** Constantes generales de la aplicación. */
  private final PropertiesConstant properties;

  /** Se registra el interceptor y al ruta a interceptar. */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(timeInterceptor).addPathPatterns(properties.getInterceptorPath());
  }
}
