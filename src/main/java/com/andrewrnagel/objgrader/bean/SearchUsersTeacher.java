package com.andrewrnagel.objgrader.bean;

/**
 * Created by Jimmy and Andrew on 10/18/16.
 */
public class SearchUsersTeacher {
    private String tLastName = null;
    private String tFirstName = null;
    private String tEmail = null;
    private String department = null;

    public SearchUsersTeacher() {
    }

    public SearchUsersTeacher(String tLastName, String tFirstName, String tEmail, String department) {
        this.tLastName = tLastName;
        this.tFirstName = tFirstName;
        this.tEmail = tEmail;
        this.department = department;
    }

    public String gettLastName() {
        return tLastName;
    }
    public String gettLastNameForSearch(){
        return tLastName == null || tLastName.equals("") ? "" : "%" + tLastName + "%";
    }


    public void settLastName(String tLastName) {
        this.tLastName = tLastName;
    }

    public String gettFirstName() {
        return tFirstName;
    }
    public String gettFirstNameForSearch(){
        return tFirstName == null || tFirstName.equals("") ? "" : "%" + tFirstName + "%";
    }


    public void settFirstName(String tFirstName) {
        this.tFirstName = tFirstName;
    }

    public String gettEmail() {
        return tEmail;
    }
    public String gettEmailForSearch(){
        return tEmail == null || tEmail.equals("") ? "" : "%" + tEmail + "%";
    }


    public void settEmail(String tEmail) {
        this.tEmail = tEmail;
    }

    public String getDepartment() {
        return department;
    }
    public String getDepartmentForSearch(){
        return department == null || department.equals("") ? "" : "%" + department + "%";
    }


    public void setDepartment(String department) {
        this.department = department;
    }
}
