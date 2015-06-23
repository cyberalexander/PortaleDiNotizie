package by.leonovich.notizieportale.controller;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.domainto.PersonDetailTO;
import by.leonovich.notizieportale.domainto.PersonTO;
import by.leonovich.notizieportale.exception.WebLayerException;
import by.leonovich.notizieportale.services.IPersonService;
import by.leonovich.notizieportale.exception.ServiceLayerException;
import by.leonovich.notizieportale.util.AttributesManager;
import by.leonovich.notizieportale.util.WebConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

import static by.leonovich.notizieportale.util.WebConstants.Const.*;
import static com.mysql.jdbc.StringUtils.isNullOrEmpty;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by alexanderleonovich on 17.06.15.
 */
@Controller
public class PersonController {
    private static final Logger logger = Logger.getLogger(PersonController.class);

    @Autowired
    private IPersonService personService;
    @Autowired
    private AttributesManager attributesManager;

    @RequestMapping(value = "login", method = GET)
    public String goLoginPage(){
        return "login";
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "person_cabinet", method = GET)
    public String goCabinet(HttpServletRequest request, Principal principal) throws WebLayerException {
        PersonTO personTo;
        try {
            personTo = personService.getByEmail(principal.getName());
            logger.info("\n" + personTo.toString() + "\n");
        } catch (ServiceLayerException e) {
            logger.error(e);
            throw new WebLayerException(e);
        }
        request.getSession().setAttribute(P_PERSON, personTo);
        request.getSession().setAttribute(PERSONTYPE, personTo.getPersonDetailTO().getRole());
        return "person_cabinet";
    }

    @RequestMapping(value = "loginfailed", method = GET)
    public String goLoginFailed(Model model) {
        model.addAttribute("loginFailed", "Fail of login on site. \n Email or password is ucorrect. Please try again.");
        return "welcome";
    }


    @RequestMapping(value = "logout", method = GET)
    public String goLogoutFormSite(){
        return "welcome";
    }

    @RequestMapping(value = "addperson", method = GET)
    public String addPersonFirstStep(Model model) {
        model.addAttribute(new PersonTO());
        return "registration_first";
    }

    @RequestMapping(value = "addperson_second_step", method = POST)
    public String addPersonAfterFirstStep(@Validated PersonTO personTo, BindingResult bindingResult, HttpSession session, Model model) {
        session.setAttribute("transientPerson", personTo);
        model.addAttribute(new PersonDetailTO());
        return "registration_second";
    }

    @RequestMapping(value = "addwriteperson", method = POST)
    public ModelAndView addWritePerson(@Validated PersonDetailTO personDetailTo,
                                       BindingResult bindingResult, HttpServletRequest request) throws WebLayerException{
        ModelAndView modelAndView = new ModelAndView();
        PersonTO personTo = (PersonTO) request.getSession().getAttribute(TRANSIENT_PERSON);
        request.getSession().removeAttribute(TRANSIENT_PERSON);
        personDetailTo.setBirthday(attributesManager.parseDateFromRequest(request.getParameter(P_BIRTHDAY)));
        try {
            personTo.setPersonDetailTO(personDetailTo);
            personDetailTo.setPersonTO(personTo);
            personService.savePersonTo(personTo);
        } catch (ServiceLayerException e) {
            logger.error(e);
            throw new WebLayerException(e);
        }
        modelAndView.addObject("registrationSucces", "Congrats! Please, authrizate on site");
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    @RequestMapping(value = "back_first_step", method = GET)
    public String backToFirstStep(HttpSession session, Model model) {
        model.addAttribute(session.getAttribute(TRANSIENT_PERSON));
        return "registration_first";
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "editperson", method = GET)
    public String editPerson(){
        return "edit_person_info";
    }


    @RequestMapping(value = "editwriteperson", method = POST)
    public String editWritePerson(@ModelAttribute(P_NAME) String name,
                                  @ModelAttribute(P_SURNAME) String surname,
                                  @ModelAttribute(P_BIRTHDAY) String birthday,
                                  @ModelAttribute(P_NEW_EMAIL) String newEmail,
                                  @ModelAttribute(P_NEW_PASSWORD) String newPassword,
                                  HttpServletRequest request) throws WebLayerException{
        PersonTO personTo = (PersonTO) request.getSession().getAttribute(P_PERSON);
        if (!isNullOrEmpty(request.getParameter(newEmail))) personTo.getPersonDetailTO().setEmail(newEmail);
        if (!isNullOrEmpty(request.getParameter(newPassword))) personTo.getPersonDetailTO().setPassword(newPassword);
        personTo.setName(name);
        personTo.setSurname(surname);
        personTo.getPersonDetailTO().setBirthday(attributesManager.parseDateFromRequest(birthday));
        try {
            personService.updatePersonTo(personTo);
        } catch (ServiceLayerException e) {
            logger.error(e);
            throw new WebLayerException(e);
        }
        request.setAttribute("personInfoUpdated", "Person-info is succesfully updated");
        return "person_cabinet";
    }

}
