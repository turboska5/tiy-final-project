package com.andrewrnagel.objgrader.entity;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
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

    @ManyToOne
    @Valid
    @NotNull
    private AcademicClass academicClass;

    @ManyToOne
    @Valid
    private Assignment assignment;

    @ManyToOne
    private Student student;

    private Double earnedPoints;

    @NotNull
    @Min(0)
    private Double possPoints;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateCreated = LocalDate.now();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateModified;

    @Transient
    private String studentClassAverage;

    public Grade() {
    }

    public Grade(Double possPoints) {
        this.possPoints = possPoints;
    }

    public Grade(Double earnedPoints, Double possPoints) {
        this.earnedPoints = earnedPoints;
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

    public Grade(AcademicClass academicClass, Assignment assignment, Student student) {
        this.academicClass = academicClass;
        this.assignment = assignment;
        this.student = student;
    }

    public Grade(AcademicClass academicClass, Assignment assignment, Student student, Double earnedPoints, Double possPoints, String dateCreated, String dateModified) {
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

    public Double getEarnedPoints() {
        return earnedPoints;
    }

    public void setEarnedPoints(Double earnedPoints) {
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

    public Double getPossPoints() {
        return possPoints;
    }

    public void setPossPoints(Double possPoints) {
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

    public String getStudentClassAverage() {
        return studentClassAverage;
    }

    public void setStudentClassAverage(String studentClassAverage) {
        this.studentClassAverage = studentClassAverage;
    }
}