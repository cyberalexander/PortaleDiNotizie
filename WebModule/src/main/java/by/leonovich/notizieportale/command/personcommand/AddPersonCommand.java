package by.leonovich.notizieportale.command.personcommand;

import static by.leonovich.notizieportale.util.WebConstants.Const;
import static by.leonovich.notizieportale.util.WebConstants.Const.P_PERSON;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.services.PersonService;
import by.leonovich.notizieportale.util.*;
import com.mysql.jdbc.StringUtils;

/**
 * Created by alexanderleonovich on 02.05.15.
 */
public class AddPersonCommand implements IActionCommand {

    private AttributesManager attributesManager;
    private PersonService personService;

    public AddPersonCommand() {
        attributesManager = AttributesManager.getInstance();
        personService = PersonService.getInstance();
    }
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;

        Person person = new Person();
        person = attributesManager.parseParametersOfPerson(sessionRequestContent, person);
        if (!(StringUtils.isNullOrEmpty(person.getPersonDetail().getEmail()))
                && !(StringUtils.isNullOrEmpty(person.getPersonDetail().getPassword()))) {
            boolean operationResult = personService.registerNewPerson(person);
            if (operationResult == true) {
                sessionRequestContent.setSessionAttribute(P_PERSON,
                        person = personService.getPersonByEmail(person.getPersonDetail().getEmail()));
                sessionRequestContent.setSessionAttribute(Const.PERSONTYPE, person.getPersonDetail().getRole());
                return page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_PERSONCABINET.getUrlCode());
            } else {
                sessionRequestContent.setRequestAttribute("duplicateEmail", MessageManager.getInstance().getProperty("message.duplicateEmail"));
                return page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_REGISTRATION.getUrlCode());
            }
        }else{
            sessionRequestContent.setRequestAttribute("nullemailorpassword", MessageManager.getInstance().getProperty("message.nullemailorpassword"));
            return page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_REGISTRATION.getUrlCode());
        }
    }
}
