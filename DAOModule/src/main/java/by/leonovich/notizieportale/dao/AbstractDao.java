package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.util.DaoConstants;
import by.leonovich.notizieportale.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.util.ArrayList;
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

    @Autowired
    protected static SessionFactory sessionFactory;

    private final ThreadLocal sessions = new ThreadLocal();

    public AbstractDao() {
        HibernateUtil util = HibernateUtil.getHibernateUtil();
        sessionFactory = util.getSessionFactory();
    }

    public Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    public Session getSession() {
       Session session = (Session) sessions.get();
        if (session == null || !(session.isOpen())) {
            session = sessionFactory.openSession();
            sessions.set(session);
        }
        return session;
    }

    public void clearSession() {
        Session session = getSession();
            if ((session != null) && (session.isOpen())) {
                session.clear();
            }
    }

    public void detachSession() {
        sessions.set(null);
    }

    /** It creates a new entry, the corresponding object object */
    @Override
    public Long save(T object, Session session)  throws PersistException {
        Long generatedId;
        try {
            generatedId = (Long) session.save(object);
        } catch (HibernateException e) {
            logger.error(ERROR_SAVE_ENTITY + e);
            throw new PersistException(e);
        }
        return generatedId;
    }

    /** It creates a new entry, the corresponding object object */
    @Override
    public void saveOrUpdate(T object, Session session) throws PersistException {
        try {
            session.saveOrUpdate(object);
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
        return (T) getSession().get(getPersistentClass(), pK);
    }

    /**
     * load the appropriate record with a primary key
     * @param pK id of object, what we get from database
     * @return object from database or objectnotfoundexception if object not consist in database
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public T load(Long pK, Session session) throws PersistException {
        T object;
        try {
            object = (T) session.load(getPersistentClass(), pK);
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
    public void update(T object, Session session) throws PersistException {
        try {
            session.update(object);
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
    public void delete(T object, Session session) throws PersistException {
        try {
            session.update(object);
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
    public void remove(T object, Session session) throws PersistException{
        try {
            session.delete(object);
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
    public List<T> getAll(Session session) throws PersistException {
        List<T> list;
        try {
            list = parseResultSet(session);
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
