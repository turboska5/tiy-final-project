package com.andrewrnagel.objgrader.entity;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:39 PM EST.
 */

@Entity
public class AcademicClass {

    @Id
    @GeneratedValue
    private Integer classID = 0;
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String identifier;
    @NotBlank
    @NotNull
    private String department;
    @NotNull
    private Integer period;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacherID")
    @Valid
    private Teacher teacher;
    @NotNull
    private Integer capacity=0;
    @Formula("(SELECT COUNT(distinct g.student_studentid) FROM Grade AS g WHERE g.academic_class_classid = classID)")
    private Integer studentNumber=0;

//    @NotNull
//    private Double average=0.0;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "students_classes",
//            joinColumns = @JoinColumn(name = "classID"),
//            inverseJoinColumns = @JoinColumn(name = "studentID")
//    )
//    @Valid
//    private List<Student> students = new ArrayList<>();
//    @OneToMany(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "classID")
//    @OrderBy("date")
//    private List<Assignment> assignmentList = new ArrayList<>();

    public AcademicClass() {
    }
    public AcademicClass(Integer classID) {
        this.classID = classID;
    }

    public AcademicClass(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }
}