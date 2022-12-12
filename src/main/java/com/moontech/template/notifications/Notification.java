package com.moontech.template.notifications;

import com.moontech.template.constants.ApiConstant;
import com.moontech.template.enums.EmailTemplate;
import freemarker.template.Configuration;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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
  /** Nombre del logo. */
  private static final String CHECK_NAME = "welcome.png";
  /** Nombre del logo. */
  private static final String LOGO_NAME = "logo.png";
  /** Icono de facebook. */
  private static final String FACEBOOK_NAME = "facebook.png";
  /** Icono de whatsapp. */
  private static final String WHATSAPP_NAME = "whatsapp.png";
  /** Icono de email. */
  private static final String EMAIL_NAME = "correo_2.png";
  /** Icono de email. */
  private static final String ARROW_ICON = "flecha.png";
  /** Icono de twitter. */
  private static final String TWITTER_ICON = "twitter.png";
  /** Icono de instagram. */
  private static final String INSTAGRAM_ICON = "instagram.png";
  /** Constante para pago. */
  private static final String PAYMENT = "PAYMENT";

  /**
   * Envía el correo.
   *
   * @param mail {@link Email}
   * @param templateName nombre de la plantilla
   */
  public void sendMail(final Email mail, EmailTemplate templateName) {
    MimeMessage mimeMessage = this.emailSender.createMimeMessage();
    try {
      String subject =
          templateName.name().equals(PAYMENT)
              ? this.mailConfiguration.getPayment()
              : this.mailConfiguration.getWelcome();
      log.info("Sending email by {}", subject);
      String template =
          this.mailConfiguration.getTemplates().get(templateName.name().toLowerCase());
      mail.getModel()
          .put(
              ApiConstant.MAIL_CONFIRM_MESSAGE_PROPERTY,
              this.mailConfiguration.getConfirmMessage());
      mail.getModel()
          .put(
              ApiConstant.MAIL_ENTERPRISE_NAME_PROPERTY,
              this.mailConfiguration.getEnterpriseName());
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
      mimeMessageHelper.setSubject(subject);
      mimeMessageHelper.setFrom(this.mailConfiguration.getMail());
      mimeMessageHelper.setTo(mail.getTo());
      mimeMessageHelper.setText(this.getContentFromTemplate(mail.getModel(), template), true);
      mimeMessageHelper.addInline(CHECK_NAME, this.mailConfiguration.getWelcomeImg());
      mimeMessageHelper.addInline(FACEBOOK_NAME, this.mailConfiguration.getFacebookIcon());
      mimeMessageHelper.addInline(EMAIL_NAME, this.mailConfiguration.getEmailIcon());
      mimeMessageHelper.addInline(ARROW_ICON, this.mailConfiguration.getArrowIcon());
      mimeMessageHelper.addInline(TWITTER_ICON, this.mailConfiguration.getTwitterIcon());
      mimeMessageHelper.addInline(INSTAGRAM_ICON, this.mailConfiguration.getInstagramIcon());

      this.emailSender.send(mimeMessageHelper.getMimeMessage());
    } catch (MessagingException e) {
      log.error(e.getMessage(), e);
    }
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
}
