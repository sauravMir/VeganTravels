package com.vegantravels.model;

import com.google.gson.annotations.SerializedName;
import com.vegantravels.utilities.StaticAccess;

/**
 * Created by Rakib on 4/3/2017.
 */

public class Guest {
    @SerializedName(StaticAccess.KEY_GUEST_ID)
    private String guestID;
    @SerializedName(StaticAccess.KEY_GUEST_Name)
    private String guestName;
    @SerializedName(StaticAccess.KEY_NUMBER_GUEST)
    private String numberOfGuest;
    @SerializedName(StaticAccess.KEY_EXCURSION)
    private String excursion;
    @SerializedName(StaticAccess.KEY_CABIN_NO)
    private String cabinNo;
    @SerializedName(StaticAccess.KEY_PAYMENT_STATUS)
    private String paymentStatus;
    @SerializedName(StaticAccess.KEY_CRUISES_ID)
    private String cruiseID;

    public Guest(String guestID, String cruiseID, String guestName, String numberOfGuest, String excursion, String cabinNo, String paymentStatus) {
        this.guestID = guestID;
        this.guestName = guestName;
        this.numberOfGuest = numberOfGuest;
        this.excursion = excursion;
        this.cabinNo = cabinNo;
        this.paymentStatus = paymentStatus;
        this.cruiseID = cruiseID;
    }

    public Guest() {
    }

    public String getCruiseID() {
        return cruiseID;
    }

    public void setCruiseID(String cruiseID) {
        this.cruiseID = cruiseID;
    }

    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getNumberOfGuest() {
        return numberOfGuest;
    }

    public void setNumberOfGuest(String numberOfGuest) {
        this.numberOfGuest = numberOfGuest;
    }

    public String getExcursion() {
        return excursion;
    }

    public void setExcursion(String excursion) {
        this.excursion = excursion;
    }

    public String getCabinNo() {
        return cabinNo;
    }

    public void setCabinNo(String cabinNo) {
        this.cabinNo = cabinNo;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
