package by.leonovich.notizieportale.mainpack;

import by.leonovich.notizieportale.dao.AbstractDao;
import by.leonovich.notizieportale.dao.IGenericDao;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.exception.PersistException;
import by.leonovich.notizieportale.util.HibernateUtil;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by alexanderleonovich on 11.05.15.
 */
public class Main {


    public static void main(String[] args) {
        IDaoFactory factory = DaoFactoryImpl.getInstance();
        try {
            IGenericDao dao = factory.getDao(Category.class);
            List<Category> list = dao.getAll();
            for (Category element : list) {
                System.out.println(element.toString());
            }
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

}
