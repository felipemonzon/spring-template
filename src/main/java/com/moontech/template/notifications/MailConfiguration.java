package com.moontech.template.notifications;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.core.io.Resource;

/**
 * Objeto con las propiedades de correo.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since Dec 01, 2022
 */
@Getter
@Builder
@AllArgsConstructor
public class MailConfiguration {
  /** Encabezado de compra. */
  private String payment;
  /** Encabezado de bienvenida. */
  private String welcome;
  /** Encabezado para reinicio de contraseña. */
  private String resetPassword;
  /** Destinatario del correo. */
  private String mail;
  /** Mensaje predeterminado de ayuda. */
  private String welcomeMessage;
  /** Icono de bienvenida. */
  private Resource welcomeImg;
  /** Icono de Facebook. */
  private Resource facebookIcon;
  /** Icono para olvidar contraseña. */
  private Resource forgotPassword;
  /** Icono de EMAIL. */
  private Resource linkedinIcon;
  /** Icono de instagram. */
  private Resource instagramIcon;
  /** Icono de EMAIL. */
  private Resource twitterIcon;
  /** Plantillas de correo. */
  private Map<String, String> templates;
  /** Nombre empresa. */
  private String enterpriseName;
}
