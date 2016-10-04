package com.andrewrnagel.objgrader.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Andrew Nagel on 10/3/16 at 11:24 AM EST.
 */
@Entity
public class Teacher {
    @Id
    @GeneratedValue
    private Integer teacherID;
    @NotBlank
    @NotNull
    private String firstName;
    @NotBlank
    @NotNull
    private String lastName;
    @NotBlank
    @NotNull
    private String emailAddress;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate hireDate;
    @NotBlank
    @NotNull
    private String department;
    @OneToOne(cascade = CascadeType.ALL)
    private User user = new User();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacherID")
    @OrderBy("period")
    List<AcademicClass> teacherClasses = new ArrayList<>();
    @Transient
    @NotBlank
    @NotNull
    private String password;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String emailAddress, String hireDate, String department, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.hireDate = LocalDate.parse(hireDate, formatter);
        this.department = department;
        this.password = password;
    }

    public Teacher(String firstName, String lastName, String emailAddress, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.department = department;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<AcademicClass> getTeacherClasses() {
        return teacherClasses;
    }

    public void setTeacherClasses(List<AcademicClass> teacherClasses) {
        this.teacherClasses = teacherClasses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTenure() {
        float tenure = (ChronoUnit.DAYS.between(hireDate, LocalDate.now()));
        tenure = tenure/(float)365.25;
        return String.format("%.2f", tenure);
    }
}