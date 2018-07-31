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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        ExcursionListRefresh();

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
                excursionLst= sortIt(excursionLst);
                excursionAdapter = new ExcursionAdapter(activity, excursionLst, cruizeUniqueID);
                lvExcursion.setAdapter(excursionAdapter);
            }
            hideProgressDialog();
        }
    }

    public ArrayList<Excursions_TMP> sortIt(ArrayList<Excursions_TMP> list) {
        // for(Excursions_TMP temp:list)
        Collections.sort(list, new Comparator<Excursions_TMP>() {
            DateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
            @Override
            public int compare(Excursions_TMP o1, Excursions_TMP o2) {
                try {
                    String date1=o1.getFrom();
                    String date2=o2.getFrom();

                    return f.parse(date1).compareTo(f.parse(date2));
                } catch (ParseException e) {
                    //throw new IllegalArgumentException(e);
                    return 0;
                }
            }
        });

        return (ArrayList<Excursions_TMP>) list.clone();

    }

    public void ExcursionListRefresh() {
        if (cruizeUniqueID != -1) {
            new ExcursionAsyncTask().execute();
        }
    }
}
