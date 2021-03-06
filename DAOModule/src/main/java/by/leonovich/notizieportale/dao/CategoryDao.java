package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.util.DaoConstants.Const;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CategoryDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Parse parameters of entity
     * @param session org.hibernate.Session
     * @return list of entities
     * @throws  - custom exception
     */
    @Override
    protected List<Category> parseResultSet(Session session) throws PersistException {
        Criteria criteria = session.createCriteria(Category.class);
        criteria.add(Restrictions.eq(Const.STATUS, StatusEnum.PERSISTED));
        List<Category> result = criteria.list();
        return result;
    }

    /**
     *  Get category object by input name of category
     * @param categoryName name of category
     * @return category object
     * @throws PersistException - custom exception classs
     */
    @Override
    public Category getByName(String categoryName) throws PersistException {
        Category categoryObj;
        try {
            StatusEnum status = StatusEnum.PERSISTED;
            String hql = "SELECT c FROM Category c WHERE c.categoryName=:categoryName and c.status=:status";
            Query query = getCurrentSession().createQuery(hql)
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