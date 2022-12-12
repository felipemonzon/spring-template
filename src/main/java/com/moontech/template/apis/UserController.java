package com.moontech.template.apis;

import com.moontech.template.constants.RoutesConstant;
import com.moontech.template.models.requests.UserRequest;
import com.moontech.template.models.responses.UserResponse;
import com.moontech.template.services.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * APIS para usuarios.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 01 Dic, 2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = RoutesConstant.USERS_BASE_PATH)
public class UserController {
  /** Servicio de usuarios. */
  private final UserService userService;

  /**
   * API para consultar usuarios por página.
   *
   * @param pageable objeto de paginación
   * @return lista de usuarios encontrados
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<UserResponse>> retrieve(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(this.userService.retrieve(pageable));
  }

  /**
   * Guarda un usuario.
   *
   * @param request datos del usuario
   * @return 201 si se registro con éxito
   */
  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> save(@RequestBody @Valid UserRequest request) {
    this.userService.save(request);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * Confirma el registro de un usuario.
   *
   * @param token token de seguridad
   * @return 204 si se confirmo con éxito el usuario.
   */
  @GetMapping(path = RoutesConstant.CONFIRM_TOKEN_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> confirm(@RequestParam("token") String token) {
    this.userService.confirm(token);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
