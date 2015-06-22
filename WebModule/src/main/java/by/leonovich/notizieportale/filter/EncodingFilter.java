package by.leonovich.notizieportale.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Created by alexanderleonovich on 27.04.15.
 * Implementation of the Filter interface for the task, changing coding focusing
 * of the request and response to the encoding specified filter parameter.
 */
@Deprecated
@WebFilter(filterName = "EncodingFilter", urlPatterns = {"/*"}, initParams =
        {@WebInitParam(name= "encoding", value = "UTF-8", description = "Encoding Param")})
public class EncodingFilter implements Filter {

    private String code;

    public void destroy() {
        code = null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String codeRequest = req.getCharacterEncoding();
// setting the encoding parameters of the filter, if not set
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            req.setCharacterEncoding(code);
            resp.setCharacterEncoding(code);
        }
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {
        code = config.getInitParameter("encoding");
    }

}
