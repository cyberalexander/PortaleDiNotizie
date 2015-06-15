package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.exception.PersistException;
import org.hibernate.Session;

/**
 * Created by alexanderleonovich on 09.06.15.
 */
public interface IPersonDao extends IGenericDao<Person> {

    Person getByEmail(String email, Session session) throws PersistException;
}
