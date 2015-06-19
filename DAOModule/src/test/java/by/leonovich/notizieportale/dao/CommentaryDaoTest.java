package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.leonovich.notizieportale.dao.util.TestConstants.TestConst;
import static org.junit.Assert.*;

/**
 * Created by alexanderleonovich on 26.05.15.
 */
@Ignore
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommentaryDaoTest {
    private static final Logger logger = Logger.getLogger(CommentaryDaoTest.class);
    private ClassPathXmlApplicationContext ac;
    @Autowired
    private CommentaryDao commentaryDao;
    private Commentary commentary;
    private Category category;
    private News news;
    private Person person;

    public CommentaryDaoTest() {
        ac = new ClassPathXmlApplicationContext(new String[]{"test-beans-dao.xml"});
        commentaryDao = (CommentaryDao) ac.getBean("commentaryDao");
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
    }

    @Test
    public void testGetByNewsPK() throws Exception {
        assertNull("Id before save() is not null.", commentary.getCommentaryId());
        Long identifier = commentaryDao.save(commentary);
        assertNotNull("After save() categoryId is null. ", identifier);
        List<Commentary> list = commentaryDao.getByNewsPK(commentary.getNews().getNewsId());
        assertNotNull(list);
        assertTrue(list.size() > TestConst.ZERO);
    }

    @Test
    public void testGetByPersonPK() throws Exception {
        assertNull("Id before save() is not null.", commentary.getCommentaryId());
        Long identifier = commentaryDao.save(commentary);
        assertNotNull("After save() categoryId is null. ", identifier);
        List<Commentary> list = commentaryDao.getByPersonPK(commentary.getPerson().getPersonId());
        assertNotNull(list);
        assertTrue(list.size() > TestConst.ZERO);
    }
}