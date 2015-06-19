package by.leonovich.notizieportale.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by alexanderleonovich on 18.04.15.
 * URL-handler for web-application
 */
public class URLManager {

    private static URLManager urlManagerSingle;
    private static Properties properties;
    private static final Logger logger = Logger.getLogger(URLManager.class);


    /**
     * конструктор извлекает информацию о местонахождении url.properties. В url.properties хранятся URL
     * для навигации по сайту
     */
    private URLManager() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource(WebConstants.Const.URL_PROPERTIES).getPath()));
        } catch (IOException e) {
            logger.error(e);
        }
    }

    /**
     * singleton of URLManager instance
     * @return single instance of URLManager.class, if does not exists
     */
    public static URLManager getInstance(){
        if (urlManagerSingle == null){
            urlManagerSingle = new URLManager();
        }
        return urlManagerSingle;
    }

    /**
     * Method for get url from properties file url.properties
     * @param key key of url-string
     * @return String - url of page for navigate to site
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
