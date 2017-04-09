package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.dialog.AllDialog;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.model.Guest;
import com.vegantravels.model.GuestDetails;
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
    TextView tvExcursionFromDate, tvExcursionTime;
    private AllDialog allDialog;
    private DatabaseManager databaseManager;
    private Excursions_TMP excursions_tmp;
    private Long cruizeKey = -1L;
    private ImageButton ibtnBackExcursion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excursion);
        activity = this;
        cruizeKey = getIntent().getLongExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, -1L);
        databaseManager = new DatabaseManager(activity);
//        Toast.makeText(activity, String.valueOf(databaseManager.excursionTempList().size()), Toast.LENGTH_SHORT).show();
        findViewById();
        allDialog = new AllDialog(new AddCruizeActivity());

        //Connection Https or http Instances
//        apiInterface = APIClient.getClient().create(APIInterface.class);
//
//        showProgressDialog();
//        Guest guest = new Guest();
//        guest.setCruiseID("1");
//        guest.setGuestID("1");
//        guest.setCabinNo("88800");
//        guest.setExcursion("hello");
//        guest.setGuestName("Reaz");
//        guest.setNumberOfGuest("4");
//        guest.setPaymentStatus("3");
//
//        getGuestPaymentMethodAdd(guest);


    }


    private void findViewById() {
        btnDone = (Button) findViewById(R.id.btnDone);
        edtExcursionTitle = (EditText) findViewById(R.id.edtExcursionTitle);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        edtMaxGuest = (EditText) findViewById(R.id.edtMaxGuest);
        tvExcursionFromDate = (TextView) findViewById(R.id.tvExcursionFromDate);
        tvExcursionTime = (TextView) findViewById(R.id.tvExcursionTime);
        ibtnBackExcursion = (ImageButton) findViewById(R.id.ibtnBackExcursion);

        tvExcursionFromDate.setOnClickListener(this);
        tvExcursionTime.setOnClickListener(this);
        btnDone.setOnClickListener(this);
        ibtnBackExcursion.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tvExcursionFromDate:
                allDialog.setCustomDate(tvExcursionFromDate, StaticAccess.DATE_FROM);
                break;
            case R.id.tvExcursionTime:
                allDialog.setCustomTime(tvExcursionTime);
                break;
            case R.id.btnDone:
                if (edtExcursionTitle.getText().toString().length() > 0 && tvExcursionFromDate.getText().toString().length() > 0 && tvExcursionTime.getText().toString().length() > 0 && edtMaxGuest.getText().toString().length() > 0) {
                    addNewExcursion();
                }
                break;
            case R.id.ibtnBackExcursion:
                Intent intent = new Intent(activity, ManagementActivity.class);
                startActivity(intent);
                finishTheActivity();
                break;

        }
    }

    private void finishTheActivity() {
        findViewById();
    }

    private void addNewExcursion() {
        excursions_tmp = new Excursions_TMP();
        excursions_tmp.setTitle(edtExcursionTitle.getText().toString());
        excursions_tmp.setFrom(tvExcursionFromDate.getText().toString());
        excursions_tmp.setTime(tvExcursionTime.getText().toString());
        excursions_tmp.setPrice(edtPrice.getText().toString());
        excursions_tmp.setMaxNumberOfGuest(Integer.valueOf(edtMaxGuest.getText().toString()));
        excursions_tmp.setTo("  ");
        excursions_tmp.setCreatedAt(System.currentTimeMillis());
        excursions_tmp.setUpdatedAt(System.currentTimeMillis());
        excursions_tmp.setExcursionUniqueId(System.currentTimeMillis());
        excursions_tmp.setCruzeId(cruizeKey);

        new newExcursionAsyncTask().execute();
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
        DialogNavBarHide.navBarHide(activity, progressDialog);
    }

    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    class newExcursionAsyncTask extends AsyncTask<Void, Void, Void> {
        boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (excursions_tmp != null) {
                databaseManager.insertExcursionTemp(excursions_tmp);
                isSuccess = true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hideProgressDialog();
            Toast.makeText(activity, "is excursion inserted: " + String.valueOf(isSuccess), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(activity, ExcursionListActivity.class));
            finishTheActivity();
        }
    }
}
