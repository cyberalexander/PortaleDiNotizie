package by.leonovich.notizieportale.util;

import org.springframework.stereotype.Component;

/**
 * Created by alexanderleonovich on 06.05.15.
 */
public enum UrlEnum {

    URL_INDEX("url.index"),
    URL_LOGIN("url.login"),
    URL_MAIN("url.main"),
    URL_PERSONCABINET("url.person.cabinet"),
    PATH_PAGE_ADD_NEWS("path.page.addnews"),
    PATH_PAGE_ADD_CATEGORY("path.page.addcategory"),
    PATH_PAGE_EDIT_NEWS("path.page.editnews"),
    PATH_PAGE_REGISTRATION_1("path.page.registration_1"),
    PATH_PAGE_REGISTRATION_2("path.page.registration_2"),
    PATH_PAGE_ERROR("path.page.error"),
    EDIT_USER_INFO("path.page.edit.person.info");

    private String urlCode;

    UrlEnum(String urlCode) {
        this.urlCode = urlCode;
    }

    public String getUrlCode() {
        return urlCode;
    }

    public void setUrlCode(String urlCode) {
        this.urlCode = urlCode;
    }
}
