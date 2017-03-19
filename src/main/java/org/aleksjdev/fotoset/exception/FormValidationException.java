package org.aleksjdev.fotoset.exception;

/**
 * Класс исключения, которое генерируется при неудачной валидации формы
 */
public class FormValidationException extends Exception {

    public FormValidationException(String message) {
        super(message);
    }

}
