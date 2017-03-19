package org.aleksjdev.fotoset.util;

/**
 * Вспомогательный компонент для построения URL запросов в приложении
 */
public class URLRequestBuilder {

    private static final String HOST_NAME = "http://api-fotki.yandex.ru";

    private static final String API_PREFIX = "/api/users/";

    public static String getAlbumsURLByUser(String user) {
        return HOST_NAME + API_PREFIX + user + "/albums/";
    }

    public static String getImagesURLByUserAndAlbumId(String user, String albumId) {
        return HOST_NAME + API_PREFIX + user + "/album/" + albumId + "/photos/";
    }
}
