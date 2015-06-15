package by.leonovich.notizieportale.command.commentary;

import static by.leonovich.notizieportale.util.WebConstants.Const;
import static by.leonovich.notizieportale.util.WebConstants.Const.COMMENT_FOR_EDIT;
import static java.util.Objects.nonNull;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.services.CommentaryService;
import by.leonovich.notizieportale.services.ICommentaryService;
import by.leonovich.notizieportale.util.MessageManager;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by alexanderleonovich on 03.05.15.
 */
public class EditWriteCommentary implements IActionCommand {

    @Autowired
    private ICommentaryService commentaryService;

    public EditWriteCommentary() {
        commentaryService = CommentaryService.getInstance();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        Commentary commentary;

        Long commentaryId = Long.parseLong(sessionRequestContent.getParameter(Const.P_COMMENTARY_ID));
        if (nonNull(commentaryId)) {
            commentary = commentaryService.get(commentaryId);
            sessionRequestContent.setSessionAttribute(COMMENT_FOR_EDIT, commentary);
        } else {
            sessionRequestContent.setRequestAttribute("errorEditCommentary",
                    MessageManager.getInstance().getProperty("message.edit.commentay.fail"));
        }
        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        return page;
    }

}
