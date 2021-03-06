package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.exception.ServiceLayerException;

import java.util.List;

/**
 * Created by alexanderleonovich on 20.05.15.
 */
public interface ICategoryService extends IService<Category> {

    List<Category> getCategories() throws ServiceLayerException;

    Category getCategoryByName(String category) throws ServiceLayerException;

    Long saveCategoryNews(Category category, News news) throws ServiceLayerException;
}
