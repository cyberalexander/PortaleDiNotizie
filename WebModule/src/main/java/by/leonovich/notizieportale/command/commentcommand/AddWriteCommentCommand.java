package by.leonovich.notizieportale.command.commentcommand;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.services.CommentaryService;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by alexanderleonovich on 03.05.15.
 */
public class AddWriteCommentCommand implements IActionCommand {

    private CommentaryService commentaryService;

    public AddWriteCommentCommand() {
        commentaryService = CommentaryService.getInstance();
    }
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        Commentary comment = new Commentary();
        String dateString = sessionRequestContent.getParameter("dateOfComment");
        System.out.println(dateString);

        if (sessionRequestContent.getSessionAttribute("commentForEdit") != null) {
            if (sessionRequestContent.getParameter("commentcontent") != null) {
                comment = commentaryService.getCommentById(Integer.parseInt(sessionRequestContent.getParameter("comment_id")));
                comment.setComment(sessionRequestContent.getParameter("commentcontent"));
                comment.setDate(dateString);
                commentaryService.updateCommentary(comment);
                sessionRequestContent.removeSessionAttribute("commentForEdit");
            }
        }else {
            if (sessionRequestContent.getParameter("commentcontent") != null) {
                comment.setUser_id(Integer.parseInt(sessionRequestContent.getParameter("user_id")));
                comment.setNews_id(Integer.parseInt(sessionRequestContent.getParameter("news_id")));
                comment.setComment(sessionRequestContent.getParameter("commentcontent"));
                comment.setDate(dateString);
                commentaryService.saveComment(comment);
            }
        }
        //adding commentary in session for adding on news page after writing and save comment
        int newsId = Integer.parseInt(sessionRequestContent.getParameter("news_id"));
        List<Commentary> commentList = commentaryService.getCommentsByNewsIdorAuthorId("F_NEWS_ID", newsId);
        sessionRequestContent.setSessionAttribute("commentList" , commentList);

        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        return page;
    }
}
