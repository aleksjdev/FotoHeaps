package org.aleksjdev.fotoset.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Модель для представления изображения в приложении
 */
public class Image implements Serializable {

    /**
     * Идентификатор изображения
     */
    private String id;

    /**
     * Автор изображения
     */
    private String author;

    /**
     * Название изображения
     */
    private String title;

    /**
     * Описание
     */
    private String description;

    /**
     * Дата публикации
     */
    private Date publishedDate;

    /**
     * Дата публикации
     */
    private Date updateDate;

    /**
     * Ссылка на альбом
     */
    private ImageAlbum album;

    /**
     * Карта фрагментов изображений в зависимости от их типов
     */
    private Map<ImageType, ImageFragment> imageMap;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public ImageAlbum getAlbum() {
        return album;
    }

    public void setAlbum(ImageAlbum album) {
        this.album = album;
    }

    public Map<ImageType, ImageFragment> getImageMap() {
        return imageMap;
    }

    public void setImageMap(Map<ImageType, ImageFragment> imageMap) {
        this.imageMap = imageMap;
    }
}
