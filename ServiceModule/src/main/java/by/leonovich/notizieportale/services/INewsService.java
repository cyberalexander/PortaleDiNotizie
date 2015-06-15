package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;

import java.util.Date;
import java.util.List;

/**
 * Created by alexanderleonovich on 29.04.15.
 */
public interface INewsService extends IService<News> {

    List<News> getNewsesByPersonId(Long personId);

    List<News> getNewsesByCategoryId(Long categoryId);

    List<News> getListOfNewsByCategoryIdNoOrder(Long categoryId);

    List<News> getNewsesByDate(Date date);

    News getNewsByPageId(String pageId);

    List<News> getNewsByCriteria(int pageNumber, int pageSize, Long categoryId);

    List getCountNews(Category category);

    List<News> getMostPopularNewsList();

    /** use this method for receiving list of pagination numbers. This numbers is used in view */
    List<Integer> getList(long numberOfPages, int pageNumber, int newsesPackageSize);

}
