package com.andrewrnagel.objgrader.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Andrew Nagel on 10/12/16 at 4:08 PM EST.
 */

@Entity
public class School {
    @Id
    @GeneratedValue
    private Integer schoolID = 0;

    @NotNull
    @NotEmpty
    @NotBlank
    private String schoolName = "Excelsior High School";

    @NotNull
    @NotEmpty
    @NotBlank
    private String district = "Fake County Public Schools";

    @NotNull
    @NotEmpty
    @NotBlank
    private String address = "1234 Elm Street";

    @NotNull
    @NotEmpty
    @NotBlank
    private String city = "Raleigh";

    @NotNull
    @NotEmpty
    @NotBlank
    private String state = "NC";

    @NotNull
    @NotEmpty
    @NotBlank
    private String zipCode = "27610";

    @NotNull
    @NotEmpty
    @NotBlank
    private String email = "excelsioroffice@fakeschools.org";

    @NotNull
    @NotEmpty
    @NotBlank
    private String phone = "1(919)123-4567";

    private String photo;
}