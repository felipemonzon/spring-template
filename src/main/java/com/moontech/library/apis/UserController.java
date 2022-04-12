package com.moontech.library.apis;

import com.moontech.library.constants.RoutesConstant;
import com.moontech.library.models.responses.UserResponse;
import com.moontech.library.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APIS para usuarios.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 01 Ene, 2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = RoutesConstant.USERS_BASE_PATH)
public class UserController {
  /** Servicio de usuarios. */
  private final UserService userService;

  @GetMapping(path = RoutesConstant.DATA_RETRIEVE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserResponse> retrieve(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(this.userService.retrieve(pageable));
  }
}
