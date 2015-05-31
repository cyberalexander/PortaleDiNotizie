package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.util.StatusEnum;
import by.leonovich.notizieportale.services.util.TestConstants;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static by.leonovich.notizieportale.domain.util.StatusEnum.SAVED;
import static by.leonovich.notizieportale.services.util.TestConstants.*;
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

    public CommentaryServiceTest() {
        commentaryService = CommentaryService.getInstance();
    }

    @Before
    public void setUp() throws Exception {
        commentary = new Commentary(COMMENTARY_CONTENT, DATE, StatusEnum.SAVED);
        category = new Category(TestConst.CATEGORY_NAME, StatusEnum.SAVED);
        news = new News(TestConst.PAGE_ID, TestConst.TITLE, TestConst.MENU_TITLE,
                TestConst.DATE, TestConst.ANNOTATION, TestConst.CONTENT, StatusEnum.SAVED);
        person = new Person(TestConst.PERSON_NAME, TestConst.PERSON_SURNAME, StatusEnum.SAVED);
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
        Commentary actual = commentaryService.saveCommentary(commentary);
        Commentary expected = commentaryService.getCommentaryByPK(actual.getCommentaryId());
        logger.info("News object, what we get from database " + expected.getCommentaryId() + " - " + expected.getComment());
        Assert.assertNotNull("After persist id is null.", expected);
    }

    @Test
    public void testGetCommentaries() throws Exception {
        List list = commentaryService.getCommentaries();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > ZERO);
    }

    @Test
    public void testGetCommentariesByAuthorId() throws Exception {
        Commentary actual = commentaryService.saveCommentary(commentary);
        List<Commentary> expected = commentaryService.getCommentariesByAuthorId(actual.getPerson().getPersonId());
        logger.info("News object, what we get from database " + expected.size() + " - " + expected.get(ZERO).getPerson().getName());
        Assert.assertNotNull("After persist id is null.", expected);
        Assert.assertTrue(expected.size() > ZERO);
    }

    @Test
    public void testGetCommentariesByNewsId() throws Exception {
        Commentary actual = commentaryService.saveCommentary(commentary);
        List<Commentary> expected = commentaryService.getCommentariesByAuthorId(actual.getNews().getNewsId());
        logger.info("News object, what we get from database " + expected.size() + " - " + expected.get(ZERO).getNews().getCategory().getCategoryName());
        Assert.assertNotNull("After persist id is null.", expected);
        Assert.assertTrue(expected.size() > ZERO);
    }

    @Test
    public void testSaveCommentary() throws Exception {
        Commentary actual = commentaryService.saveCommentary(commentary);
        logger.info('\n' + "ID AFTER SAVE " + actual.getClass() + " IS " + actual.getCommentaryId() + '\n');
        Assert.assertNotNull("After persist id is null.", actual.getCommentaryId());
    }

    @Test
    public void testUpdateCommentary() throws Exception {
        Commentary actual = commentaryService.saveCommentary(commentary);
        commentary.setComment(TestConst.UNIQUE_COMMENT);
        commentaryService.updateCommentary(commentary);
        Commentary expected = commentaryService.getCommentaryByPK(actual.getCommentaryId());
        assertEquals(expected.getComment(), commentary.getComment());
        logger.info("Saved object " + actual.getCommentaryId() + " - " + actual.getDate() + " - " + actual.getPerson().getName());
        logger.info("Updated object " + expected.getCommentaryId() + " - " + expected.getDate() + " - " + expected.getPerson().getName());
    }

    @Test
    public void testDeleteCommentary() throws Exception {
        commentaryService.saveCommentary(commentary);
        assertNotNull(commentaryService.getCommentaryByPK(commentary.getCommentaryId()).getCommentaryId());
        commentaryService.deleteCommentary(commentary);
        commentary = commentaryService.getCommentaryByPK(commentary.getCommentaryId());
        assertEquals("Can`t change status of object in database ", DELETED, commentary.getStatus());
    }

    @Test
    public void testRemoveCommentary() throws Exception {
        commentaryService.saveCommentary(commentary);
        assertNotNull(commentaryService.getCommentaryByPK(commentary.getCommentaryId()).getCommentaryId());
        commentaryService.removeCommentary(commentary);
        commentary = commentaryService.getCommentaryByPK(commentary.getCommentaryId());
        assertNull("Object is not deleted from database ", commentary);
    }
}