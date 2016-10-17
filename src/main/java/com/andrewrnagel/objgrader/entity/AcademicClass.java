package com.andrewrnagel.objgrader.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:39 PM EST.
 */

@Entity
public class AcademicClass {
    @Id
    @GeneratedValue
    private Integer classID = 0;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String identifier;

    @NotBlank
    @NotNull
    private String department;

    @NotNull
    private Integer period;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacherID")
    @JsonIgnore
    @Valid
    private Teacher teacher;

    @NotNull
    private Integer capacity=0;

    @Formula("(SELECT COUNT(distinct g.student_studentid) FROM Grade AS g WHERE g.academic_class_classid = classID)")
    private Integer studentNumber=0;

    @Formula("(SELECT COUNT(distinct g.assignment_assignmentid) FROM Grade AS g WHERE g.academic_class_classid = classID)")
    private Integer assignmentNumber=0;

    @Formula("(SELECT SUM(coalesce(g.earned_points, 0)) FROM Grade AS g WHERE g.academic_class_classid = classID AND g.earned_points IS NOT NULL)")
    private Double classSumEarnedPoints=0.0;

    @Formula("(SELECT COALESCE(SUM(g.poss_points), 0) FROM Grade AS g WHERE g.academic_class_classid = classID AND g.earned_points IS NOT NULL)")
    private Double classSumPossPoints=0.0;

    @Transient
    private Double classAverage=0.0;

    public AcademicClass() {
    }

    public AcademicClass(Integer classID) {
        this.classID = classID;
    }

    public AcademicClass(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getAssignmentNumber() {
        return assignmentNumber;
    }

    public void setAssignmentNumber(Integer assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }

    public String getClassAverage() {
        if(classSumPossPoints == null || classSumPossPoints.equals(0) ||
                 classSumEarnedPoints == null || classSumEarnedPoints.equals(0)) {
            return "0.00";
        } else {
            Double average = (classSumEarnedPoints / classSumPossPoints)*100.0;
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            return numberFormat.format(average);
        }
    }

    public void setClassAverage(Double classAverage) {
        this.classAverage = classAverage;
    }

    public Double getClassSumEarnedPoints() {
        return classSumEarnedPoints;
    }

    public void setClassSumEarnedPoints(Double classSumEarnedPoints) {
        this.classSumEarnedPoints = classSumEarnedPoints;
    }

    public Double getClassSumPossPoints() {
        return classSumPossPoints;
    }

    public void setClassSumPossPoints(Double classSumPossPoints) {
        this.classSumPossPoints = classSumPossPoints;
    }
}