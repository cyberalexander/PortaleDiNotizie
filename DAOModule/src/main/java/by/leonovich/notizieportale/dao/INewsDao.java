package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.exception.PersistException;

import java.util.Date;
import java.util.List;

/**
 * Created by alexanderleonovich on 09.06.15.
 */
public interface INewsDao extends IGenericDao<News> {

    List<News> getByPersonId(Long pK) throws PersistException;

    List<News> getByCategoryId(Long pK) throws PersistException;

    List<News> getByCategoryIdNoOrder(Long pK) throws PersistException;

    List<News> getByDate(Date date) throws PersistException;

    News getByPageId(String pageId) throws PersistException;

    List<News> getNewsByCriteria(int pageNumber, int pageSize, Long pK) throws PersistException;

    List countNews(Category category) throws PersistException;
}
