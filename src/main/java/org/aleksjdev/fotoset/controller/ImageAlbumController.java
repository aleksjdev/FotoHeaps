package org.aleksjdev.fotoset.controller;

import org.aleksjdev.fotoset.model.ImageAlbum;
import org.aleksjdev.fotoset.model.ServiceConnectionPreference;
import org.aleksjdev.fotoset.service.ImageAlbumProcessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.aleksjdev.fotoset.util.ApplicationConstants.*;

/**
 * Контроллер для работы с альбомами изображений в приложении
 */
@Controller
public class ImageAlbumController {

    private ImageAlbumProcessService albumProcessService;

    @RequestMapping(value = "/{userName}/album/{albumId}", method = RequestMethod.GET)
    public String showAlbum(@PathVariable String userName,
                            @PathVariable String albumId,
                            Model model,
                            HttpSession session) throws Exception {
        Object albumsAttribute = session.getAttribute(ALBUMS_MODEL_ATTRIBUTE);
        String currentUserName = session.getAttribute(USER_NAME_ATTRIBUTE) != null ?
                session.getAttribute(USER_NAME_ATTRIBUTE).toString() : null;
        List<ImageAlbum> albums;
        if (albumsAttribute != null && userName.equals(currentUserName)) {
            albums = (List<ImageAlbum>) albumsAttribute;
        } else {
            ServiceConnectionPreference connectionPreference = new ServiceConnectionPreference();
            connectionPreference.setUserName(userName);

            albums = albumProcessService.getAlbums(connectionPreference);
        }
        ImageAlbum album = albumProcessService.findImageAlbumById(albumId, albums);
        model.addAttribute(ALBUM_MODEL_ATTRIBUTE, albumProcessService.putImagesInAlbum(album));
        model.addAttribute(USER_NAME_ATTRIBUTE, userName);
        return "album";
    }

    @Resource(name = "imageAlbumProcessService")
    public void setAlbumProcessService(ImageAlbumProcessService albumProcessService) {
        this.albumProcessService = albumProcessService;
    }
}
