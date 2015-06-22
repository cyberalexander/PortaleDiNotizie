package by.leonovich.notizieportale.controller;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.exception.WebLayerException;
import by.leonovich.notizieportale.services.ICategoryService;
import by.leonovich.notizieportale.services.INewsService;
import by.leonovich.notizieportale.services.exception.ServiceLayerException;
import by.leonovich.notizieportale.util.AttributesManager;
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
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.leonovich.notizieportale.services.util.ServiceConstants.Const.ZERO;
import static by.leonovich.notizieportale.util.WebConstants.Const.*;
import static java.util.Objects.nonNull;

/**
 * Created by alexanderleonovich on 20.06.15.
 */
@Controller
public class NewsController {
    private static final Logger logger = Logger.getLogger(NewsController.class);

    @Autowired
    private INewsService newsService;
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    AttributesManager attributesManager;

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "add_news", method = RequestMethod.GET)
    public String addNews(HttpServletRequest request, Model model) throws WebLayerException {
        model.addAttribute(new News());
        request.setAttribute("categories", getCategories());
        return "add_news";
    }

    @RequestMapping(value = "add_write_news", method = RequestMethod.POST)
    public String addWriteNews(@Validated News news,
                               BindingResult bindingResult, HttpServletRequest request) throws WebLayerException {
        ModelAndView modelAndView = new ModelAndView();
        news.setDate((Date) request.getSession().getAttribute(P_DATE_NOW));
        long categoryId = Long.valueOf(request.getParameter(P_CATEGORY_ID));
        long personId = Long.valueOf(request.getParameter(P_PERSON_ID));
        logger.info("\n" + news.toString() + "\n");
        try {
            Category category = categoryService.get(categoryId);
            news.setPageId(pageIdIncreasingNextLevel(category.getCategoryName(), (Long) newsService.getCountNews(category).get(ZERO)));
            news.setPerson((Person) request.getSession().getAttribute(P_PERSON));
            news.setCategory(category);
            long pk = newsService.save(news);
            System.out.println("\n" + pk + "\n");
        } catch (ServiceLayerException e) {
            logger.error(e);
            throw new WebLayerException(e);
        }
        modelAndView.addObject("newsAdded", "Congrats! News page is ADDED successfull!");
        return "test_page";
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "edit_news", method = RequestMethod.POST)
    public String editNews(HttpServletRequest request, Model model) throws WebLayerException {
        Long newsId = Long.parseLong(request.getParameter(P_NEWS_ID));
        try {
            model.addAttribute(newsService.get(newsId));
        } catch (ServiceLayerException e) {
            logger.error(e);
            throw new WebLayerException(e);
        }
        return "edit_news";
    }


    @RequestMapping(value = "edit_write_news", method = RequestMethod.POST)
    public String editWriteNews(@Validated News news,
                                BindingResult bindingResult, HttpServletRequest request) throws WebLayerException {
        ModelAndView modelAndView = new ModelAndView();
        news.setDate((Date) request.getSession().getAttribute(P_DATE_NOW));
        Long categoryId = Long.valueOf(request.getParameter(P_CATEGORY_ID));
        Long personId = Long.valueOf(request.getParameter(P_PERSON_ID));
        logger.info(news.toString());
        try {
            newsService.update(news, categoryId, personId);
        } catch (ServiceLayerException e) {
            logger.error(e);
            throw new WebLayerException(e);
        }
        modelAndView.addObject("newsUpdated", "Congrats! News page is updated successfull!");
        return "redirect:/shownews.do";
    }


    @RequestMapping(value = "shownews", method = RequestMethod.GET)
    public ModelAndView showNews(HttpServletRequest request, ModelAndView modelAndView) {
        Category category = null;
        List<News> newses = null;
        News news = null;
        try {
            try {
                news = newsService.getNewsByPageId(getPageId(request));
            } catch (ServiceLayerException serviceLayerException) {
                logger.error(serviceLayerException);
            }
            if (nonNull(categoryService.getCategoryByName(news.getPageId()))
                    && news.getPageId().equals(categoryService.getCategoryByName(news.getPageId()).getCategoryName())) {
                category = categoryService.getCategoryByName(news.getPageId());
                newses = newsService.getNewsByCriteria(getPageNumber(request), PAGES_PACK_SIZE, category.getCategoryId());
                logger.info("\n pageNumber=" + getPageNumber(request) + ", newses.size()=" + newses.size() + "\n");
            }

            /** --- Attributes for response on main page --- */
            modelAndView.getModel().put(NEWS, news);

            modelAndView.getModel().put(NEWSES, newses);

            modelAndView.getModel().put(COMMENTARIES,
                    attributesManager.getCommentariesByNewsId(news));
            List<News> list = newsService.getMostPopularNewsList();
            logger.info(list.size() + " " + list.get(0).getMenuTitle());
            modelAndView.getModel().put(POPULAR_NEWSES, list);
            modelAndView.getModel().put(CATEGORIES, getCategories());

            modelAndView.getModel().put(PAGINATOR_LIST,
                    newsService.getList((Long) newsService.getCountNews(category).get(ZERO),
                            getPageNumber(request), PAGES_PACK_SIZE));
            /** ---------------------------------------------- */

        } catch (ServiceLayerException serviceLayerException) {
            logger.error(serviceLayerException);
        }
        modelAndView.setViewName("main");
        return modelAndView;

    }

    /**
     * Method for getting list with categories
     *
     * @return List<Category>
     */
    private List<Category> getCategories() {
        List<Category> categories = null;
        try {
            categories = categoryService.getCategories();
        } catch (ServiceLayerException serviceLayerException) {
            logger.error(serviceLayerException);
        }
        categories.remove(ZERO);
        return categories;
    }

    private String getPageId(HttpServletRequest request) {
        String pageId;
        if (null != request.getParameter(P_PAGE_ID)) {
            return pageId = request.getParameter(P_PAGE_ID);
        } else {
            return pageId = MAIN;
        }
    }

    private int getPageNumber(HttpServletRequest request) {
        if (nonNull(request.getParameter(P_PAGE_NUMBER))
                && Integer.parseInt(request.getParameter(P_PAGE_NUMBER)) != ZERO) {
            return Integer.parseInt(request.getParameter(P_PAGE_NUMBER));
        } else {
            return ONE;
        }
    }

    /**
     * Method for automatic increasing page_id
     *
     * @param pageId - page_id, what we will increase
     * @return increased page_id
     */
    private String pageIdIncreasing(String pageId) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(pageId);
        String idOfPage = null;
        int startIndex = ZERO;
        while (m.find()) {
            idOfPage = m.group();
            startIndex = m.start();
        }
        long number = Long.parseLong(idOfPage);
        number++;
        pageId = pageId.substring(ZERO, startIndex);
        pageId = pageId + String.valueOf(number);
        return pageId;
    }

    private String pageIdIncreasingNextLevel(String categoryName, Long key) {
        System.out.println("\n" + categoryName + "_" + key++ + "\n");
        return categoryName + "_" + key++;
    }
}
