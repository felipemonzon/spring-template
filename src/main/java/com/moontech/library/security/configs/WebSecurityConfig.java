package com.moontech.library.security.configs;

import com.moontech.library.properties.SecurityProperties;
import com.moontech.library.security.filters.JwtAuthorizationFilter;
import com.moontech.library.security.filters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Felipe Monzón
 * @since 11 ene., 2022
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  /** Propiedades de seguridad {@code SecurityProperties}. */
  private final SecurityProperties securityProperties;
  /** {@code UserDetailsService} */
  private final UserDetailsService userDetailsService;
  /** {@code PasswordEncoder} */
  private final BCryptPasswordEncoder passwordEncoder;
  /** Constante para *. */
  private static final String ASTERISK = "*";

  /**
   * Constructor.
   *
   * @param userDetailsService {@code UserDetailsService}
   * @param passwordEncoder {@code PasswordEncoder}
   */
  public WebSecurityConfig(
      UserDetailsService userDetailsService,
      BCryptPasswordEncoder passwordEncoder,
      SecurityProperties securityProperties) {
    this.userDetailsService = userDetailsService;
    this.securityProperties = securityProperties;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Administrator de autenticación.
   *
   * @return {@code AuthenticationManager}
   * @throws Exception excepción
   */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * Inicia el filtro para la autorización basada de token.
   *
   * @return filtro del token
   */
  @Bean
  public JwtAuthorizationFilter jwtAuthorizationFilterBean() {
    return new JwtAuthorizationFilter();
  }

  /**
   * Configuración de la clase que recupera los usuarios y algoritmo para procesar las contraseñas.
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(this.passwordEncoder);
  }

  /**
   * Configuración de spring security.
   *
   * @param httpSecurity {@code HttpSecurity}
   * @throws Exception excepción
   */
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .cors()
        .and()
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/v2/**", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .addFilterBefore(
            new JwtAuthenticationFilter(
                this.authenticationManager(),
                this.securityProperties.getJwtKey(),
                this.securityProperties.getJwtLifeTime(),
                new AntPathRequestMatcher(
                    this.securityProperties.getUserAuthenticationPath(), HttpMethod.POST.name())),
            UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(
            this.jwtAuthorizationFilterBean(), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
        .antMatchers("/swagger-ui/index.html")
        .antMatchers(
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/**",
            "/swagger-ui.html",
            "/webjars/**");
  }

  /**
   * Resuelve variables de spring placeholder.
   *
   * @return {@code PropertySourcesPlaceholderConfigurer}
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  /**
   * Used by spring security if CORS is enabled.
   *
   * @return {@code CorsFilter}
   */
  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.addAllowedOrigin(ASTERISK);
    config.addAllowedHeader(ASTERISK);
    config.setAllowedMethods(
        Arrays.asList(
            HttpMethod.POST.name(),
            HttpMethod.GET.name(),
            HttpMethod.PUT.name(),
            HttpMethod.DELETE.name()));
    config.setExposedHeaders(Collections.singletonList(ASTERISK));
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}
