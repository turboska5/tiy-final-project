package com.andrewrnagel.objgrader.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Andrew Nagel on 10/13/16 at 7:46 AM EST.
 */

@Entity
public class Photo {
    @Id
    @GeneratedValue
    private Integer photoID = 0;

    private String fileName;

    private byte[] data;

    public Integer getPhotoID() {
        return photoID;
    }

    public void setPhotoID(Integer photoID) {
        this.photoID = photoID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}