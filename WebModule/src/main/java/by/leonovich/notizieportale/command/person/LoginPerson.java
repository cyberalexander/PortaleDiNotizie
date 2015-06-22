package by.leonovich.notizieportale.command.person;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.services.PersonService;
import by.leonovich.notizieportale.util.*;
import org.springframework.context.ApplicationContext;

/**
 * Created by alexanderleonovich on 18.04.15.
 * Command class where checking user, when he autorizated
 */
@Deprecated
public class LoginPerson implements IActionCommand {

    private PersonService personService;
    private ApplicationContext context;

    public LoginPerson() {
        personService = new PersonService();
        /*context = new ClassPathXmlApplicationContext(new String[]{"dispatcher-servlet.xml"});
        personService = (PersonService) context.getBean("personService");*/
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        /*// extracting from the request login and password
        String email = sessionRequestContent.getParameter(EMAIL);
        String pass = sessionRequestContent.getParameter(PASSWORD);
        // checking login and password
        try {
            if (personService.checkPerson(email, pass)) {
                Person person = personService.getByEmail(email);
                sessionRequestContent.setSessionAttribute(P_PERSON, person);
                // determination url-path to person_cabinet.jsp
                page = URLManager.getInstance().getProperty(UrlEnum.URL_PERSONCABINET.getUrlCode());
            } else {
                sessionRequestContent.setRequestAttribute("errorLoginPassMessage",
                        MessageManager.getInstance().getProperty("message.loginerror"));
                page = URLManager.getInstance().getProperty(UrlEnum.URL_LOGIN.getUrlCode());
            }
        } catch (ServiceExcpetion serviceExcpetion) {
            serviceExcpetion.printStackTrace();
        }*/
        return page;
    }

}

