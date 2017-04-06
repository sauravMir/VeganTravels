package com.vegantravels.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "EXCURSIONS__TMP".
 */
public class Excursions_TMP {

    private Long id;
    private int cruzeId;
    /** Not-null value. */
    private String title;
    /** Not-null value. */
    private String from;
    private String to;
    /** Not-null value. */
    private String time;
    /** Not-null value. */
    private String price;
    private int maxNumberOfGuest;
    private long ExcursionUniqueId;
    private long createdAt;
    private long updatedAt;

    public Excursions_TMP() {
    }

    public Excursions_TMP(Long id) {
        this.id = id;
    }

    public Excursions_TMP(Long id, int cruzeId, String title, String from, String to, String time, String price, int maxNumberOfGuest, long ExcursionUniqueId, long createdAt, long updatedAt) {
        this.id = id;
        this.cruzeId = cruzeId;
        this.title = title;
        this.from = from;
        this.to = to;
        this.time = time;
        this.price = price;
        this.maxNumberOfGuest = maxNumberOfGuest;
        this.ExcursionUniqueId = ExcursionUniqueId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCruzeId() {
        return cruzeId;
    }

    public void setCruzeId(int cruzeId) {
        this.cruzeId = cruzeId;
    }

    /** Not-null value. */
    public String getTitle() {
        return title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Not-null value. */
    public String getFrom() {
        return from;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    /** Not-null value. */
    public String getTime() {
        return time;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTime(String time) {
        this.time = time;
    }

    /** Not-null value. */
    public String getPrice() {
        return price;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setPrice(String price) {
        this.price = price;
    }

    public int getMaxNumberOfGuest() {
        return maxNumberOfGuest;
    }

    public void setMaxNumberOfGuest(int maxNumberOfGuest) {
        this.maxNumberOfGuest = maxNumberOfGuest;
    }

    public long getExcursionUniqueId() {
        return ExcursionUniqueId;
    }

    public void setExcursionUniqueId(long ExcursionUniqueId) {
        this.ExcursionUniqueId = ExcursionUniqueId;
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
