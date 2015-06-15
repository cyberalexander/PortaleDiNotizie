package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.INewsDao;
import by.leonovich.notizieportale.dao.NewsDao;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.enums.StatusEnum;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.services.util.IntComparator;
import by.leonovich.notizieportale.services.util.ServiceConstants;
import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static by.leonovich.notizieportale.services.util.ServiceConstants.Const.*;
import static by.leonovich.notizieportale.services.util.ServiceConstants.Const.ERROR_GET_NEWSES_BY_PERSON;
import static com.mysql.jdbc.StringUtils.isNullOrEmpty;
import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

/**
 * Created by alexanderleonovich on 29.04.15.
 * Service layer for domain entity News
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class NewsService implements INewsService {
    private static final Logger logger = Logger.getLogger(NewsService.class);

    private static NewsService newsServiceInst;
    @Autowired
    @Qualifier("newsDao")
    private INewsDao newsDao;

    private Transaction transaction;

    public NewsService() {
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

    /**
     * @param personId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<News> getNewsesByPersonId(Long personId) {
        List<News> newses = emptyList();
        try {
            Session session = newsDao.getSession();
            transaction = session.beginTransaction();
            newses = newsDao.getByPersonId(personId, session);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(ERROR_GET_NEWSES_BY_PERSON + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        } finally {
            newsDao.clearSession();
        }
        return newses;
    }

    /**
     * @param categoryId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<News> getNewsesByCategoryId(Long categoryId) {
        List<News> newses = emptyList();
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
        } finally {
            newsDao.clearSession();
        }
        return newses;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<News> getListOfNewsByCategoryIdNoOrder(Long categoryId) {
        List<News> newses = emptyList();
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
        } finally {
            newsDao.clearSession();
        }
        return newses;
    }

    /**
     * @param date
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
        } finally {
            newsDao.clearSession();
        }
        return newses;
    }

    /**
     * @param pageId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public News getNewsByPageId(String pageId) {
        News news = null;
        if (!(isNullOrEmpty(pageId))) {
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
            } finally {
                newsDao.clearSession();
            }
        }
        return news;
    }

    /**
     * @param pageNumber
     * @param pageSize
     * @param categoryId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
     * @param category
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<News> getMostPopularNewsList() {
        List<News> newsList = new ArrayList<>();
        for (int i = ZERO; i < 4; ) {
            News news;
            Long randomId = (long) (Math.random() * 60 + 11); // вещественное число из [3;8)
            try {
                Session session = newsDao.getSession();
                transaction = session.beginTransaction();
                news = newsDao.get(randomId);
                if (news != null && news.getNewsId() != null) {
                    newsList.add(news);
                    i++;
                }
                transaction.commit();
            } catch (PersistException e) {
                logger.error(e);
                transaction.rollback();
            } finally {
                newsDao.clearSession();
            }
        }
        return newsList;
    }

    /**
     * use this method for receiving list of pagination numbers.
     * This numbers are used in view
     * @param numberOfPages     total count (from cretery) of pages in database
     * @param pageNumber        number of page what user click
     * @param newsesPackageSize
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Integer> getList(long numberOfPages, int pageNumber, int newsesPackageSize) {
        //**bigSize - number of all pageLists
        int bigSize = size(numberOfPages, newsesPackageSize);
        List<Integer> list = new ArrayList<Integer>(FIVE);
        if (pageNumber < ONE) {
            pageNumber = ONE;
        }
        if (pageNumber > bigSize) {
            pageNumber = bigSize;
        }
        list.add(pageNumber);

        int size = bigSize;
        if (size > FIVE) {
            size = FIVE;
        }
        //** every turn of cycle step will be increased by 1
        int step = ONE;

//         pagination number will be written
//         if it is more then 0 and less then bigSize
        for (int i = ZERO; ; i++, step++) {
            int elem1 = pageNumber - step;
            if (elem1 >= ONE) {
                list.add(elem1);
            }
            if (list.size() >= size) {
                break;
            }
            int elem2 = pageNumber + step;
            if (elem2 <= bigSize) {
                list.add(elem2);
            }
            if (list.size() >= size) break;
        }
        Collections.sort(list, new IntComparator());

        return list;
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
        } finally {
            newsDao.clearSession();
        }
        return savedNewsId;
    }

    @Override
    public News update(News news) {
        if (nonNull(news.getNewsId())) {
            Long updatedNewsId = news.getNewsId();
            try {
                Session session = newsDao.getSession();
                transaction = session.beginTransaction();
                news.setStatus(StatusEnum.PERSISTED);
                newsDao.update(news, session);
                news = (News) session.get(News.class, updatedNewsId);
                transaction.commit();
            } catch (HibernateException e) {
                logger.error("Error delete category from database:   " + e);
                transaction.rollback();
            } catch (PersistException e) {
                logger.error(e);
            } finally {
                newsDao.clearSession();
            }
        }
        return news;
    }

    @Override
    public News delete(News news) {
        if (nonNull(news.getNewsId())) {
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
            } finally {
                newsDao.clearSession();
            }
        }
        return news;
    }

    @Override
    public void remove(News news) {
        try {
            Session session = newsDao.getSession();
            transaction = session.beginTransaction();
            newsDao.delete(news, session);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error remove category from database:   " + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        } finally {
            newsDao.clearSession();
        }
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public News get(Long pK) {
        try {
            return newsDao.get(pK);
        } catch (PersistException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public News load(Long pK) {
        News news = null;
        try {
            Session session = newsDao.getSession();
            transaction = session.beginTransaction();
            news = newsDao.load(pK, session);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error get list of Categories from database" + e);
            transaction.rollback();
        } catch (PersistException e) {
            logger.error(e);
        } finally {
            newsDao.clearSession();
        }
        return news;
    }

    private int size(long numberOfPages, int pageSize) {
        return (int) Math.ceil((numberOfPages - 1) / pageSize) + 1;
    }
}
