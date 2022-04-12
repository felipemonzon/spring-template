package com.moontech.library.constants;

/**
 * Constantes para la aplicación.
 *
 * @author Felipe Monzón
 * @since Dec 17, 2021
 */
public abstract class ApiConstant {
  /** Constante para el símbolo de la coma. */
  public static final String COMMA = ",";
  /** Constante para el header uuid. */
  public static final String HEADER_UUID = "uuid";
  /** Constante para el número entero cero. */
  public static final int INT_ZERO_VALUE = 0;
  /** Constante el número entero uno. */
  public static final int INT_ONE_VALUE = 1;
  /** Constante para mostrar el inicio de la petición. */
  public static final String START_REQUEST = "Inicia Llamado [{}]";
  /** Constante para la llave req.time. */
  public static final String TIME_REQ_ATTRIBUTE = "req.time";
  /** Constante usada como llave para el atributo uuid header. */
  public static final String UUID_MDC_LABEL = "mdc.uuid";
  /** Constante para mostrar el tiempo de petición y respuesta. */
  public static final String TIME_ELAPSED_MESSAGE =
      "Time elapsed for request round trip [{}]: {} ms";
  /** Prefijo para las propiedades de la aplicación. */
  public static final String PROPERTY_PREFIX = "api";
  /** Prefijo para las propiedades de swagger. */
  public static final String SWAGGER_PROPERTIES = "swagger";
  /** Espacio en blanco. */
  public static final String WHITE_SPACE = " ";
  /** Usuario de sistema. */
  public static final String USER_SYSTEM = "System";
}
