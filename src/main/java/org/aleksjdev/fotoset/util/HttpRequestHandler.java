package org.aleksjdev.fotoset.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Вспомогательный класс для выполнения http-запросов
 */
public class HttpRequestHandler {

    private static final int URL_CONNECTION_READ_TIMEOUT = 60000;

    /**
     * Отправка GET-запроса без использования сокетов
     *
     * @param url url
     * @return отклик сервера в виде входящего потока
     */
    public static InputStream doUrlGet(String url) throws IOException {
        URL urlInstance = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlInstance.openConnection();
        conn.setConnectTimeout(URL_CONNECTION_READ_TIMEOUT);
        conn.setReadTimeout(URL_CONNECTION_READ_TIMEOUT);
        return conn.getInputStream();
    }

}
