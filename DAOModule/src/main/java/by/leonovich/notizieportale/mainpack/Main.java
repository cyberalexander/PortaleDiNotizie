package by.leonovich.notizieportale.mainpack;

import by.leonovich.notizieportale.dao.IGenericDao;
import by.leonovich.notizieportale.daofactory.DaoFactoryImpl;
import by.leonovich.notizieportale.daofactory.IDaoFactory;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.exception.PersistException;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Created by alexanderleonovich on 11.05.15.
 */
public class Main {

    public static void main(String[] args) {

        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String dateString = simpleDateFormatter.format(new Date());
        try {

            Date date = simpleDateFormatter.parse(dateString);
            System.out.println(date);
            System.out.println(simpleDateFormatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
