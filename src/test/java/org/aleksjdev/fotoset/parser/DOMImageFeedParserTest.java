package org.aleksjdev.fotoset.parser;

import org.aleksjdev.fotoset.model.Image;
import org.aleksjdev.fotoset.model.ImageAlbum;
import org.aleksjdev.fotoset.model.ImageFragment;
import org.aleksjdev.fotoset.model.ImageType;
import org.aleksjdev.fotoset.parser.impl.DOMImageFeedParser;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Тестирования парсера для разбора содержимого ответа от сервера Yandex.Fotki
 */
public class DOMImageFeedParserTest {

    private ImageFeedParser parser;

    @Before
    public void setUp() {
        parser = new DOMImageFeedParser();
    }

    @Test
    public void testParseImageFeed() {
        InputStream stream = this.getClass().getResourceAsStream("/parser/image_feed.xml");
        List<Image> images = parser.parseImageFeed(stream);
        Image image = images.get(0);
        Map<ImageType, ImageFragment> imageFragmentMap = image.getImageMap();

        assertNotNull(images);
        assertEquals(3, images.size());
        assertEquals("1063760", image.getId());
        assertEquals("aleksjdev", image.getAuthor());
        assertEquals(10, imageFragmentMap.size());
        assertEquals(500, imageFragmentMap.get(ImageType.LARGE).getWidth().intValue());
        assertEquals(375, imageFragmentMap.get(ImageType.LARGE).getHeight().intValue());
        assertEquals("http://img-fotki.yandex.ru/get/3911/325161172.0/0_103b50_19c65223_L", imageFragmentMap.get(ImageType.LARGE).getUrl());

    }

    @Test
    public void testParseAlbumFeed() {
        InputStream stream = this.getClass().getResourceAsStream("/parser/album_feed.xml");
        List<ImageAlbum> albums = parser.parseAlbumFeed(stream);
        ImageAlbum album = albums.get(0);
        Map<ImageType, ImageFragment> imageFragmentMap = album.getImageMap();

        assertNotNull(albums);
        assertEquals(5, albums.size());
        assertEquals("1158819", album.getId());
        assertEquals("Альбом 1", album.getTitle());
        assertEquals("Классный альбом", album.getDescription());
        assertEquals("aleksjdev", album.getAuthor());
        assertEquals("http://api-fotki.yandex.ru/api/users/aleksjdev/album/1158819/photos/", album.getImagesUrl());

        assertEquals(2, imageFragmentMap.size());
        assertEquals(113, imageFragmentMap.get(ImageType.SMALL).getWidth().intValue());
        assertEquals(150, imageFragmentMap.get(ImageType.SMALL).getHeight().intValue());
        assertEquals("http://img-fotki.yandex.ru/get/10138/325161172.0/0_104814_ea7eab2a_S", imageFragmentMap.get(ImageType.SMALL).getUrl());
    }
}
