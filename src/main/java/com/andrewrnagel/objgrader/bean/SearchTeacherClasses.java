package com.andrewrnagel.objgrader.bean;

/**
 * Created by doug on 9/21/16.
 * Modified by Andrew and Jimmy on 10/14/16.
 */

public class SearchTeacherClasses {

    private Integer period = null;
    private String name = null;
    private String identifier = null;

    public SearchTeacherClasses() {
    }

    public SearchTeacherClasses(Integer period, String name, String identifier) {
        this.period = period;
        this.name = name;
        this.identifier = identifier;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public String getNameForSearch(){
        return name == null || name.equals("") ? "" : "%" + name + "%";
    }

    public void setName(String name) {
        // if the provided name is "" then set to null
        this.name = (name == null || name.equals("") ? null : name);
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getIdentifierForSearch(){
        return identifier == null || identifier.equals("") ? "" : "%" + identifier + "%";
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}