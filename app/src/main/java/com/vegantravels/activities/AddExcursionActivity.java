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
    boolean goForEdit = false;
    EditText edtExcursionTitle, edtPrice, edtMaxGuest;
    TextView tvExcursionFromDate, tvExcursionTime, tvExcursionTitle;
    private AllDialog allDialog;
    private DatabaseManager databaseManager;
    private Excursions_TMP excursions_tmp, updateexcursions;
    private Long cruizeKey = -1L;
    private long excursionId = -1;
    //int camefromExportEXC=-1;
    private ImageButton ibtnBackExcursion;
    public int cameFromExport=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excursion);
        activity = this;
        cruizeKey = getIntent().getLongExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, -1L);
        excursionId = getIntent().getLongExtra(StaticAccess.KEY_EXCURSION_ID, -1);
        //camefromExportEXC= getIntent().getIntExtra("came", -1);
        cameFromExport = getIntent().getExtras().getInt(StaticAccess.CameFromExport,0);

        databaseManager = new DatabaseManager(activity);
//        Toast.makeText(activity, String.valueOf(databaseManager.excursionTempList().size()), Toast.LENGTH_SHORT).show();
        findViewById();

        allDialog = new AllDialog(activity);
        if (excursionId != -1 && cruizeKey != -1) {
            new EditExcursionAsyncTask().execute();
        }

//        new CabinSetupAsyncTask().execute();
    }


    private void findViewById() {
        btnDone = (Button) findViewById(R.id.btnDone);
        edtExcursionTitle = (EditText) findViewById(R.id.edtExcursionTitle);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        edtMaxGuest = (EditText) findViewById(R.id.edtMaxGuest);
        tvExcursionFromDate = (TextView) findViewById(R.id.tvExcursionFromDate);
        tvExcursionTime = (TextView) findViewById(R.id.tvExcursionTime);
        tvExcursionTitle = (TextView) findViewById(R.id.tvExcursionTitle);
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
                allDialog.setCustomDateForEx(tvExcursionFromDate, StaticAccess.DATE_FROM);
                break;
            case R.id.tvExcursionTime:
                allDialog.setCustomTimeForEx(tvExcursionTime);
                break;
            case R.id.btnDone:
                if (excursionId != -1 && cruizeKey != -1) {
                    if (edtExcursionTitle.getText().toString().length() > 0 && tvExcursionFromDate.getText().toString().length() > 0 && tvExcursionTime.getText().toString().length() > 0 && edtMaxGuest.getText().toString().length() > 0 && excursionId != -1) {
                        goForEdit = true;
                        updateExcursion();
                    }
                } else {
                    if (edtExcursionTitle.getText().toString().length() > 0 && tvExcursionFromDate.getText().toString().length() > 0 && tvExcursionTime.getText().toString().length() > 0 && edtMaxGuest.getText().toString().length() > 0 && cruizeKey != -1) {
                        addNewExcursion();
                    }
                }
                break;
            case R.id.ibtnBackExcursion:
                if(cameFromExport!=1) {
                    Intent intent = new Intent(activity, ExcursionListActivity.class);
                    intent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, cruizeKey);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(activity, ExportExcursionActivity.class);
                    startActivity(intent);
                }
                finishTheActivity();
                break;

        }
    }

    private void finishTheActivity() {
        finish();
    }


    private void updateExcursion() {
        updateexcursions = new Excursions_TMP();
        updateexcursions.setTitle(edtExcursionTitle.getText().toString());
        updateexcursions.setFrom(tvExcursionFromDate.getText().toString());
        updateexcursions.setTime(tvExcursionTime.getText().toString());
        updateexcursions.setPrice(edtPrice.getText().toString());
        updateexcursions.setMaxNumberOfGuest(Integer.valueOf(edtMaxGuest.getText().toString()));
        updateexcursions.setExcursionUniqueId(excursions_tmp.getExcursionUniqueId());
        updateexcursions.setCruzeId(cruizeKey);
        updateexcursions.setId(excursionId);
        new EditExcursionAsyncTask().execute();
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
            if(cameFromExport!=1) {
            Intent intent = new Intent(activity, ExcursionListActivity.class);
            intent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, cruizeKey);
            startActivity(intent);
            }else{
                Intent intent = new Intent(activity, ExportExcursionActivity.class);
                startActivity(intent);
            }
            finishTheActivity();
        }
    }


    class EditExcursionAsyncTask extends AsyncTask<Void, Void, Void> {
        boolean success = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
            tvExcursionTitle.setText("Edit Excursion");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //// insert new Data Here,
            if (goForEdit) {
                /// update cruize
                if (updateexcursions != null)
                    databaseManager.updateExcursionTemp(updateexcursions);

            } else {
                // get cruize
                if (excursionId != -1 && cruizeKey != -1) {
                    excursions_tmp = databaseManager.getExcursionById(excursionId);

                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!goForEdit) {
                if (excursions_tmp != null) {
                    /// fill data for edit

                    edtExcursionTitle.setText(String.valueOf(excursions_tmp.getTitle()));
                    tvExcursionFromDate.setText(String.valueOf(excursions_tmp.getFrom()));
                    tvExcursionTime.setText(String.valueOf(excursions_tmp.getTime()));
                    edtPrice.setText(String.valueOf(excursions_tmp.getPrice()));
                    edtMaxGuest.setText(String.valueOf(excursions_tmp.getMaxNumberOfGuest()));
                    hideProgressDialog();
                }
            } else {
                hideProgressDialog();
                Toast.makeText(activity, "Excursion Updated: ", Toast.LENGTH_SHORT).show();
                if(cameFromExport!=1) {
                Intent intent = new Intent(activity, ExcursionListActivity.class);
                intent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, cruizeKey);
                startActivity(intent);
            }else{
                Intent intent = new Intent(activity, ExportExcursionActivity.class);
                startActivity(intent);
            }
                finishTheActivity();
            }
        }
    }

}
