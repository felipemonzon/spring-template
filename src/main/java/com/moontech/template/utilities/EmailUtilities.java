package com.moontech.template.utilities;

import com.moontech.template.constants.ApiConstant;
import com.moontech.template.entities.UserEntity;
import com.moontech.template.models.requests.UserRequest;
import com.moontech.template.notifications.Email;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Utilería para el envío de correos.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 10 dic., 2022
 */
public abstract class EmailUtilities {
  /**
   * Obtiene los datos para enviar por correo.
   *
   * @param userData {@link UserRequest}
   * @param path ruta para enviar en caso de necesitar
   * @return {@link Email}
   */
  public static Email getUserRegisterEmail(UserEntity userData, String path) {
    Email email = new Email();
    Map<String, String> model = new HashMap<>();
    model.put(ApiConstant.MAIL_FIRSTNAME_PROPERTY, userData.getFirstName());
    model.put(ApiConstant.MAIL_LASTNAME_PROPERTY, userData.getLastName());
    model.put(ApiConstant.MAIL_URL_PROPERTY, getUrlValidationEmail(path));
    model.put(ApiConstant.MAIL_USER_PROPERTY, userData.getUsername());

    email.setTo(userData.getEmail());
    email.setModel(model);

    return email;
  }

  /**
   * Obtiene los datos para enviar por correo cuando se olvida la contraseña.
   *
   * @param userData {@link UserRequest}
   * @param path ruta para enviar en caso de necesitar
   * @return {@link Email}
   */
  public static Email getResetPassword(UserEntity userData, String path) {
    Email email = new Email();
    Map<String, String> model = new HashMap<>();
    model.put(ApiConstant.MAIL_FIRSTNAME_PROPERTY, userData.getFirstName());
    model.put(ApiConstant.MAIL_LASTNAME_PROPERTY, userData.getLastName());
    model.put(ApiConstant.MAIL_URL_PROPERTY, getUrlValidationEmail(path));

    email.setTo(userData.getEmail());
    email.setModel(model);

    return email;
  }

  /**
   * Obtiene la URL del servicio.
   *
   * @param path ruta para enviar vía correo
   * @return URL del servicio
   */
  private static String getUrlValidationEmail(final String path) {
    return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + path;
  }

  /** Constructor privado. */
  private EmailUtilities() {}
}
