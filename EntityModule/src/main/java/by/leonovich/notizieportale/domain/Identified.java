package by.leonovich.notizieportale.domain;

import java.io.Serializable;

/**
 * Created by alexanderleonovich on 11.04.15.
 *
 * Interface identifiable objects. Method prepareStatement For Delete Class Abstract JDBCDao constantly be duplicated due
 * to the fact that we can not demand from our facilities in the class id AbstractJDBCDao. This is easily solved by using
 * a single interface Identified by: getId (). If all the bean-objects (Expense, Receiver) will implement it, then delete
 * the query arguments can be installed in the delete method of class AbstractJDBCDao.
 */
public interface Identified<PK extends Serializable> {

    /** Returns the ID of the object, that is, its id */
    PK getId();
}