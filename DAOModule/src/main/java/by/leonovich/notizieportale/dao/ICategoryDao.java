package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.exception.PersistException;

/**
 * Created by alexanderleonovich on 09.06.15.
 */
public interface ICategoryDao extends IGenericDao<Category> {

    Category getByName(String categoryName) throws PersistException;
}
