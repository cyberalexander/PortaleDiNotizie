package by.leonovich.notizieportale.controller;

import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.exception.WebLayerException;
import by.leonovich.notizieportale.services.ICommentaryService;
import by.leonovich.notizieportale.services.exception.ServiceLayerException;
import by.leonovich.notizieportale.util.AttributesManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;

import static by.leonovich.notizieportale.util.WebConstants.Const.*;
import static java.util.Objects.nonNull;

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
    @RequestMapping(value = "deletecommentary", method = RequestMethod.POST)
    public String deleteCommentary(HttpServletRequest request) {
        try {
            Commentary commentary = commentaryService.get(Long.valueOf(request.getParameter(P_COMMENTARY_ID)));
            System.out.println("\n" + commentary.toString() + "\n");
            commentaryService.delete(commentary);
        } catch (ServiceLayerException e) {
            logger.error(e);
        }
        return "redirect:/shownews.do";
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "edit_commentary", method = RequestMethod.POST)
    public String editCommentary(HttpServletRequest request) throws WebLayerException {
        Long commentaryId = Long.parseLong(request.getParameter(P_COMMENTARY_ID));
        if (nonNull(commentaryId)) {
            try {
                request.getSession().setAttribute(COMMENTARY, commentaryService.get(commentaryId));
            } catch (ServiceLayerException e) {
                logger.error(e);
            }
        }
        return "edit_commentary";
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "edit_write_commentary.do", method = RequestMethod.POST)
    public String editWriteCommentary(HttpServletRequest request) throws WebLayerException {
        Commentary commentary = (Commentary) request.getSession().getAttribute(COMMENTARY);
        commentary.setComment(request.getParameter(CONTENT));
        request.getSession().removeAttribute(COMMENTARY);
        try {
            commentaryService.update(commentary);
        } catch (ServiceLayerException e) {
            logger.error(e);
        }
        return "redirect:/shownews.do";
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "add_write_commentary.do", method = RequestMethod.POST)
    public String addWriteCommentary(Principal principal, HttpServletRequest request) throws WebLayerException {
        Long newsId = Long.valueOf(request.getParameter(P_NEWS_ID));
        try {
            commentaryService.save(attributesManager.addCommentary(request), newsId, principal.getName());
        } catch (ServiceLayerException e) {
            logger.error(e);
        }
        return "redirect:/shownews.do";
    }

}
