package org.aleksjdev.fotoset.mail;

/**
 * Интерфейс для отправки писем в приложении
 */
public interface EmailSender {

    /**
     * Отправка письма
     *
     * @param emailSenderContent содержимое письма и данные отправителя
     */
    void sendEmail(EmailSenderContent emailSenderContent);

}
