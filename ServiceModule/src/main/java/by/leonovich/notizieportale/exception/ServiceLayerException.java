package by.leonovich.notizieportale.exception;

/**
 * Created by alexanderleonovich on 16.06.15.
 * Own class exception, inherited from Exception
 * In order to abstract from the data store. Ie, in this situation, if I did not use PersistException,
 * It would be caught SQLException. But the data could be stored and not in MYSQL. And if I started to work with other
 * Database, it would have to refactor a lot of code to just catch exceptions of another type.
 * Therefore, in general, use the classes in the package java.sql unified classes and interfaces is not highly desirable.
 * Use your own exceptions PersistException allows not to tie SqlException.
 */
public class ServiceLayerException extends Exception {

    public ServiceLayerException() {
    }

    public ServiceLayerException(String message) {
        super(message);
    }

    public ServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceLayerException(Throwable cause) {
        super(cause);
    }

    public ServiceLayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
