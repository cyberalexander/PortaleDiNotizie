package by.leonovich.notizieportale.util;

import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.services.NewsService;

import java.sql.Date;
import java.util.List;

/**
 * Created by alexanderleonovich on 22.04.15.
 * Class for operating attributes before sending them to the user and after the operation was performed with the news
 */
public class AttributesManager {

    private NewsService newsService;
    private static AttributesManager attributesManagerInst;

    /**
     * private constructor
     */
    private AttributesManager() {
        newsService = NewsService.getInstance();
    }


    /**
     * -=SINGLETON=-
     * Method for creating fabric
     * 1. First, you must create an instance factory or get it, and then through it to create Dao objects to the entity
     * over which you plan to perform CRUD operations.
     * @return instance of AttributesManager
     */
    public static synchronized AttributesManager getInstance(){
        if (attributesManagerInst == null){
            attributesManagerInst = new AttributesManager();
        }
        return attributesManagerInst;
    }

    /**
     *  The method in which the attributes of the session to fill the filling page after running the removal of the news.
     * Get a list of news from the last page before deleting news and delete or add to the list of the news
     * We have added or removed in the method execute (), one of the class command
     * @param sesReqContent - HttpServletRequest object interface for operating the session attributes
     * @param news - Adding / removing / edited news
     * @param command - line with the team to perform a specific action on the attributes of the session
     */
    public  void setAtributesForResponse(SessionRequestContent sesReqContent, News news, String command) {
        List<News> newsList = (List<News>) sesReqContent.getSessionAttribute("newsList");
// depending on the received command or delete or add any change in the list of news News.
        if (command.equals("addwrite")){
            newsList.add(news);
        }else if (command.equals("delete")) {
            newsList.remove(news);
        }else if (command.equals("edit")) {
            for (News element : newsList) {
                if (element.getId() == news.getId()) {
                    newsList.remove(element);
                    break;
                }
            }
            newsList.add(news);
        }
// Object news assign a link to the main page headings, which appear to us after operations NEWS
        news = newsService.getNewsByPageId(WebConstants.Const.F_PAGE_ID,news.getParent_id());

        sesReqContent.setSessionAttribute("news", news);
        sesReqContent.setSessionAttribute("newsList", newsList);
    }


    /**
     * Get attributes and parameters from request for adding or updating News-object
     * @param sessionRequestContent - object of class for saving request and session attributes
     * @param news object of NEws persistence
     * @return object of News with added parameters from request
     */
    public News parseParametersOfNews(SessionRequestContent sessionRequestContent, News news) {
// Convert String date from parameter of news to Date date
        Date date = Date.valueOf(sessionRequestContent.getParameter("date"));
// Set in news-variable parameters from request
        news.setPageId(sessionRequestContent.getParameter("page_id"));
        news.setParent_id(sessionRequestContent.getParameter("parent_id"));
        news.setTitle(sessionRequestContent.getParameter("title"));
        news.setMenuTitle(sessionRequestContent.getParameter("menu_title"));
        news.setUser_id(Integer.parseInt(sessionRequestContent.getParameter("user_id")));
        news.setDate(date);
        news.setAnnotation(sessionRequestContent.getParameter("annotation"));
        news.setContent(sessionRequestContent.getParameter("content"));
        return news;
    }

    /**
     * Get attributes and parameters from request for adding or updating User-object
     * @param sessionRequestContent - object of class for saving request and session attributes
     * @param person object of User persistence
     * @return object of User with added parameters from request
     */
    public Person parseParametersOfUser(SessionRequestContent sessionRequestContent, Person person) {
// Convert String birthday from parameter of user to Date birthday
        Date birthday = Date.valueOf(sessionRequestContent.getParameter("birthday"));
// Set in news-variable parameters from request
        person.setName(sessionRequestContent.getParameter("name"));
        person.setSurname(sessionRequestContent.getParameter("lastname"));
        person.setEmail(sessionRequestContent.getParameter("email"));
        person.setPassword(sessionRequestContent.getParameter("password"));
        person.setBirthday(birthday);
        person.setRole(sessionRequestContent.getParameter("role"));
        return person;
    }

}
