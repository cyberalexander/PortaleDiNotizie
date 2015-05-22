package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.exception.PersistException;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Class for working with persistence entity of NEWS
 */
public class NewsDao extends AbstractDao<News> {

    /**
     * Constructor of MySqlNewsDao.class
     */
    public NewsDao() {
        super();
    }

    @Override
    protected List<News> parseResultSet(Session session) throws PersistException {
        List<News> list = session.createSQLQuery("SELECT * FROM T_NEWS").addEntity(News.class).list();
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, News object) throws PersistException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, News object) throws PersistException {

    }


}
