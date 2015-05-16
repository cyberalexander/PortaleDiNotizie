package by.leonovich.notizieportale.filter;

import by.leonovich.notizieportale.domain.User;
import by.leonovich.notizieportale.util.MessageManager;
import by.leonovich.notizieportale.util.URLManager;
import by.leonovich.notizieportale.util.UrlEnum;
import by.leonovich.notizieportale.util.WebConstants;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by alexanderleonovich on 26.04.15.
 * Autentification filter for requests to MainServlet
 */
@WebFilter(filterName = "ServletSecurityFilter", urlPatterns = { "/controller" }, servletNames = { "MainServlet" })
public class ServletSecurityFilter implements Filter {

    private ClientType type = null;
    private static final Logger logger = Logger.getLogger(ServletSecurityFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(WebConstants.Const.USER);

        if (user == null) {
            type = ClientType.GUEST;
            if ((WebConstants.Const.EDIT_NEWS.equals(request.getParameter(WebConstants.Const.COMMAND)))
                    || (WebConstants.Const.ADD_NEWS.equals(request.getParameter(WebConstants.Const.COMMAND)))
                    || (WebConstants.Const.DELETE_NEWS.equals(request.getParameter(WebConstants.Const.COMMAND)))) {

                request.setAttribute("accessDenied", MessageManager.getInstance().getProperty("message.accessDenied"));
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_LOGIN.getUrlCode()));
                dispatcher.forward(request, response);
                return;
            }
        }else if (user.getId() != null && user.getRole().equals(WebConstants.Const.ADMIN)) {
                type = ClientType.ADMINISTRATOR;
        }else if (user.getId() != null && user.getRole().equals(WebConstants.Const.USER)){
                type = ClientType.USER;
        }
        session.setAttribute(WebConstants.Const.USERTYPE, type);
        logger.info("USERTYPE - " + request.getSession().getAttribute(WebConstants.Const.USERTYPE) + "; USER - " + request.getSession().getAttribute(WebConstants.Const.USER));

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
