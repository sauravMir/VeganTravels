package com.vegantravels.model;

/**
 * Created by Rakib on 4/3/2017.
 */

public class Guest {
    private int guestID;
    private String guestName;
    private String numberOfGuest;
    private String excursion;
    private String cabinNo;
    private String paymentStatus;

    public Guest(int guestID, String guestName, String numberOfGuest, String excursion, String cabinNo, String paymentStatus) {
        this.guestID = guestID;
        this.guestName = guestName;
        this.numberOfGuest = numberOfGuest;
        this.excursion = excursion;
        this.cabinNo = cabinNo;
        this.paymentStatus = paymentStatus;
    }

    public Guest() {
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
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
