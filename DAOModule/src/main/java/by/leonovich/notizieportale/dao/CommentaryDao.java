package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.dao.AbstractJDBCDao;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.exception.PersistException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexanderleonovich on 27.04.15.
 * Class for working with persistence entity of COMMENTARY
 */
public class CommentaryDao extends AbstractJDBCDao<Commentary, Integer> {

    /**
     * Constructor of MySqlCommentaryDao.class
     */
    public CommentaryDao(Connection myConnection) {
        super(myConnection);
    }

    /**
     * Constructor of MySqlCommentaryDao.class
     */
    public CommentaryDao() {
        super();
    }

    /** !!!!!!!!!!   INNER CLASS !!!!!!!!!! */
    /**
     * established in order to gain access to the protected field essence of this object by providing an internal
     * class, that inherits the essence of a protected method to set the protected nature of the field
     */
    private class PersistCommentary extends Commentary {
        @Override
        protected void setId(Integer id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT F_ID, F_USER_ID, F_NEWS_ID, F_COMMENT, F_DATE FROM mysitetonight.T_COMMENTARY";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO mysitetonight.T_COMMENTARY (F_USER_ID, F_NEWS_ID, F_COMMENT, F_DATE) VALUES(?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE mysitetonight.T_COMMENTARY SET F_USER_ID=?, F_NEWS_ID=?, F_COMMENT=?, F_DATE=?  WHERE F_ID=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM mysitetonight.T_COMMENTARY WHERE F_ID=?;";
    }

    @Override
    protected List<Commentary> parseResultSet(ResultSet resultSet) throws PersistException {
        LinkedList<Commentary> resultOfParse = new LinkedList<>();
        try {
            while (resultSet.next()) {
                PersistCommentary persistCommentary = new PersistCommentary(); // точка подмены реализации News
                persistCommentary.setId(resultSet.getInt("F_ID"));
                persistCommentary.setUser_id(resultSet.getInt("F_USER_ID"));
                persistCommentary.setNews_id(resultSet.getInt("F_NEWS_ID"));
                persistCommentary.setComment(resultSet.getString("F_COMMENT"));
                persistCommentary.setDate(resultSet.getString("F_DATE"));
                resultOfParse.add(persistCommentary);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return resultOfParse;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Commentary object) throws PersistException {
        try {
            //Date sqlDate = convert(object.getDate());
            statement.setInt(1, object.getUser_id());
            statement.setInt(2, object.getNews_id());
            statement.setString(3, object.getComment());
            statement.setString(4, object.getDate());

        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Commentary object) throws PersistException {
        try {
            //Date sqlDate = convert(object.getDate());
            statement.setInt(1, object.getUser_id());
            statement.setInt(2, object.getNews_id());
            statement.setString(3, object.getComment());
            statement.setString(4, object.getDate());
            statement.setInt(5, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public Commentary create() throws PersistException {
        Commentary commentary = new Commentary();
        return persist(commentary);
    }
}
