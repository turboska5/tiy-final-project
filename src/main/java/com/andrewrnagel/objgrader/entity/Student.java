package com.andrewrnagel.objgrader.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Andrew Nagel on 10/3/16 at 11:24 AM EST.
 */
public class Student {
    @Id
    @GeneratedValue
    @NotNull
    private Integer studentID;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String studentNumber;
    private Integer gradeLevel;
}
