package by.leonovich.notizieportale.domain;

import by.leonovich.notizieportale.domain.enums.StatusEnum;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Entity - ROLE_USER. Inmplements two intarfaces: 1. Serializable; 2. Identified<Integer> (generic intarface) - interface with
 * in order to avoid duplication of code when writing delet method (entity object).
 * Bean class for working with entity-ROLE_USER
 */
@Entity
@Table(name = "T_PERSON")
public class Person extends CustomEntity{
    private static final long serialVersionUID = -117974847658471765L;

    @Id
    @Column(name = "F_PERSON_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(name = "F_NAME")
    private String name;

    @Column(name = "F_SURNAME")
    private String surname;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
    private PersonDetail personDetail;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<News> newses;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<Commentary> commentaries;

    public Person() {
        super();
    }

    public Person(String name, String surname, StatusEnum status) {
        super(status);
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

    public Set<News> getNewses() {
        return newses;
    }

    public void setNewses(Set<News> newses) {
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
