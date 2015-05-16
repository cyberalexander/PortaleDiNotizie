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
public class EditWriteNewsCommand implements IActionCommand {

    private AttributesManager attributesManager;
    private NewsService newsService;

    public EditWriteNewsCommand() {
        attributesManager = AttributesManager.getInstance();
        newsService = NewsService.getInstance();
    }


    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
//Creating news object and get in variable data of news-object from session. It`s only necessary for the variable identifier News.
        News news = (News) sessionRequestContent.getSessionAttribute("news");
        attributesManager.parseParametersOfNews(sessionRequestContent, news);
        newsService.EditNewsPage(news);
        // set attributes in session to display the page you want after the operation
        attributesManager.setAtributesForResponse(sessionRequestContent, news, "edit");

        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        return page;
    }
}
