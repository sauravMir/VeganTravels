package com.vegantravels.model;

/**
 * Created by RAFI on 4/10/2017.
 */

public class CabinModel {
    int CabinNum;
    String FName;
    String LName;
    String ExcursionName;
    String ExcursionDate;
    String PricePerPeople;
    int People;
    int Status;
    String VTId;

    public String getPricePerPeople() {
        return PricePerPeople;
    }
    

    public void setPricePerPeople(String pricePerPeople) {
        this.PricePerPeople = pricePerPeople;
    }


    public String getVTId() {
        return VTId;
    }

    public void setVTId(String VTId) {
        this.VTId = VTId;
    }

    public int getCabinNum() {
        return CabinNum;
    }

    public void setCabinNum(int cabinNum) {
        CabinNum = cabinNum;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getExcursionName() {
        return ExcursionName;
    }

    public void setExcursionName(String excursionName) {
        ExcursionName = excursionName;
    }

    public String getExcursionDate() {
        return ExcursionDate;
    }

    public void setExcursionDate(String excursionDate) {
        ExcursionDate = excursionDate;
    }

    public int getPeople() {
        return People;
    }

    public void setPeople(int people) {
        this.People = people;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }


}
