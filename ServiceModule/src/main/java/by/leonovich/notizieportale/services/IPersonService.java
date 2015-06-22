package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.services.exception.ServiceLayerException;

/**
 * Created by alexanderleonovich on 29.04.15.
 */
public interface IPersonService extends IService<Person> {


    boolean checkPerson(String email, String password) throws ServiceLayerException;

    Person getByEmail(String email) throws ServiceLayerException;

    void logOutPerson() throws ServiceLayerException;

}
