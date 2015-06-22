package by.leonovich.notizieportale.command.person;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.services.PersonService;
import by.leonovich.notizieportale.services.exception.ServiceLayerException;
import by.leonovich.notizieportale.util.SessionRequestContent;
import by.leonovich.notizieportale.util.URLManager;

import static by.leonovich.notizieportale.util.UrlEnum.PATH_PAGE_REGISTRATION_1;
import static by.leonovich.notizieportale.util.WebConstants.Const.P_ID;
import static by.leonovich.notizieportale.util.WebConstants.Const.P_PERSON;

/**
 * Created by alexanderleonovich on 07.06.15.
 */
@Deprecated
public class RegisterBack implements IActionCommand {

    private PersonService personService;

    public RegisterBack() {
        personService = new PersonService();
    }
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        Person person = null;
        try {
            person = personService.get((Long) sessionRequestContent.getSessionAttribute(P_ID));
        } catch (ServiceLayerException serviceLayerException) {
            serviceLayerException.printStackTrace();
        }
        sessionRequestContent.setSessionAttribute(P_PERSON, person);
        return page = URLManager.getInstance().getProperty(PATH_PAGE_REGISTRATION_1.getUrlCode());
    }
}
