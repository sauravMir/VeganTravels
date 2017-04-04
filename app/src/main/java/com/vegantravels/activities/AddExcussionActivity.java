package com.vegantravels.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.vegantravels.R;
import com.vegantravels.model.Guest;
import com.vegantravels.model.GuestDetails;
import com.vegantravels.retroapi.APIClient;
import com.vegantravels.retroapi.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddExcussionActivity extends BaseActivity {
    AddExcussionActivity activity;
    // retro Call back Interface
    APIInterface apiInterface;
    ProgressDialog progressDialog;
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excussion);

        activity = this;
        btnDone = (Button) findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ViewExcursionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Connection Https or http Instances
        apiInterface =  APIClient.getClient().create(APIInterface.class);

        showProgressDialog();
        Guest guest = new Guest();
        guest.setCruiseID("1");
        guest.setGuestID("1");
        guest.setCabinNo("88800");
        guest.setExcursion("hello");
        guest.setGuestName("Reaz");
        guest.setNumberOfGuest("4");
        guest.setPaymentStatus("3");

        getGuestPaymentMethodAdd(guest);


    }

    void getGuestDetails() {
        /**
         GET List Resources
         **/
        Call<GuestDetails> call = apiInterface.getGuestDetails("");
        call.enqueue(new Callback<GuestDetails>() {
            @Override
            public void onResponse(Call<GuestDetails> call, Response<GuestDetails> response) {


                Log.d("TAG", response.code() + "");

                String displayResponse = "";

                GuestDetails resource = response.body();
                String cabinNumber = resource.cabinNumber;
                String guestName = resource.guestName;
                String guestId = resource.GuestId;

                List<GuestDetails.Excursion> excursionList = resource.excursions;
                List<GuestDetails.NumberOfGuest> numberOfGuestList = resource.numberOfGuests;

                for (GuestDetails.Excursion excursion : excursionList) {
                    displayResponse += excursion.id + " " + excursion.excursionName;
                }

                for (GuestDetails.NumberOfGuest numberOfGuest : numberOfGuestList) {
                    displayResponse += numberOfGuest.id + " " + numberOfGuest.guestName;
                }


            }

            @Override
            public void onFailure(Call<GuestDetails> call, Throwable t) {
                call.cancel();
            }
        });

    }


    void getGuestPaymentMethodAdd(Guest guest) {
        /**
         POST Methods For guest Payment and All details from details
         **/

        Call<Guest> call = apiInterface.guestDetailAdd(guest);
        call.enqueue(new Callback<Guest>() {
            @Override
            public void onResponse(Call<Guest> call, Response<Guest> response) {

                hideProgressDialog();
                Log.d("TAG", response.code() + "");

                String displayResponse = "";

                Guest resource = response.body();


            }

            @Override
            public void onFailure(Call<Guest> call, Throwable t) {
                call.cancel();
                hideProgressDialog();
            }
        });

    }


    public void showProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(getResources().getString(R.string.pleaseWait));
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }


}
