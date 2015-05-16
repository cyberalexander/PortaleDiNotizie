package by.leonovich.notizieportale.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by alexanderleonovich on 26.04.15.
 * Entity - COMMENTARY. Inmplements two intarfaces: 1. Serializable; 2. Identified<Integer> (generic intarface) - interface with
 * in order to avoid duplication of code when writing delet method (entity object).
 * Bean class for working with entity-COMMENTARY
 */
public class Commentary implements Serializable, Identified<Integer> {

    private Integer id;
    private int user_id;
    private int news_id;
    private String comment;
    private String date;

    public Commentary() {
    }

    public Commentary(Integer id, int user_id, int news_id, String comment, String date) {
        this.id = id;
        this.user_id = user_id;
        this.news_id = news_id;
        this.comment = comment;
        this.date = date;
    }

    @Override
    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 17;
        result = prime * result + id;
        result = prime * result + user_id;
        result = prime * result + news_id;
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
        if (id != other.id) {
            return false;
        }
        if (user_id != other.user_id) {
            return false;
        }
        if (news_id != other.news_id) {
            return false;
        }
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
        return "-=Commentary=- [id=" + id + ", user_id=" + user_id + ", news_id=" + news_id + ", comment=" + comment + ", date=" + date + "]";
    }
}
