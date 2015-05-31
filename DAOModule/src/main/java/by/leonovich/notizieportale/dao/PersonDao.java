package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.util.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.util.DaoConstants;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static by.leonovich.notizieportale.domain.util.StatusEnum.*;
import static by.leonovich.notizieportale.util.DaoConstants.Const.EMAIL;
import static by.leonovich.notizieportale.util.DaoConstants.Const.STATUS;

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
        Criteria criteria = session.createCriteria(Person.class);
        criteria.add(Restrictions.eq(STATUS, SAVED));
        List<Person> result = criteria.list();
        return result;
    }

    public Person getByEmail(String email) throws PersistException {
        Session session = getSession();
        StatusEnum status = SAVED;
        Person person;
        String hql = "SELECT p FROM Person p WHERE p.status=:status and p.personDetail.email=:email";
        Query query = session.createQuery(hql)
                .setParameter(STATUS, status)
                .setParameter(EMAIL, email);
        person = (Person) query.uniqueResult();
        return person;
    }
}
