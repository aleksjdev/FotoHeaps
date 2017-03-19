package org.aleksjdev.fotoset.mail;

import java.io.Serializable;

/**
 * Бин, который хранит содержимое письма и данные отправителя
 */
public class EmailSenderContent implements Serializable {

    private String userName;

    private String email;

    private String textContent;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
