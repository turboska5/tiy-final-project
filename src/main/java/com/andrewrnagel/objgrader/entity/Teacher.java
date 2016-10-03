package com.andrewrnagel.objgrader.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
    @NotNull
    private Integer teacherID;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private LocalDate hireDate;
    private String department;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacherID")
    @OrderBy("period")
    List<AcademicClass> teacherClasses = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String emailAddress, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.department = department;
        this.user = new User(this.emailAddress, 2);
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
}