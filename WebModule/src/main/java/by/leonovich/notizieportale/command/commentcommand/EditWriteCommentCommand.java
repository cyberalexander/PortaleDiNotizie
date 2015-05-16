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
public class EditWriteCommentCommand implements IActionCommand{
    private CommentaryService commentaryService;

    public EditWriteCommentCommand() {
        commentaryService = CommentaryService.getInstance();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        Commentary comment;

        int comment_id = Integer.parseInt(sessionRequestContent.getParameter("comment_id"));
        comment = commentaryService.getCommentById(comment_id);

        sessionRequestContent.setSessionAttribute("commentForEdit", comment);

        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        return page;
    }
}
