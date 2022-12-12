package com.moontech.template.configs;

import com.moontech.template.properties.SwaggerProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración para swagger-ui.
 *
 * @author Felipe Monzón
 * @since Dec 17, 2022
 */
@Configuration
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
  public OpenAPI productApi() {
    final String securitySchemeName = "Bearer JWT";
    return new OpenAPI()
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
        .components(
            new Components()
                .addSecuritySchemes(
                    securitySchemeName,
                    new SecurityScheme()
                        .name(securitySchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")))
        .info(this.apiInfo());
  }

  /**
   * Constructor para la información de la API.
   *
   * @return información del producto
   */
  private Info apiInfo() {
    return new Info()
        .title(this.swaggerConstants.getTitle())
        .description(this.swaggerConstants.getDescriptionApi())
        .version(this.swaggerConstants.getVersion())
        .contact(
            new Contact()
                .email(this.swaggerConstants.getEmailDeveloper())
                .name(this.swaggerConstants.getNameDeveloper())
                .url(this.swaggerConstants.getContactUrl()))
        .license(new License().name("MIT LICENCE").url(this.swaggerConstants.getLicenceUrl()));
  }
}
