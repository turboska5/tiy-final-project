package com.andrewrnagel.objgrader.entity;

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
    @ManyToMany(cascade = CascadeType.ALL)
    @Valid
    @JoinColumn(name = "studentID")
    private List<Student> students = new ArrayList<>();

    public AcademicClass() {
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}