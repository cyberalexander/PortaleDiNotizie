package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.CategoryDao;
import by.leonovich.notizieportale.dao.IGenericDao;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.util.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by alexanderleonovich on 20.05.15.
 * Buisnes layer for Category-entity
 */
public class CategoryService implements ICategoryService {
    private static final Logger logger = Logger.getLogger(CategoryService.class);

    private static CategoryService categoryServiceInst;
    private final ThreadLocal sessionStatus = new ThreadLocal();

    private CategoryDao categoryDao;

    private CategoryService() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            categoryDao = (CategoryDao) factory.getDao(Category.class);
        } catch (PersistException e) {
            logger.error(e);
        }
    }

    public static synchronized CategoryService getInstance(){
        if (categoryServiceInst == null){
            categoryServiceInst = new CategoryService();
        }
        return categoryServiceInst;
    }


    @Override
    public Category getCategoryByPK(Long PK) {
        Category category = new Category();
        Transaction transaction = null;
        try {
            Session session = categoryDao.getSession();
            transaction = session.beginTransaction();
            category = categoryDao.getByPK(PK);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            sessionStatus.set(true);
            categoryDao.clearSession(sessionStatus);
        }
        return category;
    }

    @Override
    public List<Category> getCategories() {
        List<Category> list = null;
        Transaction transaction = null;
        try {
            Session session = categoryDao.getSession();
            transaction = session.beginTransaction();
            list = categoryDao.getAll();
            logger.info("Category-list size: " + list.size());
            transaction.commit();
            sessionStatus.set(true);
            categoryDao.clearSession(sessionStatus);
            logger.info("successful get list!");
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            sessionStatus.set(true);
            categoryDao.clearSession(sessionStatus);
        }
        return list;
    }

    @Override
    public Long saveCategory(Category category) {
        Long savedCategoryId = null;
        Transaction transaction = null;
        try {
            Session session = categoryDao.getSession();
            transaction = session.beginTransaction();
            category.setStatus(StatusEnum.SAVED);
            savedCategoryId = categoryDao.save(category);
            logger.info("Category saved: " + savedCategoryId);
            transaction.commit();
            logger.info("successful get list!");
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }
        return savedCategoryId;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public Category deleteCategory(Category category) {
        if (null != category.getCategoryId()) {
        Long deletedCategoryId = category.getCategoryId();
            Transaction transaction = null;
            try {
                Session session = categoryDao.getSession();
                transaction = session.beginTransaction();
                category.setStatus(StatusEnum.DELETED);
                categoryDao.update(category);
                category = categoryDao.getByPK(deletedCategoryId);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error delete category from database:   " + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                categoryDao.clearSession(sessionStatus);
            }
        }
        return category;
    }

    @Override
    public void removeCategory(Category category) {
        if (null != category.getCategoryId()) {
            Transaction transaction = null;
            try {
                Session session = categoryDao.getSession();
                transaction = session.beginTransaction();
                categoryDao.remove(category);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error remove category from database:   " + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                categoryDao.clearSession(sessionStatus);
            }
        }
    }

    @Override
    public Category getCategoryByName(String category) {
        Category categoryObj;
        if (!(StringUtils.isNullOrEmpty(category))) {
            Transaction transaction = null;
            try {
                Session session = categoryDao.getSession();
                transaction = session.beginTransaction();
                categoryObj = categoryDao.getByName(category);
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
                sessionStatus.set(true);
                categoryDao.clearSession(sessionStatus);
            }
        }
        return null;
    }
}
