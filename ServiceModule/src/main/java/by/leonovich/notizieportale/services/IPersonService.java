package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.PersonDetail;

import javax.servlet.http.HttpSession;

/**
 * Created by alexanderleonovich on 29.04.15.
 */
public interface IPersonService extends IService<Person> {


    boolean checkPerson(String email, String password);

    Person getByEmail(String email);

    Long registerPersonFirstStep(Person person);

    boolean registerPersonSecondStep(HttpSession httpSession, PersonDetail personDetail);

    void logOutPerson();

}
