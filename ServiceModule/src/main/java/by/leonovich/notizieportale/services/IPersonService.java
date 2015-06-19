package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.services.util.exception.ServiceExcpetion;

import javax.servlet.http.HttpSession;

/**
 * Created by alexanderleonovich on 29.04.15.
 */
public interface IPersonService extends IService<Person> {


    boolean checkPerson(String email, String password) throws ServiceExcpetion;

    Person getByEmail(String email) throws ServiceExcpetion;

    Long registerPersonFirstStep(Person person) throws ServiceExcpetion;

    boolean registerPersonSecondStep(HttpSession httpSession, PersonDetail personDetail) throws ServiceExcpetion;

    void logOutPerson() throws ServiceExcpetion;

}
