package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.vegantravels.R;
import com.vegantravels.adapter.GuestAdapter;
import com.vegantravels.model.Guest;
import com.vegantravels.retroapi.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestListActivity extends BaseActivity {

    private ListView lvGuest;
    private GuestAdapter guestAdapter;
    GuestListActivity activity;
    private ArrayList<Guest> guestList;

    // retro Call back Interface
    APIInterface apiInterface;
    ProgressDialog progressDialog;
    String cruiseId;
    private ImageButton ibtnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);
        activity = this;
        lvGuest = (ListView) findViewById(R.id.lvGuest);
        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);
//        cruiseId = getIntent().getExtras().getString(StaticAccess.KEY_CRUISES_ID);

        //Connection Https or http Instances
//        APIClient.getClient().create(APIInterface.class);
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
                finishActivity();
            }
        });
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, MainActivity.class));
                finishActivity();
            }
        });
    }

    void parsingGuestList() {
        showProgressDialog();

        Call<List<Guest>> call = apiInterface.getGuestList(cruiseId);
        call.enqueue(new Callback<List<Guest>>() {
            @Override
            public void onResponse(Call<List<Guest>> call, Response<List<Guest>> response) {
                hideProgressDialog();
                for (Guest mGuest : response.body()) {
                    System.out.println(mGuest.toString());
//                    Guest cruises1 = new Cruises();
                    guestList.add(mGuest);

                }
            }

            @Override
            public void onFailure(Call<List<Guest>> call, Throwable t) {
                hideProgressDialog();
                System.out.println(t.getMessage());
            }
        });
    }

    public void finishActivity() {
        finish();
    }

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(getResources().getString(R.string.pleaseWait));
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.hide();
    }


}
