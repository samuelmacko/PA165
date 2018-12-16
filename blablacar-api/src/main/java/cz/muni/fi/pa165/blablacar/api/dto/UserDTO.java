package cz.muni.fi.pa165.blablacar.api.dto;
import cz.muni.fi.pa165.blablacar.api.dto.comment.CommentDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;

import java.util.Set;

/**
 *
 * @author Matus Sakala
 */

public class UserDTO {
    @NotNull
    private Long id;

    @NotNull
    @Size(min = 5, max=50)
    private String login;

    @NotNull
    @Size(min = 2, max=50)
    private String firstName;

    @NotNull
    @Size(min = 2, max=50)
    private String lastName;

    @NotNull
    @Size(min = 8, max=50)
    private String password;

    private Set<DriveDTO> beingDriver = new HashSet<>();
    private Set<DriveDTO> beingCustomer = new HashSet<>();
    private Set<CommentDTO> comments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<DriveDTO> getBeingDriver() {
        return beingDriver;
    }

    public void setBeingDriver(Set<DriveDTO> beingDriver) {
        this.beingDriver = beingDriver;
    }

    public Set<DriveDTO> getBeingCustomer() {
        return beingCustomer;
    }

    public void setBeingCustomer(Set<DriveDTO> beingCustomer) {
        this.beingCustomer = beingCustomer;
    }

    public Set<CommentDTO> getComments() {
        return comments;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + ((login == null) ? 0 : login.hashCode());
        hash = 97 * hash + ((firstName == null) ? 0 : firstName.hashCode());
        hash = 97 * hash + ((lastName == null) ? 0 : lastName.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UserDTO)) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if (this.id != other.getId()) {
            return false;
        }
        if (this.firstName == null) {
            if (other.getFirstName() != null)
                return false;
        } else {
            if (!this.firstName.equals(other.getFirstName())) {
                return false;
            }
        }
        if (this.lastName == null) {
            if (other.getLastName() != null)
                return false;
        } else {
            if (!this.lastName.equals(other.getLastName())) {
                return false;
            }
        }
        if (this.login == null) {
            if (other.getLogin() != null)
                return false;
        } else {
            if (!this.login.equals(other.getLogin())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "id=" + id +
                ", login=" + login +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", password=" + password +
                ", beingDriver=" + beingDriver +
                ", beingCustomer=" + beingCustomer +
                ", comments=" + comments + '}';
    }



}
