package org.aleksjdev.fotoset.parser.impl;

import org.aleksjdev.fotoset.model.Image;
import org.aleksjdev.fotoset.model.ImageAlbum;
import org.aleksjdev.fotoset.parser.ImageFeedParser;
import org.aleksjdev.fotoset.parser.handler.AlbumContentHandler;
import org.aleksjdev.fotoset.parser.handler.ImageContentHandler;
import org.springframework.stereotype.Component;

import javax.xml.parsers.SAXParser;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.aleksjdev.fotoset.util.XmlProcessingUtil.createSAXParser;

/**
 * Реализация интерфейса для разбора содержимого ответа от сервера Yandex.Fotki используя SAX парсер
 */
@Component("SAXImageFeedParser")
public class SAXImageFeedParser implements ImageFeedParser {

    private static final Logger LOGGER = Logger.getLogger(SAXImageFeedParser.class.getName());

    @Override
    public List<Image> parseImageFeed(InputStream stream) {
        List<Image> result;
        try {
            SAXParser parser = createSAXParser();
            ImageContentHandler handler = new ImageContentHandler();
            parser.parse(stream, handler);
            result = handler.getImages();
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
            SAXParser parser = createSAXParser();
            AlbumContentHandler handler = new AlbumContentHandler();
            parser.parse(stream, handler);
            result = handler.getAlbums();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return result;
    }
}
