package by.leonovich.notizieportale.command.usercommand;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.services.PersonService;
import by.leonovich.notizieportale.util.*;

/**
 * Created by alexanderleonovich on 10.05.15.
 */
public class EditWriteUserCommand implements IActionCommand {

    private PersonService personService;

    public EditWriteUserCommand() {
        personService = PersonService.getInstance();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        /*Person person = (Person) sessionRequestContent.getSessionAttribute(WebConstants.Const.USER);
        if (!StringUtils.isNullOrEmpty(sessionRequestContent.getParameter("new_email"))){
            person.setEmail(sessionRequestContent.getParameter("new_email"));
        }else{
            person.setEmail(sessionRequestContent.getParameter(WebConstants.Const.EMAIL));
        }
        if (!StringUtils.isNullOrEmpty(sessionRequestContent.getParameter("new_password"))){
            person.setPassword(sessionRequestContent.getParameter("new_password"));
        }else{
            person.setPassword(sessionRequestContent.getParameter("password"));
        }
        person.setName(sessionRequestContent.getParameter("name"));
        person.setSurname(sessionRequestContent.getParameter("lastname"));
        Date birthday = Date.valueOf(sessionRequestContent.getParameter("birthday"));
        person.setBirthday(birthday);

        userService.updateUserInformation(person);

        String page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_USERCABINET.getUrlCode());
        sessionRequestContent.setSessionAttribute(WebConstants.Const.USER, person);
        sessionRequestContent.setRequestAttribute("infoUpdated", MessageManager.getInstance().getProperty("message.user.info.updated"));*/
        return null;
    }
}
