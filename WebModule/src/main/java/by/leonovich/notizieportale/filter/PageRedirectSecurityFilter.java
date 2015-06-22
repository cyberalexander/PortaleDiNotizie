package by.leonovich.notizieportale.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alexanderleonovich on 29.04.15.
 * filter that redirects all direct hits to the start page.
 */
@Deprecated
@WebFilter(filterName = "PageRedirectSecurityFilter", urlPatterns = {"/WEB-INF/view/*"},
        initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp")})
public class PageRedirectSecurityFilter implements Filter {

    private String indexPath;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // the transition to a specified page
        response.sendRedirect(request.getContextPath() + indexPath);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        indexPath = config.getInitParameter("INDEX_PATH");
    }

}
