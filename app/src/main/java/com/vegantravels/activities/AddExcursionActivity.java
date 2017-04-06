package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.vegantravels.R;
import com.vegantravels.dialog.AllDialog;
import com.vegantravels.model.Guest;
import com.vegantravels.model.GuestDetails;
import com.vegantravels.retroapi.APIClient;
import com.vegantravels.retroapi.APIInterface;
import com.vegantravels.utilities.StaticAccess;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddExcursionActivity extends BaseActivity implements View.OnClickListener {
    AddExcursionActivity activity;
    // retro Call back Interface
    APIInterface apiInterface;
    ProgressDialog progressDialog;
    Button btnDone;
    EditText edtExcursionTitle, edtPrice, edtMaxGuest;
    TextView tvExcursionFromDate, tvExcursionToDate, tvExcursionTime;
    private AllDialog allDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excursion);
        activity = this;
        findViewById();
        allDialog = new AllDialog(activity);

        //Connection Https or http Instances
        apiInterface = APIClient.getClient().create(APIInterface.class);

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

    private void findViewById() {
        btnDone = (Button) findViewById(R.id.btnDone);
        edtExcursionTitle = (EditText) findViewById(R.id.edtExcursionTitle);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        edtMaxGuest = (EditText) findViewById(R.id.edtMaxGuest);
        tvExcursionFromDate = (TextView) findViewById(R.id.tvExcursionFromDate);
        tvExcursionToDate = (TextView) findViewById(R.id.tvExcursionToDate);
        tvExcursionTime = (TextView) findViewById(R.id.tvExcursionTime);
        
        tvExcursionFromDate.setOnClickListener(this);
        tvExcursionToDate.setOnClickListener(this);
        tvExcursionTime.setOnClickListener(this);
        btnDone.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tvExcursionFromDate:
                allDialog.setCustomDate(tvExcursionFromDate, StaticAccess.DATE_FROM);
                break;
            case R.id.tvExcursionToDate:
                allDialog.setCustomDate(tvExcursionToDate, StaticAccess.DATE_TO);
                break;
            case R.id.tvExcursionTime:
                allDialog.setCustomDate(tvExcursionTime, StaticAccess.TIME);
                break;
            case R.id.btnDone:
                Intent intent = new Intent(activity, ViewExcursionActivity.class);
                startActivity(intent);
                finish();
                break;

        }
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
