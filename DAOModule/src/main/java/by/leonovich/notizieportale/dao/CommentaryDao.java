package by.leonovich.notizieportale.dao;

import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.exception.PersistException;
import org.hibernate.Session;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by alexanderleonovich on 27.04.15.
 * Class for working with persistence entity of COMMENTARY
 */
public class CommentaryDao extends AbstractDao<Commentary> {

    /**
     * Constructor of MySqlCommentaryDao.class
     */
    public CommentaryDao() {
        super();
    }

    @Override
    protected List<Commentary> parseResultSet(Session session) throws PersistException {
        List<Commentary> list = session.createSQLQuery("SELECT * FROM T_COMMENTARY").addEntity(Commentary.class).list();
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Commentary object) throws PersistException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Commentary object) throws PersistException {

    }

}
