package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static by.leonovich.notizieportale.domain.enums.StatusEnum.*;
import static by.leonovich.notizieportale.util.DaoConstants.Const.*;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Class for working with persistence entity of NEWS
 */
@Repository
public class NewsDao extends AbstractDao<News> implements INewsDao {
    private static final Logger logger = Logger.getLogger(NewsDao.class);

    /**
     * Constructor of NewsDao.class
     */
    @Autowired
    public NewsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     *
     * @param session
     * @return
     * @throws PersistException
     */
    @Override
    protected List<News> parseResultSet(Session session) throws PersistException {
        Criteria criteria = session.createCriteria(News.class);
        criteria.add(Restrictions.eq(STATUS, PERSISTED));
        List<News> result = criteria.list();
        return result;
    }

    /**
     *
     * @param pK
     * @return
     * @throws PersistException
     */
    @Override
    public List<News> getByPersonId(Long pK) throws PersistException{
        List<News> newses;
        try {
            StatusEnum status = PERSISTED;
            String hql = "SELECT n FROM News n WHERE n.person.personId=:pK and n.status=:status";
            Query query = getCurrentSession().createQuery(hql)
                    .setParameter(PRIMARY_KEY, pK)
                    .setParameter(STATUS, status);
            newses = query.list();
        } catch (HibernateException e) {
            logger.error(ERROR_GET_LIST_BY_PERSON_ID + e);
            throw new PersistException(e);
        }
        return newses;
    }

    /**
     *
     * @param pK
     * @return
     * @throws PersistException
     */
    @Override
    public List<News> getByCategoryId(Long pK) throws PersistException{
        List<News> newses;
        try {
            StatusEnum status = PERSISTED;
            String hql = "SELECT n FROM News n WHERE n.category.categoryId=:pK and n.status=:status ORDER BY n.date DESC";
            Query query = getCurrentSession().createQuery(hql)
                    .setParameter(PRIMARY_KEY, pK)
                    .setParameter(STATUS, status);
            newses = query.list();
        } catch (HibernateException e) {
            logger.error(ERROR_GET_LIST_BY_CATEGORY_ID + e);
            throw new PersistException(e);
        }
        return newses;
    }

    /**
     *
     * @param pK
     * @return
     * @throws PersistException
     */
    @Override
    public List<News> getByCategoryIdNoOrder(Long pK) throws PersistException  {
        List<News> newses = null;
        try {
            String hql = "SELECT n FROM News n WHERE n.category.categoryId=:pK";
            Query query = getCurrentSession().createQuery(hql)
                    .setParameter(PRIMARY_KEY, pK);
            newses = query.list();
        } catch (HibernateException e) {
            logger.error("Error get " + newses.isEmpty() + " in Dao " + e);
            throw new PersistException(e);
        }
        return newses;
    }

    /**
     *
     * @param date
     * @return
     * @throws PersistException
     */
    @Override
    public List<News> getByDate(Date date) throws PersistException{
        List<News> newses;
        try {
            StatusEnum status = PERSISTED;
            String hql = "SELECT n FROM News n WHERE n.date=:date and n.status=:status ORDER BY n.date DESC";
            Query query = getCurrentSession().createQuery(hql)
                    .setParameter(DATE, date)
                    .setParameter(STATUS, status);
            newses = query.list();
        } catch (HibernateException e) {
            logger.error(ERROR_GET_NEWSES_BY_DATE + e);
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
    @Override
    public News getByPageId(String pageId) throws PersistException {
        News news = null;
        try {
            StatusEnum status = PERSISTED;
            String hql = "SELECT n FROM News n WHERE n.pageId=:pageId and n.status=:status";
            Query query = getCurrentSession().createQuery(hql)
                    .setParameter(PAGE_ID, pageId)
                    .setParameter(STATUS, status);
            news = (News) query.uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error get " + news.getClass().getName() + " in Dao " + e);
            throw new PersistException(e);
        }
        return news;
    }

    @Override
    public List<News> getNewsByCriteria(int pageNumber, int pageSize, Long pK) throws PersistException{
        List<News> newses;
        try {
            StatusEnum status = PERSISTED;
            String hql = "SELECT n FROM News n WHERE n.category.categoryId=:pK and n.status=:status  ORDER BY n.date DESC";
            Query query = getCurrentSession().createQuery(hql)
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

    /**
     *
     * @param category
     * @return
     * @throws PersistException
     */
    public List countNews(Category category) throws PersistException {
        List result;
        Criteria criteria = getCurrentSession().createCriteria(News.class);
        criteria.add(Restrictions.eq(STATUS, PERSISTED));
        criteria.add(Restrictions.eq(CATEGORY, category));
        criteria.setProjection(Projections.rowCount());
        result = criteria.list();
        return result;
    }


}
