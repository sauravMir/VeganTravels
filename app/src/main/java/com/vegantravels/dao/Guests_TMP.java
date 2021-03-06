package com.vegantravels.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "GUESTS__TMP".
 */
public class Guests_TMP {

    private Long id;
    /** Not-null value. */
    private String guestVT_Id;
    /** Not-null value. */
    private String fname;
    /** Not-null value. */
    private String lName;
    private int cabinNumber;
    private long GuestUniqueId;
    private Long createdAt;
    private Long updatedAt;

    public Guests_TMP() {
    }

    public Guests_TMP(Long id) {
        this.id = id;
    }

    public Guests_TMP(Long id, String guestVT_Id, String fname, String lName, int cabinNumber, long GuestUniqueId, Long createdAt, Long updatedAt) {
        this.id = id;
        this.guestVT_Id = guestVT_Id;
        this.fname = fname;
        this.lName = lName;
        this.cabinNumber = cabinNumber;
        this.GuestUniqueId = GuestUniqueId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getGuestVT_Id() {
        return guestVT_Id;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setGuestVT_Id(String guestVT_Id) {
        this.guestVT_Id = guestVT_Id;
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

    public int getCabinNumber() {
        return cabinNumber;
    }

    public void setCabinNumber(int cabinNumber) {
        this.cabinNumber = cabinNumber;
    }

    public long getGuestUniqueId() {
        return GuestUniqueId;
    }

    public void setGuestUniqueId(long GuestUniqueId) {
        this.GuestUniqueId = GuestUniqueId;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

}
