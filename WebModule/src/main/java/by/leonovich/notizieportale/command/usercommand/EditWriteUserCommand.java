package by.leonovich.notizieportale.command.usercommand;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.User;
import by.leonovich.notizieportale.services.CommentaryService;
import by.leonovich.notizieportale.services.UserService;
import by.leonovich.notizieportale.util.*;
import com.mysql.jdbc.StringUtils;

import java.sql.Date;

/**
 * Created by alexanderleonovich on 10.05.15.
 */
public class EditWriteUserCommand implements IActionCommand {

    private UserService userService;

    public EditWriteUserCommand() {
        userService = UserService.getInstance();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        User user = (User) sessionRequestContent.getSessionAttribute(WebConstants.Const.USER);
        if (!StringUtils.isNullOrEmpty(sessionRequestContent.getParameter("new_email"))){
            user.setEmail(sessionRequestContent.getParameter("new_email"));
        }else{
            user.setEmail(sessionRequestContent.getParameter(WebConstants.Const.EMAIL));
        }
        if (!StringUtils.isNullOrEmpty(sessionRequestContent.getParameter("new_password"))){
            user.setPassword(sessionRequestContent.getParameter("new_password"));
        }else{
            user.setPassword(sessionRequestContent.getParameter("password"));
        }
        user.setName(sessionRequestContent.getParameter("name"));
        user.setLastname(sessionRequestContent.getParameter("lastname"));
        Date birthday = Date.valueOf(sessionRequestContent.getParameter("birthday"));
        user.setBirthday(birthday);

        userService.updateUserInformation(user);

        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_USERCABINET.getUrlCode());
        sessionRequestContent.setSessionAttribute(WebConstants.Const.USER, user);
        sessionRequestContent.setRequestAttribute("infoUpdated", MessageManager.getInstance().getProperty("message.user.info.updated"));
        return page;
    }
}
