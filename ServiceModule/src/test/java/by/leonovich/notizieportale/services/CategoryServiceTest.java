package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.services.util.ServiceTestConfig;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static by.leonovich.notizieportale.domain.enums.StatusEnum.PERSISTED;
import static by.leonovich.notizieportale.services.util.TestConstants.TestConst.*;
import static org.junit.Assert.*;

/**
 * Created by alexanderleonovich on 28.05.15.
 */
public class CategoryServiceTest {
    private static final Logger logger = Logger.getLogger(CategoryServiceTest.class);

    private Category category;
    private CategoryService categoryService;
    private ApplicationContext ac;


    public CategoryServiceTest() {
        ac = new AnnotationConfigApplicationContext(ServiceTestConfig.class);
        categoryService = (CategoryService) ac.getBean("categoryService");
        /*categoryService = CategoryService.getInstance();*/
    }

    @Before
    public void setUp() throws Exception {
        category = new Category(CATEGORY_NAME, PERSISTED);
    }

    @After
    public void tearDown() throws Exception {
        category = null;
    }

    @Test
    public void testGetCategoryByPK() throws Exception {
        Long id = categoryService.save(category);
        Category expected = categoryService.get(id);
        logger.info("Object, what we get from database " + expected.getCategoryId() + " - " + expected.getCategoryName());
        assertNotNull("After persist id is null.", expected);
    }

    @Test
    public void testGetCategories() throws Exception {
        for (int i = ZERO; i < THREE; i++) {
            categoryService.save(category);
        }
        List list = categoryService.getCategories();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > ZERO);
    }

    @Test
    public void testSaveCategory() throws Exception {
        Long id = categoryService.save(category);
        logger.info('\n' + "ID AFTER SAVE " + category.getClass() + " IS " + id + '\n');
        Assert.assertNotNull("After persist id is null.", id);
    }

    @Test
    public void testUpdateCommentary() throws Exception {
        assertNull("Method update is already not finished.", category.getCategoryId());
    }

    @Test
    public void testDeleteCategory() throws Exception {
        categoryService.save(category);
        assertNotNull(categoryService.get(category.getCategoryId()).getCategoryId());
        categoryService.delete(category);
        category = categoryService.get(category.getCategoryId());
        assertEquals("Can`t change status of object in database ", DELETED, category.getStatus());
    }

    @Test
    public void testRemoveCategory() throws Exception {
        categoryService.save(category);
        assertNotNull(categoryService.get(category.getCategoryId()).getCategoryId());
        categoryService.remove(category);
        category = categoryService.get(category.getCategoryId());
        assertNull("Object is not deleted from database ", category);
    }

    @Test
    public void testGetCategoryByName() throws Exception {
        assertNull("Id before save() is not null.", category.getCategoryId());
        category.setCategoryName("VasiaLOh");
        categoryService.save(category);
        logger.info("Saved category " + category.getCategoryName());
        Category expected = categoryService.getCategoryByName(category.getCategoryName());
        logger.info("Names of categories: category-actual-" + category.getCategoryName() + ", category-expected-" + expected.getCategoryName());
        assertEquals("Categories names not equal. ", expected.getCategoryName(), category.getCategoryName());
    }
}