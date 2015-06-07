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
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alexanderleonovich on 26.05.15.
 */

public class CommentaryDaoTest {
    private static final Logger logger = Logger.getLogger(CommentaryDaoTest.class);
    protected Session session;
    private Transaction transaction;
    private CommentaryDao commentaryDao;
    private Commentary commentary;
    private Category category;
    private News news;
    private Person person;

    public CommentaryDaoTest() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            commentaryDao = (CommentaryDao) factory.getDao(Commentary.class);
        } catch (PersistException e) {
            logger.error(e);
        }
    }

    @Before
    public void setUp() throws Exception {
        commentary = new Commentary(TestConst.COMMENTARY_CONTENT, TestConst.DATE, StatusEnum.PERSISTED);
        category = new Category(TestConst.CATEGORY_NAME, StatusEnum.PERSISTED);
        news = new News(TestConst.PAGE_ID, TestConst.TITLE, TestConst.MENU_TITLE,
                TestConst.DATE, TestConst.ANNOTATION, TestConst.CONTENT, StatusEnum.PERSISTED);
        person = new Person(TestConst.PERSON_NAME, TestConst.PERSON_SURNAME, StatusEnum.PERSISTED);
        commentary.setPerson(person);
        news.setCategory(category);
        news.setPerson(person);
        commentary.setNews(news);
    }

    @After
    public void tearDown() throws Exception {
        commentary = null;
        category = null;
        news = null;
        person = null;
        commentaryDao.clearSession();
    }

    @Test
    public void testGetByNewsPK() throws Exception {
        assertNull("Id before save() is not null.", commentary.getCommentaryId());
        session = commentaryDao.getSession();
        transaction = session.beginTransaction();
        Long identifier = commentaryDao.save(commentary, session);
        assertNotNull("After save() categoryId is null. ", identifier);
        List<Commentary> list = commentaryDao.getByNewsPK(commentary.getNews().getNewsId(), session);
        assertNotNull(list);
        assertTrue(list.size() > TestConst.ZERO);
        transaction.commit();
    }

    @Test
    public void testGetByPersonPK() throws Exception {
        assertNull("Id before save() is not null.", commentary.getCommentaryId());
        session = commentaryDao.getSession();
        transaction = session.beginTransaction();
        Long identifier = commentaryDao.save(commentary, session);
        assertNotNull("After save() categoryId is null. ", identifier);
        List<Commentary> list = commentaryDao.getByPersonPK(commentary.getPerson().getPersonId(), session);
        assertNotNull(list);
        assertTrue(list.size() > TestConst.ZERO);
        transaction.commit();
    }
}