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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by alexanderleonovich on 11.04.15.
 * <p>
 * An abstract class provides a base implementation CRUD operations using JDBC.
 *
 * @param <T>  type of object persistence
 */
public abstract class AbstractDao<T> implements IGenericDao<T> {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);

    private SessionFactory sessionFactory;
    private final ThreadLocal sessions = new ThreadLocal();
    private Session session;
    private Transaction transaction;
    private HibernateUtil util;

    public AbstractDao() {
        util = HibernateUtil.getHibernateUtil();
        sessionFactory = util.getSessionFactory();
    }

    public Session getSession() {
        Session session = (Session) sessions.get();
        if (session == null) {
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

    /**
     * Case Sets insert query to the value fields of the object object.
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    /**
     * Sets arguments update request in accordance with the value of the object field object.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;


    /** It creates a new entry, the corresponding object object */
    public Long save(T object)  throws PersistException {
        Long generatedId;
        try {
            session = getSession();
            generatedId = (Long) session.save(object);
            logger.info("SAVE(t):" + object);
            logger.info("Save (commit):" + object);
        } catch (HibernateException e) {
            logger.error("Error save ENTITY in Database" + e);
            throw new PersistException(e);
        }
        return generatedId;
    }

    /** It creates a new entry, the corresponding object object */
    public void saveOrUpdate(T object) throws PersistException {
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            logger.info("saveOrUpdate(t):" + object);
            transaction.commit();
            logger.info("Save or update (commit):" + object);
        } catch (HibernateException e) {
            logger.error("Error save or update ENTITY in Dao" + e);
            transaction.rollback();
            throw new PersistException(e);
        }
    }

    /**
     * Gets the appropriate record with a primary key or a null key
     *
     * @param PK id of object, what we get from database
     * @return
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public T getByPK(Long PK) throws PersistException {
        logger.info("Get Object by PK: " + PK);
        T object = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            object = (T) session.get(getPersistentClass(), PK);
            transaction.commit();
            logger.info("get clazz:" + object);
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("Error get " + getPersistentClass() + " in Dao " + e);
            throw new PersistException(e);
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.clear();
            }
        }
        return object;
    }

    public T loadByPK(Long PK) throws PersistException {
        logger.info("Load Object by id: " + PK);
        T object;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            object = (T) session.load(getPersistentClass(), PK);
            logger.info("load() clazz:" + object);
            session.isDirty();
            logger.info("SESSION IS DIRTY: " + session.isDirty());
            transaction.commit();
            session.evict(object);
        } catch (HibernateException e) {
            logger.error("Error load() " + getPersistentClass() + " in Dao " + e);
            transaction.rollback();
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
            Session session = getSession();
            transaction = session.beginTransaction();
            session.update(object);
            logger.info("UPDATE(t):" + object);
            transaction.commit();
            logger.info("UPDATE (commit):" + object);
        } catch (HibernateException e) {
            logger.error(DaoConstants.Const.ERROR_UPDATE_ENTITY, e);
            transaction.rollback();
            throw new PersistException(e);
        }
    }

    /**
     * Method for delete object from database
     * @param object - object of entity for delete
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public void delete(T object) throws PersistException {
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
            logger.info("Delete: " + object);
            session.clear();
        } catch (HibernateException e) {
            logger.error("Error delete object from Database: " + e);
            transaction.rollback();
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
        List<T> list = null;
        logger.info("Get list of objects");
        try {
            session = getSession();
            list = parseResultSet(session);
            logger.info("Get list size: " + list.size());
        } catch (HibernateException e) {
            logger.error("Error get list of " + getPersistentClass() + " in Dao " + e);
            throw new PersistException(e);
        }
        return list;
    }


    /**
     * Getting elements of persistence from JDBC. Element in table must be String type int java-code
     * @param parameter name of column from table of JDBC
     * @param value     of column in table for searching and getting results
     * @return list with persistence-elements
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public List<T> getListByStringCretery(String parameter, String value) throws PersistException {
        List<T> list = new ArrayList<>();
        return list;
    }

    /**
     * Get Object by string cretery
     *
     * @param parameter name of column in database
     * @param value     value of column in database
     * @return object
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public T getByStringCretery(String parameter, String value) throws PersistException {
        T t = null;
        return t;
    }

    @Override
    public List<T> getListByIntegerId(String nameOfColumn, Integer id) throws PersistException {
        List<T> list = new ArrayList<>();
        return list;
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Method for convert Date-object
     *
     * @param date date object for convert
     * @return java.sql.Date object
     */
    protected java.sql.Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }
}
