package org.aleksjdev.fotoset.converter;

import org.aleksjdev.fotoset.model.Image;
import org.aleksjdev.fotoset.model.ImageAlbum;
import org.aleksjdev.fotoset.model.ImageFragment;
import org.aleksjdev.fotoset.model.ImageType;
import org.w3c.dom.Element;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.aleksjdev.fotoset.util.ApplicationConstants.*;
import static org.aleksjdev.fotoset.util.ApplicationUtils.getNumericIdForEntry;
import static org.aleksjdev.fotoset.util.ApplicationUtils.getNumericIdFromHREF;
import static org.aleksjdev.fotoset.util.XmlProcessingUtil.*;

/**
 * Конвертер, который преобразует xml элемент в структуры, используемые в программе
 */
public class XmlEntryElementConverter {

    /**
     * Преобразуем коллекцию xml Element в коллекцию сущностей Image
     *
     * @param elements коллекция xml Element
     * @return заполненная коллекция сущностей Image
     */
    public List<Image> convertEntriesToImages(List<Element> elements) {
        List<Image> result = new LinkedList<>();
        for (Element element : elements) {
            result.add(convertImageEntry(element));
        }
        return result;
    }

    /**
     * Преобразуем коллекцию xml Element в коллекцию сущностей ImageAlbum
     *
     * @param elements коллекция xml Element
     * @return заполненная коллекция сущностей ImageAlbum
     */
    public List<ImageAlbum> convertEntriesToAlbums(List<Element> elements) {
        List<ImageAlbum> result = new LinkedList<>();
        for (Element element : elements) {
            ImageAlbum album = convertAlbumEntry(element);
            if (album != null) result.add(album);
        }
        return result;
    }

    private Image convertImageEntry(Element element) {
        Image image = new Image();
        image.setId(getNumericIdForEntry(getSingleElementValueByTagName(element, ID_TAG_NAME)));
        image.setTitle(getSingleElementValueByTagName(element, TITLE_TAG_NAME));
        image.setDescription(getSingleElementValueByTagName(element, SUMMARY_TAG_NAME));
        image.setImageMap(getImageFragmentMap(element));

        Element authorElement = getElementByTagName(element, AUTHOR_TAG_NAME);
        image.setAuthor(getSingleElementValueByTagName(authorElement, NAME_TAG));
        return image;
    }

    private ImageAlbum convertAlbumEntry(Element element) {
        // проверяем является ли альбом закрытым
        Boolean isAlbumProtected = Boolean.valueOf(getAttributeValueByName(getElementByTagName(element, PROTECTED_TAG_NAME), VALUE_ATTRIBUTE));
        if (isAlbumProtected) return null;

        ImageAlbum album = new ImageAlbum();
        album.setId(getNumericIdForEntry(getSingleElementValueByTagName(element, ID_TAG_NAME)));
        album.setTitle(getSingleElementValueByTagName(element, TITLE_TAG_NAME));
        album.setDescription(getSingleElementValueByTagName(element, SUMMARY_TAG_NAME));
        Element authorElement = getElementByTagName(element, AUTHOR_TAG_NAME);
        album.setAuthor(getSingleElementValueByTagName(authorElement, NAME_TAG));
        album.setImageMap(getImageFragmentMap(element));

        // получаем идентификатор родительского альбома
        Element parentAlbumElement = getElementByTagNameAndAttributeValue(element, LINK_TAG_NAME, REL_ATTRIBUTE, "album");
        if (parentAlbumElement != null) {
            album.setParentAlbumId(getNumericIdFromHREF(getAttributeValueByName(parentAlbumElement, HREF_ATTRIBUTE)));
        }

        // получаем количество фото в альбоме
        Integer imageCount = Integer.parseInt(getAttributeValueByName(getElementByTagName(element, IMG_COUNT_TAG_NAME), VALUE_ATTRIBUTE));
        if (imageCount > 0) {
            Element imagesUrlElement = getElementByTagNameAndAttributeValue(element, LINK_TAG_NAME, REL_ATTRIBUTE, "photos");
            album.setImagesUrl(imagesUrlElement != null ? getAttributeValueByName(imagesUrlElement, HREF_ATTRIBUTE) : null);
        }
        return album;
    }


    private Map<ImageType, ImageFragment> getImageFragmentMap(Element element) {
        Map<ImageType, ImageFragment> result = new LinkedHashMap<>();
        List<Element> fragments = getElementCollectionByTagName(element, IMG_FRAGMENT_TAG_NAME);
        for (Element fragment : fragments) {
            ImageType imageType = ImageType.getImageTypeByCode(getAttributeValueByName(fragment, SIZE_ATTRIBUTE));
            String href = getAttributeValueByName(fragment, HREF_ATTRIBUTE);
            Integer height = Integer.parseInt(getAttributeValueByName(fragment, HEIGHT_ATTRIBUTE));
            Integer width = Integer.parseInt(getAttributeValueByName(fragment, WIDTH_ATTRIBUTE));
            ImageFragment imageFragment = new ImageFragment(height, width, href);

            result.put(imageType, imageFragment);
        }
        return result;
    }

}
