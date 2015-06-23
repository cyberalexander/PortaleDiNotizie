package by.leonovich.notizieportale.util;

import static by.leonovich.notizieportale.util.WebConstants.Const;
import static by.leonovich.notizieportale.util.WebConstants.Const.*;
import static java.util.Objects.nonNull;

import by.leonovich.notizieportale.domain.*;
import by.leonovich.notizieportale.domain.enums.RoleEnum;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.services.CategoryService;
import by.leonovich.notizieportale.services.CommentaryService;
import by.leonovich.notizieportale.services.NewsService;
import by.leonovich.notizieportale.services.PersonService;
import by.leonovich.notizieportale.exception.ServiceLayerException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by alexanderleonovich on 22.04.15.
 * Class for operating attributes before sending them to the user and after the operation was performed with the news
 */
@Component
public class AttributesManager {
    private static final Logger logger = Logger.getLogger(AttributesManager.class);

    public AttributesManager() {
    }

    /**
     * Method for parsing parameter date to java.sql.Date
     * @param date parameter date from user layer
     * @return java.sql.Date
     */
    public java.sql.Date parseDateTimeFromRequest(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * Method for parsing parameter date to java.sql.Date
     * @param date parameter date from user layer
     * @return java.sql.Date
     */
    public java.sql.Date parseDateFromRequest(String date) {
        Date dateObj = null;
        SimpleDateFormat formatter = new SimpleDateFormat(Const.BIRTH_DAY_PATTERN);
        try {
            dateObj = formatter.parse(date);
        } catch (ParseException e) {
            logger.error(e);
        }
        return new java.sql.Date(dateObj.getTime());
    }

    /**
     * Method for parsing parameters of commentary
     * @param request HttpServletRequest
     * @return Commentary entity
     */
    public Commentary addCommentary(HttpServletRequest request) {
        Commentary commentary = new Commentary();
        commentary.setComment(request.getParameter(P_CONTENT));
        Date date = (Date) request.getSession().getAttribute(P_DATE_NOW);
        commentary.setDate(parseDateTimeFromRequest(date));
        return commentary;
    }


}
