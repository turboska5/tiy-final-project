package com.andrewrnagel.objgrader.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by Andrew Nagel on 10/3/16 at 11:24 AM EST.
 */

public class Admin {
    @Id
    @GeneratedValue
    @NotNull
    private Integer adminID;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private LocalDate hireDate;
    private String title;
}