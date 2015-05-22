package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.exception.PersistException;
import org.hibernate.Session;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Class for working with persistence entity of USER
 */
public class PersonDao extends AbstractDao<Person> {

    public PersonDao() {
        super();
    }

    @Override
    protected List<Person> parseResultSet(Session session) throws PersistException {
        List<Person> list = session.createSQLQuery("SELECT * FROM T_PERSON").addEntity(Person.class).list();
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Person object) throws PersistException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Person object) throws PersistException {

    }
}
