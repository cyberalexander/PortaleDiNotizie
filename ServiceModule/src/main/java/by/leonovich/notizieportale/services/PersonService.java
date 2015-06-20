package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.PersonDao;
import by.leonovich.notizieportale.dao.PersonDetailDao;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.domain.enums.RoleEnum;
import by.leonovich.notizieportale.services.util.exception.ServiceExcpetion;
import by.leonovich.notizieportale.util.exception.PersistException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static by.leonovich.notizieportale.domain.enums.StatusEnum.PERSISTED;
import static by.leonovich.notizieportale.services.util.ServiceConstants.Const.P_ID;
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
    public boolean checkPerson(String email, String password) throws ServiceExcpetion {
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
                throw new ServiceExcpetion(e);
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
    public Person getByEmail(String email) throws ServiceExcpetion {
        if (!(isNullOrEmpty(email))) {
            Person person = null;
                person = personDao.getByEmail(email);
            return person;
        }
        return null;
    }

    /*@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserDetails loadPersonByEmail(String email) throws ServiceExcpetion {
        if (!(isNullOrEmpty(email))) {
            // с помощью нашего сервиса UserService получаем User
            Person person = null;
            try {
                person = personDao.getByEmail(email);
            } catch (PersistException e) {
                logger.error(e);
                throw new ServiceExcpetion(e);
            }
            // указываем роли для этого пользователя
            Set<GrantedAuthority> roles = new HashSet();
            roles.add(new SimpleGrantedAuthority(RoleEnum.ROLE_USER.name()));

            // на основании полученныйх даных формируем объект UserDetails
            // который позволит проверить введеный пользователем логин и пароль
            // и уже потом аутентифицировать пользователя
            UserDetails userDetails =
                    new org.springframework.security.core.userdetails.User(person.getPersonDetail().getEmail(),
                            person.getPersonDetail().getPassword(),
                            roles);
            return userDetails;
        }
        return null;
    }*/

    @Override
    public Long registerPersonFirstStep(Person person) throws ServiceExcpetion {
        Long id = null;
        if (nonNull(person)) {
            try {
                if (isNull(person.getPersonId())) {
                    id = personDao.save(person);
                } else {
                    personDao.update(person);
                }
            } catch (PersistException e) {
                logger.error(e);
                throw new ServiceExcpetion(e);
            }
        }
        return id;
    }

    @Override
    public boolean registerPersonSecondStep(HttpSession httpSession, PersonDetail personDetail) throws ServiceExcpetion {
        try {
            Person person = personDao.get((Long) httpSession.getAttribute(P_ID));
            person.setStatus(PERSISTED);
            personDetail.setPerson(person);
            List<PersonDetail> personDetails = personDetailDao.getAll();
            for (PersonDetail element : personDetails) {
                if (element.getEmail().equals(personDetail.getEmail())) {
                    System.out.println('\n' + '\n' + element.getEmail() + "-" + personDetail.getEmail() + '\n' + '\n');
                    return false;
                }
            }
            personDetailDao.save(personDetail);
        } catch (PersistException e) {
            logger.error(e);
            throw new ServiceExcpetion(e);
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
    public Person update(Person person) throws ServiceExcpetion {
        if (nonNull(person.getPersonId())) {
            Long updatedPersonId = person.getPersonId();
            try {
                personDao.update(person);
                person = (Person) personDao.get(updatedPersonId);
            } catch (PersistException e) {
                logger.error(e);
                throw new ServiceExcpetion(e);
            }
        }
        return person;
    }

    @Override
    public void logOutPerson() throws ServiceExcpetion {
    }

    @Override
    public Long save(Person person) throws ServiceExcpetion {
        return null;
    }

    @Override
    public Person delete(Person person) throws ServiceExcpetion {
        return null;
    }

    @Override
    public void remove(Person person) throws ServiceExcpetion {

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person get(Long pK) throws ServiceExcpetion {
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
    public Person load(Long pK) throws ServiceExcpetion {
        if (nonNull(pK)) {
            try {
                return personDao.load(pK);
            } catch (PersistException e) {
                logger.error(e);
            }
        }
        return null;
    }

}
