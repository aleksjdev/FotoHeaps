package org.aleksjdev.fotoset.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Модель для представления альбома изображений в приложении
 */
public class ImageAlbum implements Serializable {

    /**
     * Идентификатор альбома
     */
    private String id;

    /**
     * Автор
     */
    private String author;

    /**
     * Название альбома
     */
    private String title;

    /**
     * Описание альбома
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
     * Является ли альбом закрытым
     */
    private Boolean isProtected;

    /**
     * Карта уменьшенных фрагментов изображений альбома
     */
    private Map<ImageType, ImageFragment> imageMap;

    /**
     * Ссылка на картинки, содержащиеся внутри альбома
     */
    private String imagesUrl;

    /**
     * Список изображений
     */
    private List<Image> images;

    /**
     * Идентификатор родительского альбома
     */
    private String parentAlbumId;

    /**
     * Ссылка на родительский альбом
     */
    private ImageAlbum parentAlbum;

    /**
     * Список дочерних альбомов
     */
    private List<ImageAlbum> albums;

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

    public Boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(Boolean isProtected) {
        this.isProtected = isProtected;
    }

    public Map<ImageType, ImageFragment> getImageMap() {
        return imageMap;
    }

    public void setImageMap(Map<ImageType, ImageFragment> imageMap) {
        this.imageMap = imageMap;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public List<Image> getImages() {
        if (images == null) {
            images = new LinkedList<Image>();
        }
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getParentAlbumId() {
        return parentAlbumId;
    }

    public void setParentAlbumId(String parentAlbumId) {
        this.parentAlbumId = parentAlbumId;
    }

    public ImageAlbum getParentAlbum() {
        return parentAlbum;
    }

    public void setParentAlbum(ImageAlbum parentAlbum) {
        this.parentAlbum = parentAlbum;
    }

    public List<ImageAlbum> getAlbums() {
        if (albums == null) {
            albums = new LinkedList<ImageAlbum>();
        }
        return albums;
    }

    public void setAlbums(List<ImageAlbum> albums) {
        this.albums = albums;
    }
}
