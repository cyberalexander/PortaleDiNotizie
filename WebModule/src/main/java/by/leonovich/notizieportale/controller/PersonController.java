package by.leonovich.notizieportale.controller;

import by.leonovich.notizieportale.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by alexanderleonovich on 17.06.15.
 */
@Controller
public class PersonController {

    @Autowired
    private IPersonService personService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model){
        return "login";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String goOnWelcome(Model model){
        return "hello";
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String goHello(Model model){
        return "hello";
    }

    @Secured(value = "ADMIN")
    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String goCabinet(Model model){
        return "/protected/person_cabinet";
    }

    @RequestMapping(value = "/login?error", method = RequestMethod.GET)
    public String goError(Model model){
        return "/welcome";
    }


}
