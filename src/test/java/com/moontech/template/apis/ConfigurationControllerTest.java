package com.moontech.template.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moontech.template.configuration.MysqlBaseConfigurationTest;
import com.moontech.template.configuration.TestConstants;
import com.moontech.template.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

/**
 * test de las apis de configuraciones.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 104 sept., 2023
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser(roles = TestConstants.ROLE_ADMIN)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConfigurationControllerTest extends MysqlBaseConfigurationTest {
  @Autowired private MockMvc mockMvc;

  /** Servicio de usuarios. */
  @Autowired private UserService userService;

  /** Mapper. */
  @Autowired private ObjectMapper objectMapper;

  /** Ruta para configuraciones. */
  private static final String CONFIGURATION_BASE_PATH = "/configuration";

  @Test
  @DisplayName("GET /configuration/reCaptcha success")
  void retrieve_all() throws Exception {
    String response =
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.get(CONFIGURATION_BASE_PATH + "/reCaptcha")
                    .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andReturn()
            .getResponse()
            .getContentAsString();
    log.info("Configuración de captcha en test {}", response);
  }
}
