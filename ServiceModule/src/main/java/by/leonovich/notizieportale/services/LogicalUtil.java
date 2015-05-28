package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.dao.CategoryDao;
import by.leonovich.notizieportale.dao.CommentaryDao;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.exception.PersistException;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexanderleonovich on 27.05.15.
 */
public class LogicalUtil {

    private static CategoryDao categoryDao;
    private static Session session;
    private static Transaction transaction;

    private LogicalUtil() {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            categoryDao = (CategoryDao) factory.getDao(Category.class);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int pageNumber = 2;
        int pageSize = 4;

        session = categoryDao.getSession();
        transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Category.class);
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        List<Category> list = criteria.list();
        if (list.size() > 0) {
            System.out.println("Total Results:" + list.size());
            for (Category category : list) {
                System.out.println(category.getCategoryId() + " - " + category.getCategoryName());
            }
        }


    }

        /*List<Integer> list = getPagination(8);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }*/
}


    /*public static ArrayList getPagination(Integer countNews){
        ArrayList list = new ArrayList();
        ArrayList paginationList = new ArrayList();
        Integer listSize;
        CategoryService categoryService = CategoryService.getInstance();

            list = (ArrayList) categoryService.getCategories();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(" --- " + list.get(i).toString());
        }


        listSize = list.size();
        Integer[] start_to_news = new Integer[2];
        Integer start_news = 0;
        Integer to_news = start_news + countNews;

        start_to_news[0] = start_news;
        start_to_news[1] = to_news;
        paginationList.add(start_to_news);

        while (start_news < listSize-countNews){
            start_to_news = new Integer[2];
            start_news = start_news + countNews;
            to_news = start_news + countNews;
            start_to_news[0] = start_news;
            start_to_news[1] = to_news;
            paginationList.add(start_to_news);
        }

        return paginationList;
    }*/

