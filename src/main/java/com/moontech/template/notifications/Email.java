package com.moontech.template.notifications;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Objeto para envío de correo.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since Dec 01, 2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Email {
  /** Receptor del correo. */
  private String to;
  /** Mapa de datos a enviar. */
  private Map<String, String> model;
}
