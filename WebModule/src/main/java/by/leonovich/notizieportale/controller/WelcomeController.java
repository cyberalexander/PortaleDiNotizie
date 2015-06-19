package by.leonovich.notizieportale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by alexanderleonovich on 17.06.15.
 */
@Controller
@RequestMapping("/index")
public class WelcomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model){
        return "welcome";
    }
}
