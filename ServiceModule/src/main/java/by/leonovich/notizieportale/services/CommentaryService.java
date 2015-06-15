package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.CommentaryDao;
import by.leonovich.notizieportale.dao.ICommentaryDao;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

/**
 * Created by alexanderleonovich on 03.05.15.
 * Service layer for domain entity Commentary
 */
@Service("commentaryService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CommentaryService implements ICommentaryService {
    private static final Logger logger = Logger.getLogger(Commentary.class);

    private static CommentaryService commentServiceInst;
    @Autowired
    @Qualifier("commentaryDao")
    private ICommentaryDao commentaryDao;
    @Autowired
    @Qualifier("personService")
    private IPersonService personService;
    @Autowired
    @Qualifier("newsService")
    private NewsService newsService;

    private Transaction transaction;

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

    public CommentaryService() {
        personService = PersonService.getInstance();
        newsService = NewsService.getInstance();
        commentaryDao = CommentaryDao.getInstance();

    }

    public CommentaryService(CommentaryDao commentaryDao) {
        this.commentaryDao = commentaryDao;
        personService = PersonService.getInstance();
        newsService = NewsService.getInstance();
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commentary> getCommentaries() {
        List<Commentary> commentaries = null;
        try {
            Session session = commentaryDao.getSession();
            transaction = session.beginTransaction();
            commentaries = commentaryDao.getAll(session);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        } finally {
            commentaryDao.clearSession();
        }
        return commentaries;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commentary> getCommentariesByAuthorId(Long pK) {
        List<Commentary> commentaries = null;
        try {
            Session session = commentaryDao.getSession();
            transaction = session.beginTransaction();
            commentaries = commentaryDao.getByPersonPK(pK, session);
            logger.info("Category-list size: " + commentaries.size());
            transaction.commit();
            logger.info("successful get list!");
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            commentaryDao.clearSession();
        }
        return commentaries;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commentary> getCommentariesByNewsId(Long pK) {
        List<Commentary> commentaries = null;
            try {
                Session session = commentaryDao.getSession();
                transaction = session.beginTransaction();
                commentaries = commentaryDao.getByNewsPK(pK, session);
                transaction.commit();
                logger.info("successful get list!");
            } catch (HibernateException e) {
                logger.error("Error get list of commentaries from database" + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                commentaryDao.clearSession();
            }
            return commentaries;
    }

    public Long save(Commentary commentary, Long newsId, Long personId) {
        Long savedCommentaryId = null;
        if (nonNull(commentary)) {
            try {
                Session session = commentaryDao.getSession();
                commentaryDao.clearSession();
                transaction = session.beginTransaction();
                Person person = personService.load(personId);
                News news = newsService.load(newsId);
                commentary.setStatus(StatusEnum.PERSISTED);
                commentary.setPerson(person);
                commentary.setNews(news);
                savedCommentaryId = commentaryDao.save(commentary, session);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error get list of Categories from database" + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                commentaryDao.clearSession();
            }
            return savedCommentaryId;
        }else {
            return null;
        }
    }


    @Override
    public Long save(Commentary commentary) {
        Long savedCommentaryId = null;
        if (nonNull(commentary)) {
            try {
                Session session = commentaryDao.getSession();
                commentaryDao.clearSession();
                transaction = session.beginTransaction();

                commentary.setStatus(StatusEnum.PERSISTED);

                savedCommentaryId = commentaryDao.save(commentary, session);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error get list of Categories from database" + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                commentaryDao.clearSession();
            }
            return savedCommentaryId;
        }else {
            return null;
        }
    }

    @Override
    public Commentary update(Commentary commentary) {
        if (nonNull(commentary.getCommentaryId())) {
        Long updatedCommentaryId = commentary.getCommentaryId();
            try {
                Session session = commentaryDao.getSession();
                transaction = session.beginTransaction();
                commentaryDao.update(commentary, session);
                commentary = commentaryDao.get(updatedCommentaryId);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error get list of Categories from database" + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                commentaryDao.clearSession();
            }
            return commentary;
        }else {
            return null;
        }
    }

    @Override
    public Commentary delete(Commentary commentary) {
        if (nonNull(commentary.getCommentaryId())) {
            Long deletedCommentaryId = commentary.getCommentaryId();
            try {
                Session session = commentaryDao.getSession();
                transaction = session.beginTransaction();
                commentary.setStatus(StatusEnum.DELETED);
                commentaryDao.update(commentary, session);
                commentary = (Commentary) session.get(Commentary.class, deletedCommentaryId);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error delete Commentary from database:   " + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                commentaryDao.clearSession();
            }
        }
        return commentary;
    }

    @Override
    public void remove(Commentary commentary) {
        if (nonNull(commentary.getCommentaryId())) {
            try {
                Session session = commentaryDao.getSession();
                transaction = session.beginTransaction();
                commentaryDao.remove(commentary, session);
                transaction.commit();
                logger.info(commentary.getClass().getName() + " is REMOVED!");
            } catch (HibernateException e) {
                logger.error("Error remove " + commentary.getClass().getName()  + "from database:   " + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                commentaryDao.clearSession();
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Commentary get(Long pK) {
        try {
            return commentaryDao.get(pK);
        } catch (PersistException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Commentary load(Long pK) {
        return null;
    }


}
