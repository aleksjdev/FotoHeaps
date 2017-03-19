package org.aleksjdev.fotoset.controller;

import org.aleksjdev.fotoset.exception.FormValidationException;
import org.aleksjdev.fotoset.mail.EmailSender;
import org.aleksjdev.fotoset.mail.EmailSenderContent;
import org.aleksjdev.fotoset.model.ImageAlbum;
import org.aleksjdev.fotoset.model.ServiceConnectionPreference;
import org.aleksjdev.fotoset.service.ImageAlbumProcessService;
import org.aleksjdev.fotoset.util.HttpRequestHandler;
import org.aleksjdev.fotoset.util.validation.Validator;
import org.aleksjdev.fotoset.util.web.JsonResponse;
import org.aleksjdev.fotoset.util.web.ResponseStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;

import static org.aleksjdev.fotoset.util.ApplicationConstants.*;
import static org.aleksjdev.fotoset.util.ApplicationUtils.*;

/**
 * Контроллер для работы с запросами пользователей
 *
 * @author aleksjdev
 */
@Controller
public class UserController {

    private EmailSender emailSender;
    private Validator validator;
    private ImageAlbumProcessService albumProcessService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        prepareLoginPage(model);
        return "login";
    }

    @RequestMapping(value = "/showUserAccount.do", method = RequestMethod.POST)
    public String showUserAccount(@ModelAttribute ServiceConnectionPreference connectionPreference,
                                  Model model,
                                  HttpSession session) {
        try {
            List<ImageAlbum> albums = albumProcessService.getAlbums(validateConnectionPreference(connectionPreference));
            if (albums.isEmpty()) throw new RuntimeException(EMPTY_ACCOUNT_MSG);

            session.setAttribute(ALBUMS_MODEL_ATTRIBUTE, albums);
            session.setAttribute(USER_NAME_ATTRIBUTE, connectionPreference.getUserName());
            model.addAttribute(ALBUMS_MODEL_ATTRIBUTE, albums);
            model.addAttribute(USER_NAME_ATTRIBUTE, connectionPreference.getUserName());
            return "index";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String showAboutPage(Model model) {
        prepareAboutPage(model);
        return "about";
    }

    @RequestMapping(value = "/sendFeedback.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse sendFeedback(@RequestBody EmailSenderContent emailSenderContent) {
        try {
            List<String> notValidatedFields = validator.validate(emailSenderContent);
            if (!notValidatedFields.isEmpty()) throw new FormValidationException(FAIL_FEEDBACK_MSG);
            emailSender.sendEmail(emailSenderContent);

            return new JsonResponse(ResponseStatus.SUCCESS, SUCCESS_FEEDBACK_MSG);
        } catch (Exception e) {
            return new JsonResponse(ResponseStatus.FAIL, e.getMessage());
        }
    }

    @RequestMapping(value = "/downloadImage", method = RequestMethod.GET)
    public
    @ResponseBody
    byte[] downloadImage(@RequestParam("link") String link,
                         @RequestParam("imageTitle") String imageTitle,
                         HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
        InputStream stream = HttpRequestHandler.doUrlGet(link);
        setResponseFilename(response, request, formatImageFileName(imageTitle));
        return saveDataToByteArray(stream);
    }

    private void prepareAboutPage(Model model) {
        EmailSenderContent emailSenderContent = new EmailSenderContent();
        model.addAttribute("emailSenderContent", emailSenderContent);
    }

    private void prepareLoginPage(Model model) {
        ServiceConnectionPreference connectionPreference = new ServiceConnectionPreference();
        model.addAttribute("connectionPreference", connectionPreference);
    }

    @Resource(name = "sslEmailSender")
    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Resource(name = "fieldNotNullValidator")
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Resource(name = "imageAlbumProcessService")
    public void setAlbumProcessService(ImageAlbumProcessService albumProcessService) {
        this.albumProcessService = albumProcessService;
    }
}
