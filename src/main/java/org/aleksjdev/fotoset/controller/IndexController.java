package org.aleksjdev.fotoset.controller;

import org.aleksjdev.fotoset.model.ImageAlbum;
import org.aleksjdev.fotoset.model.ServiceConnectionPreference;
import org.aleksjdev.fotoset.service.ImageAlbumProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.aleksjdev.fotoset.util.ApplicationConstants.ALBUMS_MODEL_ATTRIBUTE;
import static org.aleksjdev.fotoset.util.ApplicationConstants.USER_NAME_ATTRIBUTE;

/**
 * Контроллер, который переадресовывает пользователя на index.jsp при старте приложения
 *
 * @author aleksjdev
 */
@Controller
public class IndexController {

    private ServiceConnectionPreference defaultConnectionPreference;
    private ImageAlbumProcessService albumProcessService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) throws Exception {
        Object albumsAttribute = session.getAttribute(ALBUMS_MODEL_ATTRIBUTE);
        String userName = session.getAttribute(USER_NAME_ATTRIBUTE) != null ?
                          session.getAttribute(USER_NAME_ATTRIBUTE).toString() :
                          defaultConnectionPreference.getUserName();
        List<ImageAlbum> albums;
        if (albumsAttribute != null) {
            albums = (List<ImageAlbum>) albumsAttribute;
        } else {
            albums = albumProcessService.getAlbums(defaultConnectionPreference);
            session.setAttribute(ALBUMS_MODEL_ATTRIBUTE, albums);
            session.setAttribute(USER_NAME_ATTRIBUTE, userName);
        }

        model.addAttribute(ALBUMS_MODEL_ATTRIBUTE, albums);
        model.addAttribute(USER_NAME_ATTRIBUTE, userName);
        return "index";
    }

    @Resource(name = "imageAlbumProcessService")
    public void setAlbumProcessService(ImageAlbumProcessService albumProcessService) {
        this.albumProcessService = albumProcessService;
    }

    @Autowired
    public void setDefaultConnectionPreference(ServiceConnectionPreference defaultConnectionPreference) {
        this.defaultConnectionPreference = defaultConnectionPreference;
    }
}
