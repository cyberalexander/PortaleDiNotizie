package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.PersonDao;
import by.leonovich.notizieportale.dao.PersonDetailDao;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.domainto.PersonTO;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.exception.ServiceLayerException;
import by.leonovich.notizieportale.util.CloneUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.leonovich.notizieportale.domain.enums.RoleEnum.ROLE_USER;
import static by.leonovich.notizieportale.util.ServiceConstants.Const.MINUS_ONE;
import static com.mysql.jdbc.StringUtils.isNullOrEmpty;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Created by alexanderleonovich on 29.04.15.
 * Service layer for domain entity User
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PersonService implements IPersonService {
    private static final Logger logger = Logger.getLogger(PersonService.class);

    @Autowired
    private PersonDao personDao;
    @Autowired
    private PersonDetailDao personDetailDao;
    @Autowired
    CloneUtil cloneUtil;


    public PersonService() {

    }


    /**
     * Server validation of user
     *
     * @param email    - email-adress of user who want to autenitcate to site
     * @param password - password of user who want to autenitcate to site
     * @return true, if user in database (registered), or false, if user not registered
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean checkPerson(String email, String password) throws ServiceLayerException {
        if (!(isNullOrEmpty(email)) && !(isNullOrEmpty(password))) {
            try {
                List<PersonDetail> personDetails = personDetailDao.getAll();
                for (PersonDetail element : personDetails) {
                    if ((element.getEmail().equals(email))
                            && (element.getPassword().equals(password))) {
                        return true;
                    }
                }
            } catch (PersistException e) {
                logger.error(e);
                throw new ServiceLayerException(e);
            }
        }
        return false;
    }

    /**
     * Get registered user from database
     *
     * @param email - field in colum, identified userØ
     * @return User user
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PersonTO getByEmail(String email) throws ServiceLayerException {
        if (!(isNullOrEmpty(email))) {
            PersonTO personTO = null;
            personTO = cloneUtil.clonePersistentPerson(personDao.getByEmail(email));
            return personTO;
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person getPersonByEmail(String email) throws ServiceLayerException {
        if (!(isNullOrEmpty(email))) {
            Person person = personDao.getByEmail(email);
            return person;
        }
        return null;
    }


    @Override
    public Person update(Person person) throws ServiceLayerException {
        if (nonNull(person.getPersonId())) {
            Long pK = person.getPersonId();
            try {
              /*  if (!(encodePassowrd(person.getPersonDetail().getPassword()).equals(
                        personDetailDao.get(person.getPersonId()).getPassword()))) {*/
                    person.getPersonDetail().setPassword(encodePassowrd(person.getPersonDetail().getPassword()));
                /*}*/
                personDao.update(person);
                person = (Person) personDao.get(pK);
            } catch (PersistException e) {
                logger.error(e);
                throw new ServiceLayerException(e);
            }
        }
        return person;
    }

    @Override
    public void logOutPerson() throws ServiceLayerException {
    }

    public Long savePersonTo(PersonTO personTo) throws ServiceLayerException {
        Long pK = (long) MINUS_ONE;
        if (!(isNullOrEmpty(personTo.getPersonDetailTO().getEmail()))) {
            Person person = cloneUtil.unClonePersistentPerson(personTo);
            Person testPerson = personDao.getByEmail((person.getPersonDetail().getEmail()));
            if (!isNull(testPerson)) {
                return pK;
            } else {
                try {
                    person.getPersonDetail().setPassword(encodePassowrd(person.getPersonDetail().getPassword()));
                    person.setStatus(StatusEnum.PERSISTED);
                    person.getPersonDetail().setRole(ROLE_USER);
                    pK = personDao.save(person);
                    return pK;
                } catch (PersistException e) {
                    logger.error(e);
                    throw new ServiceLayerException(e);
                }
            }
        }
        return pK;
    }

    @Override
    public Long updatePersonTo(PersonTO personTo) throws ServiceLayerException {
        if (nonNull(personTo.getPersonId())) {
            Long pK = personTo.getPersonId();
            try {
              /*  if (!(encodePassowrd(person.getPersonDetail().getPassword()).equals(
                        personDetailDao.get(person.getPersonId()).getPassword()))) {*/
                Person person = cloneUtil.unClonePersistentPerson(personTo);
                person.getPersonDetail().setPassword(encodePassowrd(person.getPersonDetail().getPassword()));
                /*}*/
                personDao.update(person);
                return pK;
            } catch (PersistException e) {
                logger.error(e);
                throw new ServiceLayerException(e);
            }
        }
        return (long) MINUS_ONE;
    }

    @Override
    public Long save(Person person) throws ServiceLayerException {
        return null;
    }

    @Override
    public void saveOrUpdate(Person person) throws ServiceLayerException {
    }

    @Override
    public Long delete(Person person) throws ServiceLayerException {
        return null;
    }

    @Override
    public void remove(Person person) throws ServiceLayerException {

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person get(Long pK) throws ServiceLayerException {
        if (nonNull(pK)) {
            try {
                return personDao.get(pK);
            } catch (PersistException e) {
                logger.error(e);
                throw new ServiceLayerException(e);
            }
        }
        return null;
    }

    @Override
    public Person load(Long pK) throws ServiceLayerException {
        if (nonNull(pK)) {
            try {
                return personDao.load(pK);
            } catch (PersistException e) {
                logger.error(e);
                throw new ServiceLayerException(e);
            }
        }
        return null;
    }

    private static String encodePassowrd(String passowrd) {
        String md5Hex = DigestUtils.md5Hex(passowrd);
        return md5Hex;
    }

}
