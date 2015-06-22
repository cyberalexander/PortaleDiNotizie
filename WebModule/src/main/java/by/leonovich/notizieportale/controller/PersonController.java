package by.leonovich.notizieportale.controller;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.exception.WebLayerException;
import by.leonovich.notizieportale.services.IPersonService;
import by.leonovich.notizieportale.services.exception.ServiceLayerException;
import by.leonovich.notizieportale.util.AttributesManager;
import by.leonovich.notizieportale.util.WebConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

import static by.leonovich.notizieportale.util.WebConstants.Const.*;
import static com.mysql.jdbc.StringUtils.isNullOrEmpty;

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

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String goLoginPage(){
        return "login";
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "person_cabinet", method = RequestMethod.GET)
    public String goCabinet(HttpServletRequest request, Principal principal) throws WebLayerException{
        Person person = null;
        try {
            person = personService.getByEmail(principal.getName());
        } catch (ServiceLayerException e) {
            logger.error(e);
            throw new WebLayerException(e);
        }
        request.getSession().setAttribute("person", person);
        request.getSession().setAttribute("persontype", person.getPersonDetail().getRole());
        return "person_cabinet";
    }

    @RequestMapping(value = "loginfailed", method = RequestMethod.GET)
    public String goLoginFailed(Model model) {
        model.addAttribute("loginFailed", "Fail of login on site");
        return "welcome";
    }


    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String goLogoutFormSite(){
        return "welcome";
    }

    @RequestMapping(value = "addperson", method = RequestMethod.GET)
    public String addPersonFirstStep(Model model) {
        model.addAttribute(new Person());
        return "registration_first";
    }

    @RequestMapping(value = "addperson_second_step", method = RequestMethod.POST)
    public String addPersonAfterFirstStep(@Validated Person person, BindingResult bindingResult, HttpSession session, Model model) {
        session.setAttribute("transientPerson", person);
        model.addAttribute(new PersonDetail());
        return "registration_second";
    }

    @RequestMapping(value = "addwriteperson", method = RequestMethod.POST)
    public ModelAndView addWritePerson(@Validated PersonDetail personDetail,
                                       BindingResult bindingResult, HttpServletRequest request) throws WebLayerException{
        ModelAndView modelAndView = new ModelAndView();
        Person person = (Person) request.getSession().getAttribute("transientPerson");
        request.getSession().removeAttribute("transientPerson");
        personDetail.setBirthday(attributesManager.parseDateFromRequest(request.getParameter("birthday")));
        logger.info(person.toString() + "\n" + personDetail.toString());
        try {
            person.setPersonDetail(personDetail);
            personDetail.setPerson(person);
            personService.save(person);
        } catch (ServiceLayerException e) {
            logger.error(e);
            throw new WebLayerException(e);
        }
        modelAndView.addObject("registrationSucces", "Congrats! Please, authrizate on site");
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    @RequestMapping(value = "back_first_step", method = RequestMethod.GET)
    public String backToFirstStep(HttpSession session, Model model) {
        model.addAttribute(session.getAttribute("transientPerson"));
        return "registration_first";
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "editperson", method = RequestMethod.GET)
    public String editPerson(){
        return "edit_person_info";
    }


    @RequestMapping(value = "editwriteperson", method = RequestMethod.POST)
    public String editWritePerson(HttpServletRequest request) throws WebLayerException{
        Person person = (Person) request.getSession().getAttribute(P_PERSON);
        if (!isNullOrEmpty(request.getParameter(P_NEW_EMAIL))) {
            person.getPersonDetail().setEmail(request.getParameter(P_NEW_EMAIL));
        }
        if (!isNullOrEmpty(request.getParameter(P_NEW_PASSWORD))) {
            person.getPersonDetail().setPassword(request.getParameter(P_NEW_PASSWORD));
        }
        person.setName(request.getParameter(P_NAME));
        person.setSurname(request.getParameter(P_SURNAME));
        person.getPersonDetail().setBirthday(attributesManager.parseDateFromRequest(request.getParameter(P_BIRTHDAY)));
        try {
            personService.update(person);
        } catch (ServiceLayerException e) {
            logger.error(e);
            throw new WebLayerException(e);
        }
        request.setAttribute("personInfoUpdated", "Person-info is succesfully updated");
        return "person_cabinet";
    }

}
