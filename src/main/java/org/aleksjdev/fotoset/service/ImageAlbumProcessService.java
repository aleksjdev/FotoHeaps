package org.aleksjdev.fotoset.service;

import org.aleksjdev.fotoset.model.ImageAlbum;
import org.aleksjdev.fotoset.model.ServiceConnectionPreference;

import java.util.List;

/**
 * Сервис для работы с альбомами и изображениями в приложении
 */
public interface ImageAlbumProcessService {

    /**
     * Получение списка альбомов изображений из внешнего сервиса
     *
     * @param preference настройки получения альбомов
     * @return список альбомов
     */
    List<ImageAlbum> getAlbums(ServiceConnectionPreference preference);

    /**
     * Поиск альбома в списке альбомов по его идентификатору
     *
     * @param albumId идентификатор альбома
     * @param albums список альбомов
     * @return альбом
     */
    ImageAlbum findImageAlbumById(String albumId, List<ImageAlbum> albums);

    /**
     * Заполнение альбома фотками
     *
     * @param album альбом
     * @return заполненный альбом
     */
    ImageAlbum putImagesInAlbum(ImageAlbum album);
}
