package by.leonovich.notizieportale.command.newscommand;

import static by.leonovich.notizieportale.util.WebConstants.Const;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.services.INewsService;
import by.leonovich.notizieportale.services.NewsService;
import by.leonovich.notizieportale.util.*;

/**
 * Created by alexanderleonovich on 21.04.15.
 * Class for write persist
 */
public class AddWriteNewsCommand implements IActionCommand {

    private AttributesManager attributesManager;
    private INewsService newsService;

    public AddWriteNewsCommand() {
        attributesManager = AttributesManager.getInstance();
        newsService = NewsService.getInstance();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        News news = new News();
        news = attributesManager.parseParametersOfNews(sessionRequestContent, news);
        Long operationResult = newsService.saveNews(news);
        if (operationResult != null && operationResult > 0) {
// Calling setterOfAtributes (request, news) to fill in the attributes of the session (the news that the user will see;
// A list of news or categories that will lead to a page with news
            news = newsService.getNewsByPK(operationResult);
            System.out.println('\n' + news.toString() + '\n');
            attributesManager.setAtributesForResponse(sessionRequestContent, news, Const.FROM_ADDWRITE);
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        }else{
            sessionRequestContent.setRequestAttribute("addingNewsError",
                    MessageManager.getInstance().getProperty("message.addingNewsError"));
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
        }
        return page;
    }
}
