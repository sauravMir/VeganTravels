package com.vegantravels.model;

/**
 * Created by Rakib on 4/3/2017.
 */

public class Cruises {
    private int cruiseID;
    private String cruiseName;
    private String shipName;
    private String date;
    private String time;

    public Cruises(int cruiseID, String cruiseName, String shipName, String date, String time) {
        this.cruiseID = cruiseID;
        this.cruiseName = cruiseName;
        this.shipName = shipName;
        this.date = date;
        this.time = time;
    }

    public Cruises(String cruiseName, String shipName, String date, String time) {
        this.cruiseName = cruiseName;
        this.shipName = shipName;
        this.date = date;
        this.time = time;
    }

    public Cruises() {
    }

    public int getCruiseID() {
        return cruiseID;
    }

    public void setCruiseID(int cruiseID) {
        this.cruiseID = cruiseID;
    }

    public String getCruiseName() {
        return cruiseName;
    }

    public void setCruiseName(String cruiseName) {
        this.cruiseName = cruiseName;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
