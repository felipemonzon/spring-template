package com.moontech.library.configs;

import com.moontech.library.constants.ApiConstant;
import com.moontech.library.models.responses.LoginResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * Configuración de la clase auditora.
 *
 * @author Felipe Monzón
 * @since 21/12/21
 */
@Component
public class AuditorAware implements org.springframework.data.domain.AuditorAware<String> {
  /**
   * Consulta el usuario auditor.
   *
   * @return usuario encontrado
   */
  @Override
  public Optional<String> getCurrentAuditor() {
    String user = ApiConstant.USER_SYSTEM;
    if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
      LoginResponse login =
          new ModelMapper()
              .map(
                  SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                  LoginResponse.class);
      user = login.getUsername();
    }
    return Optional.of(user);
  }
}
