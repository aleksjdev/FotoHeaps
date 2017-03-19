package org.aleksjdev.fotoset.model;

import java.io.Serializable;

/**
 * Бин для хранения настроек для получения данных из внешних сервисов
 */
public class ServiceConnectionPreference implements Serializable {

    /**
     * Имя пользователя
     */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
