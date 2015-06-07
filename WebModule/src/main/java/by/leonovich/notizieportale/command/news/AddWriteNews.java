package by.leonovich.notizieportale.command.news;

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
public class AddWriteNews implements IActionCommand {

    private AttributesManager attributesManager;
    private INewsService newsService;
    private ShowNews showNews;

    public AddWriteNews() {
        attributesManager = AttributesManager.getInstance();
        newsService = NewsService.getInstance();
        showNews = new ShowNews();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        News news = new News();
        news = attributesManager.parseParametersOfNews(sessionRequestContent, news);
        Long operationResult = newsService.save(news);
        if (operationResult != null && operationResult > Const.ZERO) {
            showNews.execute(sessionRequestContent);
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        }else{
            sessionRequestContent.setRequestAttribute("addingNewsError",
                    MessageManager.getInstance().getProperty("message.addingNewsError"));
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
        }
        return page;
    }
}
