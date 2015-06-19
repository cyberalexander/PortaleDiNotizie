package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.dao.util.TestConstants;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static by.leonovich.notizieportale.dao.util.TestConstants.TestConst;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by alexanderleonovich on 28.05.15.
 */
@org.junit.Ignore
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonDaoTest {
    private static final Logger logger = Logger.getLogger(NewsDaoTest.class);
    private ClassPathXmlApplicationContext ac;
    @Autowired
    private PersonDao personDao;
    private Person person;
    private PersonDetail personDetail;

    public PersonDaoTest() {
        ac = new ClassPathXmlApplicationContext(new String[]{"test-beans-dao.xml"});
        personDao = (PersonDao) ac.getBean("personDao");
    }

    @Before
    public void setUp() throws Exception {
        person = new Person(TestConstants.TestConst.PERSON_NAME, TestConstants.TestConst.PERSON_SURNAME, StatusEnum.PERSISTED);
        personDetail = new PersonDetail(TestConst.EMAIL, TestConst.PASSWORD, TestConst.BITHDAY);
        person.setPersonDetail(personDetail);
        personDetail.setPerson(person);
    }

    @After
    public void tearDown() throws Exception {
        person = null;
    }

    @Test
    public void testGetByEmail() throws Exception {
        assertNull("Id before save() is not null.", person.getPersonId());
        person.getPersonDetail().setEmail(TestConst.UNIQUE_EMAIL);
        personDao.save(person);
        logger.info("Saved Person  " + person.getPersonId());
        Person expected = personDao.getByEmail(person.getPersonDetail().getEmail());
        logger.info("Person email " + person.getPersonDetail().getEmail());
        assertEquals("Person, what we get by email, not equal save person", expected, person);
    }
}