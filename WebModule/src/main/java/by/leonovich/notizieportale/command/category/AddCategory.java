package by.leonovich.notizieportale.command.category;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.services.CategoryService;
import by.leonovich.notizieportale.services.ICategoryService;
import by.leonovich.notizieportale.services.INewsService;
import by.leonovich.notizieportale.services.NewsService;
import by.leonovich.notizieportale.util.*;

import static by.leonovich.notizieportale.util.WebConstants.Const.*;
import static by.leonovich.notizieportale.util.WebConstants.Const.P_PAGE_ID;

/**
 * Created by alexanderleonovich on 02.06.15.
 */
public class AddCategory implements IActionCommand {
    private INewsService newsService;
    private ICategoryService categoryService;

    public AddCategory() {
        newsService = NewsService.getInstance();
        categoryService = CategoryService.getInstance();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;

        News news = (News) sessionRequestContent.getSessionAttribute(WebConstants.Const.NEWS);
        if (news.getPageId().equals(MAIN)) {
            System.out.println("\n" + "ADDING CATEGORY" + "\n");

            sessionRequestContent.removeSessionAttribute(P_PAGE_ID);
            sessionRequestContent.setRequestAttribute(P_CATEGORY, news.getPageId());
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_CATEGORY.getUrlCode());
            return page;
        }
        return "\n EXCEPTION \n";
    }
}
