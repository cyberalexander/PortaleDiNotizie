package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.CommentaryDao;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.services.util.exception.ServiceExcpetion;
import by.leonovich.notizieportale.util.exception.PersistException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Created by alexanderleonovich on 03.05.15.
 * Service layer for domain entity Commentary
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CommentaryService implements ICommentaryService {
    private static final Logger logger = Logger.getLogger(Commentary.class);
    @Autowired
    private CommentaryDao commentaryDao;
    @Autowired
    private PersonService personService;
    @Autowired
    private NewsService newsService;

    public CommentaryService() {

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commentary> getCommentaries() throws ServiceExcpetion {
        List<Commentary> commentaries = null;
        try {
            commentaries = commentaryDao.getAll();
        } catch (PersistException e) {
            logger.error("Error get list of commentaries from database" + e);

        }
        return commentaries;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commentary> getCommentariesByAuthorId(Long pK) throws ServiceExcpetion {
        List<Commentary> commentaries = null;
        try {
            commentaries = commentaryDao.getByPersonPK(pK);
            logger.info("Commentary-list size: " + commentaries.size());
        } catch (PersistException e) {
            logger.error("Error get list of Categories from database" + e);
        }
        return commentaries;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commentary> getCommentariesByNewsId(Long pK) throws ServiceExcpetion {
        List<Commentary> commentaries = null;
        try {
            commentaries = commentaryDao.getByNewsPK(pK);
        } catch (PersistException e) {
            logger.error("Error get list of commentaries from database" + e);
        }
        return commentaries;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Long save(Commentary commentary, Long newsId, Long personId) throws ServiceExcpetion {
        Long savedCommentaryId = null;
        if (nonNull(commentary)) {
            try {
                Person person = personService.load(personId);
                News news = newsService.load(newsId);
                commentary.setStatus(StatusEnum.PERSISTED);
                commentary.setPerson(person);
                commentary.setNews(news);
                savedCommentaryId = commentaryDao.save(commentary);
            } catch (PersistException e) {
                logger.error("Error get list of Categories from database" + e);
            }
            return savedCommentaryId;
        }
        return null;
    }


    @Override
    public Long save(Commentary commentary) throws ServiceExcpetion {
        Long savedCommentaryId = null;
        if (nonNull(commentary)) {
            try {
                commentary.setStatus(StatusEnum.PERSISTED);
                savedCommentaryId = commentaryDao.save(commentary);
            } catch (PersistException e) {
                logger.error("Error get list of Categories from database" + e);
            }
            return savedCommentaryId;
        }
        return null;
    }

    @Override
    public Commentary update(Commentary commentary) throws ServiceExcpetion {
        if (nonNull(commentary.getCommentaryId())) {
            Long updatedCommentaryId = commentary.getCommentaryId();
            try {
                commentaryDao.update(commentary);
                commentary = commentaryDao.get(updatedCommentaryId);
            } catch (PersistException e) {
                logger.error("Error get list of Categories from database" + e);
            }
            return commentary;
        }
        return null;
    }

    @Override
    public Commentary delete(Commentary commentary) throws ServiceExcpetion {
        if (nonNull(commentary.getCommentaryId())) {
            Long deletedCommentaryId = commentary.getCommentaryId();
            try {
                commentary.setStatus(StatusEnum.DELETED);
                commentaryDao.update(commentary);
                commentary = commentaryDao.get(deletedCommentaryId);
            } catch (PersistException e) {
                logger.error("Error delete Commentary from database:   " + e);
            }
        }
        return commentary;
    }

    @Override
    public void remove(Commentary commentary) throws ServiceExcpetion {
        if (nonNull(commentary.getCommentaryId())) {
            try {
                commentaryDao.remove(commentary);
                logger.info(commentary.getClass().getName() + " is REMOVED!");
            } catch (PersistException e) {
                logger.error("Error remove " + commentary.getClass().getName() + "from database:   " + e);
                logger.error(e);
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Commentary get(Long pK) throws ServiceExcpetion {
        try {
            return commentaryDao.get(pK);
        } catch (PersistException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Commentary load(Long pK) throws ServiceExcpetion {
        return null;
    }


}
