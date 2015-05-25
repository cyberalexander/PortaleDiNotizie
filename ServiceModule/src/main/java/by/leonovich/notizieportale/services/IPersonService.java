package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.Person;

/**
 * Created by alexanderleonovich on 29.04.15.
 */
public interface IPersonService {

    Person getByPK(Long pK);

    boolean checkPerson(String email, String password);

    Person authenticationProcess(String email);

    boolean registerNewPerson(Person person);

    Person getPersonByEmail(String email);

    void updateUserInformation(Person person);

}
