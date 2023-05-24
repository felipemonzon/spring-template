package com.moontech.template.constants;

/**
 * Constantes de rutas
 *
 * @author Felipe Monzón
 * @since Jan 20. 2022
 */
public abstract class RoutesConstant {
  /** Ruta base para usuarios. */
  public static final String USERS_BASE_PATH = "${api.uri.domain.users}";
  /** Ruta para actualizar o eliminar los datos. */
  public static final String DATA_MODIFIED_PATH = "${api.uri.data.modified}";
  /** Ruta para actualizar o eliminar los datos. */
  public static final String CONFIRM_TOKEN_PATH = "${api.uri.data.userConfirm}";
  /** Ruta para solicitar cambio de la contraseña. */
  public static final String RESET_PWD_PATH = "${api.uri.data.reset}";

  /** Constructor de la class. */
  private RoutesConstant() {}
}
