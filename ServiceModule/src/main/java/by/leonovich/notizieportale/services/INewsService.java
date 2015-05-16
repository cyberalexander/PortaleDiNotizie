package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.News;

import java.util.List;

/**
 * Created by alexanderleonovich on 29.04.15.
 */
public interface INewsService {

    News getNewsByPageId(String columnName_PageId, String idOfPage);

    List<News> getListOfNewsByStringCretery(String parent_id, String page_id);

    boolean saveNewsInDataBase(News news);

    News getNewsByPK(Integer id);

    boolean deleteNewsPage(News news);

    void EditNewsPage(News news);

    List<News> getMostPopularNewsList();
}
