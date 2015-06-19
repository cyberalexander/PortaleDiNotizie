package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.CategoryDao;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.services.util.exception.ServiceExcpetion;
import by.leonovich.notizieportale.util.exception.PersistException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mysql.jdbc.StringUtils.isNullOrEmpty;
import static java.util.Objects.nonNull;

/**
 * Created by alexanderleonovich on 20.05.15.
 * Buisnes layer for Category-entity
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CategoryService implements ICategoryService {
    private static final Logger logger = Logger.getLogger(CategoryService.class);
    @Autowired
    private CategoryDao categoryDao;

    public CategoryService() {
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Category> getCategories() throws ServiceExcpetion{
        List<Category> list;
        try {
            list = categoryDao.getAll();
        } catch (PersistException e) {
            logger.error(e);
            throw new ServiceExcpetion(e);
        }
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Category getCategoryByName(String category) throws ServiceExcpetion {
        Category categoryObj;
        if (!(isNullOrEmpty(category))) {
            try {
                categoryObj = categoryDao.getByName(category);
                if (nonNull(categoryObj)) {
                    return categoryObj;
                }
            } catch (PersistException e) {
                logger.error(e);
                throw new ServiceExcpetion(e);
            }
        }
        return null;
    }

    @Override
    public Long save(Category category) throws ServiceExcpetion{
        Long savedCategoryId;
        try {
            category.setStatus(StatusEnum.PERSISTED);
            savedCategoryId = categoryDao.save(category);
            logger.info("Category saved: " + savedCategoryId);
        }catch (PersistException e) {
            logger.error(e);
            throw new ServiceExcpetion(e);
        }
        return savedCategoryId;
    }

    @Override
    public Category update(Category category) throws ServiceExcpetion {
        return null;
    }

    @Override
    public Category delete(Category category)  throws ServiceExcpetion{
        if (nonNull(category.getCategoryId())) {
            Long deletedCategoryId = category.getCategoryId();
            try {
                category.setStatus(StatusEnum.DELETED);
                categoryDao.update(category);
                category = categoryDao.get(deletedCategoryId);
            } catch (PersistException e) {
                logger.error(e);
                throw new ServiceExcpetion(e);
            }
        }
        return category;
    }

    @Override
    public void remove(Category category) throws ServiceExcpetion {
        if (nonNull(category.getCategoryId())) {
            try {
                categoryDao.remove(category);
            } catch (PersistException e) {
                logger.error(e);
                throw new ServiceExcpetion(e);
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Category get(Long pK) throws ServiceExcpetion {
        try {
            return categoryDao.get(pK);
        } catch (PersistException e) {
            logger.error(e);
            throw new ServiceExcpetion(e);
        }
    }

    @Override
    public Category load(Long pK) throws ServiceExcpetion {
        return null;
    }
}
