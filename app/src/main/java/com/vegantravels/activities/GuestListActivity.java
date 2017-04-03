package com.vegantravels.activities;

import android.os.Bundle;
import android.widget.ListView;

import com.vegantravels.R;
import com.vegantravels.adapter.GuestAdapter;
import com.vegantravels.model.Guest;

import java.util.ArrayList;

public class GuestListActivity extends BaseActivity {

    private ListView lvGuest;
    private GuestAdapter guestAdapter;
    GuestListActivity activity;
    private ArrayList<Guest> guestList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);
        activity = this;
        lvGuest = (ListView) findViewById(R.id.lvGuest);
        fillDummyData();

    }

    private void fillDummyData() {
        Guest guest = new Guest(1, "Milan", "3", "AXZ", "108", "Paid");
        Guest guest1 = new Guest(2, "Milan", "3", "AXZ", "108", "Paid");
        Guest guest2 = new Guest(3, "Sam", "3", "AXZ", "108", "Due");
        Guest guest3 = new Guest(4, "Milan", "3", "AXZ", "108", "Paid");
        Guest guest4 = new Guest(5, "Jhon", "3", "AXZ", "108", "Paid");
        Guest guest5 = new Guest(6, "Milan", "3", "AXZ", "108", "Paid");
        Guest guest6 = new Guest(7, "Milan", "3", "AXZ", "108", "Paid");
        Guest guest7 = new Guest(8, "Milan", "3", "AXZ", "108", "Due");
        Guest guest8 = new Guest(9, "Milan", "3", "AXZ", "108", "Paid");
        Guest guest9 = new Guest(10, "Milan", "3", "AXZ", "108", "Paid");

        guestList = new ArrayList<>();
        guestList.add(guest);
        guestList.add(guest1);
        guestList.add(guest2);
        guestList.add(guest3);
        guestList.add(guest4);
        guestList.add(guest5);
        guestList.add(guest6);
        guestList.add(guest7);
        guestList.add(guest8);
        guestList.add(guest9);
        guestAdapter = new GuestAdapter(activity, guestList);
        lvGuest.setAdapter(guestAdapter);
        
    }
}
