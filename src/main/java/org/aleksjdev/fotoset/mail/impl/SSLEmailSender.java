package org.aleksjdev.fotoset.mail.impl;

import org.aleksjdev.fotoset.mail.EmailSender;
import org.aleksjdev.fotoset.mail.EmailSenderContent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * Реализация отправки писем, используя SSL соединение
 */
@Service("sslEmailSender")
public class SSLEmailSender implements EmailSender {

    private static final String SUBJECT = "Feedback from FotoHeap";
    private static final String ADMIN_ACCOUNT = "aleksjdev@gmail.com";

    private List<String> recipients;

    @Override
    public void sendEmail(final EmailSenderContent emailSenderContent) {
        try {
            Properties properties = new Properties();
            Session session = Session.getDefaultInstance(properties, null);
            Message message = new MimeMessage(session);
            setRecipients(message, recipients);
            message.setFrom(new InternetAddress(ADMIN_ACCOUNT));
            message.setSubject(SUBJECT);
            message.setText(buildEmailContent(emailSenderContent));

            Transport.send(message);
        } catch (Exception err) {
            throw new RuntimeException(err.getMessage(), err);
        }
    }

    private void setRecipients(Message message, List<String> recipients) throws MessagingException {
        for (String recipient : recipients) {
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        }
    }

    private String buildEmailContent(EmailSenderContent content) {
        StringBuilder builder = new StringBuilder();
        builder.append("Отзыв от: " + content.getUserName());
        builder.append("\n\n");
        builder.append(content.getTextContent());
        builder.append("\n\n");
        builder.append("Мой email: " + content.getEmail());
        return builder.toString();
    }


    @Resource(name = "recipients")
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }
}
