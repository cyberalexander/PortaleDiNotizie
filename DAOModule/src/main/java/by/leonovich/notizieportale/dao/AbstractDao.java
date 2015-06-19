package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.util.exception.PersistException;
import by.leonovich.notizieportale.util.DaoConstants;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static by.leonovich.notizieportale.util.DaoConstants.Const.*;


/**
 * Created by alexanderleonovich on 11.04.15.
 * An abstract class provides a base implementation CRUD operations using JDBC.
 * @param <T>  type of object persistence
 */
@Repository
public abstract class AbstractDao<T> implements IGenericDao<T> {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);
    private SessionFactory sessionFactory;

    @Autowired
    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /** It creates a new entry, the corresponding object object */
    @Override
    public Long save(T object)  throws PersistException {
        Long generatedId;
        try {
            generatedId = (Long) getCurrentSession().save(object);
        } catch (HibernateException e) {
            logger.error(ERROR_SAVE_ENTITY + e);
            throw new PersistException(e);
        }
        return generatedId;
    }

    /** It creates a new entry, the corresponding object object */
    @Override
    public void saveOrUpdate(T object) throws PersistException {
        try {
            getCurrentSession().saveOrUpdate(object);
        } catch (HibernateException e) {
            logger.error(DaoConstants.Const.ERROR_SAVE_OR_UPDATE + e);
            throw new PersistException(e);
        }
    }

    /**
     * Gets the appropriate record with a primary key or a null key
     * @param pK id of object, what we get from database
     * @return object from database
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public T get(Long pK) throws PersistException {
        return (T) getCurrentSession().get(getPersistentClass(), pK);
    }

    /**
     * load the appropriate record with a primary key
     * @param pK id of object, what we get from database
     * @return object from database or objectnotfoundexception if object not consist in database
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public T load(Long pK) throws PersistException {
        T object;
        try {
            object = (T) getCurrentSession().load(getPersistentClass(), pK);
        } catch (HibernateException e) {
            logger.error(ERROR_LOAD_OBJECT + e);
            throw new PersistException(e);
        }
        return object;
    }

    /**
     * Method for update object persistence in database
     * @param object - object entity for update persistence in database
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public void update(T object) throws PersistException {
        try {
            getCurrentSession().update(object);
        } catch (HibernateException e) {
            logger.error(DaoConstants.Const.ERROR_UPDATE_ENTITY + e);
            throw new PersistException(e);
        }
    }

    /**
     * Method change status of object to DELETED
     * @param object - object of entity for delete
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public void delete(T object) throws PersistException {
        try {
            getCurrentSession().update(object);
        } catch (HibernateException e) {
            logger.error(ERROR_DELETE + e);
            throw new PersistException(e);
        }
    }

    /**
     * Method for remove object from database
     * @param object - object of entity for delete
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public void remove(T object) throws PersistException{
        try {
            getCurrentSession().delete(object);
        } catch (HibernateException e) {
            logger.error(ERROR_REMOVE + e);
            throw new PersistException(e);
        }
    }

    /**
     * Get All objects from database
     * @return list<T> objects
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        try {
            list = parseResultSet(getCurrentSession());
        } catch (HibernateException e) {
            logger.error(ERROR_GET_LIST + e);
            throw new PersistException(e);
        }
        return list;
    }

    /**
     * Parses ResultSet and returns a list of relevant content ResultSet.
     */
    protected abstract List<T> parseResultSet(Session session) throws PersistException;

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
