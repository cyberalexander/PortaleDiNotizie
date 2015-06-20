package by.leonovich.notizieportale.controller;

import by.leonovich.notizieportale.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by alexanderleonovich on 17.06.15.
 */
@Controller
public class PersonController {

    @Autowired
    private IPersonService personService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String goLoginPage(Model model){
        return "login";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String goHello(Model model){
        return "hello";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/protected/person_cabinet", method = RequestMethod.GET)
    public ModelAndView goCabinet() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String goLoginFailed(Model model){
        return "/welcome";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String goLogoutFormSite(Model model){
        return "welcome";
    }


}
