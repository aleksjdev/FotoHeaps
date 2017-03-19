package org.aleksjdev.fotoset.model;

/**
 * Перечисление для типов изображений относительно их размеров (small, medium, large...)
 */
public enum ImageType {

   SMALL("S"), X_SMALL("XS"), XX_SMALL("XXS"), XXX_SMALL("XXXS"),
   LARGE("L"), X_LARGE("XL"), XX_LARGE("XXL"), XXX_LARGE("XXXL"),
   MEDIUM("M"), ORIGINAL("orig");

    private String code;

    private ImageType(String code) {
        this.code = code;
    }

    public static ImageType getImageTypeByCode(String code) {
        for (ImageType imageType : ImageType.values()) {
            if (imageType.code.equals(code)) {
                return imageType;
            }
        }
        return null;
    }
}
