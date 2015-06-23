package by.leonovich.notizieportale.domainto;

import by.leonovich.notizieportale.domain.enums.RoleEnum;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by alexanderleonovich on 19.06.15.
 */
public class PersonDetailTO  extends CustomTobject implements Serializable {
    private static final long serialVersionUID = 939014522975721575L;

    private Long personId;
    private String email;
    private String password;
    private Date birthday;
    private RoleEnum role;
    private PersonTO personTO;

    public PersonDetailTO() {
    }

    public PersonDetailTO(String email, String password, Date birthday) {
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public PersonTO getPersonTO() {
        return personTO;
    }

    public void setPersonTO(PersonTO personTO) {
        this.personTO = personTO;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 17;
        result = prime * result + (email == null ? 0 : email.hashCode());
        result = prime * result + (password == null ? 0 : password.hashCode());
        result = prime * result + (birthday == null ? 0 : birthday.hashCode());
        result = prime * result + (role == null ? 0 : role.hashCode());
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
        PersonDetailTO other = (PersonDetailTO) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        }else if (!email.equals(other.email)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        }else if (!password.equals(other.password)) {
            return false;
        }
        if (birthday == null) {
            if (other.birthday != null) {
                return false;
            }
        }else if (!birthday.equals(other.birthday)) {
            return false;
        }
        if (role == null) {
            if (other.role != null) {
                return false;
            }
        }else if (!role.equals(other.role)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonDetail{" +
                "personId=" + personId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", role='" + role + '\'' +
                '}';
    }
}
