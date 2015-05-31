package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.PersonDao;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.dao.IGenericDao;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.services.util.ServiceConstants;
import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by alexanderleonovich on 29.04.15.
 * Service layer for domain entity User
 */
public class PersonService implements IPersonService {
    private static final Logger logger = Logger.getLogger(PersonService.class);

    private static PersonService personServiceInst;
    private final ThreadLocal sessionStatus = new ThreadLocal();
    private PersonDao personDao;

    private PersonService() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            personDao = (PersonDao) factory.getDao(Person.class);
        } catch (PersistException e) {
            logger.error(e);
        }
    }

    public static synchronized PersonService getInstance(){
        if (personServiceInst == null){
            personServiceInst = new PersonService();
        }
        return personServiceInst;
    }

    @Override
    public Person getByPK(Long pK) {
        if (pK != null) {
            Person person = null;
            Transaction transaction = null;
            try {
                Session session = personDao.getSession();
                transaction = session.beginTransaction();
                person = personDao.getByPK(pK);
                session.evict(person);
                transaction.commit();
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                personDao.clearSession(sessionStatus);
            }
            return person;
        }
        return null;
    }

    @Override
    public Person loadByPK(Long pK) {
        if (pK != null) {
            Person person = null;
            Transaction transaction = null;
            try {
                Session session = personDao.getSession();
                transaction = session.beginTransaction();
                person = personDao.loadByPK(pK);
                transaction.commit();
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                personDao.clearSession(sessionStatus);
            }
            return person;
        }
        return null;
    }

    /**
     * Server validation of user
     * @param email    - email-adress of user who want to autenitcate to site
     * @param password - password of user who want to autenitcate to site
     * @return true, if user in database (registered), or false, if user not registered
     */
    @Override
    public boolean checkPerson(String email, String password) {
        if (!(StringUtils.isNullOrEmpty(email)) && !(StringUtils.isNullOrEmpty(password))) {
            Transaction transaction = null;
            try {
                Session session = personDao.getSession();
                transaction = session.beginTransaction();
                List<Person> personList = personDao.getAll();
                transaction.commit();
                for (Person personElement : personList) {
                    if ((personElement.getPersonDetail().getEmail().equals(email))
                            && (personElement.getPersonDetail().getPassword().equals(password))) {
                        return true;
                    }
                }
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                personDao.clearSession(sessionStatus);
            }
        }
        return false;
    }

    /**
     * Get registered user from database
     * @param email - field in colum, identified userØ
     * @return User user
     */
    @Override
    public Person authenticationProcess(String email) {
        if (!(StringUtils.isNullOrEmpty(email))) {
            Person person = null;
            Transaction transaction = null;
            try {
                Session session = personDao.getSession();
                transaction = session.beginTransaction();
                person = personDao.getByEmail(email);
                transaction.commit();
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                personDao.clearSession(sessionStatus);
            }
            return person;
        }
        return null;
    }

    @Override
    public boolean registerNewPerson(Person person) {
        if (person != null) {
            Transaction transaction = null;
            try {
                Session session = personDao.getSession();
                transaction = session.beginTransaction();
                List<Person> persons = personDao.getAll();
                for (Person element : persons) {
                    if (element.getPersonDetail().getEmail().equals(person.getPersonDetail().getEmail())) {
                        System.out.println('\n' + '\n' + element.getPersonDetail().getEmail() + "-" + person.getPersonDetail().getEmail() + '\n' + '\n');
                        return false;
                    }
                }
                personDao.save(person);
                transaction.commit();
                return true;
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                personDao.clearSession(sessionStatus);
            }
        }
        return false;
    }

    @Override
    public Person getPersonByEmail(String email) {
        if (!(StringUtils.isNullOrEmpty(email))) {
            Person person = null;
            Transaction transaction = null;
            try {
                Session session = personDao.getSession();
                transaction = session.beginTransaction();
                person = personDao.getByEmail(email);
                transaction.commit();
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                personDao.clearSession(sessionStatus);
            }
            return person;
        }
        return null;
    }

    @Override
    public void updateUserInformation(Person person) {
        if (person.getPersonId() != null
                && person.getPersonId() != ServiceConstants.Const.ZERO
                && person.getPersonId() > ServiceConstants.Const.ZERO) {
            Transaction transaction = null;
            try {
                Session session = personDao.getSession();
                transaction = session.beginTransaction();
                personDao.update(person);
                transaction.commit();
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                personDao.clearSession(sessionStatus);
            }
        }
    }


}
