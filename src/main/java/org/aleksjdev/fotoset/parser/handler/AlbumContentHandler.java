package org.aleksjdev.fotoset.parser.handler;

import org.aleksjdev.fotoset.model.ImageAlbum;
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
import static org.aleksjdev.fotoset.util.ApplicationUtils.getNumericIdFromHREF;

/**
 * Обработчик событий при разборе списка альбомов из xml
 */
public class AlbumContentHandler extends DefaultHandler {

    private List<ImageAlbum> albums = new LinkedList<>();
    private ImageAlbum currentAlbum;
    private String currentTag;
    private Map<ImageType, ImageFragment> fragmentMap;

    public List<ImageAlbum> getAlbums() {
        return albums;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTag = qName;
        if (qName.equals(ENTRY_TAG_NAME)) {
            currentAlbum = new ImageAlbum();
            fragmentMap = new HashMap<>();
            return;
        }
        if (currentAlbum != null) {
            processElementInsideAlbumEntry(uri, localName, qName, attributes);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(ENTRY_TAG_NAME)) {
            if (!currentAlbum.getIsProtected()) {
                currentAlbum.setImageMap(fragmentMap);
                albums.add(currentAlbum);
            }

            currentAlbum = null;
            fragmentMap = null;
        }
        currentTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if ((currentAlbum != null)&&(currentTag != null)) {
            String value = new String(ch, start, length).trim();
            if (currentTag.equals(ID_TAG_NAME)) {
                currentAlbum.setId(getNumericIdForEntry(value));
            } else if (currentTag.equals(NAME_TAG)) {
                currentAlbum.setAuthor(value);
            } else if (currentTag.equals(TITLE_TAG_NAME)) {
                currentAlbum.setTitle(value);
            } else if (currentTag.equals(SUMMARY_TAG_NAME)) {
                currentAlbum.setDescription(value);
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
    private void processElementInsideAlbumEntry(String uri, String localName, String qName, Attributes attributes) {
        if (currentTag.equals(IMG_FRAGMENT_TAG_NAME)) {
            Integer height = Integer.parseInt(attributes.getValue(HEIGHT_ATTRIBUTE));
            Integer width = Integer.parseInt(attributes.getValue(WIDTH_ATTRIBUTE));
            String href = attributes.getValue(HREF_ATTRIBUTE);
            String size = attributes.getValue(SIZE_ATTRIBUTE);
            ImageFragment imageFragment = new ImageFragment(height, width, href);

            fragmentMap.put(ImageType.getImageTypeByCode(size), imageFragment);
        } else if (currentTag.equals(LINK_TAG_NAME)) {
            String relAttribute = attributes.getValue(REL_ATTRIBUTE);
            if (relAttribute.equals("album")) {
                String albumHREF = attributes.getValue(HREF_ATTRIBUTE);
                currentAlbum.setParentAlbumId(getNumericIdFromHREF(albumHREF));
            }
            if (relAttribute.equals("photos")) {
                String imagesHREF = attributes.getValue(HREF_ATTRIBUTE);
                currentAlbum.setImagesUrl(imagesHREF);
            }
        } else if (currentTag.equals(PROTECTED_TAG_NAME)) {
            currentAlbum.setIsProtected(Boolean.valueOf(attributes.getValue(VALUE_ATTRIBUTE)));
        } else if (currentTag.equals(IMG_COUNT_TAG_NAME)) {
            int imageCount = Integer.parseInt(attributes.getValue(VALUE_ATTRIBUTE));
            if (imageCount == 0) currentAlbum.setImagesUrl(null);
        }

    }

}
