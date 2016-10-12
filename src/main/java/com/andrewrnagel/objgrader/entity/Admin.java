package com.andrewrnagel.objgrader.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Created by Andrew Nagel on 10/3/16 at 11:24 AM EST.
 */

@Entity
public class Admin {
    @Id
    @GeneratedValue
    private Integer adminID = 0;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate hireDate = LocalDate.now();

    @NotNull
    @NotBlank
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private User user = new User();

    //constructors
    public Admin() {
    }

    public Admin(String firstName, String lastName, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
    }

    public Admin(String firstName, String lastName, String hireDate, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.hireDate = LocalDate.parse(hireDate, formatter);
        this.title = title;
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

    public String getTenure() {
        float tenure = (ChronoUnit.DAYS.between(hireDate, LocalDate.now()));
        tenure = tenure/(float)365.25;
        return String.format("%.2f", tenure);
    }
}