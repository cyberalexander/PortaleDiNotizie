package by.leonovich.notizieportale.command.newscommand;

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
        boolean operationResult = newsService.saveNewsInDataBase(news);
        if (operationResult == true) {
// Calling setterOfAtributes (request, news) to fill in the attributes of the session (the news that the user will see;
// A list of news or categories that will lead to a page with news
            attributesManager.setAtributesForResponse(sessionRequestContent, news, "addwrite");
            /*sesReqContent.removeParameter("command");*/
        page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        }else{
            sessionRequestContent.setRequestAttribute("addingNewsError", MessageManager.getInstance().getProperty("message.addingNewsError"));
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
        }
        return page;
    }
}
