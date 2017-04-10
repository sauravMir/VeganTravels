package com.vegantravels.model;

import com.vegantravels.dao.Cabins_TMP;

/**
 * Created by Rakib on 4/10/2017.
 */

public class GuestExport {
    private String firstName;
    private String lastName;
    private Cabins_TMP cabins_tmp;

    public GuestExport(String firstName, String lastName, Cabins_TMP cabins_tmp) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cabins_tmp = cabins_tmp;
    }

    public GuestExport() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Cabins_TMP getCabins_tmp() {
        return cabins_tmp;
    }

    public void setCabins_tmp(Cabins_TMP cabins_tmp) {
        this.cabins_tmp = cabins_tmp;
    }
}
