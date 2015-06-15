package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.dao.util.TestConstants;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.*;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static by.leonovich.notizieportale.dao.util.TestConstants.TestConst;
import static by.leonovich.notizieportale.dao.util.TestConstants.TestConst.DELETED;
import static by.leonovich.notizieportale.dao.util.TestConstants.TestConst.UNIQUE_PAGE_ID_FOR_UPDATE;
import static by.leonovich.notizieportale.dao.util.TestConstants.TestConst.ZERO;
import static org.junit.Assert.*;

/**
 * Created by alexanderleonovich on 26.05.15.
 */
public class AbstractDaoTest {
    private static final Logger logger = Logger.getLogger(AbstractDaoTest.class);
    private NewsDao dao;
    protected Session session;
    private Transaction transaction;
    private Commentary commentary;
    private Category category;
    private News news;
    private Person person;
    private PersonDetail personDetail;


    public AbstractDaoTest() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            dao = (NewsDao) factory.getDao(News.class);
        } catch (PersistException e) {
            logger.error(e);
        }
    }

    @Before
    public void setUp() throws PersistException, SQLException {
        news = new News(TestConst.PAGE_ID, TestConst.TITLE, TestConst.MENU_TITLE,
                TestConst.DATE, TestConst.ANNOTATION, TestConst.CONTENT, StatusEnum.PERSISTED);
        person = new Person(TestConst.PERSON_NAME, TestConst.PERSON_SURNAME, StatusEnum.PERSISTED);
        personDetail = new PersonDetail(TestConstants.TestConst.EMAIL,
                TestConstants.TestConst.PASSWORD, TestConstants.TestConst.DATE);
        commentary = new Commentary(TestConst.COMMENTARY_CONTENT, TestConst.DATE, StatusEnum.PERSISTED);
        category = new Category(TestConst.CATEGORY_NAME, StatusEnum.PERSISTED);
        person.setPersonDetail(personDetail);
        personDetail.setPerson(person);
        commentary.setNews(news);
        commentary.setPerson(person);
        news.setCategory(category);
        news.setPerson(person);
        session = dao.getSession();
        transaction = session.beginTransaction();
    }

    @After
    public void tearDown() throws SQLException {
        transaction.commit();
        dao.clearSession();
        commentary = null;
        category = null;
        news = null;
        person = null;
        personDetail = null;
    }

    @Test
    public void testGetSession() throws Exception {
        Session session = dao.getSession();
        Assert.assertTrue(session.isConnected());
    }

    @Test
    public void testClearSession() throws Exception {
        Session session = dao.getSession();
        dao.clearSession();
        Assert.assertFalse(session.isDirty());
    }

    @Test
    public void testSave() throws Exception {
        Long id = dao.save(news, session);
        logger.info('\n' + "ID AFTER SAVE " + news.getClass() + " IS " + id + '\n');
        Assert.assertNotNull("After persist id is null.", id);
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        dao.saveOrUpdate(news, session);

        Assert.assertNotNull(news);
        Assert.assertNotNull(news.getNewsId());
    }

    @Test
    public void testGetByPK() throws Exception {
        Long id = dao.save(news, session);
        News expected = dao.get(id);
        logger.info("News object, what we get from database " + expected.getPageId() + " - " + expected.getTitle());
        Assert.assertNotNull("After persist id is null.", expected);
    }

    @Test
    public void testLoadByPK() throws Exception {
        Long id = dao.save(news, session);
        News expected = dao.load(id, session);
        logger.info("News object, what we get from database " + expected.getPageId() + " - " + expected.getTitle());
        Assert.assertNotNull("After persist id is null.", expected);
    }

    @Test
    public void testUpdate() throws Exception {
        dao.save(news, session);
        news.setPageId(UNIQUE_PAGE_ID_FOR_UPDATE);
        dao.update(news, session);
        News expected = dao.getByPageId(UNIQUE_PAGE_ID_FOR_UPDATE, session);
        assertSame(expected, news);
        logger.info("Saved object " + news.getNewsId() + " - " + news.getPageId() + " - " + news.getTitle());
        logger.info("Updated object " + expected.getNewsId() + " - "  + expected.getPageId() + " - " + expected.getTitle());
    }

    @org.junit.Ignore
    @Test
    public void testDelete() throws Exception {
        dao.save(news, session);
        assertNotNull(dao.get(news.getNewsId()).getNewsId());
        news.setStatus(DELETED);
        dao.delete(news, session);
        news = dao.get(news.getNewsId());
        assertEquals("Can`t change status of object in database ", DELETED, news.getStatus());
    }

    @Test
    public void testRemove() throws Exception {
        dao.save(news, session);
        assertNotNull(dao.get(news.getNewsId()).getNewsId());
        dao.remove(news, session);
        news = dao.get(news.getNewsId());
        assertNull("Object is not deleted from database ", news);
    }

    @Test
    public void testGetAll() throws Exception {
        List list = dao.getAll(session);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > ZERO);
    }
}