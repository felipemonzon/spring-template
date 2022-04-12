/*
 * Copyright (c) 2021 Sano Pak
 *
 * Licensed under the GNU General Public License, Version 3 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 * https://www.gnu.org/licenses/gpl-3.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.moontech.library.constants;

/**
 * Constantes para errores de la aplicación.
 *
 * @author Felipe Monzón
 * @since Dec 18, 2021
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
  /** Mensaje para dato existente. */
  public static final String DATA_EXIST_MESSAGE = "El dato ya existe ";

  /** Constructor privado. */
  private ErrorConstant() {}
}
