package com.moontech.library.configs;

import com.moontech.library.properties.SwaggerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuración para swagger-ui.
 *
 * @author Felipe Monzón
 * @since Dec 17, 2021
 */
@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig {
  /** Constantes con los valores obtenidos del archivo properties. */
  private final SwaggerProperties swaggerConstants;

  /**
   * Genera el swagger-ui.
   *
   * @return información del producto
   */
  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(this.apiInfo())
        .useDefaultResponseMessages(Boolean.FALSE)
        .select()
        .apis(RequestHandlerSelectors.basePackage(this.swaggerConstants.getBasePackage()))
        .paths(PathSelectors.any())
        .build();
  }

  /**
   * Constructor para la información de la API.
   *
   * @return información del producto
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(swaggerConstants.getTitle())
        .description(swaggerConstants.getDescriptionApi())
        .version(swaggerConstants.getVersion())
        .contact(
            new Contact(
                swaggerConstants.getNameDeveloper(),
                swaggerConstants.getContactUrl(),
                swaggerConstants.getEmailDeveloper()))
        .build();
  }
}
