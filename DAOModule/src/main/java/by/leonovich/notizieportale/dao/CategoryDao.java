package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Category;
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
 * Created by alexanderleonovich on 20.05.15.
 * In this.Class contains special methods for Category-entity
 */
public class CategoryDao extends AbstractDao<Category> {

    public CategoryDao() {
        super();
    }

    private static final Logger logger = Logger.getLogger(CategoryDao.class);


    @Override
    protected List<Category> parseResultSet(Session session) throws PersistException {
        Criteria criteria = session.createCriteria(Category.class);
        criteria.add(Restrictions.eq(Const.STATUS, StatusEnum.SAVED));
        List<Category> result = criteria.list();
        return result;
    }

    public Category getByName(String category) throws PersistException {
        Category categoryObj = new Category();
        try {
            Session session = getSession();
            StatusEnum status = StatusEnum.SAVED;
            String hql = "SELECT c FROM Category c WHERE c.categoryName=:category and c.status=:status";
            Query query = session.createQuery(hql)
                    .setParameter("category", category)
                    .setParameter("status", status);
            categoryObj = (Category) query.uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error get " + categoryObj.getClass().getName() + " in Dao " + e);
            throw new PersistException(e);
        }
        return categoryObj;
    }

}