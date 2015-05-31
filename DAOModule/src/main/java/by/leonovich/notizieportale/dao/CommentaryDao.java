package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.util.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.util.DaoConstants.Const;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by alexanderleonovich on 27.04.15.
 * Class for working with persistence entity of COMMENTARY
 */
public class CommentaryDao extends AbstractDao<Commentary> {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);

    /**
     * Constructor of MySqlCommentaryDao.class
     */
    public CommentaryDao() {
        super();
    }



    @Override
    protected List<Commentary> parseResultSet(Session session) throws PersistException {
        Criteria criteria = session.createCriteria(Commentary.class);
        criteria.add(Restrictions.eq(Const.STATUS, StatusEnum.SAVED));
        List<Commentary> result = criteria.list();
        return result;
    }

    public List<Commentary> getByNewsPK(Long pK) throws PersistException {
        List<Commentary> commentaries = null;
        try {
            Session session = getSession();
            StatusEnum status = StatusEnum.SAVED;
            String hql = "SELECT c FROM Commentary c WHERE c.news.newsId=:pK and c.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter("pK", pK)
                    .setParameter("status", status);
            commentaries = query.list();
        } catch (HibernateException e) {
            logger.error("Error get " + commentaries.isEmpty() + " in Dao " + e);
            throw new PersistException(e);
        }
        return commentaries;
    }

    public List<Commentary> getByPersonPK(Long pK) throws PersistException {
        List<Commentary> commentaries = null;
        try {
            session = getSession();
            StatusEnum status = StatusEnum.SAVED;
            String hql = "SELECT c FROM Commentary c WHERE c.person.personId=:pK and c.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter("pK", pK)
                    .setParameter("status", status);
            commentaries = query.list();
        } catch (HibernateException e) {
            logger.error("Error get " + commentaries.isEmpty() + " in Dao " + e);
            throw new PersistException(e);
        }
        return commentaries;
    }
}
