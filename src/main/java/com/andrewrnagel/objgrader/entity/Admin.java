package com.andrewrnagel.objgrader.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Andrew Nagel on 10/3/16 at 11:24 AM EST.
 */
@Entity
public class Admin {
    @Id
    @GeneratedValue
    private Integer adminID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate hireDate;
    private String title;
    @OneToOne(cascade = CascadeType.ALL)
    private User user = new User();
    @Transient
    private String password;

    //constructors
    public Admin() {
    }

    public Admin(String firstName, String lastName, String emailAddress, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.title = title;
    }

    public Admin(String firstName, String lastName, String emailAddress, String hireDate, String title, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.hireDate = LocalDate.parse(hireDate, formatter);
        this.title = title;
        this.password = password;
    }

    //getters and setters
    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}