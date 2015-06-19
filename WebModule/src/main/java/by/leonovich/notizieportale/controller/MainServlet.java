package by.leonovich.notizieportale.controller;


import by.leonovich.notizieportale.actionfactory.ActionFactory;
import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.util.*;
import com.mysql.jdbc.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by alexanderleonovich on 12.04.15.
 * Servltet-controller of web-application
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/controller"})
public class MainServlet extends HttpServlet {

    private SessionRequestContent sessionRequestContent;

    private HttpSession session;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sessionRequestContent = new SessionRequestContent(request);
        response.setContentType(WebConstants.Const.CONTENT_TYPE);
        session = request.getSession();
        sessionRequestContent.extractValues(request);
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;

// 1.0 - command definition that came from the JSP and the transfer of control of the relevant class implements an interface IActionCommand
        ActionFactory client = new ActionFactory();
        IActionCommand IActionCommand = client.defineCommand(sessionRequestContent);
// 2.0 - a call to implement execute () method and passing parameters to the class-specific command handler
        page = IActionCommand.execute(sessionRequestContent);
        sessionRequestContent.insertAttributes(request);
// 3.0 - method returns the response page // page = null; // поэксперементировать!
        if (page.equals(URLManager.getInstance().getProperty(UrlEnum.URL_INDEX.getUrlCode()))) {
            session.invalidate();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }else if (!StringUtils.isNullOrEmpty(page)) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }else {
            session.setAttribute("nullPage", MessageManager.getInstance().getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        session.invalidate();
    }
}
