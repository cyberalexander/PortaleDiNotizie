package by.leonovich.notizieportale.command.commentary;

import static by.leonovich.notizieportale.util.WebConstants.Const.*;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.services.CommentaryService;
import by.leonovich.notizieportale.services.NewsService;
import by.leonovich.notizieportale.services.PersonService;
import by.leonovich.notizieportale.util.AttributesManager;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by alexanderleonovich on 03.05.15.
 */
public class AddWriteCommentary implements IActionCommand {

    private CommentaryService commentaryService;
    private PersonService personService;
    private NewsService newsService;

    public AddWriteCommentary() {
        commentaryService = CommentaryService.getInstance();
        personService = PersonService.getInstance();
        newsService = NewsService.getInstance();
    }
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        Commentary commentary = new Commentary();
        if (sessionRequestContent.getSessionAttribute(COMMENT_FOR_EDIT) != null) {
            if (sessionRequestContent.getParameter(P_CONTENT) != null) {
                commentaryService.update(updateCommentary(sessionRequestContent, commentary));
                sessionRequestContent.removeSessionAttribute(COMMENT_FOR_EDIT);
            }
        }else {
            if (sessionRequestContent.getParameter(P_CONTENT) != null) {
                Long newsId = Long.parseLong(sessionRequestContent.getParameter(P_NEWS_ID));
                Person person = (Person) sessionRequestContent.getSessionAttribute(P_PERSON);
                Long personId = person.getPersonId();
                commentaryService.save(addCommentary(sessionRequestContent, commentary), newsId, personId);
            }
        }
        //adding commentary in session for adding on news page after writing and save comment
        Long newsId = Long.parseLong(sessionRequestContent.getParameter(P_NEWS_ID));
        List<Commentary> commentaries = commentaryService.getCommentariesByNewsId(newsId);
        if (commentaries != null && commentaries.size() > ZERO) {
        sessionRequestContent.setSessionAttribute(COMMENTARIES , commentaries);
        }
        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        return page;
    }

    /**
     * clearSession in getHttpSession
     * refactor packejes and classes
     */

    private Commentary addCommentary(SessionRequestContent sessionRequestContent, Commentary commentary) {
/*        if (personService.get(Long.parseLong(sessionRequestContent.getParameter(P_PERSON_ID))).
                equals(newsService.get(Long.parseLong(sessionRequestContent.getParameter(P_NEWS_ID))).getPerson())){
        commentary.setPerson(newsService.get(Long.parseLong(sessionRequestContent.getParameter(P_NEWS_ID))).getPerson());
        }else{
            commentary.setPerson(personService.get(Long.parseLong(sessionRequestContent.getParameter(P_PERSON_ID))));
        }*/

        /*News news = newsService.get(Long.parseLong(sessionRequestContent.getParameter(P_NEWS_ID)));
        commentary.setPerson(personService.get((long) 3));
        commentary.setNews(news);*/
        commentary.setComment(sessionRequestContent.getParameter(P_CONTENT));
        Date date = (Date) sessionRequestContent.getSessionAttribute("dateNow");
        commentary.setDate(AttributesManager.getInstance().parseDateTimeFromRequest(date));
/*        if (news.getCommentaries() == null || news.getCommentaries().size() <= ZERO) {
            Set<Commentary> commentaries = new HashSet<>();
            commentaries.add(commentary);
            news.setCommentaries(commentaries);
        } else {
            news.getCommentaries().add(commentary);
        }*/
        return commentary;
    }

    private Commentary updateCommentary(SessionRequestContent sessionRequestContent, Commentary commentary) {
        commentary = commentaryService.get(
                Long.parseLong(sessionRequestContent.getParameter(P_COMMENTARY_ID)));
        commentary.setComment(sessionRequestContent.getParameter(P_CONTENT));
        Date date = (Date) sessionRequestContent.getSessionAttribute("dateNow");
        commentary.setDate(AttributesManager.getInstance().parseDateTimeFromRequest(date));
        return commentary;
    }
}
