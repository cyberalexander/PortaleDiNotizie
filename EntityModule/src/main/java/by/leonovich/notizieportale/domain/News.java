package by.leonovich.notizieportale.domain;

import by.leonovich.notizieportale.domain.util.StatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Entity - news. Inmplements two intarfaces: 1. Serializable; 2. Identified<Integer> (generic intarface) - interface with
 * in order to avoid duplication of code when writing delet method (entity object).
 * Bean class for working with entity-NEWS
 */
@Entity
@Table(name = "T_NEWS")
public class News extends CustomEntity{
    private static final long serialVersionUID = 592367795323659186L;

    @Id
    @Column(name = "F_NEWS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column(name = "F_PAGE_ID")
    private String pageId;

    @Column(name = "F_TITLE")
    private String title;

    @Column(name = "F_MENU_TITLE")
    private String menuTitle;

    @Column(name = "F_DATE")
    private Date date;

    @Column(name = "F_ANNOTATION", columnDefinition = "text")
    private String annotation;

    @Column(name = "F_CONTENT", columnDefinition = "longtext")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "F_PERSON_ID", nullable = false)
    private Person person;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "F_CATEGORY_ID", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private Set<Commentary> commentaries;

    public News() {
        super();
    }

    public News(String pageId, String title, String menuTitle, Date date, String annotation, String content, StatusEnum status) {
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

    public Set<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(Set<Commentary> commentaries) {
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
        News other = (News) obj;
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
