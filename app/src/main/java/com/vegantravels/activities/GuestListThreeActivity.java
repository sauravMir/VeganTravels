package com.vegantravels.activities;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.vegantravels.R;
import com.vegantravels.adapter.GuestThreeAdapter;
import com.vegantravels.model.Guest;

import java.util.ArrayList;

/**
 * Created by Rakib on 4/6/2017.
 */

public class GuestListThreeActivity extends BaseActivity {

    private ListView lstGuest;
    private GuestThreeAdapter adapter;
    private ArrayList<Guest> guestList;
    private Guest guests;
    private GuestListThreeActivity activity;
    ImageButton ibtnSearch;
    private ImageButton ibtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_layout_three);
        activity = this;
        lstGuest = (ListView) findViewById(R.id.lstGuest);
        ibtnSearch = (ImageButton) findViewById(R.id.ibtnSearch);
        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);
        fillDummyData();
    }

    private void fillDummyData() {
        Guest guest = new Guest(String.valueOf(1), String.valueOf(1), "Milan", "3", "Excursion: Budapest", "108", "Paid");
        Guest guest1 = new Guest(String.valueOf(2), String.valueOf(1), "Milan", "3", "Excursion: Budapest", "108", "Paid");
        Guest guest2 = new Guest(String.valueOf(3), String.valueOf(1), "Sam", "3", "Excursion: Budapest", "108", "Due");
        Guest guest3 = new Guest(String.valueOf(4), String.valueOf(1), "Milan", "3", "Excursion: Budapest", "108", "Paid");
        Guest guest4 = new Guest(String.valueOf(5), String.valueOf(1), "Jhon", "3", "Excursion: Budapest", "108", "Paid");
        Guest guest5 = new Guest(String.valueOf(6), String.valueOf(1), "Milan", "3", "Excursion: Budapest", "108", "Due");
        Guest guest6 = new Guest(String.valueOf(7), String.valueOf(1), "Riston", "3", "Excursion: Budapest", "108", "Paid");
        Guest guest7 = new Guest(String.valueOf(8), String.valueOf(1), "Milan", "3", "Excursion: Budapest", "108", "Due");
        Guest guest8 = new Guest(String.valueOf(9), String.valueOf(1), "Abraham", "3", "Excursion: Budapest", "108", "Paid");
        Guest guest9 = new Guest(String.valueOf(10), String.valueOf(1), "Milan", "3", "Excursion: Budapest", "108", "Paid");
        Guest guest10 = new Guest(String.valueOf(11), String.valueOf(1), "Milan", "3", "Excursion: Budapest", "108", "Paid");
        Guest guest11 = new Guest(String.valueOf(12), String.valueOf(1), "Milan", "3", "Excursion: Budapest", "108", "Paid");

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
        guestList.add(guest10);
        guestList.add(guest11);
        adapter = new GuestThreeAdapter(activity, guestList);
        lstGuest.setAdapter(adapter);

    }

}
