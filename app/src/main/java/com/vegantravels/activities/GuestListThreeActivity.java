package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.adapter.GuestThreeAdapter;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.dialog.AllDialog;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.model.Guest;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

/**
 * Created by Rakib on 4/6/2017.
 */

public class GuestListThreeActivity extends BaseActivity implements View.OnClickListener {

    public ListView lstGuest;
    public GuestThreeAdapter adapter;
    public ArrayList<Guests_TMP> guestList;
    private Guest guests;
    private GuestListThreeActivity activity;
    private ImageButton ibtnBack, ibtnSearch, ibtnAddGuest;
    private AllDialog allDialog;
    long uniqueId, cruiseId;
    String date;
    ProgressDialog progressDialog;
    IDatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_layout_three);
        activity = this;
        lstGuest = (ListView) findViewById(R.id.lstGuest);
        allDialog = new AllDialog(activity);
        ibtnSearch = (ImageButton) findViewById(R.id.ibtnSearch);
        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);
        ibtnAddGuest = (ImageButton) findViewById(R.id.ibtnAddGuest);

        cruiseId = getIntent().getExtras().getLong(StaticAccess.KEY_CRUISES_ID);
        uniqueId = getIntent().getExtras().getLong(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID);
        date = getIntent().getExtras().getString(StaticAccess.KEY_INTENT_DATE);

        ibtnSearch.setOnClickListener(this);
        ibtnBack.setOnClickListener(this);
        ibtnAddGuest.setOnClickListener(this);
        databaseManager = new DatabaseManager(activity);
        new GuestSyncAsyncTask().execute();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ibtnBack:
                Intent intent = new Intent(activity, ManagementActivity.class);
                startActivity(intent);
                break;
            case R.id.ibtnSearch:
                allDialog.dialogForSearch();
                break;
            case R.id.ibtnAddGuest:
                Toast.makeText(activity, "ibtnAddGuest clicked", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void lvGuestLoad() {
        if (guestList != null)
            adapter = new GuestThreeAdapter(activity, guestList, date);
        lstGuest.setAdapter(adapter);

    }

    class GuestSyncAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //// insert new Data Here,
//            CruiseTble cruiseTble = new CruiseTble(activity);
//            cruiseTble.addCruise();
//            cruiseTble.parsingCruisesList();


            guestList = new ArrayList<>();
            guestList = databaseManager.listGuestByUniqueId(uniqueId);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            progressDialog.dismiss();
            lvGuestLoad();
            super.onPostExecute(aVoid);

        }
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
}
