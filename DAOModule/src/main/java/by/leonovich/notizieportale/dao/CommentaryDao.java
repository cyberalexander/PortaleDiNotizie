package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.util.DaoConstants.Const;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static by.leonovich.notizieportale.util.DaoConstants.Const.*;

/**
 * Created by alexanderleonovich on 27.04.15.
 * Class for working with persistence entity of COMMENTARY
 */
public class CommentaryDao extends AbstractDao<Commentary> {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);

    /**
     * Constructor of CommentaryDao.class
     */
    public CommentaryDao() {
        super();
    }


    /**
     *
     * @param session
     * @return
     * @throws PersistException
     */
    @Override
    protected List<Commentary> parseResultSet(Session session) throws PersistException {
        Criteria criteria = session.createCriteria(Commentary.class);
        criteria.add(Restrictions.eq(Const.STATUS, StatusEnum.PERSISTED));
        List<Commentary> result = criteria.list();
        return result;
    }

    /**
     *
     * @param pK
     * @param session
     * @return
     * @throws PersistException
     */
    public List<Commentary> getByNewsPK(Long pK, Session session) throws PersistException {
        List<Commentary> commentaries;
        try {
            StatusEnum status = StatusEnum.PERSISTED;
            String hql = "SELECT c FROM Commentary c WHERE c.news.newsId=:pK and c.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter(PRIMARY_KEY, pK)
                    .setParameter(STATUS, status);
            commentaries = query.list();
        } catch (HibernateException e) {
            logger.error(ERROR_GET_LIST_BY_NEWS_ID + e);
            throw new PersistException(e);
        }
        return commentaries;
    }

    public List<Commentary> getByPersonPK(Long pK, Session session) throws PersistException {
        List<Commentary> commentaries;
        try {
            StatusEnum status = StatusEnum.PERSISTED;
            String hql = "SELECT c FROM Commentary c WHERE c.person.personId=:pK and c.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter(PRIMARY_KEY, pK)
                    .setParameter(STATUS, status);
            commentaries = query.list();
        } catch (HibernateException e) {
            logger.error(ERROR_GET_LIST_BY_PERSON_ID + e);
            throw new PersistException(e);
        }
        return commentaries;
    }
}
