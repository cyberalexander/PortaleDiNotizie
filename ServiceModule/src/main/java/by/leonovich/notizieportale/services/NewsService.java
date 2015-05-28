package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.NewsDao;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.util.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.services.util.ServiceConstants;
import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexanderleonovich on 29.04.15.
 * Service layer for domain entity News
 */
public class NewsService implements INewsService {
    private static final Logger logger = Logger.getLogger(NewsService.class);

    private static NewsService newsServiceInst;
    private NewsDao newsDao;

    private final ThreadLocal sessionStatus = new ThreadLocal();
    private Session session;
    private Transaction transaction;

    private NewsService() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            newsDao = (NewsDao) factory.getDao(News.class);
        } catch (PersistException e) {
            logger.error(e);
        }
    }

    /**
     * -=SINGLETON=-
     * Method for creating fabric
     * 1. First, you must create an instance factory or get it, and then through it to create Dao objects to the entity
     * over which you plan to perform CRUD operations.
     *
     * @return instance of CommentaryService
     */
    public static synchronized NewsService getInstance() {
        if (newsServiceInst == null) {
            newsServiceInst = new NewsService();
        }
        return newsServiceInst;
    }


    @Override
    public News getNewsByPK(Long PK) {
        News news = null;
        try {
            session = newsDao.getSession();
            transaction = session.beginTransaction();
            news = newsDao.getByPK(PK);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            sessionStatus.set(true);
            newsDao.clearSession(sessionStatus);
        }
        return news;
    }

    @Override
    public News getNewsByPageId(String pageId) {
        News news = null;
        if (!(StringUtils.isNullOrEmpty(pageId))) {
            try {
                session = newsDao.getSession();
                transaction = session.beginTransaction();
                news = newsDao.getByPageId(pageId);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error get list of Categories from database" + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                newsDao.clearSession(sessionStatus);
            }
        }
        return news;
    }

    @Override
    public List<News> getListOfNewsByPersonId(Long personId) {
        List<News> newses = null;
        try {
            session = newsDao.getSession();
            transaction = session.beginTransaction();
            newses = newsDao.getByPersonPK(personId);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            sessionStatus.set(true);
            newsDao.clearSession(sessionStatus);
        }
        return newses;
    }

    @Override
    public List<News> getListOfNewsByCategory(Long categoryId) {
        List<News> newses = null;
        try {
            session = newsDao.getSession();
            transaction = session.beginTransaction();
            newses = newsDao.getByCategoryPK(categoryId);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            sessionStatus.set(true);
            newsDao.clearSession(sessionStatus);
        }
        return newses;
    }

    @Override
    public Long saveNews(News news) {
        Long savedNewsId = null;
        try {
            session = newsDao.getSession();
            transaction = session.beginTransaction();
            news.setStatus(StatusEnum.SAVED);
            savedNewsId = newsDao.save(news);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            sessionStatus.set(true);
            newsDao.clearSession(sessionStatus);
        }
        return savedNewsId;
    }

    @Override
    public News updateNews(News news) {
        if (null != news.getNewsId()) {
            Long deletedNewsId = news.getNewsId();
            try {
                session = newsDao.getSession();
                transaction = session.beginTransaction();
                news.setStatus(StatusEnum.SAVED);
                newsDao.update(news);
                news = (News) session.get(News.class, deletedNewsId);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error delete category from database:   " + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                newsDao.clearSession(sessionStatus);
            }
        }
        return news;
    }

    @Override
    public News deleteNews(News news) {
        if (null != news.getNewsId()) {
            Long deletedNewsId = news.getNewsId();
            try {
                session = newsDao.getSession();
                transaction = session.beginTransaction();
                news.setStatus(StatusEnum.DELETED);
                newsDao.update(news);
                news = (News) session.get(News.class, deletedNewsId);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error delete category from database:   " + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                newsDao.clearSession(sessionStatus);
            }
        }
        return news;
    }

    @Override
    public void removeNews(News news) {

    }

    /**
     * REFACTOR THIS METHOD
     */
    @Override
    public List<News> getMostPopularNewsList() {
        List<News> newsList = new ArrayList<>();
        for (int i = ServiceConstants.Const.ZERO; i < 4; ) {
            News news;
            Long randomId = (long) (Math.random() * 60 + 11); // вещественное число из [3;8)
            try {
                news = newsDao.getByPK(randomId);
                if (news != null && news.getNewsId() != null) {
                    newsList.add(news);
                    i++;
                }
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                sessionStatus.set(true);
                newsDao.clearSession(sessionStatus);
            }
        }
        return newsList;
    }

    @Override
    public List<News> getNewsByCriteria(int pageNumber, int pageSize, Long categoryId) {
        List<News> newses = new ArrayList<>();
        try {
            session = newsDao.getSession();
            transaction = session.beginTransaction();
            newses = newsDao.getNewsByCriteria(pageNumber, pageSize, categoryId);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        } finally {
            sessionStatus.set(true);
            newsDao.clearSession(sessionStatus);
        }
        return newses;
    }
}
