package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.util.exception.PersistException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alexanderleonovich on 20.05.15.
 * In this.Class contains special methods for PersonDetail-entity
 */
@Repository
public class PersonDetailDao extends AbstractDao<PersonDetail> {
    private static final Logger logger = Logger.getLogger(PersonDetailDao.class);

    @Autowired
    public PersonDetailDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected List<PersonDetail> parseResultSet(Session session) throws PersistException {
        Criteria criteria = session.createCriteria(PersonDetail.class);
        return criteria.list();
    }

}
