package by.leonovich.notizieportale.dao.util;

import by.leonovich.notizieportale.domain.enums.StatusEnum;

import java.sql.Date;

/**
 * Created by alexanderleonovich on 25.05.15.
 */
public class TestConstants {

    public static class TestConst {

        public static final String CATEGORY_NAME = "testCategoryName";
        public static final String CATEGORY_NEW_NAME = "testSaveOrUpdateNameOfCategory";
        public static final int ZERO = 0;
        public static final int THREE = 3;
        public static final Date DATE = new Date(new java.util.Date().getTime());
        public static final Date BITHDAY = Date.valueOf("1991-02-21");
        public static final String COMMENTARY_CONTENT = "Test content of commentary";
        public static final String PAGE_ID = "Test_Page_ID";
        public static final String TITLE = "test_title";
        public static final String MENU_TITLE = "test_menu_title";
        public static final String ANNOTATION = "test_annotation";
        public static final String CONTENT = "test_content";
        public static final String PERSON_SURNAME = "test_person_surname";
        public static final String PERSON_NAME = "test_person_name";
        public static final String GET_BY_PAGE_ID_UNIQUE = "unique_page_id_test";
        public static final String EMAIL = "test_email";
        public static final String PASSWORD = "test_password";
        public static final String UNIQUE_EMAIL = "unique_email_test";
        public static final String UNIQUE_PAGE_ID_FOR_UPDATE = "unique_page_id_for_update_method";
        public static final StatusEnum DELETED = StatusEnum.DELETED;

//        public static final String CANT_FIND_BY_PK = "Exception on findByPK new persist data.";
    }

}
