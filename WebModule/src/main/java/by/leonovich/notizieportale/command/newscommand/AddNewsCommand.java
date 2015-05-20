package by.leonovich.notizieportale.command.newscommand;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.util.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexanderleonovich on 19.04.15.
 * Method for getting attributes for add new news-page and to forward() user to addnews page
 */
public class AddNewsCommand implements IActionCommand {

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        String page_id;

        // get newsList from session
        List<News> newsList = (List<News>) sessionRequestContent.getSessionAttribute("newsList");
        News news = (News) sessionRequestContent.getSessionAttribute("news");
        if (newsList.size() > WebConstants.Const.ZERO && !("main".equals(news.getPageId()))) {
            // get page_id from last element in newsList
            page_id = newsList.get(newsList.size() - 1).getPageId();
            // --- increasing number of page_id --------------------
            page_id = pageIdIncreasing(page_id);
            //-----------------------------------------------------
            sessionRequestContent.setSessionAttribute("page_id", page_id);
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
            return page;
        } else if (newsList.size() == WebConstants.Const.ZERO) {
            page_id = news.getPageId() + "_1";
            sessionRequestContent.setSessionAttribute("page_id", page_id);
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
            return page;
        } else if("main".equals(news.getPageId())){
            sessionRequestContent.removeSessionAttribute("page_id");
            sessionRequestContent.setRequestAttribute("parent_id", news.getPageId());
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
            return page;
        }else{
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
            sessionRequestContent.setRequestAttribute("addNewsComErr", MessageManager.getInstance().getProperty("message.addNewsComErr"));
            return page;
        }
    }

    /**
     * Method for automatic increasing page_id
     *
     * @param page_id - page_id, what we will increase
     * @return increased page_id
     */
    private String pageIdIncreasing(String page_id) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(page_id);
        String pageId = null;
        int startIndex = WebConstants.Const.ZERO;
        while (m.find()) {
            pageId = m.group();
            startIndex = m.start();
        }
        int number = Integer.parseInt(pageId);
        number++;
        page_id = page_id.substring(WebConstants.Const.ZERO, startIndex);
        page_id = page_id + String.valueOf(number);
        return page_id;
    }
}
