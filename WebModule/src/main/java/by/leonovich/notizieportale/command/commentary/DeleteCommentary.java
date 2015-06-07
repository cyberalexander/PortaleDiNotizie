package by.leonovich.notizieportale.command.commentary;

import static by.leonovich.notizieportale.util.WebConstants.Const;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.services.CommentaryService;
import by.leonovich.notizieportale.util.MessageManager;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;

import java.util.List;

/**
 * Created by alexanderleonovich on 03.05.15.
 */
public class DeleteCommentary implements IActionCommand {
    private CommentaryService commentaryService;

    public DeleteCommentary() {
        commentaryService = CommentaryService.getInstance();
    }
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        Commentary commentary = commentaryService.get(
                Long.parseLong(sessionRequestContent.getParameter(Const.P_COMMENTARY_ID)));
        if (commentary != null) {
            commentaryService.delete(commentary);
            List<Commentary> commentaries = commentaryService.getCommentariesByNewsId(commentary.getNews().getNewsId());
            sessionRequestContent.setSessionAttribute(Const.COMMENTARIES, commentaries);
        }else{
            sessionRequestContent.setRequestAttribute("noCommentaryForDelete",
                    MessageManager.getInstance().getProperty("message.no.commentay.4.delete"));
        }
        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        return page;
    }
}
