package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.connectpool.ConnectionPool;

import static by.leonovich.notizieportale.connectpool.ConnectionPool.releaseConnection;

import by.leonovich.notizieportale.domain.Identified;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.util.DaoConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


/**
 * Created by alexanderleonovich on 11.04.15.
 * <p>
 * An abstract class provides a base implementation CRUD operations using JDBC.
 *
 * @param <T>  type of object persistence
 * @param <PK> the type of the primary key
 */
public abstract class AbstractJDBCDao<T extends Identified<PK>, PK extends Integer> implements IGenericDao<T, PK> {

    private Connection myConnection;
    private ResultSet resultSet;
    ConnectionPool conPool;

    public AbstractJDBCDao(Connection myConnection) {
        this.myConnection = myConnection;
    }

    public AbstractJDBCDao() {
        conPool = new ConnectionPool();
    }

    /**
     * Returns sql query to retrieve all records.
     * <p>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();

    /**
     * Returns sql query to insert new records in the database.
     * <p>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * Returns sql query to update records.
     * <p>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Returns sql query to delete records from the database.
     * <p>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();


    /**
     * Parses ResultSet and returns a list of relevant content ResultSet.
     */
    protected abstract List<T> parseResultSet(ResultSet resultSet) throws PersistException;

    /**
     * Case Sets insert query to the value fields of the object object.
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    /**
     * Sets arguments update request in accordance with the value of the object field object.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

    /**
     * Gets the appropriate record with a primary key or a null key
     *
     * @param key id of object, what we get from database
     * @return
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public T getByPK(Integer key) throws PersistException {
        // get Connection from Pool
        myConnection = conPool.getConnection();
        List<T> list;
        String sqlQuery = getSelectQuery();
        sqlQuery += " WHERE F_ID=?";
        try (PreparedStatement preStatement = myConnection.prepareStatement(sqlQuery)) {
            preStatement.setInt(1, key);
            resultSet = preStatement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            releaseConnection(myConnection, resultSet);
        }
        if (list == null || list.size() == 0) {
            return null;
        }

        if (list.size() > 1) {
            throw new PersistException("Received more than one record.");
        }
        return list.iterator().next();
    }

    /**
     * Get All objects from database
     * @return list<T> objects
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public List<T> getAll() throws PersistException {
        // get Connection from Pool
        myConnection = conPool.getConnection();
        List<T> list;
        String sqlQuery = getSelectQuery();
        try (PreparedStatement preStatement = myConnection.prepareStatement(sqlQuery)) {
            resultSet = preStatement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            releaseConnection(myConnection, resultSet);
        }
        return list;
    }

    /**
     * The abstract of the method Persist (total for both entities, in my case, for Expense and Receiver)
     * Specialized for each entity of the methods are the same methods that are inherited from the
     * AbstractJDBCDao classes. This method creates a new record, according to the subject object.
     * @param object Generic-object (In this project it`s Expense.class or Receiver.class)
     * @return
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public T persist(T object) throws PersistException {
        // get Connection from Pool
        myConnection = conPool.getConnection();
        if (object.getId() != null) {
            throw new PersistException(DaoConstants.Const.OBJECT_IS_ALREADY_PERSIST);
        }
        T persistInstance;
        // Добавляем запись
        String sql = getCreateQuery();
        try (PreparedStatement statement = myConnection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException(DaoConstants.Const.PERSIST_EXCEPTION + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        // We get the newly inserted record
        sql = getSelectQuery() + " WHERE F_ID = last_insert_id();";
        try (PreparedStatement statement = myConnection.prepareStatement(sql)) {
            resultSet = statement.executeQuery();
            List<T> list = parseResultSet(resultSet);
            if ((list == null) || (list.size() != 1)) {
                throw new PersistException(DaoConstants.Const.CANT_FIND_BY_PK);
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            // put Connection to Pool
            releaseConnection(myConnection, resultSet);
        }
        return persistInstance;
    }

    /**
     * Method for update object persistence in database
     * @param object - object entity for update persistence in database
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public void update(T object) throws PersistException {
        myConnection = conPool.getConnection();
        String sql = getUpdateQuery();
        try (PreparedStatement statement = myConnection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException(DaoConstants.Const.UPDATE_MESSAGE + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            releaseConnection(myConnection);
        }
    }

    /**
     * Method for delete object from database
     * @param object - object of entity for delete
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public void delete(T object) throws PersistException {
        myConnection = conPool.getConnection();
        String sql = getDeleteQuery();
        try (PreparedStatement statement = myConnection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException(DaoConstants.Const.DELETE_MESSAGE + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            releaseConnection(myConnection);
        }
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
        myConnection = conPool.getConnection();
        List<T> ListbyCreteryString;
        String sqlQuery = getSelectQuery();
        sqlQuery += " WHERE " + parameter + "='" + value + "';";
        try (PreparedStatement preStatement = myConnection.prepareStatement(sqlQuery)) {
            resultSet = preStatement.executeQuery();
            ListbyCreteryString = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            releaseConnection(myConnection, resultSet);
        }
        return ListbyCreteryString;
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
        myConnection = conPool.getConnection();
        List<T> list;
        String sqlQuery = getSelectQuery();
        sqlQuery += " WHERE " + parameter + "='" + value + "';";
        try (PreparedStatement preStatement = myConnection.prepareStatement(sqlQuery)) {
            resultSet = preStatement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            releaseConnection(myConnection, resultSet);
        }
        return list.iterator().next();
    }

    @Override
    public List<T> getListByIntegerId(String nameOfColumn, Integer id) throws PersistException {
        myConnection = conPool.getConnection();
        List<T> listByIntId;
        String sqlQuery = getSelectQuery();
        sqlQuery += " WHERE " + nameOfColumn + "=" + id + ";";
        try (PreparedStatement preStatement = myConnection.prepareStatement(sqlQuery)) {
            resultSet = preStatement.executeQuery();
            listByIntId = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            releaseConnection(myConnection, resultSet);
        }
        return listByIntId;
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
