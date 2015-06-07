package by.leonovich.notizieportale.domain;


import by.leonovich.notizieportale.domain.enums.StatusEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by alexanderleonovich on 26.04.15.
 * Entity - COMMENTARY. Inmplements two intarfaces: 1. Serializable; 2. Identified<Integer> (generic intarface) - interface with
 * in order to avoid duplication of code when writing delet method (entity object).
 * Bean class for working with entity-COMMENTARY
 */
@Entity
@Table(name = "T_COMMENTARY")
public class Commentary extends CustomEntity{
    private static final long serialVersionUID = 5873704857401954452L;

    @Id
    @Column(name = "F_COMMENTARY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentaryId;

    @Column(name = "F_COMMENT", columnDefinition = "longtext")
    private String comment;

    @Column(name = "F_DATE")
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "F_PERSON_ID", nullable = false)
    private Person person;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "F_NEWS_ID", nullable = false)
    private News news;

    public Commentary() {
        super();
    }

    public Commentary(String comment, Date date, StatusEnum status) {
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
        Commentary other = (Commentary) obj;
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
