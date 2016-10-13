package com.andrewrnagel.objgrader.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    private String phone = "(919)123-4567";

    @OneToOne
    private Photo photo;

    public Integer getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(Integer schoolID) {
        this.schoolID = schoolID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}