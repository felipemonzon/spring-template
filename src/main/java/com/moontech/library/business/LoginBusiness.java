package com.moontech.library.business;

import com.moontech.library.constants.ApiConstant;
import com.moontech.library.entities.UserEntity;
import com.moontech.library.entities.RoleEntity;
import com.moontech.library.models.responses.LoginResponse;
import com.moontech.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Inicio de sesión.
 *
 * @author Felipe Monzón
 * @since Jan 12, 2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginBusiness implements UserDetailsService {
  /** Repositorio de usuarios. */
  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("Proceso de login");
    UserEntity user =
        userRepository
            .findByUsername(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username: " + username));
    LoginResponse credential = new ModelMapper().map(this.build(user), LoginResponse.class);
    credential.setCel(user.getCel());
    credential.setEmail(user.getEmail());
    credential.setFirstName(user.getFirstName());
    credential.setLastName(user.getLastName());
    credential.setGenre(user.getGenre());
    credential.setDisplayName(user.getFirstName() + ApiConstant.WHITE_SPACE + user.getLastName());
    return credential;
  }

  /**
   * Convierte el objeto {@code UserEntity} a {@code UserDetails}
   *
   * @param user {@code UserEntity}
   * @return {@code UserDetails}
   */
  private UserDetails build(UserEntity user) {
    return new User(user.getUsername(), user.getPassword(), getAuthorities(user));
  }

  /**
   * Obtiene el rol de autorización.
   *
   * @param retrievedUser {@code UserEntity}
   * @return {@code GrantedAuthority}
   */
  private Set<? extends GrantedAuthority> getAuthorities(UserEntity retrievedUser) {
    Set<RoleEntity> roles = retrievedUser.getRoles();
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
    return authorities;
  }
}
