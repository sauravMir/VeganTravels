package com.vegantravels.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CRIUZES__TMP".
 */
public class Criuzes_TMP {

    private Long id;
    /** Not-null value. */
    private String name;
    /** Not-null value. */
    private String shipName;
    /** Not-null value. */
    private String from;
    private String to;
    private long CruizeUniqueId;
    private Long createdAt;
    private Long updatedAt;

    public Criuzes_TMP() {
    }

    public Criuzes_TMP(Long id) {
        this.id = id;
    }

    public Criuzes_TMP(Long id, String name, String shipName, String from, String to, long CruizeUniqueId, Long createdAt, Long updatedAt) {
        this.id = id;
        this.name = name;
        this.shipName = shipName;
        this.from = from;
        this.to = to;
        this.CruizeUniqueId = CruizeUniqueId;
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
    public String getName() {
        return name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setName(String name) {
        this.name = name;
    }

    /** Not-null value. */
    public String getShipName() {
        return shipName;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setShipName(String shipName) {
        this.shipName = shipName;
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

    public long getCruizeUniqueId() {
        return CruizeUniqueId;
    }

    public void setCruizeUniqueId(long CruizeUniqueId) {
        this.CruizeUniqueId = CruizeUniqueId;
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
