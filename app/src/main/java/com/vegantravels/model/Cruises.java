package com.vegantravels.model;

import com.google.gson.annotations.SerializedName;
import com.vegantravels.utilities.StaticAccess;

/**
 * Created by Rakib on 4/3/2017.
 */

public class Cruises {

    @SerializedName(StaticAccess.KEY_CRUISES_ID)
    private String cruiseID;
    @SerializedName(StaticAccess.KEY_CRUISES_NAME)
    private String cruiseName;
    @SerializedName(StaticAccess.KEY_SHIP_NAME)
    private String shipName;
    @SerializedName(StaticAccess.KEY_DATE_FROM)
    private String dateFrom;
    @SerializedName(StaticAccess.KEY_DATE_TO)
    private String dateTo;

    public Cruises(String cruiseID, String cruiseName, String shipName, String dateFrom, String dateTo) {
        this.cruiseID = cruiseID;
        this.cruiseName = cruiseName;
        this.shipName = shipName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Cruises(String cruiseName, String shipName, String dateFrom, String dateTo) {
        this.cruiseName = cruiseName;
        this.shipName = shipName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Cruises() {
    }

    public String getCruiseID() {
        return cruiseID;
    }

    public void setCruiseID(String cruiseID) {
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

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
