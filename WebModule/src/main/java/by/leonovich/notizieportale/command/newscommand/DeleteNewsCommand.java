package by.leonovich.notizieportale.command.newscommand;

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
        Integer id = Integer.parseInt(sessionRequestContent.getParameter("newsIdForDelete"));
        news = newsService.getNewsByPK(id);
        newsService.deleteNewsPage(news);
        attributesManager.setAtributesForResponse(sessionRequestContent, news, "delete");

        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        return page;
    }
}
