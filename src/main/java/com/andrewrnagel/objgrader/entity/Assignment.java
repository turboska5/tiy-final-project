package com.andrewrnagel.objgrader.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by Jimmy on 10/3/16.
 */
@Entity
public class Assignment {
    @Id
    @GeneratedValue
    @NotNull
    private Integer assignmentID;

    private String assignmentName;

    //This is the assignment Id number
    private String assignmentIDNumber;

    private LocalDate date;

    private Integer possPoints;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private AcademicClass academicClass = new AcademicClass();

    public Assignment() {
    }

    public Assignment(String assignmentName, String assignmentIDNumber, LocalDate date, Integer possPoints) {
        this.assignmentName = assignmentName;
        this.assignmentIDNumber = assignmentIDNumber;
        this.date = date;
        this.possPoints = possPoints;
    }

    public Integer getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(Integer assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentIDNumber() {
        return assignmentIDNumber;
    }

    public void setAssignmentIDNumber(String assignmentIDNumber) {
        this.assignmentIDNumber = assignmentIDNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPossPoints() {
        return possPoints;
    }

    public void setPossPoints(Integer possPoints) {
        this.possPoints = possPoints;
    }
}
