package com.moontech.template.constants;

/**
 * Constantes para la base de datos.
 *
 * @author Felipe Monzón
 * @since 21/12/21
 */
public abstract class DatabaseConstant {
  /** Propiedad para el nombre de la tabla "users". */
  public static final String USERS_TABLE = "users";
  /** Propiedad para el campo "create_user". */
  public static final String CREATE_USER = "create_user";
  /** Propiedad para el campo "create_date". */
  public static final String CREATE_DATE = "create_date";
  /** Propiedad para el campo "last modified_user". */
  public static final String LAST_MODIFIED_USER = "last_modified_user";
  /** Propiedad para el campo "last modified_date". */
  public static final String LAST_MODIFIED_DATE = "last_modified_date";
  /** Propiedad para primer nombre. */
  public static final String FIRST_NAME_PROPERTY = "first_name";
  /** Propiedad para el segundo nombre. */
  public static final String LAST_NAME_PROPERTY = "last_name";
  /** Propiedad para nombre de usuario. */
  public static final String USERNAME_PROPERTY = "username";
  /** Propiedad para contraseña. */
  public static final String PASSWORD_PROPERTY = "password";
  /** Propiedad para el correo. */
  public static final String EMAIL_PROPERTY = "email_address";
  /** Propiedad para el número de celular. */
  public static final String CELLPHONE_PROPERTY = "cellphone";
  /** Nombre de la tabla "roles". */
  public static final String TABLE_ROLES = "roles";
  /** Propiedad para el nombre del rol. */
  public static final String PROPERTY_ROLE_NAME = "name";
  /** Propiedad para el valor del rol. */
  public static final String PROPERTY_ROLE_VALUE = "value";
  /** Propiedad para la relación usuarios - roles. */
  public static final String RELATION_USER_ROLES_NAME = "user_roles";
  /** Propiedad para el identificador del rol. */
  public static final String PROPERTY_ROL_ID = "id_role";
  /** Propiedad para el identificador del usuario. */
  public static final String PROPERTY_USER_ID = "id_user";
  /** Propiedad para el status. */
  public static final String STATUS_PROPERTY = "status";
  /** Propiedad para el nombre de la tabla "confirmtokens". */
  public static final String CONFIRM_TOKEN_TABLE = "confirmation_tokens";

  /** Constructor privado. */
  private DatabaseConstant() {}
}
