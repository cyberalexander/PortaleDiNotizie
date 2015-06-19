package by.leonovich.notizieportale.util;

import by.leonovich.notizieportale.domain.enums.RoleEnum;

/**
 * Created by alexanderleonovich on 15.05.15.
 */
public class WebConstants {

    public static class Const {

        public static final String CONTENT_TYPE = "text/html; charset=UTF-8";
        public static final String MESSAGE_PROPERTIES = "messages.properties";
        public static final String URL_PROPERTIES = "url.properties";

        public static final String COMMAND = "command";
        public static final String EDIT_NEWS = "editnews";
        public static final String ADD_NEWS = "addnews";
        public static final String DELETE_NEWS = "deletenews";

        public static final String COMMENT_FOR_EDIT = "commentForEdit";

        public static final String MAIN = "main";
        public static final String NEWS = "news";
        public static final String NEWSES = "newses";
        public static final String COMMENTARIES = "commentaries";
        public static final String CATEGORIES = "categories";
        public static final String FROM_ADDWRITE = "addwrite";
        public static final String FROM_DELETE = "delete";
        public static final String FROM_EDITWRITE = "edit";


        public static final String PERSONTYPE = "persontype";
        public static final RoleEnum ADMIN = RoleEnum.ADMIN;
        public static final RoleEnum PERSON = RoleEnum.USER;
        public static final String P_PERSON = "person";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";

        public static final int ZERO = 0;
        public static final int ONE = 1;
        public static final int FIVE = 5;
        public static final int THREE = 3;
        public static final int PAGES_PACK_SIZE = 5;

        /* ! NAME OF PARAMETERS FROM CLIENT LAYER */
        public static final String P_PAGE_ID = "pageId";
        public static final String P_CATEGORY_ID = "categoryId";
        public static final String P_PERSON_ID = "personId";
        public static final String P_DATE = "date";
        public static final String P_NEWS_ID = "newsId";
        public static final String P_COMMENTARY_ID = "commentaryId";
        public static final String P_CATEGORY = "category";
        public static final String P_NEWS_ID_4_DELETE = "newsIdForDelete";
        public static final String P_NEWS_ID_4_EDIT = "newsIdForEdit";
        public static final String P_CONTENT = "content";
        public static final String P_BIRTHDAY = "birthday";
        public static final String P_NAME = "name";
        public static final String P_SURNAME = "surname";
        public static final String P_NEW_PASSWORD = "new_password";
        public static final String P_NEW_EMAIL = "new_email";
        public static final String P_EMAIL = "email";
        public static final String P_PASSWORD = "password";
        public static final String P_PAGE_NUMBER = "pageNumber";
        public static final String ROLE = "role";
        public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
        public static final String BIRTH_DAY_PATTERN = "yyyy-MM-dd";
        public static final String POPULAR_NEWSES = "popularNewses";
        public static final String PAGINATOR_LIST = "paginatorList";
        public static final String P_ID = "id";
        public static final String P_ANNOTATION = "annotation";
        public static final String P_DATE_NOW = "dateNow";
    }
}
