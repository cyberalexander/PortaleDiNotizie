package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.dao.IGenericDao;
import by.leonovich.notizieportale.domain.Identified;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.services.util.ServiceConstants;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexanderleonovich on 29.04.15.
 * Service layer for domain entity News
 */
public class NewsService implements INewsService {

    private static NewsService newsServiceInst;
    private IGenericDao newsDao;
    private static final Logger logger = Logger.getLogger(NewsService.class);


    private NewsService() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            newsDao = factory.getDao(News.class);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    /**
     * -=SINGLETON=-
     * Method for creating fabric
     * 1. First, you must create an instance factory or get it, and then through it to create Dao objects to the entity
     * over which you plan to perform CRUD operations.
     * @return instance of CommentaryService
     */
    public static synchronized NewsService getInstance(){
        if (newsServiceInst == null){
            newsServiceInst = new NewsService();
        }
        return newsServiceInst;
    }


    @Override
    public News getNewsByPageId(String columnNamePageId, String idOfPage) {
        try {
            return (News) newsDao.getByStringCretery(columnNamePageId, idOfPage);
        } catch (PersistException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public List<News> getListOfNewsByStringCretery(String parent_id, String page_id) {
        try {
            return newsDao.getListByStringCretery(parent_id, page_id);
        } catch (PersistException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public boolean saveNewsInDataBase(News news) {
        if (news != null) {
            try {
                newsDao.persist(news);
                return true;
            } catch (PersistException e) {
                logger.error(e);
            }
        }
        return false;
    }

    @Override
    public News getNewsByPK(Integer id) {
        if (id != null) {
            try {
                return (News) newsDao.getByPK(id);
            } catch (PersistException e) {
                logger.error(e);
            }
        }
        return null;
    }

    @Override
    public boolean deleteNewsPage(News news) {
        if (news != null) {
            try {
                newsDao.delete(news);
                return true;
            } catch (PersistException e) {
                logger.error(e);
            }
        }
        return false;
    }

    @Override
    public void EditNewsPage(News news) {
        try {
            newsDao.update(news);
        } catch (PersistException e) {
            logger.error(e);
        }
    }

    /** REFACTOR THIS METHOD */
    @Override
    public List<News> getMostPopularNewsList() {
        List<News> newsList = new ArrayList<>();
        Identified news;
        for (int i = ServiceConstants.Const.ZERO; i < 4;){
        int randomId = (int) (Math.random() * 60 + 11); // вещественное число из [3;8)
                try {
                    news = newsDao.getByPK(randomId);
                    if (news != null && news.getId() != null) {
                        newsList.add((News) news);
                        i++;
                    }
                } catch (PersistException e) {
                    logger.error(e);
                }
        }
        return newsList;
    }
}
