package by.leonovich.notizieportale.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Entity - USER. Inmplements two intarfaces: 1. Serializable; 2. Identified<Integer> (generic intarface) - interface with
 * in order to avoid duplication of code when writing delet method (entity object).
 * Bean class for working with entity-USER
 */
public class Person implements Serializable{

    private Long personId;
    private String name;
    private String surname;

    private PersonDetail personDetail;

    private List<News> newses;

    private List<Commentary> commentaries;

    public Person() {

    }

    public Person(Long personId, String name, String surname) {
        this.personId = personId;
        this.name = name;
        this.surname = surname;
    }

    public Long getPersonId() {
        return personId;
    }

    protected void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public PersonDetail getPersonDetail() {
        return personDetail;
    }

    public void setPersonDetail(PersonDetail personDetail) {
        this.personDetail = personDetail;
    }

    public List<News> getNewses() {
        return newses;
    }

    public void setNewses(List<News> newses) {
        this.newses = newses;
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
        result = prime * result + (personId == null ? 0 : personId.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (surname == null ? 0 : surname.hashCode());
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
        Person other = (Person) obj;
        if (personId != other.personId) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        }else if (!name.equals(other.name)) {
            return false;
        }
        if (surname == null) {
            if (other.surname != null) {
                return false;
            }
        }else if (!surname.equals(other.surname)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getClass() + " [personId:" + personId + "; name:" + name + "; surname:" + surname + "]";
    }
}
