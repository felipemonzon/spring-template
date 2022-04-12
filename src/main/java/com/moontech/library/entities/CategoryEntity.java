package com.moontech.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moontech.library.constants.DatabaseConstant;
import com.moontech.library.enums.Status;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad para la tabla de categorías.
 *
 * @author Felipe Monzón
 * @since Jan 17. 2022
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = DatabaseConstant.CATEGORIES_TABLE)
@Table(name = DatabaseConstant.CATEGORIES_TABLE)
public class CategoryEntity extends AuditableEntity {
  /** Identificador de la categoría. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = DatabaseConstant.CATEGORY_ID_FIELD)
  private int id;
  /** Nombre de la categoría. */
  @NaturalId(mutable = true)
  @Column(name = DatabaseConstant.DESCRIPTION_FIELD, unique = true, nullable = false)
  private String name;
  /** Estatus de la categorías. */
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  /** Relación para la tabla de libros. */
  @JsonIgnore
  @ToString.Exclude
  @ManyToMany(mappedBy = DatabaseConstant.CATEGORIES_TABLE)
  private Set<BookEntity> books;
}
