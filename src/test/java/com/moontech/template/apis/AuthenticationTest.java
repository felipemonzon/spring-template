package com.moontech.template.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moontech.template.configuration.MysqlBaseConfigurationTest;
import com.moontech.template.configuration.TestConstants;
import com.moontech.template.models.requests.AuthorizationRequest;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Test de login.
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
class AuthenticationTest extends MysqlBaseConfigurationTest {
  @Autowired private MockMvc mockMvc;
  /** Mapper. */
  @Autowired private ObjectMapper objectMapper;
  /** Ruta para login. */
  private static final String AUTHENTICATION_BASE_PATH = "/users/authentication";

  @Test
  @DisplayName("POST /login success")
  void login_success() throws Exception {
    String response =
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(AUTHENTICATION_BASE_PATH)
                    .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                    .content(
                        this.objectMapper.writeValueAsString(
                            this.getAuthorizationRequest("felipemonzon2705", "123456"))))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
    log.info("Inicio de sesión exitoso {}", response);
  }

  @Test
  @DisplayName("POST /login bad credentials")
  void login_with_error() throws Exception {
    String response =
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(AUTHENTICATION_BASE_PATH)
                    .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                    .content(
                        this.objectMapper.writeValueAsString(
                            this.getAuthorizationRequest("felipemonzon2705", "bad"))))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andReturn()
            .getResponse()
            .getContentAsString();
    log.info("wrong credentials {}", response);
  }

  @Test
  @DisplayName("POST /login user not exists")
  void login_with_user_not_exists() throws Exception {
    String response =
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(AUTHENTICATION_BASE_PATH)
                    .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                    .content(
                        this.objectMapper.writeValueAsString(
                            this.getAuthorizationRequest("juanhermosos23", "bad"))))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andReturn()
            .getResponse()
            .getContentAsString();
    log.info("wrong credentials {}", response);
  }

  private AuthorizationRequest getAuthorizationRequest(String username, String password) {
    AuthorizationRequest response = new AuthorizationRequest();
    response.setUsername(username);
    response.setPassword(password);
    return response;
  }
}
