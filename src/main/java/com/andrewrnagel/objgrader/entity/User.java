package com.andrewrnagel.objgrader.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int id;
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String username;
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String email;
    @NotBlank
    @NotNull
    private String password;

    //Added from Admin controls
    private String firstName;
    private String lastName;
    private int role;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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