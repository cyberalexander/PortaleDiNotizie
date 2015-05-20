package by.leonovich.notizieportale;

import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.dao.IGenericDao;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.Identified;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.exception.PersistException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runners.Parameterized;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by alexanderleonovich on 04.05.15.
 */
@Ignore
public class MySqlDaoTest extends GenericDaoTest {

    private IGenericDao dao;

    private  IDaoFactory factory = DaoFactoryImpl.getInstance();

    @Parameterized.Parameters
    public static Collection getParameters() {
        return Arrays.asList(new Object[][]{
                {Person.class, new Person()},
                {News.class, new News()},
                {Commentary.class, new Commentary()}
        });
    }

    @Before
    public void setUp() throws SQLException, PersistException {
        dao = factory.getDao(daoClass);
        System.out.println(dao.getClass().getName());
    }


    @Override
    public IGenericDao dao() {
        return dao;
    }

    public MySqlDaoTest(Class clazz, Identified<Integer> notPersistedDto) {
        super(clazz, notPersistedDto);
    }



}