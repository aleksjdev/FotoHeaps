package org.aleksjdev.fotoset.service.impl;

import org.aleksjdev.fotoset.linker.ImageAlbumLinker;
import org.aleksjdev.fotoset.model.Image;
import org.aleksjdev.fotoset.model.ImageAlbum;
import org.aleksjdev.fotoset.model.ServiceConnectionPreference;
import org.aleksjdev.fotoset.parser.ImageFeedParser;
import org.aleksjdev.fotoset.service.ImageAlbumProcessService;
import org.aleksjdev.fotoset.util.HttpRequestHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.aleksjdev.fotoset.util.ApplicationConstants.FAIL_ACCOUNT_MSG;
import static org.aleksjdev.fotoset.util.URLRequestBuilder.getAlbumsURLByUser;

/**
 * Реализация сервиса для работы с альбомами и изображениями в приложении
 */
@Service("imageAlbumProcessService")
public class ImageAlbumProcessServiceImpl implements ImageAlbumProcessService {

    private static final Logger LOGGER = Logger.getLogger(ImageAlbumProcessServiceImpl.class.getName());

    private ImageFeedParser parser;
    private ImageAlbumLinker linker;

    @Override
    public List<ImageAlbum> getAlbums(ServiceConnectionPreference preference) {
        List<ImageAlbum> albums;
        try {
            InputStream imageAlbumFeed = HttpRequestHandler.doUrlGet(getAlbumsURLByUser(preference.getUserName()));
            List<ImageAlbum> imageAlbums = parser.parseAlbumFeed(imageAlbumFeed);
            albums = linker.linkImageAlbums(imageAlbums);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(FAIL_ACCOUNT_MSG);
        }
        return albums;
    }

    @Override
    public ImageAlbum findImageAlbumById(String albumId, List<ImageAlbum> albums) {
        for (ImageAlbum album : albums) {
            if (album.getId().equals(albumId)) {
                return album;
            }
        }
        return null;
    }

    @Override
    public ImageAlbum putImagesInAlbum(ImageAlbum album) {
        String imagesUrl = album.getImagesUrl();
        try {
            if (imagesUrl != null) {
                InputStream imagesFeed = HttpRequestHandler.doUrlGet(imagesUrl);
                List<Image> images = parser.parseImageFeed(imagesFeed);
                album.setImages(images);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return album;
    }

    @Resource(name = "SAXImageFeedParser")
    public void setParser(ImageFeedParser parser) {
        this.parser = parser;
    }

    @Resource(name = "imageAlbumLinker")
    public void setLinker(ImageAlbumLinker linker) {
        this.linker = linker;
    }
}
