package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.exception.PersistException;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

/**
 * Created by alexanderleonovich on 09.06.15.
 */
public interface INewsDao extends IGenericDao<News> {

    List<News> getByPersonId(Long pK, Session session) throws PersistException;

    List<News> getByCategoryId(Long pK, Session session) throws PersistException;

    List<News> getByCategoryIdNoOrder(Long pK, Session session) throws PersistException;

    List<News> getByDate(Date date, Session session) throws PersistException;

    News getByPageId(String pageId, Session session) throws PersistException;

    List<News> getNewsByCriteria(int pageNumber, int pageSize, Long pK, Session session) throws PersistException;

    List countNews(Category category, Session session) throws PersistException;
}
