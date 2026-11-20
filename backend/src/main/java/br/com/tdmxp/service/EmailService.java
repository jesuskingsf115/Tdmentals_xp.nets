package br.com.tdmxp.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final String remetente;

    public EmailService(
            JavaMailSender mailSender,
            @Value("${MAIL_FROM:}") String remetente) {
        this.mailSender = mailSender;
        this.remetente = remetente;
    }

    public void enviarEmail(String destinatario, String assunto, String texto) {
        if (remetente == null || remetente.isBlank()) {
            throw new IllegalStateException("Variável MAIL_FROM não está configurada no ambiente.");
        }

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper emailHtmlTexto = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            emailHtmlTexto.setFrom(remetente, "TDMXP Alertas");
            emailHtmlTexto.setTo(destinatario.trim());
            emailHtmlTexto.setSubject(assunto);

            String html = """
                <html>
                  <body style="font-family: Arial, sans-serif; color: #333;">
                    <p>%s</p>
                    <hr style="border: none; border-top: 1px solid #eee;">
                    <small style="color: #777;">Enviado automaticamente pelo sistema TDMXP.</small>
                  </body>
                </html>
                """.formatted(texto);

            emailHtmlTexto.setText(texto, html);
            mailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Erro ao montar ou enviar e-mail", e);
        }
    }
}