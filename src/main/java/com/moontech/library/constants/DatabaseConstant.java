package com.moontech.library.constants;

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
  /** Propiedad para el nombre de la tabla "books". */
  public static final String BOOKS_TABLE = "books";
  /** Propiedad para el nombre de la tabla "author_writer". */
  public static final String AUTHOR_WRITER_TABLE = "author_writer";
  /** Propiedad para el campo "book_id". */
  public static final String BOOK_ID_FIELD = "book_id";
  /** Propiedad para el campo "title". */
  public static final String BOOK_TITLE_FIELD = "title";
  /** Propiedad para el campo "author_id". */
  public static final String AUTHOR_ID_FIELD = "author_id";
  /** Propiedad para el nombre de la tabla "authors". */
  public static final String AUTHORS_TABLE = "authors";
  /** Propiedad para el nombre de la tabla "author_writer". */
  public static final String CATEGORIES_BOOKS_TABLE = "categories_books";
  /** Propiedad para el campo "author category id". */
  public static final String CATEGORY_ID_FIELD = "category_id";
  /** Propiedad para el nombre de la tabla "publishers". */
  public static final String PUBLISHERS_TABLE = "publishers";
  /** Propiedad para el campo "publisher_id". */
  public static final String PUBLISHER_ID_FIELD = "publisher_id";
  /** Nombre de relación entre libros y editoriales. */
  public static final String PUBLISHER_BOOK_RELATIONSHIP = "publisher";
  /** Propiedad para el nombre de la tabla "categories". */
  public static final String CATEGORIES_TABLE = "categories";
  /** Propiedad para el campo "description". */
  public static final String DESCRIPTION_FIELD = "description";

  /** Constructor privado. */
  private DatabaseConstant() {}
}
