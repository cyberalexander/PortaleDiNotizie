package by.leonovich.notizieportale.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Entity - news. Inmplements two intarfaces: 1. Serializable; 2. Identified<Integer> (generic intarface) - interface with
 * in order to avoid duplication of code when writing delet method (entity object).
 * Bean class for working with entity-NEWS
 */
public class News implements Serializable, Identified<Integer> {

    private Integer id;
    private String page_id;
    private String parent_id;
    private String title;
    private String menu_title;
    private int user_id;
    private Date date;
    private String annotation;
    private String content;

    public News() {

    }

    public News(int id, String page_id, String parent_id, String title, String menu_title, int user_id, Date date, String annotation, String content) {
        this.id = id;
        this.page_id = page_id;
        this.parent_id = parent_id;
        this.title = title;
        this.menu_title = menu_title;
        this.user_id = user_id;
        this.date = date;
        this.annotation = annotation;
        this.content = content;
    }

    @Override
    public Integer getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getPage_id() {
        return page_id;
    }

    public void setPage_id(String page_id) {
        this.page_id = page_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMenu_title() {
        return menu_title;
    }

    public void setMenu_title(String menu_title) {
        this.menu_title = menu_title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 17;
        result = prime * result + id;
        result = prime * result + (page_id == null ? 0 : page_id.hashCode());
        result = prime * result + (parent_id == null ? 0 : parent_id.hashCode());
        result = prime * result + (title == null ? 0 : title.hashCode());
        result = prime * result + (menu_title == null ? 0 : menu_title.hashCode());
        result = prime * result + user_id;
        result = prime * result + (date == null ? 0 : date.hashCode());
        result = prime * result + (annotation == null ? 0 : annotation.hashCode());
        result = prime * result + (content == null ? 0 : content.hashCode());
        return result;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            if (this.hashCode() == obj.hashCode()) {
                return true;
            }else {
                return false;
            }
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        News other = (News) obj;
        if (id != other.id) {
            return false;
        }
        if (page_id == null) {
            if (other.page_id != null) {
                return false;
            }
        }else if (!page_id.equals(other.page_id)) {
            return false;
        }
        if (parent_id == null) {
            if (other.parent_id != null) {
                return false;
            }
        }else if (!parent_id.equals(other.parent_id)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        }else if (!title.equals(other.title)) {
            return false;
        }
        if (menu_title == null) {
            if (other.menu_title != null) {
                return false;
            }
        }else if (!menu_title.equals(other.menu_title)) {
            return false;
        }
        if (user_id != other.user_id) {
            return false;
        }
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        }else if (!date.equals(other.date)) {
            return false;
        }
        if (annotation == null) {
            if (other.annotation != null) {
                return false;
            }
        }else if (!annotation.equals(other.annotation)) {
            return false;
        }
        if (content == null) {
            if (other.content != null) {
                return false;
            }
        }else if (!content.equals(other.content)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "-=News=- [id=" + id + ", page_id=" + page_id + ", parent_id=" + parent_id + ", title=" + title + ", menu_title=" + menu_title +
                ", user_id=" + user_id + ", date=" + date + ", annotation=" + annotation + /*", content=" + content +*/ "]";
    }
}
