package com.moontech.library.entities;

import com.moontech.library.constants.DatabaseConstant;
import com.moontech.library.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad para libros.
 *
 * @author Felipe Monzón
 * @since Jan 17. 2022
 */
@Getter
@Setter
@ToString
@Entity(name = DatabaseConstant.BOOKS_TABLE)
@Table(name = DatabaseConstant.BOOKS_TABLE)
public class BookEntity extends AuditableEntity {
  /** Identificador del libro. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  /** Nombre del libro. */
  @Column(name = DatabaseConstant.BOOK_TITLE_FIELD, unique = true)
  private String title;
  /** ISBN. */
  @NaturalId private String isbn;
  /** Estatus del libro. */
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  /** Relación con la tabla de autores. */
  @ManyToMany
  @JoinTable(
      name = DatabaseConstant.AUTHOR_WRITER_TABLE,
      joinColumns = @JoinColumn(name = DatabaseConstant.BOOK_ID_FIELD),
      inverseJoinColumns = @JoinColumn(name = DatabaseConstant.AUTHOR_ID_FIELD))
  @Column(name = DatabaseConstant.AUTHOR_ID_FIELD, nullable = false)
  @ToString.Exclude
  private Set<AuthorEntity> authors;
  /** Relación con la tabla de categorías. */
  @ManyToMany
  @JoinTable(
      name = DatabaseConstant.CATEGORIES_BOOKS_TABLE,
      joinColumns = @JoinColumn(name = DatabaseConstant.BOOK_ID_FIELD),
      inverseJoinColumns = @JoinColumn(name = DatabaseConstant.CATEGORY_ID_FIELD))
  @Column(name = DatabaseConstant.CATEGORY_ID_FIELD, nullable = false)
  @ToString.Exclude
  private Set<CategoryEntity> categories;
  /** Editorial del libro. */
  @ManyToOne
  @JoinColumn(
      name = DatabaseConstant.PUBLISHER_ID_FIELD,
      referencedColumnName = DatabaseConstant.PUBLISHER_ID_FIELD)
  private PublisherEntity publisher;
}
