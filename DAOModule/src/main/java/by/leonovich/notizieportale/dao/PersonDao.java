package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.util.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Class for working with persistence entity of PERSON
 */
public class PersonDao extends AbstractDao<Person> {

    public PersonDao() {
        super();
    }

    @Override
    protected List<Person> parseResultSet(Session session) throws PersistException {
        StatusEnum status = StatusEnum.SAVED;
        List<Person> list;
        String hql = "SELECT p FROM Person p WHERE p.status=:status";
        Query query = session.createQuery(hql).setParameter("status", status);
        list = query.list();
        return list;
    }

    public Person getByEmail(String email) throws PersistException {
        session = getSession();
        StatusEnum status = StatusEnum.SAVED;
        Person person;
        String hql = "SELECT p FROM Person p WHERE p.status=:status and p.personDetail.email=:email";
        Query query = session.createQuery(hql)
                .setParameter("status", status)
                .setParameter("email", email);
        person = (Person) query.uniqueResult();
        return person;
    }
}
