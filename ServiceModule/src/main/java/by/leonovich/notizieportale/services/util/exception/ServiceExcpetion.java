package by.leonovich.notizieportale.services.util.exception;

/**
 * Created by alexanderleonovich on 16.06.15.
 */
public class ServiceExcpetion extends Exception {

    public ServiceExcpetion() {
    }

    public ServiceExcpetion(String message) {
        super(message);
    }

    public ServiceExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceExcpetion(Throwable cause) {
        super(cause);
    }

    public ServiceExcpetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
