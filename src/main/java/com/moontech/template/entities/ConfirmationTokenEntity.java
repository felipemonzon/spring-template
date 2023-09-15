package com.moontech.template.entities;

import com.moontech.template.constants.DatabaseConstant;
import com.moontech.template.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad para el token de confirmación.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 11 dic., 2022
 */
@Getter
@Setter
@ToString
@Entity(name = DatabaseConstant.CONFIRM_TOKEN_TABLE)
@Table(name = DatabaseConstant.CONFIRM_TOKEN_TABLE)
public class ConfirmationTokenEntity {
  /** Identificador de la tabla. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "token_id")
  private long tokenId;

  /** Token generado de manera aleatoria. */
  @Column(name = "confirmation_token")
  private String confirmToken;

  /** Fecha de creación de token */
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  /** Fecha de caducidad del token. */
  @Column(name = "expiration_date", nullable = false, updatable = false)
  private LocalDateTime expirationDate;

  /** Usuario al que pertenece el token. */
  @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id")
  private UserEntity user;

  /** Estatus del token. */
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private Status status;
}
