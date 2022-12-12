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
package com.moontech.template.constants;

/**
 * Constantes para log.
 *
 * @author Felipe Monzón
 * @since Dec 18, 2021
 */
public abstract class LogConstant {
  public static final String USERS_RETRIEVE = "Consulta usuarios por página {}";
  /** Guarda los datos de un usuario. */
  public static final String SAVE_USER_REQUEST = "Guarda los datos del usuario {}";
  /** Prefijo de token invalido. */
  public static final String INVALID_PREFIX_TOKEN = "Invalid bearer token";
  /** Inicio de sesión correcto. */
  public static final String LOGIN_SUCCESSFULLY = "inicio de sesión satisfactorio";

  /** Constructor privado. */
  private LogConstant() {}
}
