package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.model.Participant;
import com.vegantravels.retroapi.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddParticipantActivity extends BaseActivity {
    // retro Call back Interface
    APIInterface apiInterface;
    ProgressDialog progressDialog;
    AddParticipantActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participant);
        activity = this;
        //Connection Https or http Instances
//        APIClient.getClient().create(APIInterface.class);

    }

    void addParticipant(String name) {
        Participant participant = new Participant();
        participant.setParticipantName(name);
        Call callParticipant = apiInterface.createUser(participant);
        callParticipant.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Participant participant = (Participant) response.body();
                Toast.makeText(activity, participant.getParticipantName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }
}
