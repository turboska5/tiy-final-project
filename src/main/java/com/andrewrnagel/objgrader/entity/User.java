package com.andrewrnagel.objgrader.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Integer id = 0;
    @NotBlank
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    private Integer role;
    boolean disabled = false;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate lastLogin = null;

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

    public String isDisabled() {
        if(disabled) {
            return "Yes";
        }
        return "No";
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin() {
        this.lastLogin = LocalDate.now();
    }
}