package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.util.DaoConstants.Const;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import static by.leonovich.notizieportale.util.DaoConstants.Const.*;

/**
 * Created by alexanderleonovich on 20.05.15.
 * In this.Class contains special methods for Category-entity
 */
@Repository
public class CategoryDao extends AbstractDao<Category> implements ICategoryDao {
    private static final Logger logger = Logger.getLogger(CategoryDao.class);

    public CategoryDao() {
        super();
    }

    /**
     *
     * @param session
     * @return
     * @throws PersistException
     */
    @Override
    protected List<Category> parseResultSet(Session session) throws PersistException {
        Criteria criteria = session.createCriteria(Category.class);
        criteria.add(Restrictions.eq(Const.STATUS, StatusEnum.PERSISTED));
        List<Category> result = criteria.list();
        return result;
    }

    /**
     *
     * @param categoryName
     * @param session
     * @return
     * @throws PersistException
     */
    @Override
    public Category getByName(String categoryName, Session session) throws PersistException {
        Category categoryObj;
        try {
            StatusEnum status = StatusEnum.PERSISTED;
            String hql = "SELECT c FROM Category c WHERE c.categoryName=:categoryName and c.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter(CATEGORY_NAME, categoryName)
                    .setParameter(STATUS, status);
            categoryObj = (Category) query.uniqueResult();
        } catch (HibernateException e) {
            logger.error(ERROR_GET_CATEGORY_BY_NAME + e);
            throw new PersistException(e);
        }
        return categoryObj;
    }

}