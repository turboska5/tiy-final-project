package com.andrewrnagel.objgrader.entity;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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

    @NotBlank
    @NotNull
    private String assignmentIDNumber;

    private String note;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate date = LocalDate.now();

    //number of students with non-null scores (completed)
    @Formula("(SELECT COUNT(g.student_studentid) FROM Grade AS g WHERE g.assignment_assignmentid = assignmentID AND g.earned_points IS NOT NULL)")
    private Integer studentsWithGrade=0;

    //sum of student points with non-null scores (scored)
    @Formula("(SELECT SUM(g.earned_points) FROM Grade AS g WHERE g.assignment_assignmentid = assignmentID AND g.earned_points IS NOT NULL)")
    private Double sumStudentEarnedPointsWithGrade=0.0;

    //number of students enrolled
    @Formula("(SELECT COUNT(g.student_studentid) FROM Grade AS g WHERE g.assignment_assignmentid = assignmentID AND g.student_studentID IS NOT NULL)")
    private Integer studentsAssigned=0;

    //possible points
    @Formula("(SELECT (g.poss_points) FROM Grade AS g WHERE g.assignment_assignmentid = assignmentID AND g.student_studentID IS NULL)")
    private Integer thisPossiblePts=0;

    //average of student points with non-null scores (class average for this assignment)
    @Transient
    private Double average=0.0;

    //number of students with a grade versus total assigned
    @Transient
    private Double submissionRate=0.0;

    public Assignment() {
    }

    public Assignment(String assignmentName, String assignmentIDNumber) {
        this.assignmentName = assignmentName;
        this.assignmentIDNumber = assignmentIDNumber;
    }

    public Assignment(String assignmentName, String assignmentIDNumber, String date) {
        this.assignmentName = assignmentName;
        this.assignmentIDNumber = assignmentIDNumber;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);
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

    public Integer getStudentsWithGrade() {
        return studentsWithGrade;
    }

    public void setStudentsWithGrade(Integer studentsWithGrade) {
        this.studentsWithGrade = studentsWithGrade;
    }

    public Double getSumStudentEarnedPointsWithGrade() {
        return sumStudentEarnedPointsWithGrade;
    }

    public void setSumStudentEarnedPointsWithGrade(Double sumStudentEarnedPointsWithGrade) {
        this.sumStudentEarnedPointsWithGrade = sumStudentEarnedPointsWithGrade;
    }

    public String getAverage() {
        if(studentsWithGrade.equals(0) || studentsWithGrade.equals(null)) {
            return "0.00";
        } else {
            Double average = ((sumStudentEarnedPointsWithGrade / (studentsWithGrade*thisPossiblePts))*100.0);
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            return numberFormat.format(average);
        }
    }

    public void setAverage(Double submissionRate) {
        this.average = average;
    }

    public String getSubmissionRate() {
        if(studentsAssigned.equals(0) || studentsAssigned.equals(null)) {
            return "0.00";
        } else {
            Double subRate = (((double)studentsWithGrade / studentsAssigned) * 100.0);
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            return numberFormat.format(subRate);
        }
    }

    public void setSubmissionRate(Double submissionRate) {
        this.submissionRate = submissionRate;
    }

    public String getDateAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        return this.date.format(formatter);
    }

    public Integer getStudentsAssigned() {
        return studentsAssigned;
    }

    public void setStudentsAssigned(Integer studentsAssigned) {
        this.studentsAssigned = studentsAssigned;
    }

    public Integer getThisPossiblePts() {
        return thisPossiblePts;
    }

    public void setThisPossiblePts(Integer thisPossiblePts) {
        this.thisPossiblePts = thisPossiblePts;
    }
}