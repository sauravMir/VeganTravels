package com.vegantravels.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.vegantravels.R;
import com.vegantravels.adapter.GuestTwoAdapter;
import com.vegantravels.model.Guest;

import java.util.ArrayList;

public class GuestListTwoActivity extends BaseActivity {

    private ArrayList<Guest> guestList;
    private GuestTwoAdapter adapter;
    private GuestListTwoActivity activity;
    private EditText edtCabinNo, edtGuestName, edtPeopleNumber, edtPaymentStatus;
    private ImageButton ibtnGuestSearch;
    private ListView lstGuest;
    private Button btnExportGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list_two);
        activity = this;
        //bind lv id here
        findviewById();
        fillDummyData();
    }

    private void fillDummyData() {

        Guest guest = new Guest(String.valueOf(1), String.valueOf(1), "Milan", "3", "Budapest", "108", "Paid");
        Guest guest1 = new Guest(String.valueOf(2), String.valueOf(1), "Milan", "3", "Budapest", "108", "Paid");
        Guest guest2 = new Guest(String.valueOf(3), String.valueOf(1), "Sam", "3", "Budapest", "108", "Due");
        Guest guest3 = new Guest(String.valueOf(4), String.valueOf(1), "Milan", "3", "Budapest", "108", "Paid");
        Guest guest4 = new Guest(String.valueOf(5), String.valueOf(1), "Jhon", "3", "Budapest", "108", "Paid");
        Guest guest5 = new Guest(String.valueOf(6), String.valueOf(1), "Milan", "3", "Budapest", "108", "Due");
        Guest guest6 = new Guest(String.valueOf(7), String.valueOf(1), "Riston", "3", "Budapest", "108", "Paid");
        Guest guest7 = new Guest(String.valueOf(8), String.valueOf(1), "Milan", "3", "Budapest", "108", "Due");
        Guest guest8 = new Guest(String.valueOf(9), String.valueOf(1), "Abraham", "3", "Budapest", "108", "Paid");
        Guest guest9 = new Guest(String.valueOf(10), String.valueOf(1), "Milan", "3", "Budapest", "108", "Paid");

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
        adapter = new GuestTwoAdapter(activity, guestList);
        /// set lv adapter here
        lstGuest.setAdapter(adapter);

    }

    public void findviewById() {

        lstGuest = (ListView) findViewById(R.id.lstGuest);
        edtCabinNo = (EditText) findViewById(R.id.edtCabinNo);
        edtGuestName = (EditText) findViewById(R.id.edtGuestName);
        edtPeopleNumber = (EditText) findViewById(R.id.edtPeopleNumber);
        edtPaymentStatus = (EditText) findViewById(R.id.edtPaymentStatus);
        ibtnGuestSearch = (ImageButton) findViewById(R.id.ibtnGuestSearch);
        btnExportGuest = (Button) findViewById(R.id.btnExportGuest);

    }


    public void finishActivity() {
        finish();
    }
}
