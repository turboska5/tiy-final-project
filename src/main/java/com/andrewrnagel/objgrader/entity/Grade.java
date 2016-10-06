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
//    @NotNull
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    private AcademicClass academicClass;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Assignment assignment;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Student student;

    private Integer earnedPoints;

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
}
