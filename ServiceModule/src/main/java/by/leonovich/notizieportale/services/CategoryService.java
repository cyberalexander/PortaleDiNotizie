package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.IGenericDao;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by alexanderleonovich on 20.05.15.
 */
public class CategoryService implements ICategoryService {
    private static final Logger logger = Logger.getLogger(CategoryService.class);

    private static CategoryService categoryServiceInst;
    private final ThreadLocal sessionStatus = new ThreadLocal();

    private IGenericDao categoryDao;
    private Session session;
    private Transaction transaction;

    private CategoryService() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            categoryDao = factory.getDao(Category.class);
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
    public List<Category> getCategories() {
        List<Category> list = null;
        try {
            session = categoryDao.getSession();
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
        }
        return list;
    }

    @Override
    public void saveCategory(Category category) {
        Long savedCategoryId;
        try {
            session = categoryDao.getSession();
            transaction = session.beginTransaction();
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
    }
}
