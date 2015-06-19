package by.leonovich.notizieportale.command.person;

import static by.leonovich.notizieportale.util.WebConstants.Const.PERSONTYPE;
import static by.leonovich.notizieportale.util.WebConstants.Const.P_PERSON;
import static com.mysql.jdbc.StringUtils.isNullOrEmpty;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.services.PersonService;
import by.leonovich.notizieportale.services.util.exception.ServiceExcpetion;
import by.leonovich.notizieportale.util.*;

/**
 * Created by alexanderleonovich on 02.05.15.
 */
public class RegisterSecondStep implements IActionCommand {

    private AttributesManager attributesManager;
    private PersonService personService;

    public RegisterSecondStep() {
        attributesManager = AttributesManager.getInstance();
        personService = new PersonService();
    }
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        Person person = null;
        PersonDetail personDetail = new PersonDetail();
        personDetail = attributesManager.parseParametersOfPersonDetail(sessionRequestContent, personDetail);
        if (!(isNullOrEmpty(personDetail.getEmail()))
                && !(isNullOrEmpty(personDetail.getPassword()))) {
            boolean operationResult = false;
            try {
                operationResult = personService.
                        registerPersonSecondStep(sessionRequestContent.getHttpSession(), personDetail);
            } catch (ServiceExcpetion serviceExcpetion) {
                serviceExcpetion.printStackTrace();
            }
            if (operationResult) {
                try {
                    sessionRequestContent.setSessionAttribute(P_PERSON,
                           person = personService.getByEmail(personDetail.getEmail()));
                } catch (ServiceExcpetion serviceExcpetion) {
                    serviceExcpetion.printStackTrace();
                }
                sessionRequestContent.setSessionAttribute(PERSONTYPE, person.getPersonDetail().getRole());
                return page = URLManager.getInstance().getProperty(UrlEnum.URL_PERSONCABINET.getUrlCode());
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
