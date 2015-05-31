package by.leonovich.notizieportale.command.newscommand;

import static by.leonovich.notizieportale.util.WebConstants.Const;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.services.NewsService;
import by.leonovich.notizieportale.util.MessageManager;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;
import org.apache.log4j.Logger;

import java.util.List;

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
        Long newsId = Long.parseLong(sessionRequestContent.getParameter(Const.P_NEWS_ID_4_EDIT));
        News news = newsService.getNewsByPK(newsId);
        List<News> newses = (List<News>) sessionRequestContent.getSessionAttribute(Const.NEWSES);
        sessionRequestContent.setSessionAttribute(Const.NEWSES, newses);
        if (news != null) {
            sessionRequestContent.setSessionAttribute(Const.NEWS, news);
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_EDIT_NEWS.getUrlCode());
        } else {
            sessionRequestContent.setRequestAttribute("NoPageForEdit", MessageManager.getInstance().getProperty("message.NoPageForEdit"));
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        }
        return page;
    }
}
