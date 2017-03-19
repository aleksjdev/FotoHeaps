package org.aleksjdev.fotoset.parser.impl;

import org.aleksjdev.fotoset.converter.XmlEntryElementConverter;
import org.aleksjdev.fotoset.model.Image;
import org.aleksjdev.fotoset.model.ImageAlbum;
import org.aleksjdev.fotoset.parser.ImageFeedParser;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.aleksjdev.fotoset.util.ApplicationConstants.ENTRY_TAG_NAME;
import static org.aleksjdev.fotoset.util.XmlProcessingUtil.buildXmlDocument;
import static org.aleksjdev.fotoset.util.XmlProcessingUtil.getElementCollectionFromDocument;

/**
 * Реализация интерфейса для разбора содержимого ответа от сервера Yandex.Fotki используя парсер DOM
 */
@Component("DOMImageFeedParser")
public class DOMImageFeedParser implements ImageFeedParser {

    private static final Logger LOGGER = Logger.getLogger(DOMImageFeedParser.class.getName());

    @Override
    public List<Image> parseImageFeed(InputStream stream) {
        List<Image> result;
        try {
            Document document = buildXmlDocument(stream);
            List<Element> entries = getElementCollectionFromDocument(document, ENTRY_TAG_NAME);
            result = new XmlEntryElementConverter().convertEntriesToImages(entries);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<ImageAlbum> parseAlbumFeed(InputStream stream) {
        List<ImageAlbum> result;
        try {
            Document document = buildXmlDocument(stream);
            List<Element> entries = getElementCollectionFromDocument(document, ENTRY_TAG_NAME);
            result = new XmlEntryElementConverter().convertEntriesToAlbums(entries);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return result;
    }


}
