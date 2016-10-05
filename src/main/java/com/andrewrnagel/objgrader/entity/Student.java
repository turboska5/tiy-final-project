package com.andrewrnagel.objgrader.entity;

import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.Valid;
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
    private Integer studentID = 0;
    @NotBlank
    @NotNull
    private String firstName;
    @NotBlank
    @NotNull
    private String lastName;
    @NotBlank
    @NotNull
    private String studentNumber;
    @NotNull
    private Integer gradeLevel;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private User user = new User();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "classID")
    private List<AcademicClass> studentClasses = new ArrayList<>();

    public Student() {
    }

    public Student(String firstName, String lastName, String studentNumber, Integer gradeLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.gradeLevel = gradeLevel;
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