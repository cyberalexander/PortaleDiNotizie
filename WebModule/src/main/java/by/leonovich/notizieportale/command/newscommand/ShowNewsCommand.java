package by.leonovich.notizieportale.command.newscommand;

import static by.leonovich.notizieportale.util.WebConstants.Const;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.services.*;
import by.leonovich.notizieportale.util.*;
import com.sun.javafx.geom.AreaOp;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.List;

/**
 * Created by alexanderleonovich on 19.04.15.
 * Show news command
 */
public class ShowNewsCommand implements IActionCommand {

    private INewsService newsService;
    private CategoryService categoryService;
    private ICommentaryService commentaryService;

    public ShowNewsCommand() {
        newsService = NewsService.getInstance();
        commentaryService = CommentaryService.getInstance();
        categoryService = CategoryService.getInstance();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String pageId;
        Category category;
        List<News> newses = null;
        List<Category> categories;

        if (sessionRequestContent.getParameter(Const.P_PAGE_ID) != null) {
            pageId = sessionRequestContent.getParameter(Const.P_PAGE_ID);
        } else {
            pageId = Const.MAIN;
        }
        News news = newsService.getNewsByPageId(pageId);

        if (categoryService.getCategoryByName(news.getPageId()) != null
                && news.getPageId().equals(categoryService.getCategoryByName(news.getPageId()).getCategoryName())) {
            category = categoryService.getCategoryByName(news.getPageId());
            newses = newsService.getListOfNewsByCategory(category.getCategoryId());
            if (null != sessionRequestContent.getParameter(Const.P_PAGE_NUMBER)
                    && 0 != Integer.parseInt(sessionRequestContent.getParameter(Const.P_PAGE_NUMBER))) {
                int pageNumber = Integer.parseInt(sessionRequestContent.getParameter(Const.P_PAGE_NUMBER));
                int pageSize = 4;
                newses = newsService.getNewsByCriteria(pageNumber, pageSize, category.getCategoryId());
            }
/*            System.out.println('\n' + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + '\n');
            if (true){
            String pageNumber = sessionRequestContent.getParameter("pageNumber");
            System.out.println('\n' + pageNumber + '\n');

            Integer pageNumberInt = Integer.parseInt(pageNumber);

            }else{
                newses = newsService.getListOfNewsByCategory(category.getCategoryId());
            }*/
        }/*else{
            //newses = newsService.getListOfNewsByCategory(news.getCategory().getCategoryId());
            String pageNumber = sessionRequestContent.getParameter("pageNumber");
            System.out.println('\n' + pageNumber + '\n');
            int pageSize = 4;
            int pageNumberInt = Integer.parseInt(pageNumber);
            //newses = newsService.getNewsByCriteria(pageNumberInt, pageSize, news.getCategory().getCategoryId());
            newses = null;
        }*/

/** Most popular news attributes getting for response */
        List<News> listPopNews = newsService.getMostPopularNewsList();

        sessionRequestContent.setSessionAttribute(Const.NEWS, news);
        sessionRequestContent.setSessionAttribute(Const.NEWSES, newses);
        sessionRequestContent.setSessionAttribute(Const.COMMENTARIES,
                AttributesManager.getInstance().getCommentariesByNewsId(news.getNewsId()));
        sessionRequestContent.setSessionAttribute("listPopNews", listPopNews);
        sessionRequestContent.setSessionAttribute(Const.CATEGORIES,
                AttributesManager.getInstance().getCategories());
        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        return page;
    }
}
