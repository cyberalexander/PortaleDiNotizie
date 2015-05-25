package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.util.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Class for working with persistence entity of NEWS
 */
public class NewsDao extends AbstractDao<News> {
    private static final Logger logger = Logger.getLogger(NewsDao.class);

    /**
     * Constructor of MySqlNewsDao.class
     */
    public NewsDao() {
        super();
    }

    @Override
    protected List<News> parseResultSet(Session session) throws PersistException {
        StatusEnum status = StatusEnum.SAVED;
        List<News> list;
        String hql = "SELECT n FROM News n WHERE n.status=:status";
        Query query = session.createQuery(hql).setParameter("status", status);
        list = query.list();
        return list;
    }


    public List<News> getByPersonPK(Long pK) throws PersistException{
        List<News> newses = null;
        try {
            session = getSession();
            StatusEnum status = StatusEnum.SAVED;
            String hql = "SELECT n FROM News n WHERE n.person.personId=:pK and n.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter("pK", pK)
                    .setParameter("status", status);
            newses = query.list();
        } catch (HibernateException e) {
            logger.error("Error get " + newses.isEmpty() + " in Dao " + e);
            throw new PersistException(e);
        }
        return newses;
    }

    public List<News> getByCategory(Long pK) throws PersistException{
        List<News> newses = null;
        try {
            session = getSession();
            StatusEnum status = StatusEnum.SAVED;
            String hql = "SELECT n FROM News n WHERE n.category.categoryId=:pK and n.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter("pK", pK)
                    .setParameter("status", status);
            newses = query.list();
        } catch (HibernateException e) {
            logger.error("Error get " + newses.isEmpty() + " in Dao " + e);
            throw new PersistException(e);
        }
        return newses;
    }

    public List<News> getByDate(Date date) throws PersistException{
        List<News> newses = null;
        try {
            session = getSession();
            StatusEnum status = StatusEnum.SAVED;
            String hql = "SELECT n FROM News n WHERE n.date=:date and n.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter("date", date)
                    .setParameter("status", status);
            newses = query.list();
        } catch (HibernateException e) {
            logger.error("Error get " + newses.isEmpty() + " in Dao " + e);
            throw new PersistException(e);
        }
        return newses;
    }

    /**
     * Get Object by string cretery
     *
     * @param pageId     value of column in database
     * @return object
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    public News getByPageId(String pageId) throws PersistException {
        News news = null;
        try {
            session = getSession();
            StatusEnum status = StatusEnum.SAVED;
            String hql = "SELECT n FROM News n WHERE n.pageId=:pageId and n.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter("pageId", pageId)
                    .setParameter("status", status);
            news = (News) query.uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error get " + news.getClass().getName() + " in Dao " + e);
            throw new PersistException(e);
        }
        return news;
    }

}
