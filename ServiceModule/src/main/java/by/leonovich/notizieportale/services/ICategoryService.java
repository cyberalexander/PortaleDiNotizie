package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Category;

import java.util.List;

/**
 * Created by alexanderleonovich on 20.05.15.
 */
public interface ICategoryService {

    Category getCategoryByPK(Long PK);

    List<Category> getCategories();

    Long saveCategory(Category category);

    Category updateCategory(Category category);

    Category deleteCategory(Category category);

    void removeCategory(Category category);

    Category getCategoryByName(String category);
}
