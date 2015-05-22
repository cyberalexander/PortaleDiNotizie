package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Category;

import java.util.List;

/**
 * Created by alexanderleonovich on 20.05.15.
 */
public interface ICategoryService {

    List<Category> getCategories();

    void saveCategory(Category category);
}
