package by.leonovich.notizieportale.daofactory;

import by.leonovich.notizieportale.dao.IGenericDao;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.dao.CommentaryDao;
import by.leonovich.notizieportale.dao.NewsDao;
import by.leonovich.notizieportale.dao.PersonDao;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexanderleonovich on 11.04.15.
 * It implements the interface methods to connect to the database for domain entity and building factory
 */
public class DaoFactoryImpl implements IDaoFactory {
    
    private static DaoFactoryImpl instance;
    private Map<Class, IDaoCreator> creators;
    private static final Logger logger = Logger.getLogger(DaoFactoryImpl.class);

    private DaoFactoryImpl() {
        creators = new HashMap<Class, IDaoCreator>();
        //Inherited from IDaoFactory to build a factory to work with a specific domain entity
        creators.put(Person.class, new IDaoCreator() {
            @Override
            public IGenericDao create() {
                return new PersonDao();
            }
        });
        creators.put(News.class, new IDaoCreator() {
            @Override
            public IGenericDao create() {
                return new NewsDao();
            }
        });
        creators.put(Commentary.class, new IDaoCreator() {
            @Override
            public IGenericDao create() {
                return new CommentaryDao();
            }
        });
    }

    public static synchronized DaoFactoryImpl getInstance(){
        if (instance == null){
            instance = new DaoFactoryImpl();
        }
        return instance;
    }

    /**
     * Method for creating DAO objects (в моем случае либо Expense либо Receiver)
     * 2. First you need to create a factory, and then through it to create objects for the Dao of the essence,
      * Over which you plan to perform CRUD operations.
     * @param dtoClass class of domain entity for getting dao
     * @return dao for domain entity
     * @throws PersistException
     */
    @Override
    public IGenericDao getDao(Class dtoClass) throws PersistException {
        IDaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create();
    }

}
