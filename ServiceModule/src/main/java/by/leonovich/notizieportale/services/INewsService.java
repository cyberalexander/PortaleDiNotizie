package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domainto.PersonTO;
import by.leonovich.notizieportale.exception.ServiceLayerException;

import java.util.Date;
import java.util.List;

/**
 * Created by alexanderleonovich on 29.04.15.
 */
public interface INewsService extends IService<News> {

    List<News> getNewsesByPersonId(Long personId) throws ServiceLayerException;

    List<News> getNewsesByCategoryId(Long categoryId) throws ServiceLayerException;

    List<News> getListOfNewsByCategoryIdNoOrder(Long categoryId) throws ServiceLayerException;

    List<News> getNewsesByDate(Date date) throws ServiceLayerException;

    News getNewsByPageId(String pageId) throws ServiceLayerException;

    List<News> getNewsByCriteria(int pageNumber, int pageSize, Long categoryId) throws ServiceLayerException;

    List getCountNews(Category category) throws ServiceLayerException;

    List<News> getMostPopularNewsList() throws ServiceLayerException;

    /** use this method for receiving list of pagination numbers. This numbers is used in view */
    List<Integer> getList(long numberOfPages, int pageNumber, int newsesPackageSize) throws ServiceLayerException;

    Long update(News news, Long categoryId, Long personId) throws ServiceLayerException;

   /* Long save(News news, Long categoryId, Long personId) throws ServiceLayerException;

    Long saveNews(News news, PersonTO personTo) throws ServiceLayerException;*/
}
