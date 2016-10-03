package com.andrewrnagel.objgrader.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:39 PM EST.
 */

@Entity
public class AcademicClass {

    @Id
    @GeneratedValue
    @NotNull
    private Integer classID;

    public AcademicClass() {
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }
}
