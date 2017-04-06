package com.vegantravels.model;

/**
 * Created by Rakib on 4/6/2017.
 */

public class XlsModel {
    private String VTID;
    private String firstNameGuestOne;
    private String lastNameGuestOne;
    private String sexGuestOne;
    private String firstNameGuestTwo;
    private String lastNameGuestTwo;
    private String sexGuestTwo;
    private String cabinNo;
    private String guestInCabin;

    public XlsModel(String vtid, String firstNameGuestOne, String lastNameGuestOne, String sexGuestOne, String firstNameGuestTwo, String lastNameGuestTwo, String sexGuestTwo, String cabinNo, String guestInCabin) {
        VTID = vtid;
        this.firstNameGuestOne = firstNameGuestOne;
        this.lastNameGuestOne = lastNameGuestOne;
        this.sexGuestOne = sexGuestOne;
        this.firstNameGuestTwo = firstNameGuestTwo;
        this.lastNameGuestTwo = lastNameGuestTwo;
        this.sexGuestTwo = sexGuestTwo;
        this.cabinNo = cabinNo;
        this.guestInCabin = guestInCabin;
    }

    public XlsModel() {
    }

    public String getVTID() {
        return VTID;
    }

    public void setVTID(String VTID) {
        this.VTID = VTID;
    }

    public String getFirstNameGuestOne() {
        return firstNameGuestOne;
    }

    public void setFirstNameGuestOne(String firstNameGuestOne) {
        this.firstNameGuestOne = firstNameGuestOne;
    }

    public String getLastNameGuestOne() {
        return lastNameGuestOne;
    }

    public void setLastNameGuestOne(String lastNameGuestOne) {
        this.lastNameGuestOne = lastNameGuestOne;
    }

    public String getSexGuestOne() {
        return sexGuestOne;
    }

    public void setSexGuestOne(String sexGuestOne) {
        this.sexGuestOne = sexGuestOne;
    }

    public String getFirstNameGuestTwo() {
        return firstNameGuestTwo;
    }

    public void setFirstNameGuestTwo(String firstNameGuestTwo) {
        this.firstNameGuestTwo = firstNameGuestTwo;
    }

    public String getLastNameGuestTwo() {
        return lastNameGuestTwo;
    }

    public void setLastNameGuestTwo(String lastNameGuestTwo) {
        this.lastNameGuestTwo = lastNameGuestTwo;
    }

    public String getSexGuestTwo() {
        return sexGuestTwo;
    }

    public void setSexGuestTwo(String sexGuestTwo) {
        this.sexGuestTwo = sexGuestTwo;
    }

    public String getCabinNo() {
        return cabinNo;
    }

    public void setCabinNo(String cabinNo) {
        this.cabinNo = cabinNo;
    }

    public String getGuestInCabin() {
        return guestInCabin;
    }

    public void setGuestInCabin(String guestInCabin) {
        this.guestInCabin = guestInCabin;
    }
}
