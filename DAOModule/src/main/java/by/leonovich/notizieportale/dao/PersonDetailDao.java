package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.exception.PersistException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by alexanderleonovich on 20.05.15.
 * In this.Class contains special methods for PersonDetail-entity
 */
public class PersonDetailDao extends AbstractDao<PersonDetail>{

        @Override
        protected List<PersonDetail> parseResultSet(Session session) throws PersistException {
                List<PersonDetail> list = session.createSQLQuery("SELECT * FROM T_PERSON_DETAIL").addEntity(PersonDetail.class).list();
                return list;
        }

}
