package com.andrewrnagel.objgrader.entity;

import org.hibernate.annotations.Formula;
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

    private String note;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate date;

    //number of students with non-null scores (completed)
    @Formula("(SELECT COUNT(g.student_studentid) FROM Grade AS g WHERE g.assignment_assignmentid = assignmentID AND g.earned_points IS NOT NULL)")
    private Integer studentsWithGrade=0;

    //sum of student points with non-null scores (scored)
    @Formula("(SELECT SUM(g.earned_points) FROM Grade AS g WHERE g.assignment_assignmentid = assignmentID AND g.earned_points IS NOT NULL)")
    private Integer sumStudentEarnedPointsWithGrade=0;

    //average of student points with non-null scores (class average for this assignment)
    @Formula("(sumStudentEarnedPointsWithGrade/studentsWithGrade)*100.0")
    private Double average=0.0;

    public Assignment() {
    }

    public Assignment(String assignmentName, String assignmentIDNumber) {
        this.assignmentName = assignmentName;
        this.assignmentIDNumber = assignmentIDNumber;
    }

    public Assignment(String assignmentName, String assignmentIDNumber, LocalDate date) {
        this.assignmentName = assignmentName;
        this.assignmentIDNumber = assignmentIDNumber;
        this.date = date;
    }

    public Assignment(String assignmentName, String assignmentIDNumber, LocalDate date, String note) {
        this.assignmentName = assignmentName;
        this.assignmentIDNumber = assignmentIDNumber;
        this.date = date;
        this.note = note;
    }

    public Assignment(String assignmentName, String assignmentIDNumber, String date) {
        this.assignmentName = assignmentName;
        this.assignmentIDNumber = assignmentIDNumber;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);
    }

    public Assignment(String assignmentName, String assignmentIDNumber, String date, String note) {
        this.assignmentName = assignmentName;
        this.assignmentIDNumber = assignmentIDNumber;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}