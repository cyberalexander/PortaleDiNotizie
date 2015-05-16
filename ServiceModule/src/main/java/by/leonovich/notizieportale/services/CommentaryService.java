package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.dao.IGenericDao;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.exception.PersistException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by alexanderleonovich on 03.05.15.
 * Service layer for domain entity Commentary
 */
public class CommentaryService implements ICommentaryService {

    private static CommentaryService commentServiceInst;
    private IGenericDao commentaryDao;
    private static final Logger logger = Logger.getLogger(Commentary.class);


    /**
     * -=SINGLETON=-
     * Method for creating fabric
     * 1. First, you must create an instance factory or get it, and then through it to create Dao objects to the entity
     * over which you plan to perform CRUD operations.
     *
     * @return instance of CommentaryService
     */
    public static synchronized CommentaryService getInstance() {
        if (commentServiceInst == null) {
            commentServiceInst = new CommentaryService();
        }
        return commentServiceInst;
    }

    private CommentaryService() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            commentaryDao = factory.getDao(Commentary.class);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Commentary> getCommentsByNewsIdorAuthorId(String nameOfColumn, Integer id) {
        if ((nameOfColumn != null) && !(nameOfColumn.isEmpty() && (id != null))) {
            try {
                return commentaryDao.getListByIntegerId(nameOfColumn, id);
            } catch (PersistException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void saveComment(Commentary comment) {
        if (comment != null) {
            try {
                commentaryDao.persist(comment);
            } catch (PersistException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Commentary getCommentById(int comment_id) {
        if (comment_id != 0 && comment_id > 0) {
            try {
                return (Commentary) commentaryDao.getByPK(comment_id);
            } catch (PersistException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void updateCommentary(Commentary comment) {
        if (comment != null) {
            try {
                commentaryDao.update(comment);
            } catch (PersistException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void deleteCommentary(Commentary commentary) {
        try {
            commentaryDao.delete(commentary);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

}
