package com.moontech.template.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moontech.template.configuration.MysqlBaseConfigurationTest;
import com.moontech.template.configuration.TestConstants;
import com.moontech.template.enums.Genre;
import com.moontech.template.models.requests.ResetPasswordRequest;
import com.moontech.template.models.requests.UserRequest;
import com.moontech.template.services.UserService;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * test de las apis de usuarios.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 04 nov., 2022
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser(roles = TestConstants.ROLE_ADMIN)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest extends MysqlBaseConfigurationTest {
  @Autowired private MockMvc mockMvc;
  /** Servicio de usuarios. */
  @Autowired private UserService userService;
  /** Mapper. */
  @Autowired private ObjectMapper objectMapper;
  /** Ruta para usuarios. */
  private static final String USERS_BASE_PATH = "/users";
  /** Ruta para resetear la contraseña. */
  private static final String PASSWORD_RESET_BASE_PATH = "/password/reset";

  @Test
  @DisplayName("GET /users success")
  void retrieve_all() throws Exception {
    String response =
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.get(USERS_BASE_PATH)
                    .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andReturn()
            .getResponse()
            .getContentAsString();
    log.info("Usuarios encontrados en test {}", response);
  }

  @Test
  @Order(2)
  @DisplayName("POST /users success")
  void save_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(USERS_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    this.objectMapper.writeValueAsString(
                        this.getUserRequest("123456", "felipemonzon1010"))))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  @Order(3)
  @DisplayName("POST /users exists")
  void save_exists(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(USERS_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    this.objectMapper.writeValueAsString(
                        this.getUserRequest("123456", "felipemonzon2705"))))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  @Test
  @Order(4)
  @DisplayName("POST /users/confirm invalid token")
  void confirm_user_invalid(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    String confirmToken = "d005a5ad-0ebb-429f-9cbf-b864cbd6a3aa";
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(USERS_BASE_PATH + "/confirm?token=" + confirmToken)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .with(
                    SecurityMockMvcRequestPostProcessors.jwt()
                        .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  @Test
  @Order(5)
  @DisplayName("POST /users/confirm valid token")
  void confirm_valid_token(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    String token = "7b920dac-d5d2-4e6e-91f7-6c2f848e366d";
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(USERS_BASE_PATH + "/confirm?token=" + token)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  @Test
  @Order(6)
  @DisplayName("POST /users/confirm expired token")
  void confirm_expired_token(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    String token = "a7749c83-c1ed-4bf7-9f8f-a50105a526c5";
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(USERS_BASE_PATH + "/confirm?token=" + token)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  @Test
  @Order(7)
  @DisplayName("POST /users/password/reset success")
  void reset_password_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(USERS_BASE_PATH + PASSWORD_RESET_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    this.objectMapper.writeValueAsString(
                        this.getResetPasswordRequest("felipemonzon2705"))))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  @Test
  @Order(8)
  @DisplayName("POST /users/password/reset not exists user")
  void reset_password_not_exists_user(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(USERS_BASE_PATH + PASSWORD_RESET_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    this.objectMapper.writeValueAsString(
                        this.getResetPasswordRequest("usuarionoexistente"))))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  @Test
  @Order(9)
  @DisplayName("POST /users/password/reset bad request")
  void reset_password_bad_request(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(USERS_BASE_PATH + PASSWORD_RESET_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.getResetPasswordRequest(""))))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  @Order(8)
  @DisplayName("POST /users/password/reset method not allowed")
  void reset_password_method_not_allowed(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(USERS_BASE_PATH + PASSWORD_RESET_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    this.objectMapper.writeValueAsString(this.getResetPasswordRequest("felipe"))))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  private ResetPasswordRequest getResetPasswordRequest(String user) {
    ResetPasswordRequest request = new ResetPasswordRequest();
    request.setUsername(user);
    return request;
  }

  private UserRequest getUserRequest(String password, String username) {
    UserRequest request = new UserRequest();
    request.setId(3L);
    request.setCel("6671568899");
    request.setGenre(Genre.MALE);
    request.setEmail("felipemonzon@gmail.com");
    request.setFirstName("Felipe");
    request.setLastName("monzon");
    request.setUsername(username);
    request.setPassword(password);
    request.setReCaptcha("uriosla");
    Set<Long> profiles = new HashSet<>();
    profiles.add(1L);
    request.setProfiles(profiles);
    return request;
  }
}
