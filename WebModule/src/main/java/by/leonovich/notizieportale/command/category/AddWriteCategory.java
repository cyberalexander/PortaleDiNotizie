package by.leonovich.notizieportale.command.category;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.command.news.ShowNews;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.services.*;
import by.leonovich.notizieportale.services.util.exception.ServiceExcpetion;
import by.leonovich.notizieportale.util.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by alexanderleonovich on 02.06.15.
 */
public class AddWriteCategory implements IActionCommand{

    private AttributesManager attributesManager;
    @Autowired
    private NewsService newsService;
    @Autowired
    private CategoryService categoryService;
    private ShowNews showNews;

    public AddWriteCategory() {
        attributesManager = AttributesManager.getInstance();
        categoryService = new CategoryService();
        newsService = new NewsService();
        showNews = new ShowNews();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        News news = new News();
        Category category = new Category();
        news = attributesManager.parseParametersOfNews(sessionRequestContent, news);
        category.setCategoryName(news.getPageId());
        category.setStatus(StatusEnum.PERSISTED);
        Long saveNewsResult = newsService.save(news);
        Long saveCategoryResult = null;
        try {
            saveCategoryResult = categoryService.save(category);
        } catch (ServiceExcpetion serviceExcpetion) {
            serviceExcpetion.printStackTrace();
        }
        if (saveNewsResult != null && saveNewsResult > WebConstants.Const.ZERO
                && saveCategoryResult != null && saveCategoryResult > WebConstants.Const.ZERO ) {
            showNews.execute(sessionRequestContent);
            page = URLManager.getInstance().getProperty(UrlEnum.URL_MAIN.getUrlCode());
        }else{
            sessionRequestContent.setRequestAttribute("addingNewsError",
                    MessageManager.getInstance().getProperty("message.addingNewsError"));
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
        }
        return page;
    }
}
