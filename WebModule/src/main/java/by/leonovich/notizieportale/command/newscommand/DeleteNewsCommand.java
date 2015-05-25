package by.leonovich.notizieportale.command.newscommand;

import static by.leonovich.notizieportale.util.WebConstants.Const;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.services.NewsService;
import by.leonovich.notizieportale.util.AttributesManager;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.UrlEnum;
import org.apache.log4j.Logger;

/**
 * Created by alexanderleonovich on 21.04.15.
 */
public class DeleteNewsCommand implements IActionCommand {

    private AttributesManager attributesManager;
    private NewsService newsService;

    public DeleteNewsCommand() {
        attributesManager = AttributesManager.getInstance();
        newsService = NewsService.getInstance();
    }


    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        News news;
        Long newsId = Long.parseLong(sessionRequestContent.getParameter(Const.P_NEWS_ID_4_DELETE));
        news = newsService.getNewsByPK(newsId);
        newsService.deleteNews(news);
        attributesManager.setAtributesForResponse(sessionRequestContent, news, Const.FROM_DELETE);

        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        return page;
    }
}
