package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.util.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.util.DaoConstants.Const;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
import java.util.List;

import static by.leonovich.notizieportale.domain.util.StatusEnum.*;
import static by.leonovich.notizieportale.util.DaoConstants.Const.*;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Class for working with persistence entity of NEWS
 */
public class NewsDao extends AbstractDao<News> {
    private static final Logger logger = Logger.getLogger(NewsDao.class);

    /**
     * Constructor of NewsDao.class
     */
    public NewsDao() {
        super();
    }

    @Override
    protected List<News> parseResultSet(Session session) throws PersistException {
        Criteria criteria = session.createCriteria(News.class);
        criteria.add(Restrictions.eq(STATUS, SAVED));
        List<News> result = criteria.list();
        return result;
    }


    public List<News> getByPersonPK(Long pK) throws PersistException{
        List<News> newses = null;
        try {
            Session session = getSession();
            StatusEnum status = SAVED;
            String hql = "SELECT n FROM News n WHERE n.person.personId=:pK and n.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter("pK", pK)
                    .setParameter(STATUS, status);
            newses = query.list();
        } catch (HibernateException e) {
            logger.error("Error get " + newses.isEmpty() + " in Dao " + e);
            throw new PersistException(e);
        }
        return newses;
    }

    public List<News> getByCategoryPK(Long pK) throws PersistException{
        List<News> newses = null;
        try {
            Session session = getSession();
            StatusEnum status = SAVED;
            String hql = "SELECT n FROM News n WHERE n.category.categoryId=:pK and n.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter(PRIMARY_KEY, pK)
                    .setParameter(STATUS, status);
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
            Session session = getSession();
            StatusEnum status = SAVED;
            String hql = "SELECT n FROM News n WHERE n.date=:date and n.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter(DATE, date)
                    .setParameter(STATUS, status);
            newses = query.list();
        } catch (HibernateException e) {
            logger.error("Error get " + newses.isEmpty() + " in Dao " + e);
            throw new PersistException(e);
        }
        return newses;
    }

    /**
     * Get Object by string cretery
     * @param pageId     value of column in database
     * @return object
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    public News getByPageId(String pageId) throws PersistException {
        News news = null;
        try {
            Session session = getSession();
            StatusEnum status = SAVED;
            String hql = "SELECT n FROM News n WHERE n.pageId=:pageId and n.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter(PAGE_ID, pageId)
                    .setParameter(STATUS, status);
            news = (News) query.uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error get " + news.getClass().getName() + " in Dao " + e);
            throw new PersistException(e);
        }
        return news;
    }

    public List<News> getNewsByCriteria(int pageNumber, int pageSize, Long pK) throws PersistException{
        List<News> newses;
        try {
            Session session = getSession();
            StatusEnum status = SAVED;
            String hql = "SELECT n FROM News n WHERE n.category.categoryId=:pK and n.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter(PRIMARY_KEY, pK)
                    .setParameter(STATUS, status)
                    .setFirstResult((pageNumber - ONE) * pageSize)
                    .setMaxResults(pageSize);
            newses = query.list();
        } catch (HibernateException e) {
            throw new PersistException(e);
        }
        return newses;
    }

    public List countNews(Category category) throws PersistException {
        List result;
        Session session = getSession();
        Criteria criteria = session.createCriteria(News.class);
        criteria.add(Restrictions.eq(STATUS, SAVED));
        criteria.add(Restrictions.eq("category", category));
        criteria.setProjection(Projections.rowCount());
        result = criteria.list();
        /*try {
            session = getSession();
            StatusEnum status = SAVED;
            String hql = "SELECT n count(n) FROM News n WHERE n.category.category=:category and n.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter("category", category)
                    .setParameter(STATUS, status);
            result = (long) query.uniqueResult();
        } catch (HibernateException e) {
            throw new PersistException(e);
        }*/
        return result;
    }
}
