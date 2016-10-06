package com.andrewrnagel.objgrader.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 10/3/16.
 */
@Entity
public class Assignment {
    @Id
    @GeneratedValue
    private Integer assignmentID = 0;
    @NotBlank
    @NotNull
    private String assignmentName;

    //This is the assignment Id number
    @NotBlank
    @NotNull
    private String assignmentIDNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate date;

    @NotNull
    private Integer possPoints;

    @NotNull
    private Integer period;

    private String note;

    //TODO
//    private List<Grade> gradeList = new ArrayList<>();

    public Assignment() {
    }

    public Assignment(String assignmentName, String assignmentIDNumber, LocalDate date, Integer possPoints, Integer period) {
        this.assignmentName = assignmentName;
        this.assignmentIDNumber = assignmentIDNumber;
        this.date = date;
        this.possPoints = possPoints;
        this.period = period;
    }

    public Assignment(String assignmentName, String assignmentIDNumber, String date, Integer possPoints, Integer period) {
        this.assignmentName = assignmentName;
        this.assignmentIDNumber = assignmentIDNumber;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);
        this.possPoints = possPoints;
        this.period = period;
    }

    public Assignment(String assignmentName, String assignmentIDNumber, Integer possPoints, Integer period) {
        this.assignmentName = assignmentName;
        this.assignmentIDNumber = assignmentIDNumber;
        this.possPoints = possPoints;
        this.period = period;
    }

    public Assignment(String assignmentName, String assignmentIDNumber, LocalDate date, Integer possPoints, Integer period, String note) {
        this.assignmentName = assignmentName;
        this.assignmentIDNumber = assignmentIDNumber;
        this.date = date;
        this.possPoints = possPoints;
        this.period = period;
        this.note = note;
    }

    public Assignment(String assignmentName, String assignmentIDNumber, String date, Integer possPoints, Integer period, String note) {
        this.assignmentName = assignmentName;
        this.assignmentIDNumber = assignmentIDNumber;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);
        this.possPoints = possPoints;
        this.period = period;
        this.note = note;
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

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
