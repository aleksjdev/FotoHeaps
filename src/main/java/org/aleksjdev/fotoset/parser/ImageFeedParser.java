package org.aleksjdev.fotoset.parser;

import org.aleksjdev.fotoset.model.Image;
import org.aleksjdev.fotoset.model.ImageAlbum;

import java.io.InputStream;
import java.util.List;

/**
 * Интерфейс для разбора содержимого ответа от сервера Yandex.Fotki
 */
public interface ImageFeedParser {

    /**
     * Получение списка изображений из ответа сервера
     *
     * @param stream ответ от сервера (входной поток)
     * @return список изображений
     */
    List<Image> parseImageFeed(InputStream stream);

    /**
     * Получение списка альбомов изображений из ответа сервера
     *
     * @param stream ответ от сервера (входной поток)
     * @return список альбомов
     */
    List<ImageAlbum> parseAlbumFeed(InputStream stream);

}
