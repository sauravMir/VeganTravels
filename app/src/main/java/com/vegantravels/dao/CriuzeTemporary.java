package com.vegantravels.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CRIUZE_TEMPORARY".
 */
public class CriuzeTemporary {

    private Long id;
    /** Not-null value. */
    private String cruizeName;
    /** Not-null value. */
    private String shipName;
    /** Not-null value. */
    private java.util.Date dateFrom;
    /** Not-null value. */
    private java.util.Date dateTo;
    private long cruizeKey;

    public CriuzeTemporary() {
    }

    public CriuzeTemporary(Long id) {
        this.id = id;
    }

    public CriuzeTemporary(Long id, String cruizeName, String shipName, java.util.Date dateFrom, java.util.Date dateTo, long cruizeKey) {
        this.id = id;
        this.cruizeName = cruizeName;
        this.shipName = shipName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.cruizeKey = cruizeKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getCruizeName() {
        return cruizeName;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCruizeName(String cruizeName) {
        this.cruizeName = cruizeName;
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
    public java.util.Date getDateFrom() {
        return dateFrom;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setDateFrom(java.util.Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /** Not-null value. */
    public java.util.Date getDateTo() {
        return dateTo;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setDateTo(java.util.Date dateTo) {
        this.dateTo = dateTo;
    }

    public long getCruizeKey() {
        return cruizeKey;
    }

    public void setCruizeKey(long cruizeKey) {
        this.cruizeKey = cruizeKey;
    }

}
