package com.moontech.template.constants;

/**
 * Constantes para errores de la aplicación.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 01 Dic, 2022
 */
public class ErrorConstant {
  /** Código genérico. */
  public static final Integer GENERIC_ERROR_CODE = 9009;
  /** Mensaje genérico. */
  public static final String GENERIC_ERROR_MESSAGE = "Ocurrió un error desconocido";
  /** Código para dato no encontrado. */
  public static final Integer BAD_REQUEST_CODE = 9002;
  /** Código para dato no encontrado. */
  public static final Integer RECORD_NOT_FOUND_CODE = 9003;
  /** Mensaje para dato no encontrado. */
  public static final String RECORD_NOT_FOUND_MESSAGE = "No se encontró el registro";
  /** Código para credenciales inválidas. */
  public static final int INVALID_CREDENTIAL_USER_CODE = 9000;
  /** Mensaje para usuario o contraseña incorrecto. */
  public static final String INVALID_CREDENTIAL_USER_MESSAGE = "Usuario/contraseña inválida";
  /** Código para token incorrecto. */
  public static final int INVALID_TOKEN_CODE = 9001;
  /** Mensaje para token incorrecto. */
  public static final String INVALID_TOKEN_MESSAGE = "Acceso Denegado";
  /** Código para acceso denegado. */
  public static final int ACCESS_DENIED_CODE = 9008;
  /** Mensaje para acceso denegado. */
  public static final String ACCESS_DENIED_MESSAGE = "Acceso Denegado.";
  /** Prefijo pata detalles. */
  public static final String PREFIX_DETAIL_MESSAGE = "Detail";
  /** Código de error para datos existentes. */
  public static final int DATA_EXIST_CODE = 9007;
  /** Nombre de usuario ya existe. */
  public static final String USERNAME_EXIST = "El nombre de usuario ya existe";
  /** Mensaje para token invalido. */
  public static final String INVALID_TOKEN = "El token es invalido o esta corrompido";

  /** Constructor privado. */
  private ErrorConstant() {}
}
