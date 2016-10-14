package com.andrewrnagel.objgrader.bean;

/**
 * Created by Andrew on 10/14/16.
 */

public class SearchTeacherStudents {

    private Integer sPeriod = null;
    private String sLastName = null;
    private String sFirstName = null;
    private String sAName = null;
    private String sAID = null;

    public SearchTeacherStudents() {
    }

    public SearchTeacherStudents(Integer sPeriod, String sLastName, String sFirstName, String sAName, String sAID) {
        this.sPeriod = sPeriod;
        this.sLastName = sLastName;
        this.sFirstName = sFirstName;
        this.sAName = sAName;
        this.sAID = sAID;
    }

    public Integer getsPeriod() {
        return sPeriod;
    }

    public void setsPeriod(Integer sPeriod) {
        this.sPeriod = sPeriod;
    }

    public String getsLastName() {
        return sLastName;
    }

    public String getsLastNameForSearch(){
        return sLastName == null || sLastName.equals("") ? "" : "%" + sLastName + "%";
    }

    public void setsLastName(String sLastName) {
        // if the provided name is "" then set to null
        this.sLastName = (sLastName == null || sLastName.equals("") ? null : sLastName);
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

    public String getsAName() {
        return sAName;
    }

    public String getsANameForSearch(){
        return sAName == null || sAName.equals("") ? "" : "%" + sAName + "%";
    }

    public void setsAName(String sAName) {
        // if the provided name is "" then set to null
        this.sAName = (sAName == null || sAName.equals("") ? null : sAName);
    }

    public String getsAID() {
        return sAID;
    }

    public String getsAIDForSearch(){
        return sAID == null || sAID.equals("") ? "" : "%" + sAID + "%";
    }

    public void setsAID(String sAID) {
        this.sAID = sAID;
    }
}