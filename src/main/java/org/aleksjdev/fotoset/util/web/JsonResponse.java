package org.aleksjdev.fotoset.util.web;

import java.io.Serializable;

/**
 * Вспомогательный класс для передачи ответов от сервера на UI
 */
public class JsonResponse implements Serializable {

    /**
     * Статус ответа от сервера
     */
    private ResponseStatus status;

    /**
     * Сообщение
     */
    private String message;

    /**
     * Объект ответа
     */
    private Object responseObject;

    public JsonResponse(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public JsonResponse(ResponseStatus status, String message, Object responseObject) {
        this.status = status;
        this.message = message;
        this.responseObject = responseObject;
    }

    public JsonResponse() {
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }
}
