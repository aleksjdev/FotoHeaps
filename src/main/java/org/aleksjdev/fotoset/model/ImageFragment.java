package org.aleksjdev.fotoset.model;

import java.io.Serializable;

/**
 * Модель для хранения параметров и URL изображений в зависимости от их размеров
 */
public class ImageFragment implements Serializable {

    public ImageFragment(Integer height, Integer width, String url) {
        this.height = height;
        this.width = width;
        this.url = url;
    }

    /**
     * Высота изображения
     */
    private Integer height;

    /**
     * Ширина изображения
     */
    private Integer width;

    /**
     * URL изображения
     */
    private String url;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
