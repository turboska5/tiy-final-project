package com.andrewrnagel.objgrader.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by rush on 10/3/16.
 */
@Entity
public class Grade {
    @Id
    @GeneratedValue
    @NotNull
    private Integer gradeID;

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
