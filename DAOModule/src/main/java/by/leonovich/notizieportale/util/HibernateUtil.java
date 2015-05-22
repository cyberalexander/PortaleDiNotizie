package by.leonovich.notizieportale.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * Created by alexanderleonovich on 20.05.15.
 */
public class HibernateUtil {
    private static final Logger logger = Logger.getLogger(HibernateUtil.class);

    private static HibernateUtil util = null;

    private SessionFactory sessionFactory = null;

    private HibernateUtil() {
        try {
//            // Create the sessionFactory from hibernate.cfg.xml
//            Configuration configuration = new AnnotationConfiguration();
//            configuration.setNamingStrategy(new CustomNamingStrategy());

            // нужен ли метод .buildSessionFactory(), если он зачеркнут. И почему методы зачеркнуты?
            sessionFactory = new AnnotationConfiguration().configure().setNamingStrategy(new CustomNamingStrategy()).buildSessionFactory();
        } catch (Throwable ex) {
            logger.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static synchronized HibernateUtil getHibernateUtil(){
        if (util == null){
            util = new HibernateUtil();
        }
        return util;
    }
}
