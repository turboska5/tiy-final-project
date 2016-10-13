package com.andrewrnagel.objgrader.entity;

import javax.persistence.*;

/**
 * Created by Andrew Nagel on 10/13/16 at 7:46 AM EST.
 */

@Entity
public class Photo {
    @Id
    @GeneratedValue
    private Integer photoID = 0;

    @Lob
    @Basic(fetch=FetchType.EAGER)
    private byte[] data;

    private String contentType;

    public Photo() {
    }

    public Integer getPhotoID() {
        return photoID;
    }

    public void setPhotoID(Integer photoID) {
        this.photoID = photoID;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}