package com.andrewrnagel.objgrader.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:39 PM EST.
 */

@Entity
public class AcademicClass {

    @Id
    @GeneratedValue
    @NotNull
    private Integer classID;

    private String name;
    private String identifier;
    private String department;
    private Integer period;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentID")
    @OrderBy("lastName")
    List<Student> students;

    public AcademicClass() {
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }
}
