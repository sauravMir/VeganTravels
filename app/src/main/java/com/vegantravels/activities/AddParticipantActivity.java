package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.dao.Cabins_TMP;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.model.Participant;
import com.vegantravels.retroapi.APIInterface;
import com.vegantravels.utilities.StaticAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddParticipantActivity extends BaseActivity implements View.OnClickListener {
    // retro Call back Interface
    APIInterface apiInterface;
    ProgressDialog progressDialog;
    AddParticipantActivity activity;
    EditText etCabinNum, etFName, etLName, etVTid;
    Button btnDone;
    IDatabaseManager databaseManager;
    Guests_TMP tempGuest, tempGuestEdit;
    private long cruizeID = -1;
    private long cruizeUniqueID = -1;
    public long guestID = -1;
    private String fDate = "";
    private ImageButton ibtnBackGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participant);
        activity = this;
        //Connection Https or http Instances
//        apiInterface =  APIClient.getClient().create(APIInterface.class);
        cruizeID = getIntent().getLongExtra(StaticAccess.KEY_CRUISES_ID, -1);
        cruizeUniqueID = getIntent().getLongExtra(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID, -1);
        guestID = getIntent().getLongExtra(StaticAccess.KEY_GUEST_ID, -1);
        fDate = getIntent().getStringExtra(StaticAccess.KEY_INTENT_DATE);
        initialization();

    }

    private void initialization() {

        etCabinNum = (EditText) findViewById(R.id.etCabinNum);
        etFName = (EditText) findViewById(R.id.etFName);
        etLName = (EditText) findViewById(R.id.etLName);
        etLName = (EditText) findViewById(R.id.etLName);
        etVTid = (EditText) findViewById(R.id.etVTid);
        ibtnBackGuest = (ImageButton) findViewById(R.id.ibtnBackGuest);

        databaseManager = new DatabaseManager(activity);

        btnDone = (Button) findViewById(R.id.btnDone);
        btnDone.setOnClickListener(activity);
        ibtnBackGuest.setOnClickListener(activity);


        //// fill data for edit
        if (cruizeUniqueID != -1 && guestID != -1) {
            fillEditableData();
        }
    }


    boolean isGetData = false;/// true to get the specific guest and false to update specific guest data

    //// fill data for edit
    private void fillEditableData() {
        new EditGuestAsyncTask().execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDone:
                if (cruizeUniqueID != -1 && guestID != -1) {
                    if (etFName.getText().length() > 0 && etLName.getText().length() > 0 &&
                            etCabinNum.getText().length() > 0 && etVTid.getText().length() > 0 && cruizeUniqueID != -1 && guestID != -1) {
                        isGetData = true;
                        tempGuestEdit = new Guests_TMP();
                        tempGuestEdit.setId(guestID);
                        tempGuestEdit.setCabinNumber(Integer.valueOf(etCabinNum.getText().toString()));
                        tempGuestEdit.setFname(etFName.getText().toString());
                        tempGuestEdit.setLName(etLName.getText().toString());
                        tempGuestEdit.setGuestVT_Id(etVTid.getText().toString());
                        tempGuestEdit.setGuestUniqueId(cruizeUniqueID); /// cruize unique id
                        new EditGuestAsyncTask().execute();

                    }

                } else {
                    if (etFName.getText().length() > 0 && etLName.getText().length() > 0 &&
                            etCabinNum.getText().length() > 0 && etVTid.getText().length() > 0 && cruizeUniqueID != -1) {

                        tempGuest = new Guests_TMP();
                        tempGuest.setCabinNumber(Integer.valueOf(etCabinNum.getText().toString()));
                        tempGuest.setFname(etFName.getText().toString());
                        tempGuest.setLName(etLName.getText().toString());
                        tempGuest.setGuestVT_Id(etVTid.getText().toString());
                        tempGuest.setGuestUniqueId(cruizeUniqueID); /// cruize unique id


                        new GuestAsyncTask().execute();

                    }
                }
                break;
            case R.id.ibtnBackGuest:
                Intent intent = new Intent(activity, GuestListThreeActivity.class);
                intent.putExtra(StaticAccess.KEY_CRUISES_ID, cruizeID);
                intent.putExtra(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID, cruizeUniqueID);
                intent.putExtra(StaticAccess.KEY_INTENT_DATE, fDate);
                startActivity(intent);
                finishTheActivity();
                break;

        }
    }


    public void showProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(getResources().getString(R.string.pleaseWait));
        progressDialog.setCancelable(false);
        progressDialog.show();
        DialogNavBarHide.navBarHide(activity, progressDialog);
    }

    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    void addParticipant(String name) {
        Participant participant = new Participant();
        participant.setParticipantName(name);
        Call callParticipant = apiInterface.createUser(participant);
        callParticipant.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                hideProgressDialog();
                Participant participant = (Participant) response.body();
                Toast.makeText(activity, participant.getParticipantName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
                hideProgressDialog();
            }
        });
    }


    /////////****************  ASYNC TASKS ********************** ///////////////////
    ////****************************************************************************
    class GuestAsyncTask extends AsyncTask<Void, Void, Void> {
        boolean success = false;
        Guests_TMP tempGuestV = new Guests_TMP();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            
            //// insert new Data Here,
            if (tempGuest != null) {

                /// update cruize
                tempGuestV = databaseManager.insertGuestTemporary(tempGuest);
                Cabins_TMP insertCabinPayment = new Cabins_TMP();

                if (tempGuestV != null)
                    insertCabinPayment.setCabinNumber(tempGuestV.getCabinNumber());
                insertCabinPayment.setGuestVT_Id(tempGuestV.getGuestVT_Id());
                insertCabinPayment.setOccupancy(1);
//              insertCabinPayment.setNumberOfGuest(Integer.valueOf(xlsDataList.get(i).getGuestInCabin()));
                // here is CruiseId
                insertCabinPayment.setCruizeId(tempGuestV.getGuestUniqueId());
                insertCabinPayment.setExcursion(-1L);
                insertCabinPayment.setPaymentStatus(-1);
                insertCabinPayment.setCabinUniqueId(System.currentTimeMillis());

                databaseManager.insertCabinTemp(insertCabinPayment);

            } else {
               /* // get cruize
                if (cruizeID != -1 && cruizeUniqueID != -1) {
                    criuzes_tmp = databaseManager.getCruiseTempById(cruizeID);

                }*/
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hideProgressDialog();
            if (tempGuestV != null) {
                Intent intent = new Intent(activity, ViewExcursionActivity.class);
                intent.putExtra(StaticAccess.INTENT_GUEST_ID_KEY, tempGuestV.getId());
                startActivity(intent);
                finish();
            }
        }
    }

    /// guest update async task
    class EditGuestAsyncTask extends AsyncTask<Void, Void, Void> {
        Guests_TMP tempGuest;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //// insert new Data Here,
            if (isGetData && guestID != -1 && tempGuestEdit != null) {
                databaseManager.updateGuestTemporary(tempGuestEdit);

            } else {
                tempGuest = databaseManager.getGuestTempById(guestID);

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!isGetData) {
                etCabinNum.setText(String.valueOf(tempGuest.getCabinNumber()));
                etFName.setText(String.valueOf(tempGuest.getFname()));
                etLName.setText(String.valueOf(tempGuest.getLName()));
                etVTid.setText(String.valueOf(tempGuest.getGuestVT_Id()));

            } else {
                Toast.makeText(activity, "Guest Edit Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, GuestListThreeActivity.class);
                intent.putExtra(StaticAccess.KEY_CRUISES_ID, cruizeID);
                intent.putExtra(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID, cruizeUniqueID);
                intent.putExtra(StaticAccess.KEY_INTENT_DATE, fDate);
                startActivity(intent);
                finishTheActivity();
            }
            hideProgressDialog();

        }
    }


    void finishTheActivity() {
        finish();
    }
}
