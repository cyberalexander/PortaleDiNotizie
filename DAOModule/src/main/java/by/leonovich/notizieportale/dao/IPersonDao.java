package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Person;

/**
 * Created by alexanderleonovich on 09.06.15.
 */
public interface IPersonDao extends IGenericDao<Person> {

    Person getByEmail(String email);
}
