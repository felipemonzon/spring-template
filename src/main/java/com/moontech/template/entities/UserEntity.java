package com.moontech.template.entities;

import com.moontech.template.constants.DatabaseConstant;
import com.moontech.template.enums.Genre;
import com.moontech.template.enums.Status;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Entidad para usuario.
 *
 * @author Felipe Monzón
 * @since Dic 18. 2022
 */
@Getter
@Setter
@ToString
@Entity(name = DatabaseConstant.USERS_TABLE)
@Table(name = DatabaseConstant.USERS_TABLE)
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends AuditableEntity {
  /** Identificador del usuario. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  /** Propiedad para el nombre del usuario. */
  @NaturalId
  @Column(name = DatabaseConstant.USERNAME_PROPERTY, nullable = false, length = 20, unique = true)
  private String username;
  /** Propiedad primer nombre. */
  @Column(name = DatabaseConstant.FIRST_NAME_PROPERTY, nullable = false)
  private String firstName;
  /** Propiedad segundo nombre. */
  @Column(name = DatabaseConstant.LAST_NAME_PROPERTY, nullable = false)
  private String lastName;
  /** Propiedad para el correo. */
  @Column(name = DatabaseConstant.EMAIL_PROPERTY, nullable = false)
  private String email;
  /** Propiedad para el celular. */
  @Column(name = DatabaseConstant.CELLPHONE_PROPERTY, nullable = false)
  private String cel;
  /** Propiedad para el género. */
  @Enumerated(EnumType.ORDINAL)
  private Genre genre;
  /** Propiedad para la contraseña. */
  @Column(name = DatabaseConstant.PASSWORD_PROPERTY, nullable = false, length = 200)
  private String password;
  /** Propiedad para el status del usuario. */
  @Enumerated(EnumType.STRING)
  @Column(name = DatabaseConstant.STATUS_PROPERTY, nullable = false)
  private Status status;
  /** Propiedad para el rol del usuario. */
  @ManyToMany
  @ToString.Exclude
  @JoinTable(
      name = DatabaseConstant.RELATION_USER_ROLES_NAME,
      joinColumns = {@JoinColumn(name = DatabaseConstant.PROPERTY_USER_ID)},
      inverseJoinColumns = {@JoinColumn(name = DatabaseConstant.PROPERTY_ROL_ID)})
  @Column(name = DatabaseConstant.PROPERTY_ROL_ID, nullable = false)
  private Set<RoleEntity> roles;
}
