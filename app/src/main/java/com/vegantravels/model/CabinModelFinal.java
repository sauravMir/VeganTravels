package com.vegantravels.model;

import java.util.ArrayList;

/**
 * Created by Macbook on 10/04/2017.
 */

public class CabinModelFinal {
    int CabinNum;
    String FName;
    String LName;
    ArrayList<String> ExcursionName=new ArrayList<>();
    ArrayList<String> ExcursionDate=new ArrayList<>();
    ArrayList<Float>  ExcursionPrice=new ArrayList<>();
    ArrayList<Integer> People=new ArrayList<>();
    ArrayList<Integer>  Status=new ArrayList<>();
    String VTId;


    public ArrayList<Float>  getExcursionPrice() {
        return ExcursionPrice;
    }
    public void setExcursionPrice(Float excursionPrice) {
        ExcursionPrice.add(excursionPrice);
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

    public ArrayList<String> getExcursionName() {
        return ExcursionName;
    }

    public void setExcursionName(String excursionName) {
        ExcursionName.add(excursionName);
    }

    public ArrayList<String> getExcursionDate() {
        return ExcursionDate;
    }

    public void setExcursionDate(String excursionDate) {
        ExcursionDate.add(excursionDate);
    }

    public ArrayList<Integer> getPeople() {
        return People;
    }

    public void setPeople(int people) {
        this.People.add(people);
    }

    public ArrayList<Integer> getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status.add(status);
    }


}
