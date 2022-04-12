package com.moontech.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moontech.library.constants.DatabaseConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad para editorial.
 *
 * @author Felipe Monzón
 * @since Jan 17. 2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = DatabaseConstant.PUBLISHERS_TABLE)
@Table(name = DatabaseConstant.PUBLISHERS_TABLE)
public class PublisherEntity extends AuditableEntity {
  /** Identificador de editoriales. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(
      name = DatabaseConstant.PUBLISHER_ID_FIELD,
      nullable = false,
      unique = true,
      updatable = false)
  private int id;
  /** Nombre de la editorial. */
  @Column(name = DatabaseConstant.DESCRIPTION_FIELD, nullable = false, unique = true)
  private String name;
  /** libros de la editorial. */
  @JsonIgnore
  @OneToMany(
      mappedBy = DatabaseConstant.PUBLISHER_BOOK_RELATIONSHIP,
      orphanRemoval = true,
      cascade = CascadeType.ALL)
  private Set<BookEntity> books;
}
