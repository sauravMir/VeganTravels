package com.vegantravels.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        Guest guest = new Guest(String.valueOf(1), String.valueOf(1), "Milan", "3", "AXZ", "108", "Paid");
        Guest guest1 = new Guest(String.valueOf(2), String.valueOf(1), "Milan", "3", "AXZ", "108", "Paid");
        Guest guest2 = new Guest(String.valueOf(3), String.valueOf(1), "Sam", "3", "AXZ", "108", "Due");
        Guest guest3 = new Guest(String.valueOf(4), String.valueOf(1), "Milan", "3", "AXZ", "108", "Paid");
        Guest guest4 = new Guest(String.valueOf(5), String.valueOf(1), "Jhon", "3", "AXZ", "108", "Paid");
        Guest guest5 = new Guest(String.valueOf(6), String.valueOf(1), "Milan", "3", "AXZ", "108", "Paid");
        Guest guest6 = new Guest(String.valueOf(7), String.valueOf(1), "Milan", "3", "AXZ", "108", "Paid");
        Guest guest7 = new Guest(String.valueOf(8), String.valueOf(1), "Milan", "3", "AXZ", "108", "Due");
        Guest guest8 = new Guest(String.valueOf(9), String.valueOf(1), "Milan", "3", "AXZ", "108", "Paid");
        Guest guest9 = new Guest(String.valueOf(10), String.valueOf(1), "Milan", "3", "AXZ", "108", "Paid");

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
        lvGuest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(activity, ViewExcursionActivity.class));
                finish();
            }
        });

    }
    
}
