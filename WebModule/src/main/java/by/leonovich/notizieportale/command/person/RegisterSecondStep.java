package by.leonovich.notizieportale.command.person;

import by.leonovich.notizieportale.command.IActionCommand;
import by.leonovich.notizieportale.services.PersonService;
import by.leonovich.notizieportale.util.*;

/**
 * Created by alexanderleonovich on 02.05.15.
 */
@Deprecated
public class RegisterSecondStep implements IActionCommand {

    private AttributesManager attributesManager;
    private PersonService personService;

    public RegisterSecondStep() {
        attributesManager = AttributesManager.getInstance();
        personService = new PersonService();
    }
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        return null;
        /*Person person = null;
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
                *//*try {
                    sessionRequestContent.setSessionAttribute(P_PERSON,
                           person = personService.getByEmail(personDetail.getEmail()));
                } catch (ServiceExcpetion serviceExcpetion) {
                    serviceExcpetion.printStackTrace();
                }*//*
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
        }*/
    }
}
