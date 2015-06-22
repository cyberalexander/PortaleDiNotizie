package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.services.util.ServiceTestConfig;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static by.leonovich.notizieportale.services.util.TestConstants.TestConst;
import static by.leonovich.notizieportale.services.util.TestConstants.TestConst.*;
import static org.junit.Assert.*;

/**
 * Created by alexanderleonovich on 31.05.15.
 */
public class CommentaryServiceTest {
    private static final Logger logger = Logger.getLogger(CommentaryServiceTest.class);

    private Commentary commentary;
    private Category category;
    private News news;
    private Person person;
    private CommentaryService commentaryService;
    private ApplicationContext ac;

    public CommentaryServiceTest() {
        ac = new AnnotationConfigApplicationContext(ServiceTestConfig.class);
        commentaryService = (CommentaryService) ac.getBean(COMMENTARY_SERVICE);
    }

    @Before
    public void setUp() throws Exception {
        commentary = new Commentary(COMMENTARY_CONTENT, DATE, StatusEnum.PERSISTED);
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
    }

    @Test
    public void testGetCommentaryByPK() throws Exception {
        Long actual = commentaryService.save(commentary);
        Commentary expected = commentaryService.get(actual);
        logger.info("News object, what we get from database " + expected.getCommentaryId() + " - " + expected.getComment());
        Assert.assertNotNull("After persist id is null.", expected);
    }

    @Test
    public void testGetCommentaries() throws Exception {
        for (int i = 0; i < THREE; i++) {
            commentaryService.save(commentary);
        }
        List list = commentaryService.getCommentaries();
        System.out.println(list.size());
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > ZERO);
        Assert.assertTrue(list.size() == THREE);
    }

    @Test
    public void testGetCommentariesByAuthorId() throws Exception {
        Long actual = commentaryService.save(commentary);
        List<Commentary> expected = commentaryService.getCommentariesByAuthorId(commentaryService.get(actual).getPerson().getPersonId());
        logger.info("News object, what we get from database " + expected.size() + " - " + expected.get(ZERO).getPerson().getName());
        Assert.assertNotNull("After persist id is null.", expected);
        Assert.assertTrue(expected.size() > ZERO);
    }

    @Test
    public void testGetCommentariesByNewsId() throws Exception {
        Long actual = commentaryService.save(commentary);
        List<Commentary> expected = commentaryService.getCommentariesByNewsId(commentaryService.get(actual).getNews().getNewsId());
        logger.info("News object, what we get from database " + expected.size() + " - " + expected.get(ZERO).getNews().getCategory().getCategoryName());
        Assert.assertNotNull("After persist id is null.", expected);
        Assert.assertTrue(expected.size() > ZERO);
    }

    @Test
    public void testSaveCommentary() throws Exception {
        Long actual = commentaryService.save(commentary);
        logger.info('\n' + "ID AFTER SAVE " + commentary.getClass() + " IS " + actual + '\n');
        Assert.assertNotNull("After persist id is null.", actual);
    }

    @Test
    public void testUpdateCommentary() throws Exception {
        Long actual = commentaryService.save(commentary);
        commentary.setComment(TestConst.UNIQUE_COMMENT);
        commentaryService.update(commentary);
        Commentary expected = commentaryService.get(actual);
        assertEquals(expected.getComment(), commentary.getComment());
        logger.info("Saved object " + commentary.getClass() + " - " + commentary.getDate() + " - " + commentary.getPerson().getName());
        logger.info("Updated object " + expected.getCommentaryId() + " - " + expected.getDate() + " - " + expected.getPerson().getName());
    }

    @Test
    public void testDeleteCommentary() throws Exception {
        commentaryService.save(commentary);
        assertNotNull(commentaryService.get(commentary.getCommentaryId()).getCommentaryId());
        commentaryService.delete(commentary);
        commentary = commentaryService.get(commentary.getCommentaryId());
        assertEquals("Can`t change status of object in database ", DELETED, commentary.getStatus());
    }

    @Test
    public void testRemoveCommentary() throws Exception {
        commentaryService.save(commentary);
        assertNotNull(commentaryService.get(commentary.getCommentaryId()).getCommentaryId());
        commentaryService.remove(commentary);
        commentary = commentaryService.get(commentary.getCommentaryId());
        assertNull("Object is not deleted from database ", commentary);
    }
}