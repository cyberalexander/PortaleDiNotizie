package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.exception.PersistException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static by.leonovich.notizieportale.domain.util.StatusEnum.SAVED;
import static by.leonovich.notizieportale.util.DaoConstants.Const.STATUS;

/**
 * Created by alexanderleonovich on 20.05.15.
 * In this.Class contains special methods for PersonDetail-entity
 */
public class PersonDetailDao extends AbstractDao<PersonDetail>{

        @Override
        protected List<PersonDetail> parseResultSet(Session session) throws PersistException {
                Criteria criteria = session.createCriteria(PersonDetail.class);
                criteria.add(Restrictions.eq(STATUS, SAVED));
                List<PersonDetail> result = criteria.list();
                return result;
        }

}
