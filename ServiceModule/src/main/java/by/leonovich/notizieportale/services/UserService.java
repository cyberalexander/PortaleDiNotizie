package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.dao.IGenericDao;
import by.leonovich.notizieportale.domain.Identified;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.services.util.ServiceConstants;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by alexanderleonovich on 29.04.15.
 * Service layer for domain entity User
 */
public class UserService implements IUserService {

    private static UserService userServiceInst;
    private IGenericDao userDao;
    private static final Logger logger = Logger.getLogger(UserService.class);


    private UserService() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            userDao = factory.getDao(Person.class);
        } catch (PersistException e) {
            logger.error(e);
        }
    }

    public static synchronized UserService getInstance(){
        if (userServiceInst == null){
            userServiceInst = new UserService();
        }
        return userServiceInst;
    }

    /**
     * Server validation of user
     *
     * @param email    - email-adress of user who want to autenitcate to site
     * @param password - password of user who want to autenitcate to site
     * @return true, if user in database (registered), or false, if user not registered
     */
    @Override
    public boolean checkUser(String email, String password) {
        if (email != null && password != null) {
            try {
                List<Person> personList = userDao.getAll();
                for (Person personElement : personList) {
                    if ((personElement.getEmail().equals(email)) && (personElement.getPassword().equals(password))) {
                        return true;
                    }
                }
            } catch (PersistException e) {
                logger.error(e);
            }
        }
        return false;
    }

    /**
     * Get registered user from database
     *
     * @param nameOfColum - name of colum for search parameter
     * @param cretery     - field in colum, identified userØ
     * @return User user
     */
    @Override
    public Person authenticationProcess(String nameOfColum, String cretery) {
        Person person = new Person();
        try {
            person = (Person) userDao.getByStringCretery(nameOfColum, cretery);
        } catch (PersistException e) {
            logger.error(e);
        }
        return person;
    }

    @Override
    public boolean registerNewUser(Person person) {
        if (person != null) {
            List<Person> personList = null;
            try {
                personList = userDao.getAll();
                for (Person element : personList) {
                    if (element.getEmail().equals(person.getEmail())) {
                        return false;
                    }
                }
            } catch (PersistException e) {
                e.printStackTrace();
            }
            try {
                userDao.persist(person);
                return true;
            } catch (PersistException e) {
                logger.error(e);
            }
        }
        return false;
    }

    @Override
    public Person getUserByEmail(String email) {
        Identified user = null;
        if (email != null) {
            try {
                user = userDao.getByStringCretery(ServiceConstants.Const.F_EMAIL, email);
            } catch (PersistException e) {
                logger.error(e);
            }
        }
        return (Person) user;
    }

    @Override
    public void updateUserInformation(Person person) {
        if (person.getId() != null &&
                person.getId() != ServiceConstants.Const.ZERO &&
                person.getId() > ServiceConstants.Const.ZERO) {
            try {
                userDao.update(person);
            } catch (PersistException e) {
                logger.error(e);
            }
        }
    }


}
