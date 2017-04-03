package com.vegantravels.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.vegantravels.R;
import com.vegantravels.adapter.GuestAdapter;
import com.vegantravels.model.Cruises;
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
        guestList = new ArrayList<>();
        guestAdapter = new GuestAdapter(activity, guestList);
        lvGuest.setAdapter(guestAdapter);
    }
}
