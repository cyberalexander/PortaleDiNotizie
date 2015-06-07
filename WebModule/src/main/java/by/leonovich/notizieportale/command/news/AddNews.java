package by.leonovich.notizieportale.command.news;

import static by.leonovich.notizieportale.util.WebConstants.Const;
import static by.leonovich.notizieportale.util.WebConstants.Const.*;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.services.*;
import by.leonovich.notizieportale.util.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexanderleonovich on 19.04.15.
 * Method for getting attributes for add new news-page and to forward() user to addnews page
 */
public class AddNews implements IActionCommand {

    private INewsService newsService;
    private ICategoryService categoryService;

    public AddNews() {
        newsService = NewsService.getInstance();
        categoryService = CategoryService.getInstance();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        String pageId;
        Category category;

        // get newsList from session
        News news = (News) sessionRequestContent.getSessionAttribute(Const.NEWS);
        if (!(news.getPageId().equals(MAIN)) && (news.getCategory().getCategoryId() == ONE)) {
            category = categoryService.getCategoryByName(news.getPageId());
            List<News> list = newsService.getListOfNewsByCategoryIdNoOrder(category.getCategoryId());
            if (list != null && list.size() > Const.ZERO) {
                pageId = newsService.get(list.get(list.size() - ONE).getNewsId()).getPageId();
            } else {
                pageId = news.getPageId() + "_" + Const.ONE;
                sessionRequestContent.setSessionAttribute(P_PAGE_ID, pageId);
                sessionRequestContent.setRequestAttribute(P_CATEGORY, news.getPageId());
                page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
                return page;
            }
            // --- increasing number of page_id --------------------
            pageId = pageIdIncreasing(pageId);
            //-----------------------------------------------------
            sessionRequestContent.setRequestAttribute(P_CATEGORY, news.getPageId());
            sessionRequestContent.setSessionAttribute(P_PAGE_ID, pageId);
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
        } else {
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
            sessionRequestContent.setRequestAttribute("addNewsComErr", MessageManager.getInstance().getProperty("message.addNewsComErr"));
            return page;
        }
        return page;
    }

    /**
     * Method for automatic increasing page_id
     *
     * @param pageId - page_id, what we will increase
     * @return increased page_id
     */
    private String pageIdIncreasing(String pageId) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(pageId);
        String idOfPage = null;
        int startIndex = WebConstants.Const.ZERO;
        while (m.find()) {
            idOfPage = m.group();
            startIndex = m.start();
        }
        long number = Long.parseLong(idOfPage);
        number++;
        pageId = pageId.substring(WebConstants.Const.ZERO, startIndex);
        pageId = pageId + String.valueOf(number);
        return pageId;
    }
}
