package by.leonovich.notizieportale;

import by.leonovich.notizieportale.dao.IGenericDao;
import by.leonovich.notizieportale.domain.Identified;
import by.leonovich.notizieportale.exception.PersistException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alexanderleonovich on 04.05.15.
 */
@Ignore
@RunWith(Parameterized.class)
public abstract class GenericDaoTest {

    /**
     * Class of testing dao - object (News or User or Commentary)
     */
    protected Class daoClass;

    /**
     * Domain object, what not have in database
     */
    protected Identified notPersistedDto;

    /**
     * copy of testing dao-object
     *
     * @return
     */
    public abstract IGenericDao dao();


    public GenericDaoTest(Class clazz, Identified<Integer> notPersistedDto) {
        this.daoClass = clazz;
        this.notPersistedDto = notPersistedDto;
    }


    /**
     * Create method test
     *
     * @throws PersistException - my custom class of exception
     */
    @Test
    public void testCreate() throws PersistException {
        Identified dto = dao().create();

        Assert.assertNotNull("Hello ", dto);
        System.out.println("dto from testCreate: " + dto);
        Assert.assertNotNull(dto.getId());
        System.out.println("dto.getId() from testCreate: " + dto.getId());
    }

    /**
     * Persist method test
     * @throws PersistException - my custom class of exception
     */
    @Test
    public void testPersist() throws PersistException {
        Assert.assertNull("Id before persist is not null.", notPersistedDto.getId());
        System.out.println("Id before persist is not null." + notPersistedDto.getId());

        notPersistedDto = dao().persist(notPersistedDto);
        System.out.println("notPersistedDto after dao().persist(notPersistedDto)" + notPersistedDto);

        Assert.assertNotNull("After persist id is null. ", notPersistedDto.getId());
        System.out.println("After persist id is null. " + notPersistedDto.getId());
    }

    /**
     * GetByPK method test
     * @throws PersistException - my custom class of exception
     */
    @Test
    public void testGetByPK() throws PersistException {
        Serializable id = dao().create().getId();
        System.out.println("Serializable id = dao().create().getId();  " + id);

        Identified dto = dao().getByPK(id);
        System.out.println("Identified dto = dao().getByPK(id); " + dto);

        Assert.assertNotNull(dto);
    }

    /**
     * Delete method test
     * @throws PersistException - my custom class of exception
     */
    @Test
    public void testDelete() throws PersistException {
        Identified dto = dao().create();
        System.out.println("Identified dto = dao().create(); " + dto);
        Assert.assertNotNull(dto);

        List list = dao().getAll();
        Assert.assertNotNull(list);

        int oldSize = list.size();
        System.out.println("int oldSize = list.size(); " + oldSize);
        Assert.assertTrue(oldSize > 0);

        dao().delete(dto);

        list = dao().getAll();
        Assert.assertNotNull(list);

        int newSize = list.size();
        System.out.println("int newSize = list.size(); " + newSize);
        Assert.assertEquals(1, oldSize - newSize);
    }

    /**
     * GetAll method test
     * @throws PersistException - my custom class of exception
     */
    @Test
    public void testGetAll() throws PersistException {
        List list = dao().getAll();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Ignore
    @Test
    public void testGetByStringCretery() throws PersistException {
        Identified dto = dao().getByStringCretery("comment", "ololol");
        System.out.println("---------------------------------- " + dto);
        Assert.assertNotNull(dto);
    }




}