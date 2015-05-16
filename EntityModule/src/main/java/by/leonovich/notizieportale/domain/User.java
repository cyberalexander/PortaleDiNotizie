package by.leonovich.notizieportale.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by alexanderleonovich on 11.04.15.
 * Entity - USER. Inmplements two intarfaces: 1. Serializable; 2. Identified<Integer> (generic intarface) - interface with
 * in order to avoid duplication of code when writing delet method (entity object).
 * Bean class for working with entity-USER
 */
public class User implements Serializable, Identified<Integer> {

    private Integer id;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private Date birthday;
    private String role;

    public User() {

    }

    public User(int id, String name, String lastname, String password, String email, Date birthday, String role) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.role = role;
    }

    @Override
    public Integer getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 17;
        result = prime * result + id;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (lastname == null ? 0 : lastname.hashCode());
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
        User other = (User) obj;
        if (id != other.id) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        }else if (!name.equals(other.name)) {
            return false;
        }
        if (lastname == null) {
            if (other.lastname != null) {
                return false;
            }
        }else if (!lastname.equals(other.lastname)) {
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
        return getClass() + " [id:" + id  + "; name:" + name + "; lastname:" + lastname + "; email:" + email+ "; password:" + password + "; birthday:" + birthday + "; role=" + role + "]";
    }
}
