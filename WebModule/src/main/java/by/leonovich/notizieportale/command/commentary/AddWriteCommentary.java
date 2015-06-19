package by.leonovich.notizieportale.command.commentary;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.services.CommentaryService;
import by.leonovich.notizieportale.services.util.exception.ServiceExcpetion;
import by.leonovich.notizieportale.util.AttributesManager;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static by.leonovich.notizieportale.util.WebConstants.Const.*;
import static java.util.Objects.nonNull;

/**
 * Created by alexanderleonovich on 03.05.15.
 */
public class AddWriteCommentary implements IActionCommand {
    private static final Logger logger = Logger.getLogger(AddWriteCommentary.class);
    @Autowired
    private CommentaryService commentaryService;


    public AddWriteCommentary() {
        commentaryService = new CommentaryService();
    }
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        /*ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{this.getClass().getClassLoader().getResource("dispatcher-servlet.xml").getPath()});
        commentaryService = (CommentaryService) ac.getBean("commentaryService");*/
        Commentary commentary = new Commentary();
        Long newsId = Long.parseLong(sessionRequestContent.getParameter(P_NEWS_ID));
        if (nonNull(sessionRequestContent.getSessionAttribute(COMMENT_FOR_EDIT))) {
            if (nonNull(sessionRequestContent.getParameter(P_CONTENT))) {
                try {
                    commentaryService.update(updateCommentary(sessionRequestContent));
                } catch (ServiceExcpetion serviceExcpetion) {
                    serviceExcpetion.printStackTrace();
                }
                sessionRequestContent.removeSessionAttribute(COMMENT_FOR_EDIT);
            }
        }else {
            if (nonNull(sessionRequestContent.getParameter(P_CONTENT))) {
                Person person = (Person) sessionRequestContent.getSessionAttribute(P_PERSON);
                Long personId = person.getPersonId();
                try {
                    commentaryService.save(addCommentary(sessionRequestContent, commentary), newsId, personId);
                } catch (ServiceExcpetion serviceExcpetion) {
                    serviceExcpetion.printStackTrace();
                }
            }
        }
        //adding commentary in session for adding on news page after writing and save comment
        List<Commentary> commentaries = null;
        try {
            commentaries = commentaryService.getCommentariesByNewsId(newsId);
        } catch (ServiceExcpetion serviceExcpetion) {
            serviceExcpetion.printStackTrace();
        }
        if (nonNull(commentaries) && commentaries.size() > ZERO) {
        sessionRequestContent.setSessionAttribute(COMMENTARIES , commentaries);
        }
        String page = URLManager.getInstance().getProperty(UrlEnum.URL_MAIN.getUrlCode());
        return page;
    }



    private Commentary addCommentary(SessionRequestContent sessionRequestContent, Commentary commentary) {
        commentary.setComment(sessionRequestContent.getParameter(P_CONTENT));
        Date date = (Date) sessionRequestContent.getSessionAttribute(P_DATE_NOW);
        commentary.setDate(AttributesManager.getInstance().parseDateTimeFromRequest(date));
        return commentary;
    }

    private Commentary updateCommentary(SessionRequestContent sessionRequestContent) {
        Commentary commentary = null;
        try {
            commentary = commentaryService.get(
                    Long.parseLong(sessionRequestContent.getParameter(P_COMMENTARY_ID)));
        } catch (ServiceExcpetion serviceExcpetion) {
            serviceExcpetion.printStackTrace();
        }
        commentary.setComment(sessionRequestContent.getParameter(P_CONTENT));
        Date date = (Date) sessionRequestContent.getSessionAttribute(P_DATE_NOW);
        commentary.setDate(AttributesManager.getInstance().parseDateTimeFromRequest(date));
        return commentary;
    }
}
