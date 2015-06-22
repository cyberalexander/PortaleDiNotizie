package by.leonovich.notizieportale.command.commentary;

import static by.leonovich.notizieportale.util.WebConstants.Const;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.services.CommentaryService;
import by.leonovich.notizieportale.services.ICommentaryService;
import by.leonovich.notizieportale.services.exception.ServiceLayerException;
import by.leonovich.notizieportale.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by alexanderleonovich on 03.05.15.
 */
@Deprecated
public class DeleteCommentary implements IActionCommand {

    @Autowired
    private ICommentaryService commentaryService;

    public DeleteCommentary() {
        commentaryService = new CommentaryService();
    }
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        Commentary commentary = null;
        try {
            commentary = commentaryService.get(
                    Long.parseLong(sessionRequestContent.getParameter(Const.P_COMMENTARY_ID)));
        } catch (ServiceLayerException serviceLayerException) {
            serviceLayerException.printStackTrace();
        }
        if (commentary != null) {
            try {
                commentaryService.delete(commentary);
            } catch (ServiceLayerException serviceLayerException) {
                serviceLayerException.printStackTrace();
            }
            List<Commentary> commentaries = null;
            try {
                commentaries = commentaryService.getCommentariesByNewsId(commentary.getNews().getNewsId());
            } catch (ServiceLayerException serviceLayerException) {
                serviceLayerException.printStackTrace();
            }
            sessionRequestContent.setSessionAttribute(Const.COMMENTARIES, commentaries);
        }else{
            sessionRequestContent.setRequestAttribute("noCommentaryForDelete",
                    MessageManager.getInstance().getProperty("message.no.commentay.4.delete"));
        }
        String page = URLManager.getInstance().getProperty(UrlEnum.URL_MAIN.getUrlCode());
        return page;
    }
}
