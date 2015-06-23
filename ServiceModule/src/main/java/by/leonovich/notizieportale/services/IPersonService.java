package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domainto.PersonTO;
import by.leonovich.notizieportale.exception.ServiceLayerException;

/**
 * Created by alexanderleonovich on 29.04.15.
 */
public interface IPersonService extends IService<Person> {


    boolean checkPerson(String email, String password) throws ServiceLayerException;

    PersonTO getByEmail(String email) throws ServiceLayerException;

    Person getPersonByEmail(String email) throws ServiceLayerException;

    void logOutPerson() throws ServiceLayerException;

    Long savePersonTo(PersonTO personTo) throws ServiceLayerException;

    Long updatePersonTo(PersonTO personTo) throws ServiceLayerException;

}
