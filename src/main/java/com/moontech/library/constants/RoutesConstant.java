package com.moontech.library.constants;

/**
 * Constantes de rutas
 *
 * @author Felipe Monzón
 * @since Jan 20. 2022
 */
public abstract class RoutesConstant {
  /** Ruta base para usuarios. */
  public static final String USERS_BASE_PATH = "${api.uri.domain.users}";
  /** Ruta para guardar los datos. */
  public static final String DATA_CREATE_PATH = "${api.uri.data.create}";
  /** Ruta para actualizar o eliminar los datos. */
  public static final String DATA_MODIFIED_PATH = "${api.uri.data.modified}";
  /** Ruta para consulta de los datos. */
  public static final String DATA_RETRIEVE_PATH = "${api.uri.data.retrieve}";
  /** Ruta base para libros. */
  public static final String BOOKS_BASE_PATH = "${api.uri.domain.books}";
  /** Ruta base para autores. */
  public static final String AUTHORS_BASE_PATH = "${api.uri.domain.authors}";
  /** Ruta base para categorías. */
  public static final String CATEGORIES_BASE_PATH = "${api.uri.domain.categories}";
  /** Ruta base para editoriales. */
  public static final String PUBLISHERS_BASE_PATH = "${api.uri.domain.publishers}";

  /** Constructor de la class. */
  private RoutesConstant() {}
}
