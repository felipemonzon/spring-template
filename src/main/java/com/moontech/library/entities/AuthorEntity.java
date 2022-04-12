package com.moontech.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moontech.library.constants.DatabaseConstant;
import com.moontech.library.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad de autores.
 *
 * @author Felipe Monzón
 * @since Jan 17. 2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = DatabaseConstant.AUTHORS_TABLE)
@Table(name = DatabaseConstant.AUTHORS_TABLE)
public class AuthorEntity extends AuditableEntity {
  /** Identificador de la tabla. */
  @Id
  @Column(name = DatabaseConstant.AUTHOR_ID_FIELD)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  /** Propiedad para el primer nombre del autor. */
  @Column(name = DatabaseConstant.FIRST_NAME_PROPERTY, nullable = false)
  private String firstName;
  /** Segundo nombre. */
  @Column(name = DatabaseConstant.LAST_NAME_PROPERTY, nullable = false)
  private String lastName;
  /** Status del autor. */
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  /** Relación para la tabla de libros. */
  @JsonIgnore
  @ManyToMany(
      mappedBy = DatabaseConstant.AUTHORS_TABLE,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<BookEntity> books;
}
