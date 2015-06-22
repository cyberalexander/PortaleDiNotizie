package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.CommentaryDao;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import by.leonovich.notizieportale.services.exception.ServiceLayerException;

import java.util.List;

import static by.leonovich.notizieportale.services.util.ServiceConstants.Const.MINUS_ONE;
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
    public List<Commentary> getCommentaries() throws ServiceLayerException {
        List<Commentary> commentaries;
        try {
            commentaries = commentaryDao.getAll();
        } catch (PersistException e) {
            logger.error("Error get list of commentaries from database => " + e);
            throw new ServiceLayerException(e);
        }
        return commentaries;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commentary> getCommentariesByAuthorId(Long personId) throws ServiceLayerException {
        List<Commentary> commentaries;
        try {
            commentaries = commentaryDao.getByPersonId(personId);
            logger.info("Commentary-list size: " + commentaries.size() + ", for Person with Id = " + personId);
        } catch (PersistException e) {
            logger.error("Error get list of Commentaries from database => " + e);
            throw new ServiceLayerException(e);
        }
        return commentaries;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commentary> getCommentariesByNewsId(Long newsId) throws ServiceLayerException {
        List<Commentary> commentaries = null;
        try {
            commentaries = commentaryDao.getByNewsPK(newsId);
            logger.info("Commentary-list size: " + commentaries.size() + ", for News with Id = " + newsId);
        } catch (PersistException e) {
            logger.error("Error get list of commentaries from database => " + e);
            throw new ServiceLayerException(e);
        }
        return commentaries;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Long save(Commentary commentary, Long newsId, String login) throws ServiceLayerException {
        Long pK;
        try {
            Person person = personService.getByEmail(login);
            News news = newsService.load(newsId);
            commentary.setStatus(StatusEnum.PERSISTED);
            commentary.setPerson(person);
            commentary.setNews(news);
            pK = commentaryDao.save(commentary);
        } catch (PersistException e) {
            logger.error("Error save commentary in Service layer => " + e);
            throw new ServiceLayerException(e);
        }
        return pK;
    }


    @Override
    public Long save(Commentary commentary) throws ServiceLayerException {
        Long pK;
        try {
            commentary.setStatus(StatusEnum.PERSISTED);
            pK = commentaryDao.save(commentary);
        } catch (PersistException e) {
            logger.error("Error get list of Categories from database" + e);
            throw new ServiceLayerException(e);
        }
        return pK;
    }

    @Override
    public Long saveOrUpdate(Commentary commentary) throws ServiceLayerException {
        Long pK = (long) MINUS_ONE;
        return pK;
    }

    @Override
    public Commentary update(Commentary commentary) throws ServiceLayerException {
        if (nonNull(commentary.getCommentaryId())) {
            Long pK = commentary.getCommentaryId();
            try {
                commentaryDao.update(commentary);
                commentary = commentaryDao.get(pK);
            } catch (PersistException e) {
                logger.error("Error get list of Categories from database" + e);
                throw new ServiceLayerException(e);
            }
            return commentary;
        }
        return null;
    }

    @Override
    public Long delete(Commentary commentary) throws ServiceLayerException {
        if (nonNull(commentary.getCommentaryId())) {
            Long pK = commentary.getCommentaryId();
            try {
                commentary.setStatus(StatusEnum.DELETED);
                commentaryDao.update(commentary);
                return pK;
            } catch (PersistException e) {
                logger.error("Error delete Commentary from database:   " + e);
                throw new ServiceLayerException(e);
            }
        }
        return (long) MINUS_ONE;
    }

    @Override
    public void remove(Commentary commentary) throws ServiceLayerException {
        if (nonNull(commentary.getCommentaryId())) {
            try {
                commentaryDao.remove(commentary);
                logger.info(commentary.getClass().getName() + " is REMOVED!");
            } catch (PersistException e) {
                logger.error("Error remove " + commentary.getClass().getName() + "from database:   " + e);
                logger.error(e);
                throw new ServiceLayerException(e);
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Commentary get(Long commentaryId) throws ServiceLayerException {
        try {
            return commentaryDao.get(commentaryId);
        } catch (PersistException e) {
            logger.error(e);
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public Commentary load(Long pK) throws ServiceLayerException {
        return null;
    }


}
