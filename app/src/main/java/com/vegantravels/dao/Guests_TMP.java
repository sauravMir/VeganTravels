package com.vegantravels.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "GUESTS__TMP".
 */
public class Guests_TMP {

    private Long id;
    private int guestId;
    /** Not-null value. */
    private String fname;
    /** Not-null value. */
    private String lName;
    private long createdAt;
    private long updatedAt;

    public Guests_TMP() {
    }

    public Guests_TMP(Long id) {
        this.id = id;
    }

    public Guests_TMP(Long id, int guestId, String fname, String lName, long createdAt, long updatedAt) {
        this.id = id;
        this.guestId = guestId;
        this.fname = fname;
        this.lName = lName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    /** Not-null value. */
    public String getFname() {
        return fname;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /** Not-null value. */
    public String getLName() {
        return lName;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLName(String lName) {
        this.lName = lName;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

}
