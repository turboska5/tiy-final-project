package com.andrewrnagel.objgrader.bean;

/**
 * Created by Jimmy and Andrew on 10/18/16.
 */

public class SearchUsersAdmin {
    private String lastName = null;
    private String firstName = null;
    private String email = null;
    private String title = null;

    public SearchUsersAdmin() {}

    public SearchUsersAdmin(String lastName, String firstName, String email, String title) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.title = title;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLastNameForSearch(){
        return lastName == null || lastName.equals("") ? "" : "%" + lastName + "%";
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFirstNameForSearch(){
        return firstName == null || firstName.equals("") ? "" : "%" + firstName + "%";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailForSearch(){
        return email == null || email.equals("") ? "" : "%" + email + "%";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleForSearch(){
        return title == null || title.equals("") ? "" : "%" + title + "%";
    }

    public void setTitle(String title) {
        this.title = title;
    }
}