package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.CategoryDao;
import by.leonovich.notizieportale.dao.ICategoryDao;
import by.leonovich.notizieportale.dao.IGenericDao;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alexanderleonovich on 20.05.15.
 * Buisnes layer for Category-entity
 */
@Service("categoryService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CategoryService implements ICategoryService {
    private static final Logger logger = Logger.getLogger(CategoryService.class);
    private static CategoryService categoryService;


    @Autowired
    @Qualifier("categoryDao")
    private ICategoryDao categoryDao;

    public CategoryService() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            categoryDao = (CategoryDao) factory.getDao(Category.class);
        } catch (PersistException e) {
            logger.error(e);
        }
    }

    public static synchronized CategoryService getInstance() {
        if (categoryService == null) {
            categoryService = new CategoryService();
        }
        return categoryService;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Category> getCategories() {
        List<Category> list = null;
        Transaction transaction = null;
        try {
            Session session = categoryDao.getSession();
            transaction = session.beginTransaction();
            list = categoryDao.getAll(session);
            logger.info("Category-list size: " + list.size());
            transaction.commit();
            logger.info("successful get list!");
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            categoryDao.clearSession();
        }
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Category getCategoryByName(String category) {
        Category categoryObj;
        if (!(StringUtils.isNullOrEmpty(category))) {
            Transaction transaction = null;
            try {
                Session session = categoryDao.getSession();
                transaction = session.beginTransaction();
                categoryObj = categoryDao.getByName(category, session);
                transaction.commit();
                if (categoryObj != null) {
                    return categoryObj;
                }
            } catch (HibernateException e) {
                logger.error("Error get list of Categories from database" + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                categoryDao.clearSession();
            }
        }
        return null;
    }

    @Override
    public Long save(Category category) {
        Long savedCategoryId = null;
        Transaction transaction = null;
        try {
            Session session = categoryDao.getSession();
            transaction = session.beginTransaction();
            category.setStatus(StatusEnum.PERSISTED);
            savedCategoryId = categoryDao.save(category, session);
            logger.info("Category saved: " + savedCategoryId);
            transaction.commit();
            logger.info("successful get list!");
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            categoryDao.clearSession();
        }
        return savedCategoryId;
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public Category delete(Category category) {
        if (null != category.getCategoryId()) {
            Long deletedCategoryId = category.getCategoryId();
            Transaction transaction = null;
            try {
                Session session = categoryDao.getSession();
                transaction = session.beginTransaction();
                category.setStatus(StatusEnum.DELETED);
                categoryDao.update(category, session);
                category = categoryDao.get(deletedCategoryId);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error delete category from database:   " + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                categoryDao.clearSession();
            }
        }
        return category;
    }

    @Override
    public void remove(Category category) {
        if (null != category.getCategoryId()) {
            Transaction transaction = null;
            try {
                Session session = categoryDao.getSession();
                transaction = session.beginTransaction();
                categoryDao.remove(category, session);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error remove category from database:   " + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                categoryDao.clearSession();
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Category get(Long pK) {
        try {
            return categoryDao.get(pK);
        } catch (PersistException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Category load(Long pK) {
        return null;
    }
}
