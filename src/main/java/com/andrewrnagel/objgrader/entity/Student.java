package com.andrewrnagel.objgrader.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Nagel on 10/3/16 at 11:24 AM EST.
 */
@Entity
public class Student {
    @Id
    @GeneratedValue
    @NotNull
    private Integer studentID;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String studentNumber;
    private Integer gradeLevel;

    @OneToOne(cascade = CascadeType.ALL)
    private User user = new User(emailAddress, 3);

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentID")
    @OrderBy("lastName")
    private List<AcademicClass> studentClasses = new ArrayList<>();

    public Student() {
    }

    public Student(String firstName, String lastName, String emailAddress, String studentNumber, Integer gradeLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.studentNumber = studentNumber;
        this.gradeLevel = gradeLevel;
        this.user = new User(this.emailAddress, 3);
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
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

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<AcademicClass> getStudentClasses() {
        return studentClasses;
    }

    public void setStudentClasses(List<AcademicClass> studentClasses) {
        this.studentClasses = studentClasses;
    }
}
