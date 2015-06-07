package by.leonovich.notizieportale.command.news;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.services.NewsService;
import by.leonovich.notizieportale.util.AttributesManager;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;

import static by.leonovich.notizieportale.util.WebConstants.Const.*;

/**
 * Created by alexanderleonovich on 21.04.15.
 */
public class EditWriteNews implements IActionCommand {

    private AttributesManager attributesManager;
    private NewsService newsService;

    public EditWriteNews() {
        attributesManager = AttributesManager.getInstance();
        newsService = NewsService.getInstance();
    }


    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
//Creating news object and get in variable data of news-object from session. It`s only necessary for the variable identifier News.
        News news = (News) sessionRequestContent.getSessionAttribute(NEWS);
        attributesManager.parseParametersOfNews(sessionRequestContent, news);
        news = newsService.update(news);
        // set attributes in session to display the page you want after the operation
        attributesManager.setAtributesForResponse(sessionRequestContent, news);
        sessionRequestContent.setRequestAttribute(P_PAGE_NUMBER, String.valueOf(ZERO));

        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        return page;
    }
}
