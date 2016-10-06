package com.andrewrnagel.objgrader.entity;

import org.hibernate.validator.constraints.NotEmpty;

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
    @NotNull
    @NotEmpty
    @ManyToOne(cascade = CascadeType.PERSIST)
    private AcademicClass academicClass;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Assignment assignment;

    @NotNull
    @NotEmpty
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Student student;

    private Integer earnedPoints;

    private Integer possiblePoints;

    private String dateCreated;

    private String dateModifided;

    public Grade() {
    }

    public Grade(Integer earnedPoints) {
        this.earnedPoints = earnedPoints;
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

    public Integer getPossiblePoints() {
        return possiblePoints;
    }

    public void setPossiblePoints(Integer possiblePoints) {
        this.possiblePoints = possiblePoints;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModifided() {
        return dateModifided;
    }

    public void setDateModifided(String dateModifided) {
        this.dateModifided = dateModifided;
    }
}
