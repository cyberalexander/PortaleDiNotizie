package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.exception.PersistException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import static by.leonovich.notizieportale.domain.enums.StatusEnum.PERSISTED;
import static by.leonovich.notizieportale.util.DaoConstants.Const.STATUS;

/**
 * Created by alexanderleonovich on 20.05.15.
 * In this.Class contains special methods for PersonDetail-entity
 */
@Repository
public class PersonDetailDao extends AbstractDao<PersonDetail> {
    private static final Logger logger = Logger.getLogger(PersonDetailDao.class);


    public PersonDetailDao() {
        super();
    }

    @Override
    protected List<PersonDetail> parseResultSet(Session session) throws PersistException {
        Criteria criteria = session.createCriteria(PersonDetail.class);
        return criteria.list();
    }

}
