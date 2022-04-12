package com.moontech.library.entities;

import com.moontech.library.constants.DatabaseConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entidad auditora.
 *
 * @author Felipe Monzón
 * @since Dec 17. 2020
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity implements Serializable {
  /** serial. */
  private static final long serialVersionUID = -7937775898702198567L;
  /** Usuario que crea el registro. */
  @CreatedBy
  @Column(name = DatabaseConstant.CREATE_USER, updatable = false, nullable = false)
  protected String createdBy;
  /** Fecha de creación. */
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = DatabaseConstant.CREATE_DATE, updatable = false, nullable = false)
  protected Date createdDate;
  /** Usuario que modifica el registro. */
  @LastModifiedBy
  @Column(name = DatabaseConstant.LAST_MODIFIED_USER, nullable = false)
  protected String lastModifiedBy;
  /** Fecha que modifica. */
  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = DatabaseConstant.LAST_MODIFIED_DATE, nullable = false)
  protected Date lastModifiedDate;
}
