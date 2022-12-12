package com.moontech.template.configs;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Configuración para el correo.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 10/12/22
 */
@Configuration
@PropertySource(value = {"classpath:application.yml"})
public class MailConfiguration {
  @Value("${spring.mail.server.protocol}")
  private String mailServerProtocol;

  @Value("${spring.mail.host}")
  private String mailServerHost;

  @Value("${spring.mail.server.debug}")
  private String mailServerDebug;

  @Value("${spring.mail.port}")
  private Integer mailServerPort;

  @Value("${spring.mail.username}")
  private String mailServerUsername;

  @Value("${spring.mail.password}")
  private String mailServerPassword;

  @Value("${spring.mail.properties.mail.smtp.auth}")
  private String mailServerAuth;

  @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
  private String mailServerStartTls;

  /**
   * Configuración de java mail.
   *
   * @return {@code JavaMailSender}
   */
  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    mailSender.setHost(mailServerHost);
    mailSender.setPort(mailServerPort);

    mailSender.setUsername(mailServerUsername);
    mailSender.setPassword(mailServerPassword);

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", this.mailServerProtocol);
    props.put("mail.smtp.auth", mailServerAuth);
    props.put("mail.smtp.starttls.enable", mailServerStartTls);
    props.put("mail.debug", this.mailServerDebug);

    return mailSender;
  }
}
