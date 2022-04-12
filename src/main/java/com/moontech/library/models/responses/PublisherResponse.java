package com.moontech.library.models.responses;

import com.moontech.library.models.requests.Publisher;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Respuesta de la API editoriales.
 *
 * @author Felipe Monzón
 * @since Jan 30, 2022
 */
@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class PublisherResponse extends Pagination {
  /** Lista de editoriales. */
  List<Publisher> publishers;
}
