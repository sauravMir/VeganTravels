package com.vegantravels.model;

import com.google.gson.annotations.SerializedName;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RAFI on 4/3/2017.
 */

public class GuestDetails {
    @SerializedName(StaticAccess.KEY_GUEST_ID)
    public String GuestId;
    @SerializedName(StaticAccess.KEY_GUEST_NAME)
    public String guestName;
    @SerializedName(StaticAccess.KEY_CABIN_NO)
    public String cabinNumber;

    @SerializedName(StaticAccess.KEY_EXCURSION)
    public List<Excursion> excursions = new ArrayList<>();

    @SerializedName(StaticAccess.KEY_NUMBER_GUEST)
    public List<NumberOfGuest> numberOfGuests = new ArrayList<>();

    public class Excursion {

        @SerializedName(StaticAccess.KEY_EXCURSION_ID)
        public String id;

        @SerializedName(StaticAccess.KEY_EXCURSION_NAME)
        public String excursionName;


    }

    public class NumberOfGuest {
        @SerializedName(StaticAccess.KEY_GUEST_NUMBER_ID)
        public String id;

        @SerializedName(StaticAccess.KEY_GUEST_NUMBER_NAME)
        public String guestName;


    }
}
