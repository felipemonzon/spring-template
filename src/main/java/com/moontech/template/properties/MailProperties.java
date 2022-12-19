package com.moontech.template.properties;

import com.moontech.template.constants.ApiConstant;
import com.moontech.template.notifications.MailConfiguration;
import java.util.Map;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * Propiedades para el correo de la aplicación.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 30 nov., 2022
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = ApiConstant.PROPERTIES_MAIL)
public class MailProperties {
  /** Encabezado del correo. */
  private String payment;
  /** Encabezado para mensaje de bienvenida. */
  private String welcome;
  /** Encabezado para reinicio de contraseña. */
  private String resetPassword;
  /** Destinatario del correo. */
  private String from;
  /** Nombre empresa. */
  private String enterpriseName;
  /** Imagen de venta. */
  @Value("classpath:/templates/images/welcome.png")
  private Resource welcomeIcon;
  /** Icono de Facebook. */
  @Value("classpath:/templates/images/facebook-rounded-gray.png")
  private Resource facebookIcon;
  /** Icono para olvidar contraseña. */
  @Value("classpath:/templates/images/forgot_password.png")
  private Resource forgotPassword;
  /** Icono de EMAIL. */
  @Value("classpath:/templates/images/linkedin-rounded-gray.png")
  private Resource linkedinIcon;
  /** Icono de instagram. */
  @Value("classpath:/templates/images/instagram-rounded-gray.png")
  private Resource instagramIcon;
  /** Icono de EMAIL. */
  @Value("classpath:/templates/images/twitter-rounded-gray.png")
  private Resource twitterIcon;
  /** Plantilla de correo. */
  private Map<String, String> templates;

  /**
   * Configuración de las propiedades de correo.
   *
   * @return {@code MailConfiguration}
   */
  @Bean
  public MailConfiguration loadConfig() {
    return MailConfiguration.builder()
        .mail(this.from)
        .welcomeImg(this.welcomeIcon)
        .forgotPassword(this.forgotPassword)
        .payment(this.payment)
        .welcome(this.welcome)
        .resetPassword(this.resetPassword)
        .welcomeMessage(this.welcome)
        .templates(this.templates)
        .facebookIcon(this.facebookIcon)
        .enterpriseName(this.enterpriseName)
        .twitterIcon(this.twitterIcon)
        .instagramIcon(this.instagramIcon)
        .linkedinIcon(this.linkedinIcon)
        .build();
  }
}
