package com.andrewrnagel.objgrader.entity;

import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @NotNull
    private Integer id;
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String email;
    @NotBlank
    @NotNull
    private String password;
    @NotNull
    private Integer role;

    //constructors
    //default constructor
    public User() {
    }

    //assign specific email and password of 12345
    public User(String email, Integer role) {
        this.password = "12345";
        this.role = role;
    }

    //assign specific email and password
    public User(String email, String password, Integer role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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
}