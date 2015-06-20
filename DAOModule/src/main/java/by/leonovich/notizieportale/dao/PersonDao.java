package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.util.DaoConstants;
import by.leonovich.notizieportale.util.exception.PersistException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static by.leonovich.notizieportale.domain.enums.StatusEnum.*;
import static by.leonovich.notizieportale.util.DaoConstants.Const.EMAIL;
import static by.leonovich.notizieportale.util.DaoConstants.Const.STATUS;
import static by.leonovich.notizieportale.util.DaoConstants.Const.ZERO;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Class for working with persistence entity of PERSON
 */
@Repository
public class PersonDao extends AbstractDao<Person> implements IPersonDao {
    private static final Logger logger = Logger.getLogger(PersonDao.class);


    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     *
     * @param session
     * @return
     * @throws PersistException
     */
    @Override
    protected List<Person> parseResultSet(Session session) throws PersistException {
        Criteria criteria = session.createCriteria(Person.class);
        criteria.add(Restrictions.eq(STATUS, PERSISTED));
        List<Person> result = criteria.list();
        return result;
    }

    /**
     *
     * @param email
     * @return
     * @throws PersistException
     */
    @Override
    @SuppressWarnings("unchecked")
    public Person getByEmail(String email){
        StatusEnum status = PERSISTED;
        List<Person> persons = new ArrayList<>();
        String hql = "SELECT p FROM Person p WHERE p.status=:status and p.personDetail.email=:email";
        Query query = getCurrentSession().createQuery(hql)
                .setParameter(STATUS, status)
                .setParameter(EMAIL, email);
        persons = (List<Person>) query.list();
        if (persons.size() > ZERO) {
            return persons.get(ZERO);
        } else {
            return null;
        }
    }
}
