package by.leonovich.notizieportale.util;

import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.domainto.PersonDetailTO;
import by.leonovich.notizieportale.domainto.PersonTO;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

/**
 * Created by alexanderleonovich on 20.06.15.
 */
@Component
public class CloneUtil {

    public CloneUtil() {
    }

    public PersonTO clonePersistentPerson(Person person) {
        PersonTO personTO = new PersonTO();
        PersonDetailTO personDetailTO = clonePersonDetail(person.getPersonDetail());
        personTO.setCommentaries(person.getCommentaries());
        personTO.setName(person.getName());
        personTO.setNewses(person.getNewses());
        personTO.setPersonId(person.getPersonId());
        personTO.setSurname(person.getSurname());
        personTO.setStatus(person.getStatus());

        personTO.setPersonDetailTO(personDetailTO);
        personDetailTO.setPersonTO(personTO);
        return personTO;
    }

    private PersonDetailTO clonePersonDetail(PersonDetail personDetail) {
        PersonDetailTO personDetailTO = new PersonDetailTO();
        personDetailTO.setBirthday(personDetail.getBirthday());
        personDetailTO.setEmail(personDetail.getEmail());
        personDetailTO.setPassword(personDetail.getPassword());
        personDetailTO.setPersonId(personDetail.getPersonId());
        personDetailTO.setRole(personDetail.getRole());
        return personDetailTO;
    }

    public Person unClonePersistentPerson(PersonTO personTo) {
        Person person = new Person();
        PersonDetail personDetail = unClonePersonDetail(personTo.getPersonDetailTO());
        person.setCommentaries(personTo.getCommentaries());
        person.setName(personTo.getName());
        person.setNewses(personTo.getNewses());
        person.setPersonId(personTo.getPersonId());
        person.setSurname(personTo.getSurname());
        person.setStatus(personTo.getStatus());

        person.setPersonDetail(personDetail);
        personDetail.setPerson(person);
        return person;
    }

    private PersonDetail unClonePersonDetail(PersonDetailTO personDetailTo) {
        PersonDetail personDetail = new PersonDetail();
        personDetail.setBirthday(personDetailTo.getBirthday());
        personDetail.setEmail(personDetailTo.getEmail());
        personDetail.setPassword(personDetailTo.getPassword());
        personDetail.setPersonId(personDetailTo.getPersonId());
        personDetail.setRole(personDetailTo.getRole());
        return personDetail;
    }




}
