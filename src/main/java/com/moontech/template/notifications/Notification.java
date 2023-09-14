package com.moontech.template.notifications;

import com.moontech.template.constants.ApiConstant;
import com.moontech.template.enums.EmailTemplate;
import freemarker.template.Configuration;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 * Configuración y envío de correo.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since Dec 01, 2022
 */
@Slf4j
@Service
public class Notification {
  /** Envío de correo. */
  @Autowired private JavaMailSender emailSender;

  /** Configuración. */
  @Autowired private Configuration fmConfiguration;

  /** Propiedades del correo. */
  @Autowired private MailConfiguration mailConfiguration;

  /** Nombre del logo para el registro de usuario. */
  private static final String REGISTER_USER = "welcome.png";

  /** Constante para pago. */
  private static final String PAYMENT = "PAYMENT";

  /** Constante para olvidar contraseña. */
  private static final String FORGOT_PASSWORD = "FORGOTPASSWORD";

  /** Icono de facebook. */
  private static final String FACEBOOK2_NAME = "facebook-rounded-gray.png";

  /** Icono para olvidar contraseña. */
  private static final String FORGOT_PASSWORD_ICON = "forgot_password.png";

  /** Icono de instagram. */
  private static final String INSTAGRAM2_ICON = "instagram-rounded-gray.png";

  /** Icono de linkedin. */
  private static final String LINKEDIN_ICON = "linkedin-rounded-gray.png";

  /** Icono de twitter. */
  private static final String TWITTER2_ICON = "twitter-rounded-gray.png";

  /**
   * Envía el correo.
   *
   * @param mail {@link Email}
   * @param templateName nombre de la plantilla
   */
  public void sendMail(final Email mail, EmailTemplate templateName) {
    MimeMessage mimeMessage = this.emailSender.createMimeMessage();
    try {
      String subject = this.getSubject(templateName);
      log.info("Sending email by {}", templateName.name());
      String template =
          this.mailConfiguration.getTemplates().get(templateName.name().toLowerCase());
      mail.getModel()
          .put(
              ApiConstant.MAIL_ENTERPRISE_NAME_PROPERTY,
              this.mailConfiguration.getEnterpriseName());
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
      mimeMessageHelper.setSubject(subject);
      mimeMessageHelper.setFrom(this.mailConfiguration.getMail());
      mimeMessageHelper.setTo(mail.getTo());
      mimeMessageHelper.setText(this.getContentFromTemplate(mail.getModel(), template), true);

      if (templateName.name().equals(FORGOT_PASSWORD)) {
        mimeMessageHelper.addInline(
            FORGOT_PASSWORD_ICON, this.mailConfiguration.getForgotPassword());
      } else {
        mimeMessageHelper.addInline(REGISTER_USER, this.mailConfiguration.getWelcomeImg());
      }

      this.getIcons(mimeMessageHelper);

      this.emailSender.send(mimeMessageHelper.getMimeMessage());
    } catch (MessagingException e) {
      log.error(e.getMessage(), e);
    }
  }

  /**
   * Obtiene los iconos para el envío del correo.
   *
   * @param mimeMessageHelper {@code MimeMessageHelper}
   */
  private void getIcons(MimeMessageHelper mimeMessageHelper) throws MessagingException {
    mimeMessageHelper.addInline(FACEBOOK2_NAME, this.mailConfiguration.getFacebookIcon());
    mimeMessageHelper.addInline(LINKEDIN_ICON, this.mailConfiguration.getLinkedinIcon());
    mimeMessageHelper.addInline(TWITTER2_ICON, this.mailConfiguration.getTwitterIcon());
    mimeMessageHelper.addInline(INSTAGRAM2_ICON, this.mailConfiguration.getInstagramIcon());
  }

  /**
   * Obtiene la plantilla y le envía su contenido.
   *
   * @param model datos a enviar en la plantilla
   * @param template plantilla de correo
   * @return la plantilla preparada para enviarse.
   */
  private String getContentFromTemplate(Map<String, String> model, String template) {
    StringBuilder content = new StringBuilder();
    try {
      content.append(
          FreeMarkerTemplateUtils.processTemplateIntoString(
              this.fmConfiguration.getTemplate(template), model));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return content.toString();
  }

  /**
   * Obtiene el asunto del correo.
   *
   * @param templateName nombre del template
   * @return asunto del correo.
   */
  private String getSubject(EmailTemplate templateName) {
    return switch (templateName.name()) {
      case PAYMENT -> this.mailConfiguration.getPayment();
      case FORGOT_PASSWORD -> this.mailConfiguration.getResetPassword();
      default -> this.mailConfiguration.getWelcome();
    };
  }
}
