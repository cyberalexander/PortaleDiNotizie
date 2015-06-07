package by.leonovich.notizieportale.dao;

import static by.leonovich.notizieportale.dao.util.TestConstants.TestConst;

import by.leonovich.notizieportale.dao.util.TestConstants;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.*;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alexanderleonovich on 28.05.15.
 */
public class PersonDaoTest {
    private static final Logger logger = Logger.getLogger(NewsDaoTest.class);
    protected Session session;
    private Transaction transaction;
    private PersonDao personDao;
    private Person person;
    private PersonDetail personDetail;

    public PersonDaoTest() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            personDao = (PersonDao) factory.getDao(Person.class);
        } catch (PersistException e) {
            logger.error(e);
        }
    }

    @Before
    public void setUp() throws Exception {
        person = new Person(TestConstants.TestConst.PERSON_NAME, TestConstants.TestConst.PERSON_SURNAME, StatusEnum.PERSISTED);
        personDetail = new PersonDetail(TestConst.EMAIL, TestConst.PASSWORD, TestConst.DATE);
        person.setPersonDetail(personDetail);
        personDetail.setPerson(person);
        session = personDao.getSession();
        transaction = session.beginTransaction();
    }

    @After
    public void tearDown() throws Exception {
        transaction.commit();
        personDao.clearSession();
        person = null;
    }

    @Test
    public void testGetByEmail() throws Exception {
        assertNull("Id before save() is not null.", person.getPersonId());
        person.getPersonDetail().setEmail(TestConst.UNIQUE_EMAIL);
        personDao.save(person, session);
        logger.info("Saved Person  " + person.getPersonId());
        Person expected = personDao.getByEmail(person.getPersonDetail().getEmail(), session);
        logger.info("Person email " + person.getPersonDetail().getEmail());
        assertEquals("Person, what we get by email, not equal save person", expected, person);
    }
}