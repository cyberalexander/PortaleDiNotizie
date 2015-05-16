package by.leonovich.notizieportale.command.usercommand;


import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.User;
import by.leonovich.notizieportale.services.IUserService;
import by.leonovich.notizieportale.services.UserService;
import by.leonovich.notizieportale.util.*;
import org.apache.log4j.Logger;

/**
 * Created by alexanderleonovich on 18.04.15.
 * Command class where checking user, when he autorizated
 */
public class LoginCommand implements IActionCommand {

    private IUserService userService;

    public LoginCommand() {
        userService = UserService.getInstance();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        // extracting from the request login and password
        String email = sessionRequestContent.getParameter(WebConstants.Const.EMAIL);
        String pass = sessionRequestContent.getParameter(WebConstants.Const.PASSWORD);
        // checking login and password
        if (userService.checkUser(email, pass)) {
            System.out.println("bebe");
            User user = userService.authenticationProcess(WebConstants.Const.F_EMAIL, email);
            sessionRequestContent.setSessionAttribute(WebConstants.Const.USER, user);
            // determination url-path to usercabinet.jsp
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_USERCABINET.getUrlCode());
        } else {
            sessionRequestContent.setRequestAttribute("errorLoginPassMessage",
                    MessageManager.getInstance().getProperty("message.loginerror"));
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_LOGIN.getUrlCode());
        }
        return page;
    }

}

