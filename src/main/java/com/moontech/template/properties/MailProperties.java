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
  /** Encabezado para confirmación. */
  private String confirm;
  /** Encabezado para mensaje de bienvenida. */
  private String welcome;
  /** Destinatario del correo. */
  private String from;
  /** Nombre empresa. */
  private String enterpriseName;
  /** Imagen de venta. */
  @Value("classpath:/templates/images/welcome.png")
  private Resource welcomeImage;
  /** Logo. */
  @Value("classpath:/templates/images/logo.png")
  private Resource logoImg;
  /** Icono de Facebook. */
  @Value("classpath:/templates/images/facebook.png")
  private Resource facebookIcon;
  /** Icono de Whatsapp. */
  @Value("classpath:/templates/images/whatsapp.png")
  private Resource whatsIcon;
  /** Icono de EMAIL. */
  @Value("classpath:/templates/images/correo_2.png")
  private Resource emailIcon;
  /** Icono de flecha. */
  @Value("classpath:/templates/images/flecha.png")
  private Resource arrow;
  /** Icono de instagram. */
  @Value("classpath:/templates/images/instagram.png")
  private Resource instagramIcon;
  /** Icono de EMAIL. */
  @Value("classpath:/templates/images/twitter.png")
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
        .welcomeImg(this.welcomeImage)
        .payment(this.payment)
        .welcome(this.welcome)
        .confirmMessage(this.confirm)
        .welcomeMessage(this.welcome)
        .templates(templates)
        .facebookIcon(this.facebookIcon)
        .whatsIcon(this.whatsIcon)
        .emailIcon(this.emailIcon)
        .logoImg(this.logoImg)
        .arrowIcon(this.arrow)
        .enterpriseName(this.enterpriseName)
        .twitterIcon(this.twitterIcon)
        .instagramIcon(this.instagramIcon)
        .build();
  }
}
