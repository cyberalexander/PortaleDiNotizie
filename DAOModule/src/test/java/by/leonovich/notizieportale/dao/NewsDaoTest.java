package by.leonovich.notizieportale.dao;

import static by.leonovich.notizieportale.dao.util.TestConstants.TestConst;

import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alexanderleonovich on 27.05.15.
 */
public class NewsDaoTest {
    private static final Logger logger = Logger.getLogger(NewsDaoTest.class);
    private final ThreadLocal sessionStatus = new ThreadLocal();
    protected Session session;
    private Transaction transaction;
    private NewsDao newsDao;
    private Commentary commentary;
    private Category category;
    private News news;
    private Person person;

    public NewsDaoTest() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            newsDao = (NewsDao) factory.getDao(News.class);
        } catch (PersistException e) {
            logger.error(e);
        }
    }

    @Before
    public void setUp() throws Exception {
        news = new News(TestConst.PAGE_ID, TestConst.TITLE, TestConst.MENU_TITLE,
                TestConst.DATE, TestConst.ANNOTATION, TestConst.CONTENT, StatusEnum.PERSISTED);
        person = new Person(TestConst.PERSON_NAME, TestConst.PERSON_SURNAME, StatusEnum.PERSISTED);
        commentary = new Commentary(TestConst.COMMENTARY_CONTENT, TestConst.DATE, StatusEnum.PERSISTED);
        category = new Category(TestConst.CATEGORY_NAME, StatusEnum.PERSISTED);
        commentary.setNews(news);
        commentary.setPerson(person);
        news.setCategory(category);
        news.setPerson(person);
        session = newsDao.getSession();
        transaction = session.beginTransaction();
    }

    @After
    public void tearDown() throws Exception {
        transaction.commit();
        sessionStatus.set(true);
        newsDao.clearSession();
        commentary = null;
        category = null;
        news = null;
        person = null;
    }

    @Test
    public void testGetByPersonPK() throws Exception {
        assertNull("Id before save() is not null.", news.getNewsId());
        newsDao.save(news, session);
        logger.info("Saved news " + news.getNewsId());
        Long personId = news.getPerson().getPersonId();
        List<News> expected = newsDao.getByPersonId(personId, session);
        logger.info("List of news by personId " + expected.size());
        assertNotNull(expected.size());
    }

    @Test
    public void testGetByCategoryPK() throws Exception {
        assertNull("Id before save() is not null.", news.getNewsId());
        newsDao.save(news, session);
        logger.info("Saved news " + news.getNewsId());
        Long categoryId = news.getCategory().getCategoryId();
        List<News> expected = newsDao.getByCategoryId(categoryId, session);
        logger.info("List of news by categoryId " + expected.size());
        assertNotNull(expected.size());
    }

    @Test
    public void testGetByDate() throws Exception {
        assertNull("Id before save() is not null.", news.getNewsId());
        newsDao.save(news, session);
        logger.info("Saved news " + news.getNewsId());
        Date date = news.getDate();
        List<News> expected = newsDao.getByDate(new java.sql.Date(date.getTime()), session);
        logger.info("List of news by DATE " + expected.size() + ", DATE in database " +
                news.getDate() + ", DATE? what we get from database " + new java.sql.Date(date.getTime()));
        assertNotNull(expected.size());
    }

    @Test
    public void testGetByPageId() throws Exception {
        assertNull("Id before save() is not null.", news.getNewsId());
        news.setPageId(TestConst.GET_BY_PAGE_ID_UNIQUE);
        Long id = newsDao.save(news, session);
        logger.info("Saved news " + news.getNewsId());
        News expected = newsDao.getByPageId(news.getPageId(), session);
        logger.info("News serched by pageId " + expected.getNewsId());
        assertEquals("Newses is not equals", expected, news);
    }

    @Ignore
    @Test
    public void testGetNewsByCriteria() throws Exception {

    }
}