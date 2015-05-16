package by.leonovich.notizieportale.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by alexanderleonovich on 18.04.15.
 * Messages-handler for web-application
 */
public class MessageManager {

    private static final Logger logger = Logger.getLogger(MessageManager.class);
    private static MessageManager messManagerSingle;
    private static Properties properties;

    private MessageManager() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader(). /** WHAT IS THIS??? */
                    getResource(WebConstants.Const.MESSAGE_PROPERTIES).getPath()));
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public static MessageManager getInstance(){
        if (messManagerSingle == null){
            messManagerSingle = new MessageManager();
        }
        return messManagerSingle;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
