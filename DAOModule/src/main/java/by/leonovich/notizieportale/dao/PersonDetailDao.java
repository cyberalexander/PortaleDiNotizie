package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.exception.PersistException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alexanderleonovich on 20.05.15.
 * In this.Class contains special methods for PersonDetail-entity
 */
@Repository
public class PersonDetailDao extends AbstractDao<PersonDetail> {

    @Autowired
    public PersonDetailDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Parse parameters of entity
     * @param session org.hibernate.Session
     * @return list of entities
     * @throws PersistException - custom exception
     */
    @Override
    protected List<PersonDetail> parseResultSet(Session session) throws PersistException {
        Criteria criteria = session.createCriteria(PersonDetail.class);
        return criteria.list();
    }

}
