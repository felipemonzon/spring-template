package com.moontech.library.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moontech.library.business.LoginBusiness;
import com.moontech.library.exceptions.custom.ErrorResponse;
import com.moontech.library.exceptions.management.ExceptionManagement;
import com.moontech.library.constants.ApiConstant;
import com.moontech.library.constants.ErrorConstant;
import com.moontech.library.constants.LogConstant;
import com.moontech.library.properties.SecurityProperties;
import com.moontech.library.security.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Filtro de autorización del servicio.
 *
 * @author Felipe Monzón
 * @since Jan 05. 2022
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {
  /** Propiedades de seguridad {@code SecurityProperties} . */
  @Autowired private SecurityProperties securityProperties;
  /** Servicio de usuarios. */
  @Autowired private LoginBusiness loginBusiness;

  /**
   * Filtro que válida si la cabecera "Authorization" está presente y si el token provista esta
   * activo y es válido.
   *
   * @param httpServletRequest {@code HttpServletRequest}
   * @param httpServletResponse {@code HttpServletResponse}
   * @param filterChain {@code FilterChain}
   * @throws ServletException excepción {@code ServletException}
   * @throws IOException excepción {@code IOException}
   */
  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {
    String authorizationHeader =
        Objects.isNull(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION))
            ? StringUtils.EMPTY
            : httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

    if (!authorizationHeader.startsWith(SecurityConstants.TOKEN_BEARER_PREFIX)) {
      logger.debug(LogConstant.INVALID_PREFIX_TOKEN);
      httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
      httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
      httpServletResponse
          .getWriter()
          .print(
              new ObjectMapper()
                  .writeValueAsString(
                      ErrorResponse.builder()
                          .type(ExceptionManagement.ErrorType.INVALID.name())
                          .code(ErrorConstant.INVALID_TOKEN_CODE)
                          .message(ErrorConstant.INVALID_TOKEN_MESSAGE)
                          .uuid(httpServletRequest.getHeader(ApiConstant.HEADER_UUID))
                          .build()));
      filterChain.doFilter(httpServletRequest, httpServletResponse);
      return;
    }

    final String token =
        authorizationHeader.replace(
            SecurityConstants.TOKEN_BEARER_PREFIX + ApiConstant.WHITE_SPACE, StringUtils.EMPTY);

    String userName = this.getUserName(token);
    UserDetails user = this.loginBusiness.loadUserByUsername(userName);

    UsernamePasswordAuthenticationToken authenticationToken = this.getAuthentication(token, user);
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  /***
   * Obtiene los datos de autenticación.
   *
   * @param token token recibido en la petición
   * @param userDetails {@link UserDetails}
   * @return {@link UsernamePasswordAuthenticationToken}
   */
  private UsernamePasswordAuthenticationToken getAuthentication(
      final String token, final UserDetails userDetails) {
    final JwtParser jwtParser = Jwts.parser().setSigningKey(this.securityProperties.getJwtKey());
    final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
    final Claims claims = claimsJws.getBody();
    final Collection<SimpleGrantedAuthority> authorities =
        Arrays.stream(
                claims.get(SecurityConstants.AUTHORITIES_KEY).toString().split(ApiConstant.COMMA))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    return new UsernamePasswordAuthenticationToken(userDetails, StringUtils.EMPTY, authorities);
  }

  /**
   * Obtiene el usuario del token.
   *
   * @param token token recibido en la petición
   * @return usuario
   */
  private String getUserName(final String token) {
    final JwtParser jwtParser = Jwts.parser().setSigningKey(this.securityProperties.getJwtKey());
    final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
    return claimsJws.getBody().getSubject();
  }
}
