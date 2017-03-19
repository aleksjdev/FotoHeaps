package org.aleksjdev.fotoset.parser.handler;

import org.aleksjdev.fotoset.model.Image;
import org.aleksjdev.fotoset.model.ImageFragment;
import org.aleksjdev.fotoset.model.ImageType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.aleksjdev.fotoset.util.ApplicationConstants.*;
import static org.aleksjdev.fotoset.util.ApplicationUtils.getNumericIdForEntry;

/**
 * Обработчик событий при разборе списка картинок из xml
 */
public class ImageContentHandler extends DefaultHandler {

    private List<Image> images = new LinkedList<>();
    private Image currentImage;
    private String currentTag;
    private Map<ImageType, ImageFragment> fragmentMap;

    public List<Image> getImages() {
        return images;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTag = qName;
        if (qName.equals(ENTRY_TAG_NAME)) {
            currentImage = new Image();
            fragmentMap = new HashMap<>();
            return;
        }
        if (currentImage != null) {
            processElementInsideImageEntry(uri, localName, qName, attributes);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(ENTRY_TAG_NAME)) {
            currentImage.setImageMap(fragmentMap);
            images.add(currentImage);

            currentImage = null;
            fragmentMap = null;
        }
        currentTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if ((currentImage != null)&&(currentTag != null)) {
            String value = new String(ch, start, length).trim();
            if (currentTag.equals(ID_TAG_NAME)) {
                currentImage.setId(getNumericIdForEntry(value));
            } else if (currentTag.equals(NAME_TAG)) {
                currentImage.setAuthor(value);
            } else if (currentTag.equals(TITLE_TAG_NAME)) {
                currentImage.setTitle(value);
            } else if (currentTag.equals(SUMMARY_TAG_NAME)) {
                currentImage.setDescription(value);
            }
        }
    }

    /**
     * Обработка элементов внутри тэгов <entry></entry>
     *
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     */
    private void processElementInsideImageEntry(String uri, String localName, String qName, Attributes attributes) {
        if (currentTag.equals(IMG_FRAGMENT_TAG_NAME)) {
            Integer height = Integer.parseInt(attributes.getValue(HEIGHT_ATTRIBUTE));
            Integer width = Integer.parseInt(attributes.getValue(WIDTH_ATTRIBUTE));
            String href = attributes.getValue(HREF_ATTRIBUTE);
            String size = attributes.getValue(SIZE_ATTRIBUTE);
            ImageFragment imageFragment = new ImageFragment(height, width, href);

            fragmentMap.put(ImageType.getImageTypeByCode(size), imageFragment);
        }
    }

}
