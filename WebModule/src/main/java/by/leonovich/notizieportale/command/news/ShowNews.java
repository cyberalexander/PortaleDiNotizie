package by.leonovich.notizieportale.command.news;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.services.*;
import by.leonovich.notizieportale.services.util.exception.ServiceExcpetion;
import by.leonovich.notizieportale.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.leonovich.notizieportale.util.WebConstants.Const.*;
import static java.util.Objects.nonNull;

/**
 * Created by alexanderleonovich on 19.04.15.
 * Show news command
 */
public class ShowNews implements IActionCommand {
    private static final Logger logger = Logger.getLogger(ShowNews.class);
    private INewsService newsService;
    @Autowired
    private CategoryService categoryService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ShowNews() {
        newsService = new NewsService();
        categoryService = new CategoryService();
        /*ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{this.getClass().getClassLoader().getResource("beans-services.xml").getPath()});
        categoryService = (CategoryService) ac.getBean("categoryService");*/
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        Category category = null;
        List<News> newses = null;
        News news = null;
        String page = null;
        try {
        try {
            news = newsService.getNewsByPageId(getPageId(sessionRequestContent));
        } catch (ServiceExcpetion serviceExcpetion) {
            serviceExcpetion.printStackTrace();
        }

            if (nonNull(categoryService.getCategoryByName(news.getPageId()))
                    && news.getPageId().equals(categoryService.getCategoryByName(news.getPageId()).getCategoryName())) {
                category = categoryService.getCategoryByName(news.getPageId());
                newses = newsService.getNewsByCriteria(AttributesManager.getInstance().getPageNumber(sessionRequestContent), PAGES_PACK_SIZE, category.getCategoryId());
                logger.info("\n pageNumber=" + AttributesManager.getInstance().getPageNumber(sessionRequestContent) + ", newses.size()=" + newses.size() + "\n");
            }

        /** --- Attributes for response on main page --- */
        sessionRequestContent.setSessionAttribute(NEWS, news);

        sessionRequestContent.setSessionAttribute(NEWSES, newses);

        sessionRequestContent.setSessionAttribute(COMMENTARIES,
                AttributesManager.getInstance().getCommentariesByNewsId(news));

        sessionRequestContent.setSessionAttribute(POPULAR_NEWSES, newsService.getMostPopularNewsList());

        sessionRequestContent.setSessionAttribute(CATEGORIES, getCategories());

        sessionRequestContent.setSessionAttribute(PAGINATOR_LIST,
                newsService.getList((Long) newsService.getCountNews(category).get(ZERO),
                        AttributesManager.getInstance().getPageNumber(sessionRequestContent), PAGES_PACK_SIZE));
        /** ---------------------------------------------- */

        page = URLManager.getInstance().getProperty(UrlEnum.URL_MAIN.getUrlCode());
        } catch (ServiceExcpetion serviceExcpetion) {
            serviceExcpetion.printStackTrace();
        }
        return page;
    }

    /**
     * Method for getting list with categories
     * @return List<Category>
     */
    private List<Category> getCategories() {
        List<Category> categories = null;
        try {
            categories = categoryService.getCategories();
        } catch (ServiceExcpetion serviceExcpetion) {
            serviceExcpetion.printStackTrace();
        }
        //categories.remove(WebConstants.Const.ZERO);
        return categories;
    }

    private String getPageId(SessionRequestContent sessionRequestContent) {
        String pageId;
        if (null != sessionRequestContent.getParameter(P_PAGE_ID)) {
            return pageId = sessionRequestContent.getParameter(P_PAGE_ID);
        } else {
            return pageId = MAIN;
        }
    }

}
