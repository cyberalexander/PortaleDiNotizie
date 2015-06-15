package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.IPersonDao;
import by.leonovich.notizieportale.dao.PersonDao;
import by.leonovich.notizieportale.dao.PersonDetailDao;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.domain.enums.RoleEnum;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.services.util.ServiceConstants;
import by.leonovich.notizieportale.util.HibernateUtil;
import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static by.leonovich.notizieportale.domain.enums.StatusEnum.PERSISTED;
import static by.leonovich.notizieportale.services.util.ServiceConstants.Const.CONNECTION;
import static by.leonovich.notizieportale.services.util.ServiceConstants.Const.HIBER_SESSION;
import static by.leonovich.notizieportale.services.util.ServiceConstants.Const.P_ID;
import static com.mysql.jdbc.StringUtils.isNullOrEmpty;
import static java.util.Objects.nonNull;

/**
 * Created by alexanderleonovich on 29.04.15.
 * Service layer for domain entity User
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PersonService implements IPersonService {
    private static final Logger logger = Logger.getLogger(PersonService.class);

    private static PersonService personServiceInst;
    @Autowired
    @Qualifier("personDao")
    private IPersonDao personDao;
    @Autowired
    @Qualifier("personDetailDao")
    private PersonDetailDao personDetailDao;
    private Transaction transaction;

    public PersonService() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            personDao = (PersonDao) factory.getDao(Person.class);
            personDetailDao = (PersonDetailDao) factory.getDao(PersonDetail.class);
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

    public void putSessionInHttp(HttpSession httpSession) {
        Session hiberSession = personDao.getSession();
        Connection connecion = hiberSession.disconnect();
        httpSession.setAttribute(HIBER_SESSION, hiberSession);
        httpSession.setAttribute(CONNECTION, connecion);
        personDao.detachSession();
    }

    public Session getSessionFromHttp(HttpSession httpSession) {
        Session hiberSession = (Session) httpSession.getAttribute(HIBER_SESSION);
        hiberSession.getSessionFactory().openSession();
        return hiberSession;
    }

    public void clearHttpAttributes(HttpSession httpSession) {
        if (nonNull(httpSession.getAttribute(HIBER_SESSION))) {
            httpSession.removeAttribute(HIBER_SESSION);
        }
        if (nonNull(httpSession.getAttribute(CONNECTION))) {
            httpSession.removeAttribute(CONNECTION);
        }
        if (nonNull(httpSession.getAttribute(P_ID))) {
            httpSession.removeAttribute(P_ID);
        }
    }

    /**
     * Server validation of user
     * @param email    - email-adress of user who want to autenitcate to site
     * @param password - password of user who want to autenitcate to site
     * @return true, if user in database (registered), or false, if user not registered
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean checkPerson(String email, String password) {
        if (!(isNullOrEmpty(email)) && !(isNullOrEmpty(password))) {
            try {
                Session session = personDao.getSession();
                transaction = session.beginTransaction();
                List<PersonDetail> personDetails = personDetailDao.getAll(session);
                transaction.commit();
                for (PersonDetail element : personDetails) {
                    if ((element.getEmail().equals(email))
                            && (element.getPassword().equals(password))) {
                        return true;
                    }
                }
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                personDao.clearSession();
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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person getByEmail(String email) {
        if (!(isNullOrEmpty(email))) {
            Person person = null;
            try {
                Session session = personDao.getSession();
                transaction = session.beginTransaction();
                person = personDao.getByEmail(email, session);
                transaction.commit();
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                personDao.clearSession();
            }
            return person;
        }
        return null;
    }

    @Override
    public Long registerPersonFirstStep(Person person) {
            Long id = null;
        if (nonNull(person)) {
            try {
                Session session = personDao.getSession();
                transaction = session.beginTransaction();
                if (person.getPersonId() == null){
                    id = personDao.save(person, session);
                }else{
                    personDao.update(person, session);
                }
                transaction.commit();
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                personDao.clearSession();
            }
        }
        return id;
    }

    @Override
    public boolean registerPersonSecondStep(HttpSession httpSession, PersonDetail personDetail) {
            try {
                Session session = getSessionFromHttp(httpSession);
                transaction = session.beginTransaction();
                Person person = (Person) session.get(Person.class,
                        (Serializable) httpSession.getAttribute(P_ID));
                person.setStatus(PERSISTED);
                personDetail.setPerson(person);
                //person.setPersonDetail(personDetail);
                List<PersonDetail> personDetails = personDetailDao.getAll(session);
                for (PersonDetail element : personDetails) {
                    if (element.getEmail().equals(personDetail.getEmail())) {
                        System.out.println('\n' + '\n' + element.getEmail() + "-" + personDetail.getEmail() + '\n' + '\n');
                        return false;
                    }
                }
                personDetailDao.save(personDetail, session);
                transaction.commit();
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                personDao.clearSession();
                clearHttpAttributes(httpSession);
            }
        return true;
    }

/*    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person getPersonByEmail(String email) {
        if (!(isNullOrEmpty(email))) {
            Person person = null;
            try {
                Session session = personDao.getSession();
                transaction = session.beginTransaction();
                person = personDao.getByEmail(email, session);
                transaction.commit();
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                personDao.clearSession();
            }
            return person;
        }
        return null;
    }*/

    @Override
    public Person update(Person person) {
        if (nonNull(person.getPersonId())) {
            Long updatedPersonId = person.getPersonId();
            try {
                Session session = personDao.getSession();
                transaction = session.beginTransaction();
                personDao.update(person, session);
                person = (Person) session.get(Person.class, updatedPersonId);
                transaction.commit();
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                personDao.clearSession();
            }
        }
        return person;
    }

    @Override
    public void logOutPerson() {
         personDao.getSession().close();
    }

    @Override
    public Long save(Person person) {
        return null;
    }

    @Override
    public Person delete(Person person) {
        return null;
    }

    @Override
    public void remove(Person person) {

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person get(Long pK) {
        if (nonNull(pK)) {
            try {
                return personDao.get(pK);
            } catch (PersistException e) {
                logger.error(e);
            }
        }
        return null;
    }

    @Override
    public Person load(Long pK) {
        if (nonNull(pK)) {
            Person person = null;
            try {
                Session session = personDao.getSession();
                transaction = session.beginTransaction();
                person = personDao.load(pK, session);
                transaction.commit();
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                personDao.clearSession();
            }
            return person;
        }
        return null;
    }

}
