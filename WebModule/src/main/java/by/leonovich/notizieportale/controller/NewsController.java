package by.leonovich.notizieportale.controller;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domainto.PersonTO;
import by.leonovich.notizieportale.exception.ServiceLayerException;
import by.leonovich.notizieportale.exception.WebLayerException;
import by.leonovich.notizieportale.services.ICategoryService;
import by.leonovich.notizieportale.services.ICommentaryService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static by.leonovich.notizieportale.util.ServiceConstants.Const.ZERO;
import static by.leonovich.notizieportale.util.WebConstants.Const.*;
import static java.util.Objects.nonNull;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    private ICommentaryService commentaryService;
    @Autowired
    private AttributesManager attributesManager;
    @Autowired
    CloneUtil cloneUtil;

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "delete_news", method = GET)
    public String deleteNews(@ModelAttribute(P_NEWS_ID) String newsId,
                             @ModelAttribute(P_PAGE_ID) String pageId,
                             ModelMap modelMap) throws WebLayerException {
        try {
            News news = newsService.get(Long.valueOf(newsId));
            newsService.delete(news);
        } catch (ServiceLayerException e) {
            logger.error(e);
        }
        modelMap.addAttribute(P_PAGE_ID, pageId);
        return "forward:/shownews.do";
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "add_news", method = GET)
    public String addNews(ModelMap modelMap) throws WebLayerException {
        modelMap.addAttribute(new News());
        modelMap.addAttribute(CATEGORIES, getCategories());
        return "add_news";
    }

    @RequestMapping(value = "add_write_news", method = POST)
    public String addWriteNews(@Validated News news, BindingResult bindingResult,
                               HttpServletRequest request) throws WebLayerException {
        news.setDate(attributesManager.parseDateTimeFromRequest((Date) request.getSession().getAttribute(P_DATE_NOW)));
        long categoryId = Long.valueOf(request.getParameter(P_CATEGORY_ID));
        try {
            Category category = categoryService.get(categoryId);
            news.setPageId(pageIdIncreasingNextLevel(category.getCategoryName(),
                    (Long) newsService.getCountNews(category).get(ZERO)));
            news.setPerson(cloneUtil.unClonePersistentPerson((PersonTO) request.getSession().getAttribute(P_PERSON)));
            news.setCategory(category);
            newsService.save(news);
        } catch (ServiceLayerException e) {
            logger.error(e);
            throw new WebLayerException(e);
        }
        return "redirect:/shownews.do";
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @RequestMapping(value = "edit_news", method = GET)
    public String editNews(@ModelAttribute(P_NEWS_ID) String newsId, Model model) throws WebLayerException {
        try {
            model.addAttribute(newsService.get(Long.valueOf(newsId)));
        } catch (ServiceLayerException e) {
            logger.error(e);
            throw new WebLayerException(e);
        }
        return "edit_news";
    }


    @RequestMapping(value = "edit_write_news", method = POST)
    public String editWriteNews(@Validated News news, BindingResult bindingResult,
                                HttpServletRequest request) throws WebLayerException {
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
        return "redirect:/shownews.do";
    }


    @RequestMapping(value = "shownews", method = {POST, GET})
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
                    && news.getPageId().equals(categoryService.getCategoryByName(
                    news.getPageId()).getCategoryName())) {
                category = categoryService.getCategoryByName(news.getPageId());
                newses = newsService.getNewsByCriteria(getPageNumber(request),
                        PAGES_PACK_SIZE, category.getCategoryId());
                logger.info("\n pageNumber=" + getPageNumber(request) + ", newses.size()=" + newses.size() + "\n");
            }

            /** --- Attributes for response on main page --- */
            modelAndView.getModel().put(NEWS, news);

            modelAndView.getModel().put(NEWSES, newses);

            modelAndView.getModel().put(COMMENTARIES,
                    commentaryService.getCommentariesByNewsId(news.getNewsId()));
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
     * @param key - page_id, what we will increase
     * @return increased page_id
     */
    private String pageIdIncreasingNextLevel(String categoryName, Long key) {
        System.out.println("\n" + categoryName + "_" + key++ + "\n");
        return categoryName + "_" + key++;
    }
}
