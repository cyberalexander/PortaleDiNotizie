package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.CommentaryDao;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.util.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by alexanderleonovich on 03.05.15.
 * Service layer for domain entity Commentary
 */
public class CommentaryService implements ICommentaryService {
    private static final Logger logger = Logger.getLogger(Commentary.class);

    private static CommentaryService commentServiceInst;
    private final ThreadLocal sessionStatus = new ThreadLocal();
    private CommentaryDao commentaryDao;

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
            commentaryDao = (CommentaryDao) factory.getDao(Commentary.class);
        } catch (PersistException e) {
            logger.error(e);
        }
    }

    @Override
    public Commentary getCommentaryByPK(Long PK) {
        Commentary commentary = null;
        Transaction transaction = null;
        try {
            Session session = commentaryDao.getSession();
            transaction = session.beginTransaction();
            commentary = commentaryDao.getByPK(PK);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            sessionStatus.set(true);
            commentaryDao.clearSession(sessionStatus);
        }
        return commentary;
    }

    @Override
    public List<Commentary> getCommentaries() {
        List<Commentary> commentaries = null;
        Transaction transaction = null;
        try {
            Session session = commentaryDao.getSession();
            transaction = session.beginTransaction();
            commentaries = commentaryDao.getAll();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        } finally {
            sessionStatus.set(true);
            commentaryDao.clearSession(sessionStatus);
        }
        return commentaries;
    }

    @Override
    public List<Commentary> getCommentariesByAuthorId(Long PK) {
        List<Commentary> commentaries = null;
        Transaction transaction = null;
        try {
            Session session = commentaryDao.getSession();
            transaction = session.beginTransaction();
            commentaries = commentaryDao.getByPersonPK(PK);
            logger.info("Category-list size: " + commentaries.size());
            transaction.commit();
            logger.info("successful get list!");
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            sessionStatus.set(true);
            commentaryDao.clearSession(sessionStatus);
        }
        return commentaries;
    }
    //

    @Override
    public List<Commentary> getCommentariesByNewsId(Long PK) {
        List<Commentary> commentaries = null;
        Transaction transaction = null;
        if (PK != null) {
            try {
                Session session = commentaryDao.getSession();
                transaction = session.beginTransaction();
                commentaries = commentaryDao.getByNewsPK(PK);
                transaction.commit();
                logger.info("successful get list!");
            } catch (HibernateException e) {
                logger.error("Error get list of commentaries from database" + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                commentaryDao.clearSession(sessionStatus);
            }
            return commentaries;
        }else {
            return null;
        }
    }

    @Override
    public Commentary saveCommentary(Commentary commentary) {
        Long savedCommentaryId;
        Transaction transaction = null;
        if (commentary != null) {
            try {
                Session session = commentaryDao.getSession();
                transaction = session.beginTransaction();
                commentary.setStatus(StatusEnum.SAVED);
                savedCommentaryId = commentaryDao.save(commentary);
                if (savedCommentaryId != null) {
                    commentary = commentaryDao.getByPK(savedCommentaryId);
                }
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error get list of Categories from database" + e);
                transaction.rollback();
            } catch (PersistException e) {
                transaction.rollback();
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                commentaryDao.clearSession(sessionStatus);
            }
            return commentary;
        }else {
            return null;
        }
    }


    @Override
    public Commentary updateCommentary(Commentary commentary) {
        if (null != commentary.getCommentaryId()) {
        Long updatedCommentaryId = commentary.getCommentaryId();
            Transaction transaction = null;
            try {
                Session session = commentaryDao.getSession();
                transaction = session.beginTransaction();
                commentaryDao.update(commentary);
                commentary = commentaryDao.getByPK(updatedCommentaryId);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error get list of Categories from database" + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                commentaryDao.clearSession(sessionStatus);
            }
            return commentary;
        }else {
            return null;
        }
    }

    @Override
    public Commentary deleteCommentary(Commentary commentary) {
        if (null != commentary.getCommentaryId()) {
            Long deletedCommentaryId = commentary.getCommentaryId();
            Transaction transaction = null;
            try {
                Session session = commentaryDao.getSession();
                transaction = session.beginTransaction();
                commentary.setStatus(StatusEnum.DELETED);
                commentaryDao.update(commentary);
                commentary = (Commentary) session.get(Commentary.class, deletedCommentaryId);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error delete Commentary from database:   " + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                commentaryDao.clearSession(sessionStatus);
            }
        }
        return commentary;
    }

    @Override
    public void removeCommentary(Commentary commentary) {
        if (null != commentary.getCommentaryId()) {
            Transaction transaction = null;
            try {
                Session session = commentaryDao.getSession();
                transaction = session.beginTransaction();
                commentaryDao.remove(commentary);
                transaction.commit();
                logger.info(commentary.getClass().getName() + " is REMOVED!");
            } catch (HibernateException e) {
                logger.error("Error remove " + commentary.getClass().getName()  + "from database:   " + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                commentaryDao.clearSession(sessionStatus);
            }
        }
    }


}
