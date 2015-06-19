package by.leonovich.notizieportale.command.news;

import static by.leonovich.notizieportale.util.WebConstants.Const.P_NEWS_ID_4_DELETE;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.services.NewsService;
import by.leonovich.notizieportale.util.AttributesManager;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.UrlEnum;

/**
 * Created by alexanderleonovich on 21.04.15.
 */
public class DeleteNews implements IActionCommand {

    private AttributesManager attributesManager;
    private NewsService newsService;

    public DeleteNews() {
        attributesManager = AttributesManager.getInstance();
        newsService = new NewsService();
    }


    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        News news;
        Long newsId = Long.parseLong(sessionRequestContent.getParameter(P_NEWS_ID_4_DELETE));
        news = newsService.get(newsId);
        newsService.delete(news);
        attributesManager.setAtributesForResponse(sessionRequestContent, news);

        String page = URLManager.getInstance().getProperty(UrlEnum.URL_MAIN.getUrlCode());
        return page;
    }
}
