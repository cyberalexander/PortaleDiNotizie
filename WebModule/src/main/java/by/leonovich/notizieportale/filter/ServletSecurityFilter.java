package by.leonovich.notizieportale.filter;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.util.RoleEnum;
import by.leonovich.notizieportale.util.MessageManager;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;
import by.leonovich.notizieportale.util.WebConstants.Const;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.leonovich.notizieportale.util.WebConstants.Const.*;

/**
 * Created by alexanderleonovich on 26.04.15.
 * Autentification filter for requests to MainServlet
 */
@WebFilter(filterName = "ServletSecurityFilter", urlPatterns = { "/controller" }, servletNames = { "MainServlet" })
public class ServletSecurityFilter implements Filter {

    private RoleEnum type = null;
    private static final Logger logger = Logger.getLogger(ServletSecurityFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        Person person = (Person) session.getAttribute(P_PERSON);

        if (person == null) {
            type = RoleEnum.GUEST;
            if ((Const.EDIT_NEWS.equals(request.getParameter(COMMAND)))
                    || (Const.ADD_NEWS.equals(request.getParameter(COMMAND)))
                    || (Const.DELETE_NEWS.equals(request.getParameter(COMMAND)))) {

                request.setAttribute("accessDenied", MessageManager.getInstance().getProperty("message.access.denied"));
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_LOGIN.getUrlCode()));
                dispatcher.forward(request, response);
                return;
            }
        }else if (person.getPersonId() != null && person.getPersonDetail().getRole().equals(ADMIN)) {
                type = RoleEnum.ADMIN;
        }else if (person.getPersonId() != null && person.getPersonDetail().getRole().equals(PERSON)){
                type = RoleEnum.USER;
        }
        session.setAttribute(PERSONTYPE, type);
        logger.info("PERSONTYPE - " + request.getSession().getAttribute(PERSONTYPE) + "; PERSON - " + request.getSession().getAttribute(P_PERSON));

         //pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * Init method of filter "ServletSecurityFilter"
     * @param config configuration parameters for filter
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {

    }

}
