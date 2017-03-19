package org.aleksjdev.fotoset.linker;

import org.aleksjdev.fotoset.model.ImageAlbum;

import java.util.List;

/**
 * Вспомогательный класс для связывания альбомов и построения дерева альбомов
 */
public interface ImageAlbumLinker {

    /**
     * Связывание альбомов и построение дерева альбомов
     *
     * @param albums исходный список альбомов
     * @return связанный список альбомов (дерево альбомов)
     */
    List<ImageAlbum> linkImageAlbums(List<ImageAlbum> albums);
}
