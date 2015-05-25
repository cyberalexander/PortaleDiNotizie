package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.util.DaoConstants;
import by.leonovich.notizieportale.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by alexanderleonovich on 11.04.15.
 * An abstract class provides a base implementation CRUD operations using JDBC.
 * @param <T>  type of object persistence
 */
public abstract class AbstractDao<T> implements IGenericDao<T> {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);

    private SessionFactory sessionFactory;
    private final ThreadLocal sessions = new ThreadLocal();
    protected Session session;
    private HibernateUtil util;

    public AbstractDao() {
        util = HibernateUtil.getHibernateUtil();
        sessionFactory = util.getSessionFactory();
    }


    public Session getSession() {
       Session session = (Session) sessions.get();
        if (session == null || !(session.isOpen())) {
            session = sessionFactory.openSession();
            sessions.set(session);
        }
        return session;
    }

    public void clearSession(ThreadLocal sessionStatus) {
        boolean cleaner = (boolean) sessionStatus.get();
        if (true == cleaner) {
            if ((session != null) && (session.isOpen())) {
                session.clear();
            }
            sessionStatus.set(false);
        }
    }

    /**
     * Parses ResultSet and returns a list of relevant content ResultSet.
     */
    protected abstract List<T> parseResultSet(Session session) throws PersistException;

    /** It creates a new entry, the corresponding object object */
    @Override
    public Long save(T object)  throws PersistException {
        Long generatedId;
        try {
            session = getSession();
            generatedId = (Long) session.save(object);
        } catch (HibernateException e) {
            logger.error("Error save ENTITY in Database" + e);
            throw new PersistException(e);
        }
        return generatedId;
    }

    /** It creates a new entry, the corresponding object object */
    @Override
    public void saveOrUpdate(T object) throws PersistException {
        try {
            session = getSession();
            session.saveOrUpdate(object);
        } catch (HibernateException e) {
            logger.error("Error save or update ENTITY in Dao" + e);
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
    public T getByPK(Long pK) throws PersistException {
        T object;
        try {
            session = getSession();
            object = (T) session.get(getPersistentClass(), pK);
        } catch (HibernateException e) {
            logger.error("Error get " + getPersistentClass() + " in Dao " + e);
            throw new PersistException(e);
        }
        return object;
    }

    /**
     * load the appropriate record with a primary key
     * @param pK id of object, what we get from database
     * @return object from database or objectnotfoundexception if object not consist in database
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public T loadByPK(Long pK) throws PersistException {
        T object;
        try {
            session = getSession();
            object = (T) session.load(getPersistentClass(), pK);
            session.evict(object);
        } catch (HibernateException e) {
            logger.error("Error load() " + getPersistentClass() + " in Dao " + e);
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
            session = getSession();
            session.update(object);
        } catch (HibernateException e) {
            logger.error(DaoConstants.Const.ERROR_UPDATE_ENTITY, e);
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
            session = getSession();
            session.update(object);
        } catch (HibernateException e) {
            logger.error("Error delete object from Database: " + e);
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
            session = getSession();
            session.delete(object);
        } catch (HibernateException e) {
            logger.error("Error REMOVE object from Database: " + e);
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
            session = getSession();
            list = parseResultSet(session);
        } catch (HibernateException e) {
            logger.error("Error get list of " + getPersistentClass() + " in Dao " + e);
            throw new PersistException(e);
        }
        return list;
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
