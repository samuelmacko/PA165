package cz.muni.fi.pa165.blablacar.persistence.entity;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 * Class representing user
 * @author Matus Sakala
 */

@Entity
@Table(name="USERS")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable=false,unique=false)
    private String firstName;
    
    @Column(nullable=false,unique=false)
    private String lastName;
    
    @Column(nullable=false,unique=true)
    private String login;

    private String password;

    @OneToMany(mappedBy = "driver")
    private Set<Drive> beingDriver = new HashSet<>();

    @ManyToMany(mappedBy = "customers")
    private Set<Drive> beingCustomer = new HashSet<>();

    @OneToMany(mappedBy = "author")
    private List<Comment> comments = new ArrayList<>();

    public Set<Drive> getBeingDriver() {
        return beingDriver;
    }

    public Set<Drive> getBeingCustomer() {
        return beingCustomer;
    }

    public void setBeingDriver(Set<Drive> beingDriver) {
        this.beingDriver = beingDriver;
    }

    public void setBeingCustomer(Set<Drive> beingCustomer) {
        this.beingCustomer = beingCustomer;
    }

    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String pswd){
        this.password = pswd;
    }
    
    public String getLogin(){
        return this.login;
    }
    
    public void setLogin(String log){
        this.login = log;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public void setLastName(String lName){
        this.lastName = lName;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User(){
    }
    
    public User(String log){
        this.login = log;
    }
    
    public User(String fName, String lName){
        this.firstName = fName;
        this.lastName = lName;
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
        if (!(obj instanceof User)) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.getId()) {
            return false;
        }
        if(this.firstName == null ){
            if(other.getFirstName() != null)
                return false;
        }else{
            if(!this.firstName.equals(other.getFirstName())){
                return false;
            }
        }
        if(this.lastName == null){
            if(other.getLastName() != null)
                return false;
        }else{
            if(!this.lastName.equals(other.getLastName())){
                return false;
            }
        }
        if(this.login == null){
            if(other.getLogin() != null)
                return false;
        }else{
            if (!this.login.equals(other.getLogin())){
               return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + 
                "id=" + id + 
                ", firstName=" + firstName + 
                ", lastName=" + lastName + 
                ", login=" + login + '}';
    }
}
