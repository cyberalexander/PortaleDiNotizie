package by.leonovich.notizieportale.dao;

import static by.leonovich.notizieportale.dao.util.TestConstants.TestConst;

import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alexanderleonovich on 25.05.15.
 */

public class CategoryDaoTest {
    private static final Logger logger = Logger.getLogger(CategoryDaoTest.class);
    private Session session;
    private Transaction transaction;
    CategoryDao categoryDao;
    Category category;

    public CategoryDaoTest() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            categoryDao = (CategoryDao) factory.getDao(Category.class);
        } catch (PersistException e) {
            logger.error(e);
        }
    }

    @Before
    public void setUp() throws Exception {
        category = new Category(TestConst.CATEGORY_NAME, StatusEnum.PERSISTED);
    }

    @After
    public void tearDown() throws Exception {
        category = null;
        categoryDao.clearSession();
    }

    @Test
    public void testGetByName() throws Exception {
        assertNull("Id before save() is not null.", category.getCategoryId());
        session = categoryDao.getSession();
        transaction = session.beginTransaction();
        category.setCategoryName("VasiaLOh");
        categoryDao.save(category, session);
        logger.info("Saved category " + category.getCategoryName());
        Category expected = categoryDao.getByName(category.getCategoryName(), session);
        transaction.commit();
        logger.info("Names of categories: category-actual-" + category.getCategoryName() + ", category-expected-" + expected.getCategoryName());
        assertEquals("Categories names not equal. ", expected.getCategoryName(), category.getCategoryName());
    }

}