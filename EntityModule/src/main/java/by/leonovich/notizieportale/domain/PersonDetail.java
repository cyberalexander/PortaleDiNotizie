package by.leonovich.notizieportale.domain;

import by.leonovich.notizieportale.domain.util.RoleEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by alexanderleonovich on 20.05.15.
 */
@Entity
@Table(name = "T_PERSON_DETAIL")
public class PersonDetail implements Serializable{
    private static final long serialVersionUID = -3983636454021133509L;

    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "person"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "F_PERSON_ID", unique = true, nullable = false)
    private Long personId;

    @Column(name = "F_EMAIL")
    private String email;

    @Column(name = "F_PASSWORD")
    private String password;

    @Column(name = "F_BIRTHDAY")
    private Date birthday;

    @Column(name = "F_ROLE")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Person person;


    public PersonDetail() {
    }

    public PersonDetail(String email, String password, Date birthday) {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        PersonDetail other = (PersonDetail) obj;
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
