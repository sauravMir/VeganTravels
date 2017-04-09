package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.model.GuestDetails;
import com.vegantravels.model.Participant;
import com.vegantravels.retroapi.APIInterface;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participant);
        activity = this;
        //Connection Https or http Instances
//        apiInterface =  APIClient.getClient().create(APIInterface.class);

        initialization();

    }

    private void initialization() {

        etCabinNum = (EditText) findViewById(R.id.etCabinNum);
        etFName = (EditText) findViewById(R.id.etFName);
        etLName = (EditText) findViewById(R.id.etLName);
        etVTid = (EditText) findViewById(R.id.etVTid);

        databaseManager = new DatabaseManager(activity);

        btnDone = (Button) findViewById(R.id.btnDone);
        btnDone.setOnClickListener(activity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDone:

                if (etFName.getText().length() > 0 && etLName.getText().length() > 0 &&
                        etCabinNum.getText().length() > 0 && etVTid.getText().length() > 0) {

                }
                break;

        }
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

}
