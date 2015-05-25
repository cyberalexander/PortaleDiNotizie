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
        List<News> newses;
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
        }else{
            newses = newsService.getListOfNewsByCategory(news.getCategory().getCategoryId());
        }
/** Comments attributes getting for response */
        List<Commentary> commentaries = commentaryService.getCommentariesByNewsId(news.getNewsId());
/** Most popular news attributes getting for response */
        List<News> listPopNews = newsService.getMostPopularNewsList();

        categories = categoryService.getCategories();
        categories.remove(Const.ZERO);

        sessionRequestContent.setSessionAttribute("news", news);
        sessionRequestContent.setSessionAttribute("newses", newses);
        sessionRequestContent.setSessionAttribute("commentaries", commentaries);
        sessionRequestContent.setSessionAttribute("listPopNews", listPopNews);
        sessionRequestContent.setSessionAttribute("categories", categories);
        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        return page;
    }
}
