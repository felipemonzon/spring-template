package com.moontech.template.configs;

import com.moontech.template.constants.ApiConstant;
import com.moontech.template.models.responses.SecurityResponse;
import java.util.Objects;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * Configuración de la clase auditora.
 *
 * @author Felipe Monzón
 * @since 21/12/21
 */
@Component
public class Auditor implements AuditorAware<String> {
  /**
   * Consulta el usuario auditor.
   *
   * @return usuario encontrado
   */
  @Override
  public Optional<String> getCurrentAuditor() {
    String user = ApiConstant.USER_SYSTEM;
    if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
      SecurityResponse login =
          new ModelMapper()
              .map(
                  SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                  SecurityResponse.class);
      if (ObjectUtils.isEmpty(login.getUsername())) {
        user = ApiConstant.USER_SYSTEM;
      } else {
        user = login.getUsername();
      }
    }
    return Optional.of(user);
  }
}
