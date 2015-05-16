package by.leonovich.notizieportale.command.newscommand;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.services.NewsService;
import by.leonovich.notizieportale.util.MessageManager;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;
import org.apache.log4j.Logger;

/**
 * Created by alexanderleonovich on 21.04.15.
 */
public class EditNewsCommand implements IActionCommand {

    private NewsService newsService;

    public EditNewsCommand() {
        newsService = NewsService.getInstance();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        Integer id = Integer.parseInt(sessionRequestContent.getParameter("newsIdForEdit"));
        News news = newsService.getNewsByPK(id);
        if (news != null) {
            sessionRequestContent.setSessionAttribute("news", news);
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_EDIT_NEWS.getUrlCode());
        } else {
            sessionRequestContent.setRequestAttribute("NoPageForEdit", MessageManager.getInstance().getProperty("message.NoPageForEdit"));
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        }
        return page;
    }
}
