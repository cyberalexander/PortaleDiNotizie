package by.leonovich.notizieportale.domainto;

import by.leonovich.notizieportale.domain.Category;
import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.enums.StatusEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by alexanderleonovich on 19.06.15.
 */
public class NewsTO  extends CustomTobject  implements Serializable{
    private static final long serialVersionUID = 1548422778666776877L;

    private Long newsId;
    private String pageId;
    private String title;
    private String menuTitle;
    private Date date;
    private String annotation;
    private String content;
    private Person person;
    private Category category;
    private List<Commentary> commentaries;

    public NewsTO() {
        super();
    }

    public NewsTO(String pageId, String title, String menuTitle, Date date, String annotation, String content, StatusEnum status) {
        super(status);
        this.pageId = pageId;
        this.title = title;
        this.menuTitle = menuTitle;
        this.date = date;
        this.annotation = annotation;
        this.content = content;
    }

    public Long getNewsId() {
        return newsId;
    }

    protected void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<Commentary> commentaries) {
        this.commentaries = commentaries;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 17;
        result = prime * result + (newsId == null ? 0 : newsId.hashCode());
        result = prime * result + (pageId == null ? 0 : pageId.hashCode());
        result = prime * result + (title == null ? 0 : title.hashCode());
        result = prime * result + (menuTitle == null ? 0 : menuTitle.hashCode());
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
        NewsTO other = (NewsTO) obj;
        if (pageId == null) {
            if (other.pageId != null) {
                return false;
            }
        }else if (!pageId.equals(other.pageId)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        }else if (!title.equals(other.title)) {
            return false;
        }
        if (menuTitle == null) {
            if (other.menuTitle != null) {
                return false;
            }
        }else if (!menuTitle.equals(other.menuTitle)) {
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
        return "-=News=- [newsId=" + newsId + ", pageId=" + pageId + ", title=" + title + ", menuTitle=" + menuTitle +
                ", date=" + date + ", annotation=" + annotation + /*", content=" + content +*/ "]";
    }
}
