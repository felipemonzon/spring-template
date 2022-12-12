/*
 * Copyright (c) 2021 Sano Pak
 *
 * Licensed under the GNU General Public License, Version 3 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 * https://www.gnu.org/licenses/gpl-3.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
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
