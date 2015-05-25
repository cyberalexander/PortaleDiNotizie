package by.leonovich.notizieportale.util;

/**
 * Created by alexanderleonovich on 06.05.15.
 */
public enum UrlEnum {

    PATH_PAGE_INDEX("path.page.index"),
    PATH_PAGE_LOGIN("path.page.login"),
    PATH_PAGE_MAIN("path.page.main"),
    PATH_PAGE_USERCABINET("path.page.person.cabinet"),
    PATH_PAGE_ADD_NEWS("path.page.addnews"),
    PATH_PAGE_EDIT_NEWS("path.page.editnews"),
    PATH_PAGE_REGISTRATION("path.page.registration"),
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
