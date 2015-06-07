package by.leonovich.notizieportale.dao;


import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.exception.PersistException;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Unified Management Interface persistent state of objects
 * @param <T> type of object persistence
 * Persistence object - a storage facility in a constant state persistence mechanism such as a database.
 */
public interface IGenericDao<T> {

    Session getSession();

    /** It creates a new entry, the corresponding object object */
     Long save(T object, Session session)  throws PersistException;

    /** It creates a new entry, the corresponding object object */
    void saveOrUpdate(T object, Session session) throws PersistException;

    /** Gets the appropriate record with a primary key or a null key */
    T get(Long pK, Session session) throws PersistException;

    T load(Long pK, Session session) throws PersistException;

    /** It saves the state of the object group in the database */
    void update(T object, Session session) throws PersistException;

    /** Removes the entry of the object from the database */
    void delete(T object, Session session) throws PersistException;

    /** Removes the entry of the object from the database */
    void remove(T object, Session session) throws PersistException;

    /** Returns a list of all the relevant records in the database */
    List<T> getAll(Session session) throws PersistException;
}
