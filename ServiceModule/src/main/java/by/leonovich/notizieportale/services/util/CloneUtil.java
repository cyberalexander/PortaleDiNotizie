package by.leonovich.notizieportale.services.util;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domainto.PersonTO;
import org.springframework.stereotype.Component;

/**
 * Created by alexanderleonovich on 20.06.15.
 */
public class CloneUtil {

    public CloneUtil() {
    }

    public PersonTO clonePersistentPerson(Person person) {
        PersonTO personTO = new PersonTO();
        personTO.setCommentaries(person.getCommentaries());
        personTO.setName(person.getName());
        personTO.setNewses(person.getNewses());
        personTO.setPersonDetail(person.getPersonDetail());
        personTO.setPersonId(person.getPersonId());
        personTO.setSurname(person.getSurname());
        personTO.setStatus(person.getStatus());
        return personTO;
    }
}
