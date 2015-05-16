package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.exception.PersistException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Class for working with persistence entity of NEWS
 */
public class NewsDao extends AbstractJDBCDao<News, Integer> {

    /**
     * Constructor of MySqlNewsDao.class
     */
    public NewsDao(Connection myConnection) {
        super(myConnection);
    }

    /**
     * Constructor of MySqlNewsDao.class
     */
    public NewsDao() {
        super();
    }

    /** !!!!!!!!!!   INNER CLASS !!!!!!!!!! */
    /**
     * established in order to gain access to the protected field essence of this object by providing an internal
     * class, that inherits the essence of a protected method to set the protected nature of the field
     */
    private class PersistNews extends News {
        @Override
        public void setId(int id) {
            super.setId(id);
        }
    }


    @Override
    public String getSelectQuery() {
        return "SELECT F_ID, F_PAGE_ID, F_PARENT_ID, F_TITLE, F_MENU_TITLE, F_USER_ID, F_DATE, F_ANNOTATION, F_CONTENT FROM mysitetonight.T_NEWS";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO mysitetonight.T_NEWS (F_PAGE_ID, F_PARENT_ID, F_TITLE, F_MENU_TITLE, F_USER_ID, F_DATE, F_ANNOTATION, F_CONTENT) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE mysitetonight.T_NEWS SET F_PAGE_ID=?, F_PARENT_ID=?, F_TITLE=?, F_MENU_TITLE=?, F_USER_ID=?, F_DATE=?, F_ANNOTATION=?, F_CONTENT=? WHERE F_ID=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM mysitetonight.T_NEWS WHERE F_ID=?;";
    }

    @Override
    public News create() throws PersistException {
        News news = new News();
        return persist(news);
    }

    @Override
    protected List<News> parseResultSet(ResultSet resultSet) throws PersistException {
        LinkedList<News> resultOfParse = new LinkedList<News>();
        try {
            while (resultSet.next()) {
                PersistNews news = new PersistNews(); // точка подмены реализации News
                news.setId(resultSet.getInt("F_ID"));
                news.setPage_id(resultSet.getString("F_PAGE_ID"));
                news.setParent_id(resultSet.getString("F_PARENT_ID"));
                news.setTitle(resultSet.getString("F_TITLE"));
                news.setMenu_title(resultSet.getString("F_MENU_TITLE"));
                news.setUser_id(resultSet.getInt("F_USER_ID"));
                news.setDate(resultSet.getDate("F_DATE"));
                news.setAnnotation(resultSet.getString("F_ANNOTATION"));
                news.setContent(resultSet.getString("F_CONTENT"));
                resultOfParse.add(news);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return resultOfParse;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, News object) throws PersistException {
        try {
            Date sqlDate = convert(object.getDate());
            statement.setString(1, object.getPage_id());
            statement.setString(2, object.getParent_id());
            statement.setString(3, object.getTitle());
            statement.setString(4, object.getMenu_title());
            statement.setInt(5, object.getUser_id());
            statement.setDate(6, sqlDate);
            statement.setString(7, object.getAnnotation());
            statement.setString(8, object.getContent());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, News object) throws PersistException {
        try {
            Date sqlDate = convert(object.getDate());
            statement.setString(1, object.getPage_id());
            statement.setString(2, object.getParent_id());
            statement.setString(3, object.getTitle());
            statement.setString(4, object.getMenu_title());
            statement.setInt(5, object.getUser_id());
            statement.setDate(6, sqlDate);
            statement.setString(7, object.getAnnotation());
            statement.setString(8, object.getContent());
            statement.setInt(9, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }


}
