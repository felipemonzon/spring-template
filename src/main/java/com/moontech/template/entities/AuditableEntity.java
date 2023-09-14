package com.moontech.template.entities;

import com.moontech.template.constants.DatabaseConstant;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
  @Serial private static final long serialVersionUID = -7937775898702198567L;

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
