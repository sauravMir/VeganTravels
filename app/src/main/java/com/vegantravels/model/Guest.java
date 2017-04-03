package com.vegantravels.model;

/**
 * Created by Rakib on 4/3/2017.
 */

public class Guest {
    private int guestID;
    private String guestName;
    private String numberOfGuest;

    public Guest(int guestID, String guestName, String numberOfGuest) {
        this.guestID = guestID;
        this.guestName = guestName;
        this.numberOfGuest = numberOfGuest;
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
}
