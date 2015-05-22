package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.exception.PersistException;
import org.hibernate.Session;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by alexanderleonovich on 20.05.15.
 */
public class CategoryDao extends AbstractDao<Category> {


    @Override
    protected List<Category> parseResultSet(Session session) throws PersistException {
        List<Category> list = session.createSQLQuery("SELECT * FROM T_CATEGORY").addEntity(Category.class).list();
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Category object) throws PersistException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Category object) throws PersistException {

    }


}