package com.andrewrnagel.objgrader.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Jimmy on 10/3/16.
 */
@Entity
public class Grade {
    @Id
    @GeneratedValue
    private Integer gradeID = 0;

    //TODO
    @ManyToOne
    private AcademicClass academicClass;

//    (cascade = CascadeType.PERSIST)
    @ManyToOne
    private Assignment assignment;

    @NotNull
    @ManyToOne
    private Student student;

    private Integer earnedPoints;

    private Integer possPoints;

    private String dateCreated;

    private String dateModified;

    public Grade() {
    }

    public Grade(AcademicClass academicClass, Assignment assignment) {
        this.academicClass = academicClass;
        this.assignment = assignment;
    }

    public Grade(AcademicClass academicClass, Assignment assignment, Student student) {
        this.academicClass = academicClass;
        this.assignment = assignment;
        this.student = student;
    }

    public Grade(AcademicClass academicClass, Student student) {
        this.academicClass = academicClass;
        this.student = student;
    }

    public Grade(AcademicClass academicClass, Assignment assignment, Student student, Integer earnedPoints, Integer possPoints, String dateCreated, String dateModifided) {
        this.academicClass = academicClass;
        this.assignment = assignment;
        this.student = student;
        this.earnedPoints = earnedPoints;
        this.possPoints = possPoints;
        this.dateCreated = dateCreated;
        this.dateModified = dateModifided;
    }

    public Grade(Integer earnedPoints, Integer possPoints) {
        this.earnedPoints = earnedPoints;
        this.possPoints = possPoints;
    }

    public Grade(Integer possPoints) {
        this.possPoints = possPoints;
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
}