package com.vegantravels;

/**
 * Created by RAFI on 4/3/2017.
 */

public class StaticAccess {
    // Cruises Keys
    public static final String KEY_CRUISES_ID = "id";
    public static final String KEY_CRUISES_JSON_ARRAY = "cruizes";
    public static final String KEY_CRUISES_NAME = "name";
    public static final String KEY_SHIP_NAME = "ship_name";
    public static final String KEY_DATE_FROM = "from";
    public static final String KEY_DATE_TO = "to";


    /// allt he fields of database
    public static final String Table_CRUIZE = "Criuzes";
    public static final String Table_CABIN = "Cabins";
    public static final String Table_EXCURSIONS = "Excursions";
    public static final String Table_GUESTS = "Guests";
    public static final String Table_CRUIZE_TMP = "Criuzes_TMP";
    public static final String Table_CABIN_TMP = "Cabins_TMP";
    public static final String Table_EXCURSIONS_TMP = "Excursions_TMP";
    public static final String Table_GUESTS_TMP = "Guests_TMP";



    //////////All the columns
    //Cruizes
    public static final String Column_id = "id";
    public static final String Column_name = "name";
    public static final String Column_shipName = "shipName";
    public static final String Column_from = "from";
    public static final String Column_to = "to";
    public static final String Column_createdAt = "createdAt";
    public static final String Column_updatedAt = "updatedAt";

    //Guests
    public static final String Column_fname = "fname";
    public static final String Column_lName = "lName";

    //Excursions
    public static final String Column_cruzeId = "cruzeId";
    public static final String Column_title = "title";
    public static final String Column_time = "time";
    public static final String Column_price = "price";
    public static final String Column_maxNumberOfGuest = "maxNumberOfGuest";

    //Cabins

    public static final String Column_cabinNumber = "cabinNumber";
    public static final String Column_guestId = "guestId";
    public static final String Column_numberOfGuest = "numberOfGuest";
    public static final String Column_paymentStatus = "paymentStatus";
    public static final String Column_deviceDate = "deviceDate";
















    //Guest Key
//    public static final String KEY_CRUISES_ID = "cruiseId";
 public static final String KEY_GUEST_ID = "guestID";
 public static final String KEY_GUEST_NAME = "guestName";
 public static final String KEY_NUMBER_GUEST = "numberOfGuest";
 public static final String KEY_EXCURSION = "excursion";
 public static final String KEY_CABIN_NO = "cabinNo";
 public static final String KEY_PAYMENT_STATUS = "paymentStatus";

    //Guest detail Key
//    public static final String KEY_CRUISES_ID = "cruiseId";


    //excursion  Key
 public static final String KEY_EXCURSION_ID = "id";
 public static final String KEY_EXCURSION_NAME = "excursionName";

    //Number0fGuest  Key
 public static final String KEY_GUEST_NUMBER_ID = "id";
 public static final String KEY_GUEST_NUMBER_NAME = "guestNumberName";

    //Participant

    public static final String KEY_PARTICIPANT_GUEST_ID = "id";
    public static final String KEY_PARTICIPANT_GUEST_NAME = "guestName";
    public static final String KEY_PARTICIPANT_GUEST_FIRST_NAME = "guestFName";
    public static final String KEY_PARTICIPANT_GUEST_LAST_NAME = "guestLName";
}
