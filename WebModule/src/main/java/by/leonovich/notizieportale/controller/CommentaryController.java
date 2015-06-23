package by.leonovich.notizieportale.controller;

import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.exception.WebLayerException;
import by.leonovich.notizieportale.services.ICommentaryService;
import by.leonovich.notizieportale.exception.ServiceLayerException;
import by.leonovich.notizieportale.util.AttributesManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;

import static by.leonovich.notizieportale.util.WebConstants.Const.*;
import static java.lang.Long.*;
import static java.lang.Long.valueOf;
import static java.util.Objects.nonNull;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by alexanderleonovich on 20.06.15.
 */
@Controller
public class CommentaryController {
    private static final Logger logger = Logger.getLogger(CommentaryController.class);


    @Autowired
    private ICommentaryService commentaryService;
    @Autowired
    private AttributesManager attributesManager;


    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "deletecommentary", method = GET)
    public String deleteCommentary(@ModelAttribute(P_COMMENTARY_ID) String commentaryId,
                                   @ModelAttribute(P_PAGE_ID) String pageId, ModelMap modelMap) throws WebLayerException{
        try {
            Commentary commentary = commentaryService.get(valueOf(commentaryId));
            commentaryService.delete(commentary);
        } catch (ServiceLayerException e) {
            logger.error(e);
            throw new WebLayerException(e);
        }
        modelMap.addAttribute(P_PAGE_ID, pageId);
        return "forward:/shownews.do";
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "edit_commentary", method = GET)
    public String editCommentary(@ModelAttribute(P_COMMENTARY_ID) String commentaryId,
                                 @ModelAttribute(P_PAGE_ID) String pageId, ModelMap modelMap,
                                 HttpServletRequest request) throws WebLayerException {
        if (nonNull(commentaryId)) {
            try {
                request.getSession().setAttribute(COMMENTARY_FOR_EDIT, commentaryService.get(valueOf(commentaryId)));
            } catch (ServiceLayerException e) {
                logger.error(e);
            }
        }
        modelMap.addAttribute(P_PAGE_ID, pageId);
        return "forward:/shownews.do";
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "edit_write_commentary.do", method = POST)
    public String editWriteCommentary(@ModelAttribute(CONTENT) String content,
                                      @ModelAttribute(P_PAGE_ID) String pageId, ModelMap modelMap,
                                      HttpServletRequest request) throws WebLayerException {
        Commentary commentary = (Commentary) request.getSession().getAttribute(COMMENTARY_FOR_EDIT);
        commentary.setComment(content);
        request.getSession().removeAttribute(COMMENTARY_FOR_EDIT);
        try {
            commentaryService.update(commentary);
        } catch (ServiceLayerException e) {
            logger.error(e);
        }
        modelMap.addAttribute(P_PAGE_ID, pageId);
        return "forward:/shownews.do";
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "add_write_commentary.do", method = POST)
    public String addWriteCommentary(@ModelAttribute(P_NEWS_ID) String newsId,
                                     @ModelAttribute(P_PAGE_ID) String pageId, ModelMap modelMap,
                                     Principal principal, HttpServletRequest request) throws WebLayerException {
        try {
            commentaryService.save(attributesManager.addCommentary(request), valueOf(newsId), principal.getName());
        } catch (ServiceLayerException e) {
            logger.error(e);
        }
        modelMap.addAttribute(P_PAGE_ID, pageId);
        return "forward:/shownews.do";
    }

}
