package by.leonovich.notizieportale.domain;

import java.io.Serializable;

/**
 * Created by alexanderleonovich on 26.04.15.
 * Entity - COMMENTARY. Inmplements two intarfaces: 1. Serializable; 2. Identified<Integer> (generic intarface) - interface with
 * in order to avoid duplication of code when writing delet method (entity object).
 * Bean class for working with entity-COMMENTARY
 */
public class Commentary implements Serializable {

    private Long commentaryId;
    private String comment;
    private String commentDate;

    private Person person;

    private News news;

    public Commentary() {
    }

    public Commentary(Long commentaryId, String comment, String commentDate) {
        this.commentaryId = commentaryId;

        this.comment = comment;
        this.commentDate = commentDate;
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

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
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
        result = prime * result + (commentaryId == null ? 0 : commentaryId.hashCode());
        result = prime * result + (comment == null ? 0 : comment.hashCode());
        result = prime * result + (commentDate == null ? 0 : commentDate.hashCode());
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
        Commentary other = (Commentary) obj;
        if (commentaryId != other.commentaryId) {
            return false;
        }
        if (comment == null) {
            if (other.comment != null) {
                return false;
            }
        }else if (!comment.equals(other.comment)) {
            return false;
        }
        if (commentDate == null) {
            if (other.commentDate != null) {
                return false;
            }
        }else if (!commentDate.equals(other.commentDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "-=Commentary=- [commentaryId=" + commentaryId + ", userId=" + ", comment=" + comment + ", commentDate=" + commentDate + "]";
    }
}
