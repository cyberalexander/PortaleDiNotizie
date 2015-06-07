package by.leonovich.notizieportale.command.person;

import static by.leonovich.notizieportale.util.WebConstants.Const;
import static by.leonovich.notizieportale.util.WebConstants.Const.PERSONTYPE;
import static by.leonovich.notizieportale.util.WebConstants.Const.P_PERSON;
import static com.mysql.jdbc.StringUtils.isNullOrEmpty;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.services.PersonService;
import by.leonovich.notizieportale.util.*;
import com.mysql.jdbc.StringUtils;

/**
 * Created by alexanderleonovich on 02.05.15.
 */
public class RegisterSecondStep implements IActionCommand {

    private AttributesManager attributesManager;
    private PersonService personService;

    public RegisterSecondStep() {
        attributesManager = AttributesManager.getInstance();
        personService = PersonService.getInstance();
    }
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        Person person;
        PersonDetail personDetail = new PersonDetail();
        personDetail = attributesManager.parseParametersOfPersonDetail(sessionRequestContent, personDetail);
        if (!(isNullOrEmpty(personDetail.getEmail()))
                && !(isNullOrEmpty(personDetail.getPassword()))) {
            boolean operationResult = personService.
                    registerPersonSecondStep(sessionRequestContent.getHttpSession(), personDetail);
            if (operationResult) {
                sessionRequestContent.setSessionAttribute(P_PERSON,
                       person = personService.getPersonByEmail(personDetail.getEmail()));
                sessionRequestContent.setSessionAttribute(PERSONTYPE, person.getPersonDetail().getRole());
                return page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_PERSONCABINET.getUrlCode());
            } else {
                sessionRequestContent.setRequestAttribute("duplicateEmail",
                        MessageManager.getInstance().getProperty("message.duplicateEmail"));
                return page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_REGISTRATION_2.getUrlCode());
            }
        }else{
            sessionRequestContent.setRequestAttribute("nullemailorpassword",
                    MessageManager.getInstance().getProperty("message.nullemailorpassword"));
            return page = URLManager.getInstance().getProperty(UrlEnum.PATH_PAGE_REGISTRATION_2.getUrlCode());
        }
    }
}
