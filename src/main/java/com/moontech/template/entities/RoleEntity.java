package com.moontech.template.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moontech.template.constants.DatabaseConstant;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad de la tabla "roles".
 *
 * @author Felipe Monzón
 * @since Jan 17. 2022
 */
@Getter
@Setter
@ToString
@Table(name = DatabaseConstant.TABLE_ROLES)
@Entity(name = DatabaseConstant.TABLE_ROLES)
public class RoleEntity implements Serializable {
  /** Serial. */
  private static final long serialVersionUID = 2013073849429702841L;
  /** Identificador del perfil. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  /** Descripción del perfil. */
  @Column(name = DatabaseConstant.PROPERTY_ROLE_NAME, length = 30, nullable = false)
  private String name;
  /** Valor del perfil. */
  @Column(name = DatabaseConstant.PROPERTY_ROLE_VALUE, nullable = false, length = 40)
  private String value;
  /** Usuarios pertenecientes al rol. */
  @JsonIgnore
  @ToString.Exclude
  @ManyToMany(
      mappedBy = "roles",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<UserEntity> users;
}
