package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.NewsDao;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.services.util.ServiceConstants;
import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexanderleonovich on 29.04.15.
 * Service layer for domain entity News
 */
public class NewsService implements INewsService {
    private static final Logger logger = Logger.getLogger(NewsService.class);

    private static NewsService newsServiceInst;
    private NewsDao newsDao;
    private Transaction transaction;

    private final ThreadLocal sessionStatus = new ThreadLocal();

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
     * @return instance of CommentaryService
     */
    public static synchronized NewsService getInstance() {
        if (newsServiceInst == null) {
            newsServiceInst = new NewsService();
        }
        return newsServiceInst;
    }

    /**
     *
     * @param personId
     * @return
     */
    @Override
    public List<News> getNewsesByPersonId(Long personId) {
        List<News> newses = null;
        try {
            Session session = newsDao.getSession();
            transaction = session.beginTransaction();
            newses = newsDao.getByPersonId(personId, session);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            newsDao.clearSession();
        }
        return newses;
    }

    /**
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<News> getNewsesByCategoryId(Long categoryId) {
        List<News> newses = null;
        try {
            Session session = newsDao.getSession();
            transaction = session.beginTransaction();
            newses = newsDao.getByCategoryId(categoryId, session);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            newsDao.clearSession();
        }
        return newses;
    }

    @Override
    public List<News> getListOfNewsByCategoryIdNoOrder(Long categoryId) {
        List<News> newses = null;
        try {
            Session session = newsDao.getSession();
            transaction = session.beginTransaction();
            newses = newsDao.getByCategoryIdNoOrder(categoryId, session);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            newsDao.clearSession();
        }
        return newses;
    }

    /**
     *
     * @param date
     * @return
     */
    @Override
    public List<News> getNewsesByDate(Date date) {
        List<News> newses = null;
        try {
            Session session = newsDao.getSession();
            transaction = session.beginTransaction();
            newses = newsDao.getByDate(date, session);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            newsDao.clearSession();
        }
        return newses;
    }

    /**
     *
     * @param pageId
     * @return
     */
    @Override
    public News getNewsByPageId(String pageId) {
        News news = null;
        if (!(StringUtils.isNullOrEmpty(pageId))) {
            try {
                Session session = newsDao.getSession();
                transaction = session.beginTransaction();
                news = newsDao.getByPageId(pageId, session);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error get list of Categories from database" + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                newsDao.clearSession();
            }
        }
        return news;
    }

    /**
     *
     * @param pageNumber
     * @param pageSize
     * @param categoryId
     * @return
     */
    @Override
    public List<News> getNewsByCriteria(int pageNumber, int pageSize, Long categoryId) {
        List<News> newses = new ArrayList<>();
        try {
            Session session = newsDao.getSession();
            transaction = session.beginTransaction();
            newses = newsDao.getNewsByCriteria(pageNumber, pageSize, categoryId, session);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        } finally {
            newsDao.clearSession();
        }
        return newses;
    }

    /**
     *
     * @param category
     * @return
     */
    @Override
    public List getCountNews(Category category) {
        List result = null;
        try {
            Session session = newsDao.getSession();
            transaction = session.beginTransaction();
            result = newsDao.countNews(category, session);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        } finally {
            newsDao.clearSession();
        }
        return result;
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
                Session session = newsDao.getSession();
                transaction = session.beginTransaction();
                news = newsDao.get(randomId, session);
                if (news != null && news.getNewsId() != null) {
                    newsList.add(news);
                    i++;
                }
                    transaction.commit();
            } catch (PersistException e) {
                logger.error(e);
                transaction.rollback();
            }finally {
                newsDao.clearSession();
            }
        }
        return newsList;
    }




    @Override
    public Long save(News news) {
        Long savedNewsId = null;
        try {
            Session session = newsDao.getSession();
            transaction = session.beginTransaction();
            news.setStatus(StatusEnum.PERSISTED);
            savedNewsId = newsDao.save(news, session);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            newsDao.clearSession();
        }
        return savedNewsId;
    }

    @Override
    public News update(News news) {
        if (null != news.getNewsId()) {
            Long deletedNewsId = news.getNewsId();
            try {
                Session session = newsDao.getSession();
                transaction = session.beginTransaction();
                news.setStatus(StatusEnum.PERSISTED);
                newsDao.update(news, session);
                news = (News) session.get(News.class, deletedNewsId);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error delete category from database:   " + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                newsDao.clearSession();
            }
        }
        return news;
    }

    @Override
    public News delete(News news) {
        if (null != news.getNewsId()) {
            Long deletedNewsId = news.getNewsId();
            try {
                Session session = newsDao.getSession();
                transaction = session.beginTransaction();
                news.setStatus(StatusEnum.DELETED);
                newsDao.update(news, session);
                news = (News) session.get(News.class, deletedNewsId);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error delete category from database:   " + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            }finally {
                newsDao.clearSession();
            }
        }
        return news;
    }

    @Override
    public void remove(News news) {

    }


    @Override
    public News get(Long pK) {
        News news = null;
        try {
            Session session = newsDao.getSession();
            transaction = session.beginTransaction();
            news = newsDao.get(pK, session);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        }finally {
            newsDao.clearSession();
        }
        return news;
    }

    @Override
    public News load(Long pK) {
        return null;
    }
}
