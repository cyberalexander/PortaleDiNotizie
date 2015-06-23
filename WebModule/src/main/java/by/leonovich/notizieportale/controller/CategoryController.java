package by.leonovich.notizieportale.controller;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.domainto.PersonTO;
import by.leonovich.notizieportale.exception.ServiceLayerException;
import by.leonovich.notizieportale.exception.WebLayerException;
import by.leonovich.notizieportale.services.ICategoryService;
import by.leonovich.notizieportale.services.INewsService;
import by.leonovich.notizieportale.util.AttributesManager;
import by.leonovich.notizieportale.util.CloneUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

import static by.leonovich.notizieportale.util.ServiceConstants.Const.ZERO;
import static by.leonovich.notizieportale.util.WebConstants.Const.*;
import static by.leonovich.notizieportale.util.WebConstants.Const.P_PERSON;

/**
 * Created by alexanderleonovich on 20.06.15.
 */
@Controller
@Secured({ROLE_ADMIN, ROLE_USER})
public class CategoryController {
    private static final Logger logger = Logger.getLogger(CategoryController.class);

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private AttributesManager attributesManager;
    @Autowired
    private CloneUtil cloneUtil;


    @RequestMapping(value = "add_category", method = RequestMethod.GET)
    public String addCategory(Model model) throws WebLayerException {
        model.addAttribute(new News());
        return "add_category";
    }

    @RequestMapping(value = "add_write_category", method = RequestMethod.POST)
    public String addWriteCategory(@Validated News news, BindingResult bindingResult,
                               HttpServletRequest request) throws WebLayerException {
        Category category = new Category();
        category.setCategoryName(news.getPageId());
        news.setDate(attributesManager.parseDateTimeFromRequest((Date) request.getSession().getAttribute(P_DATE_NOW)));
        try {
            news.setPerson(cloneUtil.unClonePersistentPerson((PersonTO) request.getSession().getAttribute(P_PERSON)));
            news.setCategory(categoryService.get((long) ONE));
            categoryService.saveCategoryNews(category, news);
        } catch (ServiceLayerException e) {
            logger.error(e);
            throw new WebLayerException(e);
        }
        return "redirect:/shownews.do";
    }
}
