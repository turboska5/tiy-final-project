package com.andrewrnagel.objgrader.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Andrew Nagel on 10/3/16 at 11:24 AM EST.
 */
public class Teacher {
    @Id
    @GeneratedValue
    @NotNull
    private Integer teacherID;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private LocalDate hireDate;
    private String department;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacherID")
    @OrderBy("period")
    List<AcademicClass> teacherClasses;
}