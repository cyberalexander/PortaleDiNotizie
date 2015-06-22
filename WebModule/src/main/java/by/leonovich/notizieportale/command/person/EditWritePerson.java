package by.leonovich.notizieportale.command.person;

import static by.leonovich.notizieportale.util.WebConstants.Const;
import static by.leonovich.notizieportale.util.WebConstants.Const.P_PERSON;
import static com.mysql.jdbc.StringUtils.isNullOrEmpty;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.services.PersonService;
import by.leonovich.notizieportale.services.exception.ServiceLayerException;
import by.leonovich.notizieportale.util.*;


/**
 * Created by alexanderleonovich on 10.05.15.
 */
@Deprecated
public class EditWritePerson implements IActionCommand {

    private PersonService personService;

    public EditWritePerson() {
        personService = new PersonService();
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        Person person = (Person) sessionRequestContent.getSessionAttribute(P_PERSON);
        PersonDetail personDetail = person.getPersonDetail();
        if (!isNullOrEmpty(sessionRequestContent.getParameter(Const.P_NEW_EMAIL))) {
            personDetail.setEmail(sessionRequestContent.getParameter(Const.P_NEW_EMAIL));
        }/* else {
            personDetail.setEmail(sessionRequestContent.getParameter(EMAIL));
        }*/
        if (!isNullOrEmpty(sessionRequestContent.getParameter(Const.P_NEW_PASSWORD))) {
            personDetail.setPassword(sessionRequestContent.getParameter(Const.P_NEW_PASSWORD));
        }/* else {
            personDetail.setPassword(sessionRequestContent.getParameter(Const.PASSWORD));
        }*/
        person.setName(sessionRequestContent.getParameter(Const.P_NAME));
        person.setSurname(sessionRequestContent.getParameter(Const.P_SURNAME));
        personDetail.setBirthday(AttributesManager.getInstance()
                .parseDateFromRequest(sessionRequestContent.getParameter(Const.P_BIRTHDAY)));
        person.setPersonDetail(personDetail);
        try {
            personService.update(person);
        } catch (ServiceLayerException serviceLayerException) {
            serviceLayerException.printStackTrace();
        }

        String page = URLManager.getInstance().getProperty(UrlEnum.URL_PERSONCABINET.getUrlCode());
        sessionRequestContent.setSessionAttribute(P_PERSON, person);
        sessionRequestContent.setRequestAttribute("infoUpdated", MessageManager.getInstance().getProperty("message.user.info.updated"));
        return page;
    }
}
