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
    private ShowNewsCommand showNewsCommand;

    public AddWriteNewsCommand() {
        attributesManager = AttributesManager.getInstance();
        newsService = NewsService.getInstance();
        showNewsCommand = new ShowNewsCommand();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        News news = new News();
        news = attributesManager.parseParametersOfNews(sessionRequestContent, news);
        Long operationResult = newsService.saveNews(news);
        if (operationResult != null && operationResult > Const.ZERO) {
            showNewsCommand.execute(sessionRequestContent);
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        }else{
            sessionRequestContent.setRequestAttribute("addingNewsError",
                    MessageManager.getInstance().getProperty("message.addingNewsError"));
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
        }
        return page;
    }
}
