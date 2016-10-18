package com.andrewrnagel.objgrader.bean;

/**
 * Created by Jimmy and Andrew on 10/18/16.
 */
public class SearchUsersStudent {
    private String sLastName = null;
    private String sFirstName = null;
    private String sEmail = null;
    private String sID = null;
    private Integer grade = null;

    public SearchUsersStudent() {
    }

    public SearchUsersStudent(String sLastName, String sFirstName, String sEmail, String sID, Integer grade) {
        this.sLastName = sLastName;
        this.sFirstName = sFirstName;
        this.sEmail = sEmail;
        this.sID = sID;
        this.grade = grade;
    }

    public String getsLastName() {
        return sLastName;
    }
    public String getsLastNameForSearch(){
        return sLastName == null || sLastName.equals("") ? "" : "%" + sLastName + "%";
    }

    public void setsLastName(String sLastName) {
        this.sLastName = sLastName;
    }

    public String getsFirstName() {
        return sFirstName;
    }
    public String getsFirstNameForSearch(){
        return sFirstName == null || sFirstName.equals("") ? "" : "%" + sFirstName + "%";
    }

    public void setsFirstName(String sFirstName) {
        this.sFirstName = sFirstName;
    }

    public String getsEmail() {
        return sEmail;
    }
    public String getsEmailForSearch(){
        return sEmail == null || sEmail.equals("") ? "" : "%" + sEmail + "%";
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsID() {
        return sID;
    }
    public String getsIDForSearch(){
        return sID == null || sID.equals("") ? "" : "%" + sID + "%";
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
