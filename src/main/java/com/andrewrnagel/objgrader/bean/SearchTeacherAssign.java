package com.andrewrnagel.objgrader.bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Andrew on 10/14/16.
 */

public class SearchTeacherAssign {

    private Integer aPeriod = null;
    private String aName = null;
    private String aID = null;
    private String aDate = null;
    private Integer aPoints = null;

    public SearchTeacherAssign() {
    }

    public SearchTeacherAssign(Integer aPeriod, String aName, String aID, String aDate, Integer aPoints) {
        this.aPeriod = aPeriod;
        this.aName = aName;
        this.aID = aID;
        this.aDate = aDate;
        this.aPoints = aPoints;
    }

    public Integer getaPeriod() {
        return aPeriod;
    }

    public void setaPeriod(Integer aPeriod) {
        this.aPeriod = aPeriod;
    }

    public String getaName() {
        return aName;
    }

    public String getaNameForSearch(){
        return aName == null || aName.equals("") ? "" : "%" + aName + "%";
    }

    public void setaName(String aName) {
        // if the provided name is "" then set to null
        this.aName = (aName == null || aName.equals("") ? null : aName);
    }

    public String getaID() {
        return aID;
    }

    public String getaIDForSearch(){
        return aID == null || aID.equals("") ? "" : "%" + aID + "%";
    }

    public void setaID(String aID) {
        this.aID = aID;
    }

    public String getaDate() {
        return aDate;
    }

    public LocalDate getaDateForSearch(){
        LocalDate aDateConverted = null;
        if(!(this.aDate == null) && !(this.aDate.equals(""))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            aDateConverted = LocalDate.parse(this.getaDate(), formatter);
        }
        return aDateConverted;
    }

    public void setaDate(String aDate) {
        // if the provided name is "" then set to null
        this.aName = (aName == null || aName.equals("") ? null : aName);
    }

    public Integer getaPoints() {
        return aPoints;
    }

    public void setaPoints(Integer aPoints) {
        this.aPoints = aPoints;
    }
}