package io.bumgardner.ping.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.joda.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;

    
    @NotNull
    @Column(name = "firstname")
    
    
    
    
    
    private String firstname;
    
    @NotNull
    @Column(name = "lastname")
    
    
    
    
    
    private String lastname;
    
    @NotNull
    @Column(name = "email")
    
    
    
    
    
    private String email;
    
    
    @Column(name = "photourl")
    
    
    
    
    
    private String photourl;
    

    public long getId() {
        return id;
    }

    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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
    
    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
