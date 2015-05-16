package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.dao.AbstractJDBCDao;
import by.leonovich.notizieportale.domain.User;
import by.leonovich.notizieportale.exception.PersistException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Class for working with persistence entity of USER
 */
public class UserDao extends AbstractJDBCDao<User, Integer> {

    public UserDao() {
        super();
    }

    /**
     * !!!!!!!!!!   INNER CLASS !!!!!!!!!!
     */
    private class PersistUser extends User {
        @Override
        public void setId(int id) {
            super.setId(id);
        }
    }


    @Override
    public String getSelectQuery() {
        return "SELECT F_ID, F_NAME, F_LASTNAME, F_EMAIL, F_PASSWORD, F_BIRTHDAY, F_ROLE FROM mysitetonight.T_USER";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO mysitetonight.T_USER (F_NAME, F_LASTNAME, F_EMAIL, F_PASSWORD, F_BIRTHDAY, F_ROLE) VALUES (?, ?, ?, ?, ?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE mysitetonight.T_USER SET F_NAME=?, F_LASTNAME=?, F_EMAIL=?, F_PASSWORD=?, F_BIRTHDAY=?, F_ROLE=? WHERE F_ID=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM mysitetonight.T_USER WHERE F_ID=?;";
    }


    /**
     * Create a new object on the outside, I fill his field and transmit it to the DAO method to persist
     * Since I do not know in advance of his designer
     * @return persisted object of User
     * @throws PersistException
     */
    @Override
    public User create() throws PersistException {
        User user = new User();
        return persist(user);
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws PersistException {
        LinkedList<User> resultOfParse = new LinkedList<User>();
        try {
            while (resultSet.next()) {
                PersistUser persistUser = new PersistUser(); //точка подмены реализации User
                persistUser.setId(resultSet.getInt("F_ID"));
                persistUser.setName(resultSet.getString("F_NAME"));
                persistUser.setLastname(resultSet.getString("F_LASTNAME"));
                persistUser.setEmail(resultSet.getString("F_EMAIL"));
                persistUser.setPassword(resultSet.getString("F_PASSWORD"));
                persistUser.setBirthday(resultSet.getDate("F_BIRTHDAY"));
                persistUser.setRole(resultSet.getString("F_ROLE"));
                resultOfParse.add(persistUser);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return resultOfParse;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
        try {
            Date sqlDate = convert(object.getBirthday());
            statement.setString(1, object.getName());
            statement.setString(2, object.getLastname());
            statement.setString(3, object.getEmail());
            statement.setString(4, object.getPassword());
            statement.setDate(5, sqlDate);
            statement.setString(6, object.getRole());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
        try {
            Date sqlDate = convert(object.getBirthday());
            statement.setString(1, object.getName());
            statement.setString(2, object.getLastname());
            statement.setString(3, object.getEmail());
            statement.setString(4, object.getPassword());
            statement.setDate(5, sqlDate);
            statement.setString(6, object.getRole());
            statement.setInt(7, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
