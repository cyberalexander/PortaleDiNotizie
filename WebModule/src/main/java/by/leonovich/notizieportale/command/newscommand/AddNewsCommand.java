package by.leonovich.notizieportale.command.newscommand;

import static by.leonovich.notizieportale.util.WebConstants.Const;
import static by.leonovich.notizieportale.util.WebConstants.Const.*;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.services.CategoryService;
import by.leonovich.notizieportale.services.ICategoryService;
import by.leonovich.notizieportale.services.INewsService;
import by.leonovich.notizieportale.services.NewsService;
import by.leonovich.notizieportale.util.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexanderleonovich on 19.04.15.
 * Method for getting attributes for add new news-page and to forward() user to addnews page
 */
public class AddNewsCommand implements IActionCommand {

    private INewsService newsService;
    private ICategoryService categoryService;

    public AddNewsCommand() {
        newsService = NewsService.getInstance();
        categoryService = CategoryService.getInstance();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        String pageId = null;

        // get newsList from session
        News news = (News) sessionRequestContent.getSessionAttribute(Const.NEWS);
        if (news.getCategory().getCategoryName().equals(MAIN)) {
            if (news.getPageId().equals(MAIN)) {
                sessionRequestContent.removeSessionAttribute(P_PAGE_ID);
                sessionRequestContent.setRequestAttribute(P_CATEGORY, news.getPageId());
                page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
                return page;
            }else{
                Category category = categoryService.getCategoryByName(news.getPageId());
                List<News> list = newsService.getListOfNewsByCategory(category.getCategoryId());
                if (list != null && list.size() > Const.ZERO) {
                    pageId = newsService.getNewsByPK(list.get(list.size() - ONE).getNewsId()).getPageId();
                } else {
                    pageId = news.getPageId() + Const.ONE;
                    sessionRequestContent.setSessionAttribute(P_PAGE_ID, pageId);
                    page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
                    return page;
                }
                System.out.println("\n" + news.getCategory() + "\n");
                // --- increasing number of page_id --------------------
                pageId = pageIdIncreasing(pageId);
                //-----------------------------------------------------
                sessionRequestContent.setSessionAttribute(P_PAGE_ID, pageId);
                page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
            }
        } else if (!(news.getCategory().getCategoryName().equals(MAIN))) {
            List<News> list = newsService.getListOfNewsByCategory(news.getCategory().getCategoryId());
            if (list != null  && list.size() > Const.ZERO) {
                pageId = newsService.getNewsByPK(list.get(list.size() - ONE).getNewsId()).getPageId();
            }else {
                pageId = news.getPageId();
            }
            sessionRequestContent.setSessionAttribute(P_PAGE_ID, pageId);
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
        }else{
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
            sessionRequestContent.setRequestAttribute("addNewsComErr", MessageManager.getInstance().getProperty("message.addNewsComErr"));
            return page;
        }
        /*List<News> newses = (List<News>) sessionRequestContent.getSessionAttribute(Const.NEWSES);
        if (newses.size() > Const.ZERO && !(MAIN.equals(news.getPageId()))) {
            // get page_id from last element in newsList
            List<News> list = newsService.getListOfNewsByCategory(newses.get(ZERO).getCategory().getCategoryId());
            pageId = newsService.getNewsByPK(list.get(list.size() - ONE).getNewsId()).getPageId();
            System.out.println("\n" + news.getCategory() + "\n");
            // --- increasing number of page_id --------------------
            pageId = pageIdIncreasing(pageId);
            //-----------------------------------------------------
            sessionRequestContent.setSessionAttribute(P_PAGE_ID, pageId);
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
            return page;
        } else if (newses.size() == WebConstants.Const.ZERO) {
            pageId = news.getPageId() + Const.ONE;
            sessionRequestContent.setSessionAttribute(P_PAGE_ID, pageId);
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
            return page;
        } else if(MAIN.equals(news.getPageId())){
            sessionRequestContent.removeSessionAttribute(P_PAGE_ID);
            sessionRequestContent.setRequestAttribute(P_CATEGORY, news.getPageId());
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
            return page;
        }else{
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
            sessionRequestContent.setRequestAttribute("addNewsComErr", MessageManager.getInstance().getProperty("message.addNewsComErr"));
            return page;
        }*/
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
