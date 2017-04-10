package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.vegantravels.R;
import com.vegantravels.adapter.ExcursionAdapter;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

public class ExcursionListActivity extends BaseActivity implements View.OnClickListener {
    ExcursionListActivity activity;
    ImageButton ibtnBackExcursionList, ibtnAddExcursion;
    ListView lvExcursion;
    ExcursionAdapter excursionAdapter;
    private ArrayList<Excursions_TMP> excursionLst;
    IDatabaseManager databaseManager;
    private long cruizeUniqueID = -1;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion_list);
        activity = this;
        cruizeUniqueID = getIntent().getLongExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, -1);
        databaseManager = new DatabaseManager(activity);
        excursionLst = new ArrayList<>();
        findViewById();
        if (cruizeUniqueID != -1) {
            new ExcursionAsyncTask().execute();
        }

    }

    private void findViewById() {
        ibtnBackExcursionList = (ImageButton) findViewById(R.id.ibtnBackExcursionList);
        ibtnAddExcursion = (ImageButton) findViewById(R.id.ibtnAddExcursion);
        lvExcursion = (ListView) findViewById(R.id.lvExcursion);

        ibtnBackExcursionList.setOnClickListener(this);
        ibtnAddExcursion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtnBackExcursionList:
                startActivity(new Intent(activity, MainActivity.class));
                finishTheActivity();
                break;

            case R.id.ibtnAddExcursion:
                Intent exIntent = new Intent(activity, AddExcursionActivity.class);
                exIntent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, cruizeUniqueID);
                startActivity(exIntent);
                finishTheActivity();
                break;

        }
    }

    private void finishTheActivity() {
        finish();
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

    class ExcursionAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            excursionLst = databaseManager.excursionTempListByCruiseUniqueId(cruizeUniqueID);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (excursionLst != null) {
                excursionAdapter = new ExcursionAdapter(activity, excursionLst,cruizeUniqueID);
                lvExcursion.setAdapter(excursionAdapter);
            }
            hideProgressDialog();
        }
    }
}
