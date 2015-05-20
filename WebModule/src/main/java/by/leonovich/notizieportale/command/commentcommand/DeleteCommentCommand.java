package by.leonovich.notizieportale.command.commentcommand;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.services.CommentaryService;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;

import java.util.List;

/**
 * Created by alexanderleonovich on 03.05.15.
 */
public class DeleteCommentCommand implements IActionCommand {
    private CommentaryService commentaryService;

    public DeleteCommentCommand() {
        commentaryService = CommentaryService.getInstance();
    }
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        Commentary commentary = commentaryService.getCommentById(Integer.parseInt(sessionRequestContent.getParameter("comment_id")));
        if (commentary != null) {
            commentaryService.deleteCommentary(commentary);
        }
        List<Commentary> commentList = commentaryService.getCommentsByNewsIdorAuthorId("F_NEWS_ID", commentary.getNewsId());
        sessionRequestContent.setSessionAttribute("commentList", commentList);
        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        return page;
    }
}
