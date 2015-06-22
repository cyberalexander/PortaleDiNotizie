package by.leonovich.notizieportale.domainto;

import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.Person;
import by.leonovich.notizieportale.domain.enums.StatusEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by alexanderleonovich on 19.06.15.
 */
public class CommentaryTO extends CustomTobject implements Serializable {
    private static final long serialVersionUID = -1220916492869131063L;

    private Long commentaryId;
    private String comment;
    private Date date;
    private Person person;
    private News news;

    public CommentaryTO() {
        super();
    }

    public CommentaryTO(String comment, Date date, StatusEnum status) {
        super(status);
        this.comment = comment;
        this.date = date;
    }

    public Long getCommentaryId() {
        return commentaryId;
    }

    protected void setCommentaryId(Long commentaryId) {
        this.commentaryId = commentaryId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date commentDate) {
        this.date = commentDate;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 17;
        result = prime * result + (comment == null ? 0 : comment.hashCode());
        result = prime * result + (date == null ? 0 : date.hashCode());
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
        CommentaryTO other = (CommentaryTO) obj;
        if (comment == null) {
            if (other.comment != null) {
                return false;
            }
        }else if (!comment.equals(other.comment)) {
            return false;
        }
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        }else if (!date.equals(other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "-=Commentary=- [commentaryId=" + commentaryId  + ", comment=" + comment + ", commentDate=" + date + ", status=" + status + "]";
    }
}
