package by.leonovich.notizieportale.command.person;

import static by.leonovich.notizieportale.util.WebConstants.Const.*;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.services.IPersonService;
import by.leonovich.notizieportale.services.PersonService;
import by.leonovich.notizieportale.util.*;

/**
 * Created by alexanderleonovich on 18.04.15.
 * Command class where checking user, when he autorizated
 */
public class LoginPerson implements IActionCommand {

    private IPersonService personService;

    public LoginPerson() {
        personService = PersonService.getInstance();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        // extracting from the request login and password
        String email = sessionRequestContent.getParameter(EMAIL);
        String pass = sessionRequestContent.getParameter(PASSWORD);
        // checking login and password
        if (personService.checkPerson(email, pass)) {
            Person person = personService.getByEmail(email);
            sessionRequestContent.setSessionAttribute(P_PERSON, person);
            // determination url-path to person_cabinet.jsp
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_PERSONCABINET.getUrlCode());
        } else {
            sessionRequestContent.setRequestAttribute("errorLoginPassMessage",
                    MessageManager.getInstance().getProperty("message.loginerror"));
            page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_LOGIN.getUrlCode());
        }
        return page;
    }

}

