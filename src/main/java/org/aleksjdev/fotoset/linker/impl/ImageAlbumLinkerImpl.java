package org.aleksjdev.fotoset.linker.impl;

import org.aleksjdev.fotoset.linker.ImageAlbumLinker;
import org.aleksjdev.fotoset.model.ImageAlbum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Реализация сервиса для связывания альбомов и построения дерева альбомов
 */
@Component("imageAlbumLinker")
public class ImageAlbumLinkerImpl implements ImageAlbumLinker {

    private static final Logger LOGGER = Logger.getLogger(ImageAlbumLinkerImpl.class.getName());

    @Override
    public List<ImageAlbum> linkImageAlbums(List<ImageAlbum> albums) {
        try {
            for (ImageAlbum album : albums) {
                if (album.getParentAlbumId() != null) {
                    linkAlbum(album, albums);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return albums;
    }

    private void linkAlbum(ImageAlbum childAlbum, List<ImageAlbum> albums) {
        String parentAlbumId = childAlbum.getParentAlbumId();
        for (ImageAlbum album : albums) {
            if (parentAlbumId.equals(album.getId())) {
                childAlbum.setParentAlbum(album);
                album.getAlbums().add(childAlbum);
            }
        }
    }
}
