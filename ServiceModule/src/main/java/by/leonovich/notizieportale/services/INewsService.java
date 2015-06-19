package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.services.util.exception.ServiceExcpetion;

import java.util.Date;
import java.util.List;

/**
 * Created by alexanderleonovich on 29.04.15.
 */
public interface INewsService extends IService<News> {

    List<News> getNewsesByPersonId(Long personId) throws ServiceExcpetion;

    List<News> getNewsesByCategoryId(Long categoryId) throws ServiceExcpetion;

    List<News> getListOfNewsByCategoryIdNoOrder(Long categoryId) throws ServiceExcpetion;

    List<News> getNewsesByDate(Date date) throws ServiceExcpetion;

    News getNewsByPageId(String pageId) throws ServiceExcpetion;

    List<News> getNewsByCriteria(int pageNumber, int pageSize, Long categoryId) throws ServiceExcpetion;

    List getCountNews(Category category) throws ServiceExcpetion;

    List<News> getMostPopularNewsList() throws ServiceExcpetion;

    /** use this method for receiving list of pagination numbers. This numbers is used in view */
    List<Integer> getList(long numberOfPages, int pageNumber, int newsesPackageSize) throws ServiceExcpetion;

}
