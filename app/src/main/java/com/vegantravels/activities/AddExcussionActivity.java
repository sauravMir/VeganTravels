package com.vegantravels.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import android.util.Log;


import com.vegantravels.R;
import com.vegantravels.model.GuestDetails;
import com.vegantravels.retroapi.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddExcussionActivity extends Activity {
    AddExcussionActivity activity;
    // retro Call back Interface
    APIInterface apiInterface;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excussion);

        activity = this;
        //        APIClient.getClient().create(APIInterface.class);
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
}
