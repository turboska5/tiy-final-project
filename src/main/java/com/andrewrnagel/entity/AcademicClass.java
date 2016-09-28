package com.andrewrnagel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:39 PM EST.
 */

@Entity
public class AcademicClass {

    @Id
    @GeneratedValue
    @Column(name = "ClassID", nullable = false, unique = true)
    private int classID;

}
