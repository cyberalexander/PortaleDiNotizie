package by.leonovich.notizieportale.command.commentcommand;

import static by.leonovich.notizieportale.util.WebConstants.Const;
import static by.leonovich.notizieportale.util.WebConstants.Const.*;
import static by.leonovich.notizieportale.util.WebConstants.Const.P_PERSON;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.services.CommentaryService;
import by.leonovich.notizieportale.services.NewsService;
import by.leonovich.notizieportale.services.PersonService;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by alexanderleonovich on 03.05.15.
 */
public class AddWriteCommentCommand implements IActionCommand {

    private CommentaryService commentaryService;
    private PersonService personService;
    private NewsService newsService;

    public AddWriteCommentCommand() {
        commentaryService = CommentaryService.getInstance();
        personService = PersonService.getInstance();
        newsService = NewsService.getInstance();
    }
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        Commentary comment = new Commentary();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = sessionRequestContent.getParameter(P_DATE);

        try {

            java.util.Date date = formatter.parse(dateInString);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date = Date.valueOf(sessionRequestContent.getParameter(P_DATE));

        if (sessionRequestContent.getSessionAttribute(COMMENT_FOR_EDIT) != null) {
            if (sessionRequestContent.getParameter(P_CONTENT) != null) {
                comment = commentaryService.getCommentaryByPK(
                        Long.parseLong(sessionRequestContent.getParameter(P_COMMENTARY_ID)));
                comment.setComment(sessionRequestContent.getParameter(P_CONTENT));
                comment.setDate(date);
                commentaryService.updateCommentary(comment);
                sessionRequestContent.removeSessionAttribute(COMMENT_FOR_EDIT);
            }
        }else {
            if (sessionRequestContent.getParameter(P_CONTENT) != null) {
                comment.setPerson(personService.getByPK(Long.parseLong(sessionRequestContent.getParameter(P_PERSON_ID))));
                comment.setNews(newsService.getNewsByPK(Long.parseLong(sessionRequestContent.getParameter(P_NEWS_ID))));

                comment.setComment(sessionRequestContent.getParameter(P_CONTENT));
                comment.setDate(date);
                commentaryService.saveCommentary(comment);
            }
        }
        //adding commentary in session for adding on news page after writing and save comment
        Long newsId = Long.parseLong(sessionRequestContent.getParameter(P_NEWS_ID));
        List<Commentary> commentaries = commentaryService.getCommentariesByNewsId(newsId);
        sessionRequestContent.setSessionAttribute(COMMENTARIES , commentaries);

        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        return page;
    }
}
