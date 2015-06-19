package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.*;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.util.exception.PersistException;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static by.leonovich.notizieportale.dao.util.TestConstants.TestConst.*;
import static org.junit.Assert.*;

/**
 * Created by alexanderleonovich on 26.05.15.
 */
@Ignore
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class AbstractDaoTest {
    private static final Logger logger = Logger.getLogger(AbstractDaoTest.class);
    private ClassPathXmlApplicationContext ac;
    @Autowired
    private NewsDao dao;
    private Commentary commentary;
    private Category category;
    private News news;
    private Person person;
    private PersonDetail personDetail;


    public AbstractDaoTest() {
        ac = new ClassPathXmlApplicationContext(new String[]{"test-beans-dao.xml"});
        dao = (NewsDao) ac.getBean("newsDao");
    }

    @Before
    public void setUp() throws PersistException, SQLException {
        news = new News(PAGE_ID, TITLE, MENU_TITLE,
                DATE, ANNOTATION, CONTENT, StatusEnum.PERSISTED);
        person = new Person(PERSON_NAME, PERSON_SURNAME, StatusEnum.PERSISTED);
        personDetail = new PersonDetail(EMAIL,
                PASSWORD, BITHDAY);
        commentary = new Commentary(COMMENTARY_CONTENT, DATE, StatusEnum.PERSISTED);
        category = new Category(CATEGORY_NAME, StatusEnum.PERSISTED);
        person.setPersonDetail(personDetail);
        personDetail.setPerson(person);
        commentary.setNews(news);
        commentary.setPerson(person);
        news.setCategory(category);
        news.setPerson(person);
    }

    @After
    public void tearDown()  throws PersistException{
        commentary = null;
        category = null;
        news = null;
        person = null;
        personDetail = null;
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void testSave() throws Exception {
        Long id = dao.save(news);
        logger.info('\n' + "ID AFTER SAVE " + news.getClass() + " IS " + id + '\n');
        assertNotNull("After persist id is null.", id);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void testSaveOrUpdate()  throws PersistException {
        dao.saveOrUpdate(news);
        assertNotNull(news);
        assertNotNull(news.getNewsId());
    }

    @Test
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void testGetByPK()  throws PersistException {
        Long id = dao.save(news);
        News expected = dao.get(id);
        logger.info("News object, what we get from database " + expected.getPageId() + " - " + expected.getTitle());
        assertNotNull("After persist id is null.", expected);
    }

    @Ignore
    @Test
    public void testLoadByPK()  throws PersistException {
        Long id = dao.save(news);
        dao.update(news);
        News expected = dao.load(news.getNewsId());
        logger.info("News object, what we get from database " + expected.getPageId() + " - " + expected.getTitle());
        assertNotNull("After persist id is null.", expected);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void testUpdate()  throws PersistException{
        dao.save(news);
        news.setPageId(UNIQUE_PAGE_ID_FOR_UPDATE);
        dao.update(news);
        News expected = dao.getByPageId(UNIQUE_PAGE_ID_FOR_UPDATE);
        assertSame(expected.getNewsId(), news.getNewsId());
        logger.info("Saved object " + news.getNewsId() + " - " + news.getPageId() + " - " + news.getTitle());
        logger.info("Updated object " + expected.getNewsId() + " - " + expected.getPageId() + " - " + expected.getTitle());
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void testDelete()  throws PersistException {
        dao.save(news);
        assertNotNull(dao.get(news.getNewsId()).getNewsId());
        news.setStatus(DELETED);
        dao.delete(news);
        news = dao.get(news.getNewsId());
        assertEquals("Can`t change status of object in database ", DELETED, news.getStatus());
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void testRemove()  throws PersistException{
        dao.save(news);
        assertNotNull(dao.get(news.getNewsId()).getNewsId());
        dao.remove(news);
        news = dao.get(news.getNewsId());
        assertNull("Object is not deleted from database ", news);
    }

    @Test
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void testGetAll()  throws PersistException {
        for (int i = 0; i < THREE; i++) {
            dao.save(news);
        }
        List list = dao.getAll();
        assertNotNull(list);
        assertTrue(list.size() > ZERO);
        assertTrue(list.size() == THREE);
    }
}