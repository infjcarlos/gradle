package com.soft.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jcarlos on 7/20/2017.
 */
@Entity
@Table(name = "users")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "firstname",length = 200)
    private String firstname;

    @Column(name = "lastname",length = 200)
    private String lastname;

    @Column(name = "email",length = 150)
    private String email;

    @Column(name = "username",length = 100)
    private String username;

    @Column(name = "password",length = 200)
    private String password;

    @Column(name = "status",length = 50)
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
