package by.leonovich.notizieportale.command.usercommand;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.services.UserService;
import by.leonovich.notizieportale.util.*;

/**
 * Created by alexanderleonovich on 02.05.15.
 */
public class AddUserCommand implements IActionCommand {

    private AttributesManager attributesManager;
    private UserService userService;

    public AddUserCommand() {
        attributesManager = AttributesManager.getInstance();
        userService = UserService.getInstance();
    }
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;

        Person person = new Person();
        person = attributesManager.parseParametersOfUser(sessionRequestContent, person);
        if (person.getEmail() != null && person.getPassword() != null) {
            boolean operationResult = userService.registerNewUser(person);
            if (operationResult == true) {
                sessionRequestContent.setSessionAttribute(WebConstants.Const.USER, userService.getUserByEmail(person.getEmail()));
                page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_USERCABINET.getUrlCode());
            } else {
                sessionRequestContent.setRequestAttribute("duplicateEmail", MessageManager.getInstance().getProperty("message.duplicateEmail"));
                page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_REGISTRATION.getUrlCode());
            }
        }else{
            sessionRequestContent.setRequestAttribute("nullemailorpassword", MessageManager.getInstance().getProperty("message.nullemailorpassword"));
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_REGISTRATION.getUrlCode());
        }


    return page;
    }
}
