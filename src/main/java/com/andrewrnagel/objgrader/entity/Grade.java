package com.andrewrnagel.objgrader.entity;

import org.apache.tomcat.jni.Local;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Jimmy on 10/3/16.
 */

@Entity
public class Grade {
    @Id
    @GeneratedValue
    private Integer gradeID = 0;

    @ManyToOne(cascade = CascadeType.ALL)
    private AcademicClass academicClass;

    @ManyToOne(cascade = CascadeType.ALL)
    private Assignment assignment;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    private Integer earnedPoints;

    private Integer possPoints;

    private LocalDate dateCreated;

    private LocalDate dateModified;

    public Grade() {
        this.dateCreated = LocalDate.now();
    }

    public Grade(Integer possPoints) {
        this.possPoints = possPoints;
    }

    public Grade(AcademicClass academicClass, Assignment assignment) {
        this.academicClass = academicClass;
        this.assignment = assignment;
    }

    public Grade(AcademicClass academicClass, Student student) {
        this.academicClass = academicClass;
        this.student = student;
    }

    public Grade(Integer earnedPoints, Integer possPoints) {
        this.earnedPoints = earnedPoints;
        this.possPoints = possPoints;
    }

    public Grade(AcademicClass academicClass, Assignment assignment, Student student) {
        this.academicClass = academicClass;
        this.assignment = assignment;
        this.student = student;
    }

    public Grade(AcademicClass academicClass, Assignment assignment, Student student, Integer earnedPoints, Integer possPoints, String dateCreated, String dateModified) {
        this.academicClass = academicClass;
        this.assignment = assignment;
        this.student = student;
        this.earnedPoints = earnedPoints;
        this.possPoints = possPoints;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dateCreated = LocalDate.parse(dateCreated, formatter);
        this.dateModified = LocalDate.parse(dateModified, formatter);
    }

    public Integer getGradeID() {
        return gradeID;
    }

    public void setGradeID(Integer gradeID) {
        this.gradeID = gradeID;
    }

    public Integer getEarnedPoints() {
        return earnedPoints;
    }

    public void setEarnedPoints(Integer earnedPoints) {
        this.earnedPoints = earnedPoints;
    }

    public AcademicClass getAcademicClass() {
        return academicClass;
    }

    public void setAcademicClass(AcademicClass academicClass) {
        this.academicClass = academicClass;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getPossPoints() {
        return possPoints;
    }

    public void setPossPoints(Integer possPoints) {
        this.possPoints = possPoints;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }
}