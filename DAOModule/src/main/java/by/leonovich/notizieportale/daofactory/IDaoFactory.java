package by.leonovich.notizieportale.daofactory;


import by.leonovich.notizieportale.dao.IGenericDao;
import by.leonovich.notizieportale.exception.PersistException;

/**
 * Created by alexanderleonovich on 11.04.15.
 *
 * Factory objects to work with the database
 * Methods to connect to and work with databases. Implementing class MYSQLDAOFACTORY.
 */
public interface IDaoFactory {

    /**
     * -= INNER INTARFACE =-
     * Created with a unique method create (Connection connection), returns an object interface IGenericDao
     * IGenericDao-object - an object containing a set of operations on the database on behalf of one of the entities,
     * IGenericDao which implements methods in the code
     */
    interface IDaoCreator {
        IGenericDao create();
    }

    /** Returns object to control a persistent state of the object without created connection
     * Connection created in each method */
    IGenericDao getDao(Class dtoClass) throws PersistException;

}
