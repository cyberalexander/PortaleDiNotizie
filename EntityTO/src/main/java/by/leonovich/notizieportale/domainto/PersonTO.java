package by.leonovich.notizieportale.domainto;

import by.leonovich.notizieportale.domain.Commentary;
import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.PersonDetail;
import by.leonovich.notizieportale.domain.enums.StatusEnum;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by alexanderleonovich on 19.06.15.
 */
public class PersonTO  extends CustomTobject  implements Serializable, Cloneable{
    private static final long serialVersionUID = -5061450486668650618L;

    private Long personId;
    private String name;
    private String surname;
    private PersonDetailTO personDetailTO;
    private Set<News> newses;
    private List<Commentary> commentaries;
    public PersonTO() {
        super();
    }

    public PersonTO(String name, String surname, StatusEnum status) {
        super(status);
        this.name = name;
        this.surname = surname;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
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

    public PersonDetailTO getPersonDetailTO() {
        return personDetailTO;
    }

    public void setPersonDetailTO(PersonDetailTO personDetailTO) {
        this.personDetailTO = personDetailTO;
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
        PersonTO other = (PersonTO) obj;
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
