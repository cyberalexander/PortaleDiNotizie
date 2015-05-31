package by.leonovich.notizieportale.util;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Special class for keeping resources of this project
 */
public class DaoConstants {

    public static class Const {

        public static final String DELETE_MESSAGE = "On delete modify more then 1 record: ";
        public static final String UPDATE_MESSAGE = "On update modify more then 1 record: ";
        public static final String OBJECT_IS_ALREADY_PERSIST = "Object is already persist.";
        public static final String PERSIST_EXCEPTION = "On persist modify more then 1 record: ";
        public static final String CANT_FIND_BY_PK = "Exception on findByPK new persist data.";


        public static final Object ERROR_UPDATE_ENTITY = "Error update ENTITY in Dao. ";
        public static final String STATUS = "status";
        public static final String EMAIL = "email";
        public static final String PRIMARY_KEY = "pK";
        public static final String DATE = "date";
        public static final String PAGE_ID = "pageId";
        public static final int ONE = 1;
//        public static final String CANT_FIND_BY_PK = "Exception on findByPK new persist data.";
    }
}
