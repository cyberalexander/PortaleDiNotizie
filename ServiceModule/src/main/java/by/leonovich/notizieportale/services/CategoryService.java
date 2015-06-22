package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.CategoryDao;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.services.util.ServiceConstants;
import by.leonovich.notizieportale.services.util.ServiceConstants.Const;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import by.leonovich.notizieportale.services.exception.ServiceLayerException;

import java.util.List;

import static by.leonovich.notizieportale.services.util.ServiceConstants.Const.MINUS_ONE;
import static com.mysql.jdbc.StringUtils.isNullOrEmpty;
import static java.util.Objects.nonNull;

/**
 * Created by alexanderleonovich on 20.05.15.
 * Buisnes layer for Category-entity
 */
@Service("categoryService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CategoryService implements ICategoryService {
    private static final Logger logger = Logger.getLogger(CategoryService.class);
    @Autowired
    private CategoryDao categoryDao;

    public CategoryService() {
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Category> getCategories() throws ServiceLayerException {
        List<Category> list;
        try {
            list = categoryDao.getAll();
        } catch (PersistException e) {
            logger.error(e);
            throw new ServiceLayerException(e);
        }
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Category getCategoryByName(String category) throws ServiceLayerException {
        Category categoryObj;
        if (!(isNullOrEmpty(category))) {
            try {
                categoryObj = categoryDao.getByName(category);
                if (nonNull(categoryObj)) {
                    return categoryObj;
                }
            } catch (PersistException e) {
                logger.error(e);
                throw new ServiceLayerException(e);
            }
        }
        return null;
    }

    @Override
    public Long save(Category category) throws ServiceLayerException {
        Long savedCategoryId;
        try {
            category.setStatus(StatusEnum.PERSISTED);
            savedCategoryId = categoryDao.save(category);
            logger.info("Category saved: " + savedCategoryId);
        }catch (PersistException e) {
            logger.error(e);
            throw new ServiceLayerException(e);
        }
        return savedCategoryId;
    }

    @Override
    public Long saveOrUpdate(Category category)  throws ServiceLayerException {
        return null;
    }

    @Override
    public Category update(Category category) throws ServiceLayerException {
        return null;
    }

    @Override
    public Long delete(Category category)  throws ServiceLayerException {
        if (nonNull(category.getCategoryId())) {
            Long deletedCategoryId = category.getCategoryId();
            try {
                category.setStatus(StatusEnum.DELETED);
                categoryDao.update(category);
                return deletedCategoryId;
            } catch (PersistException e) {
                logger.error(e);
                throw new ServiceLayerException(e);
            }
        }
        return (long) MINUS_ONE;
    }

    @Override
    public void remove(Category category) throws ServiceLayerException {
        if (nonNull(category.getCategoryId())) {
            try {
                categoryDao.remove(category);
            } catch (PersistException e) {
                logger.error(e);
                throw new ServiceLayerException(e);
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Category get(Long pK) throws ServiceLayerException {
        try {
            return categoryDao.get(pK);
        } catch (PersistException e) {
            logger.error(e);
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public Category load(Long pK) throws ServiceLayerException {
        return null;
    }
}
