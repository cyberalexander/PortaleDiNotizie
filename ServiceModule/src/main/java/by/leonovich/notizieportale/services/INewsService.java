package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.News;

import java.util.List;

/**
 * Created by alexanderleonovich on 29.04.15.
 */
public interface INewsService {

    News getNewsByPK(Long PK);

    News getNewsByPageId(String pageId);

    List<News> getListOfNewsByPersonId(Long personId);

    List<News> getListOfNewsByCategory(Long categoryId);

    Long saveNews(News news);

    News updateNews(News news);

    News deleteNews(News news);

    void removeNews(News news);

    List<News> getMostPopularNewsList();

    List<News> getNewsByCriteria(int pageNumber, int pageSize, Long categoryId);

    // List<News> getListOfNewsByPageId(String pageId);
}
