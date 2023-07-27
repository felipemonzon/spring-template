package com.moontech.template.security.utilities;

import java.util.UUID;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utilería para seguridad.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 12 Dec., 2022
 */
@Slf4j
public abstract class SecurityUtilities {
  /** Mascará para la tarjeta. */
  private static final String MASK_CARD = "XXXX XXXX XXXX ";
  /** Perfil de cliente. */
  public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
  /** Expresión regular para validar el captcha. */
  public static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

  /**
   * Encripta la contraseña.
   *
   * @param password contraseña a encriptar
   * @return contraseña encriptada
   */
  public static String passwordEncoder(final String password) {
    return new BCryptPasswordEncoder().encode(password);
  }

  /**
   * Agrega la mascara al número de tarjeta.
   *
   * @param cardNumber número de tarjeta
   * @return tarjeta enmascarada
   */
  public static String maskCard(final String cardNumber) {
    return MASK_CARD + cardNumber;
  }

  /**
   * Genera la ruta para confirmación de usuario segura.
   *
   * @param path ruta de confirmación
   */
  public static String getConfirmPath(final String path, final String token) {
    return path + "?token=" + token;
  }

  /**
   * Genera un uuid.
   *
   * @return uuid
   */
  public static String getToken() {
    return UUID.randomUUID().toString();
  }

  /** Constructor */
  private SecurityUtilities() {}
}
