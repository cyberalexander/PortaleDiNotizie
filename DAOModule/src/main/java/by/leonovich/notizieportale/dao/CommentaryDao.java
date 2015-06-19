package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.util.exception.PersistException;
import by.leonovich.notizieportale.util.DaoConstants.Const;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.leonovich.notizieportale.util.DaoConstants.Const.*;

/**
 * Created by alexanderleonovich on 27.04.15.
 * Class for working with persistence entity of COMMENTARY
 */
@Repository
public class CommentaryDao extends AbstractDao<Commentary> implements ICommentaryDao {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);

    /**
     * Constructor of CommentaryDao.class
     */
    @Autowired
    public CommentaryDao(SessionFactory sessionFactory) {
        super(sessionFactory);
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
     * @return
     * @throws PersistException
     */
    @Override
    public List<Commentary> getByNewsPK(Long pK) throws PersistException {
        List<Commentary> commentaries;
        try {
            StatusEnum status = StatusEnum.PERSISTED;
            String hql = "SELECT c FROM Commentary c WHERE c.news.newsId=:pK and c.status=:status";
            Query query = getCurrentSession().createQuery(hql)
                    .setParameter(PRIMARY_KEY, pK)
                    .setParameter(STATUS, status);
            commentaries = query.list();
        } catch (HibernateException e) {
            logger.error(ERROR_GET_LIST_BY_NEWS_ID + e);
            throw new PersistException(e);
        }
        return commentaries;
    }

    @Override
    public List<Commentary> getByPersonPK(Long pK) throws PersistException {
        List<Commentary> commentaries;
        try {
            StatusEnum status = StatusEnum.PERSISTED;
            String hql = "SELECT c FROM Commentary c WHERE c.person.personId=:pK and c.status=:status";
            Query query = getCurrentSession().createQuery(hql)
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
