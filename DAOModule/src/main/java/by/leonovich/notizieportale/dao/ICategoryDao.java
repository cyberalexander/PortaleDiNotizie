package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.exception.PersistException;
import org.hibernate.Session;

/**
 * Created by alexanderleonovich on 09.06.15.
 */
public interface ICategoryDao extends IGenericDao<Category> {

    Category getByName(String categoryName, Session session) throws PersistException;
}
