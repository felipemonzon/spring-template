package com.moontech.template.configuration;

/**
 * Constantes para pruebas.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 21 nov., 2022
 */
public abstract class TestConstants {
  /** UUID header. */
  public static final String UUID_HEADER = "uuid";
  /** Perfil de administrador. */
  public static final String ROLE_ADMIN = "ADMIN";
  /** Perfil de otros. */
  public static final String ROLE_OTHER = "OTHER";
  /** Log running. */
  public static final String TEST_RUNNING = "Running {}";

  private TestConstants() {}
}
