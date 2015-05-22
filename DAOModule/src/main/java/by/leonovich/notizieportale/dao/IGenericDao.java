package by.leonovich.notizieportale.dao;


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
     Long save(T object)  throws PersistException;

    /** It creates a new entry, the corresponding object object */
    void saveOrUpdate(T object) throws PersistException;

    /** Gets the appropriate record with a primary key or a null key */
     T getByPK(Long PK) throws PersistException;

    T loadByPK(Long PK) throws PersistException;

    /** It saves the state of the object group in the database */
     void update(T object) throws PersistException;

    /** Removes the entry of the object from the database */
     void delete(T object) throws PersistException;

    /** Returns a list of all the relevant records in the database */
     List<T> getAll() throws PersistException;

    /** Returns a list of the relevant entries in the database, where there is a field with a value opredelnie
     * String type! */
     List<T> getListByStringCretery(String parameter, String value) throws PersistException;

    /** Gets the corresponding record in the database where the field is present with a certain value */
     T getByStringCretery(String parameter, String value) throws PersistException;

    /** Returns a list of the relevant entries in the database, where there is a field with a value opredelnie
     * TYPE INT */
    List<T> getListByIntegerId(String nameOfColumn, Integer id) throws PersistException ;
}
