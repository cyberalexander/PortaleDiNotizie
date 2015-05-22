package by.leonovich.notizieportale.command.newscommand;


import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.services.*;
import by.leonovich.notizieportale.util.*;

import java.util.List;

/**
 * Created by alexanderleonovich on 19.04.15.
 * Show news command
 */
public class ShowNewsCommand implements IActionCommand {

    private INewsService newsService;
    private ICommentaryService commentaryService;

    public ShowNewsCommand() {
        newsService = NewsService.getInstance();
        commentaryService = CommentaryService.getInstance();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        /*String page_id;
        List newsList;

        if (sessionRequestContent.getParameter(WebConstants.Const.PARAM_PAGE_ID) != null) {
// Если в параметре page_id есть значение, мы выводим информацию с той самой page (значение page_id)
            page_id = sessionRequestContent.getParameter(WebConstants.Const.PARAM_PAGE_ID);
        } else {
// Если параметр page_id пуст, мы выводим информацию с main page (Главной страницы портала)
            page_id = "main";
        }
        // Создаем переменую news и загружаем в нее страницу с новостью из СУБД
        News news = newsService.getNewsByPageId(WebConstants.Const.F_PAGE_ID, page_id);
// Если parent_id у загруженной новости пуст (главная страница(main page)) или данный параметр соотвутствует "main"
        if (news.getParent_id() == null || news.getParent_id().isEmpty() || news.getParent_id().equals("main")) {
//то в список новостей попадают те новости, у которых column "parent_id" equals параметру page_id (если page_id = main, то в список попадут все новости, у которых parent_id=main)
            newsList = newsService.getListOfNewsByStringCretery(WebConstants.Const.F_PARENT_ID, page_id);
        }else{
// иначе в список новостей попадают те новости, "parent_id" которых равен "parent_id" страницы новостей
            newsList = newsService.getListOfNewsByStringCretery(WebConstants.Const.F_PARENT_ID, news.getParent_id());
        }
*//** Comments attributes getting for response *//*
        List<Commentary> commentList = commentaryService.getCommentsByNewsIdorAuthorId(WebConstants.Const.F_NEWS_ID, news.getId());
*//** Most popular news attributes getting for response *//*
        List<News> listPopNews = newsService.getMostPopularNewsList();

        sessionRequestContent.setSessionAttribute("news", news);
        sessionRequestContent.setSessionAttribute("newsList", newsList);
        sessionRequestContent.setSessionAttribute("commentList", commentList);
        sessionRequestContent.setSessionAttribute("listPopNews", listPopNews);
        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_MAIN.getUrlCode());
        return page;*/
        return null;
    }
}
