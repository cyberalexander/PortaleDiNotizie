package by.leonovich.notizieportale.util;

import static by.leonovich.notizieportale.util.WebConstants.Const;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.services.CategoryService;
import by.leonovich.notizieportale.services.NewsService;
import by.leonovich.notizieportale.services.PersonService;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by alexanderleonovich on 22.04.15.
 * Class for operating attributes before sending them to the user and after the operation was performed with the news
 */
public class AttributesManager {
    private static final Logger logger = Logger.getLogger(AttributesManager.class);

    private NewsService newsService;
    private CategoryService categoryService;
    private PersonService personService;
    private static AttributesManager attributesManagerInst;

    /**
     * private constructor
     */
    private AttributesManager() {
        newsService = NewsService.getInstance();
        categoryService = CategoryService.getInstance();
        personService = PersonService.getInstance();
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
        List<News> newses = (List<News>) sesReqContent.getSessionAttribute(Const.NEWSES);
// depending on the received command or delete or add any change in the list of news News.
        if (command.equals(Const.FROM_ADDWRITE)){
            newses.add(news);
        }else if (command.equals(Const.FROM_DELETE)) {
            newses.remove(news);
        }else if (command.equals(Const.FROM_EDITWRITE)) {
            for (News element : newses) {
                if (element.getNewsId() == news.getNewsId()) {
                    newses.remove(element);
                    break;
                }
            }
            newses.add(news);
        }
// Object news assign a link to the main page headings, which appear to us after operations NEWS
        news = newsService.getNewsByPageId(news.getCategory().getCategoryName());

        sesReqContent.setSessionAttribute(Const.NEWS, news);
        sesReqContent.setSessionAttribute(Const.NEWSES, newses);
    }


    /**
     * Get attributes and parameters from request for adding or updating News-object
     * @param sessionRequestContent - object of class for saving request and session attributes
     * @param news object of NEws persistence
     * @return object of News with added parameters from request
     */
    public News parseParametersOfNews(SessionRequestContent sessionRequestContent, News news) {
        if (news.getNewsId() == null) {
            Category category = categoryService.getCategoryByName(sessionRequestContent.getParameter("category"));
            Person person = personService.getByPK(Long.parseLong(sessionRequestContent.getParameter("personId")));
            news.setCategory(category);
            news.setPerson(person);
        }
        String date = sessionRequestContent.getParameter("date");
        news.setPageId(sessionRequestContent.getParameter("pageId"));
        news.setTitle(sessionRequestContent.getParameter("title"));
        news.setMenuTitle(sessionRequestContent.getParameter("menuTitle"));
        news.setDate(parseDateFromRequest(date));
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
    public Person parseParametersOfPerson(SessionRequestContent sessionRequestContent, Person person) {
        person.setName(sessionRequestContent.getParameter(Const.P_NAME));
        person.setSurname(sessionRequestContent.getParameter(Const.P_SURNAME));

        PersonDetail personDetail = new PersonDetail();
        personDetail.setEmail(sessionRequestContent.getParameter(Const.P_EMAIL));
        personDetail.setPassword(sessionRequestContent.getParameter(Const.P_PASSWORD));
        personDetail.setBirthday(parseDateFromRequest(sessionRequestContent.getParameter(Const.P_BIRTHDAY)));
        personDetail.setRole(sessionRequestContent.getParameter(Const.ROLE));

        person.setPersonDetail(personDetail);
        personDetail.setPerson(person);
        return person;
    }

    /**
     * Method for parsing parameter date to java.sql.Date
     * @param date parameter date from user layer
     * @return java.sql.Date
     */
    public java.sql.Date parseDateFromRequest(String date) {
        Date dateObj = null;
        SimpleDateFormat formatter = new SimpleDateFormat(Const.DATE_PATTERN);
        try {
            dateObj = formatter.parse(date);
        } catch (ParseException e) {
            logger.error(e);
        }
        return new java.sql.Date(dateObj.getTime());
    }
}
