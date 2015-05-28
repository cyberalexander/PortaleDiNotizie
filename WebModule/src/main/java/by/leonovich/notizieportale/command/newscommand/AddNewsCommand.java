package by.leonovich.notizieportale.command.newscommand;

import static by.leonovich.notizieportale.util.WebConstants.Const;

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
        String pageId;

        // get newsList from session
        List<News> newses = (List<News>) sessionRequestContent.getSessionAttribute(Const.NEWSES);
        News news = (News) sessionRequestContent.getSessionAttribute(Const.NEWS);
        if (newses.size() > Const.ZERO && !(Const.MAIN.equals(news.getPageId()))) {
            // get page_id from last element in newsList
            pageId = newses.get(newses.size() - 1).getPageId();
            // --- increasing number of page_id --------------------
            pageId = pageIdIncreasing(pageId);
            //-----------------------------------------------------
            sessionRequestContent.setSessionAttribute(Const.P_PAGE_ID, pageId);
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
            return page;
        } else if (newses.size() == WebConstants.Const.ZERO) {
            pageId = news.getPageId() + Const.ONE;
            sessionRequestContent.setSessionAttribute(Const.P_PAGE_ID, pageId);
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_ADD_NEWS.getUrlCode());
            return page;
        } else if(Const.MAIN.equals(news.getPageId())){
            sessionRequestContent.removeSessionAttribute(Const.P_PAGE_ID);
            sessionRequestContent.setRequestAttribute(Const.P_CATEGORY, news.getPageId());
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
        int number = Integer.parseInt(idOfPage);
        number++;
        pageId = pageId.substring(WebConstants.Const.ZERO, startIndex);
        pageId = pageId + String.valueOf(number);
        return pageId;
    }
}
