package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Category;
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

import static by.leonovich.notizieportale.dao.util.TestConstants.TestConst;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by alexanderleonovich on 25.05.15.
 */
@Ignore
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CategoryDaoTest {
    private static final Logger logger = Logger.getLogger(CategoryDaoTest.class);
    private ClassPathXmlApplicationContext ac;
    @Autowired
    CategoryDao categoryDao;
    Category category;

    public CategoryDaoTest() {
        ac = new ClassPathXmlApplicationContext(new String[]{"test-beans-dao.xml"});
        categoryDao = (CategoryDao) ac.getBean("categoryDao");
    }

    @Before
    public void setUp() throws Exception {
        category = new Category(TestConst.CATEGORY_NAME, StatusEnum.PERSISTED);
    }

    @After
    public void tearDown() throws Exception {
        category = null;
    }

    @Test
    public void testGetByName() throws Exception {
        assertNull("Id before save() is not null.", category.getCategoryId());
        category.setCategoryName("VasiaLOh");
        categoryDao.save(category);
        logger.info("Saved category " + category.getCategoryName());
        Category expected = categoryDao.getByName(category.getCategoryName());
        logger.info("Names of categories: category-actual-" + category.getCategoryName() + ", category-expected-" + expected.getCategoryName());
        assertEquals("Categories names not equal. ", expected.getCategoryName(), category.getCategoryName());
    }

}