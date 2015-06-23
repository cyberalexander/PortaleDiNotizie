package by.leonovich.notizieportale.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by alexanderleonovich on 17.06.15.
 */
@Controller
public class WelcomeController {
    private static final Logger logger = Logger.getLogger(WelcomeController.class);


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String welcome(Model model){
        logger.info(" in welcome method. From index.jsp to welcome.jsp");
        return "welcome";
    }

    @RequestMapping(value = "/changelocale", method = RequestMethod.GET)
    public ModelAndView changeLocale(ModelAndView modelAndView) {
        modelAndView.addObject("languageChanged", "LANGUAGE CHANGED!");
        modelAndView.setViewName("welcome");
        return modelAndView;
    }
}
