package com.vegantravels.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.vegantravels.R;
import com.vegantravels.adapter.ExportExcursionAdapter;
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

public class ExportExcursionActivity extends BaseActivity implements View.OnClickListener {

    ExportExcursionActivity activity;
    private ListView lstExportExcursion;
    private ImageButton ibtnBackExportExcursion, ibtnAddExportExcursion;
    private ExportExcursionAdapter exportExcursionAdapter;
    private IDatabaseManager databaseManager;
    private ProgressDialog progressDialog;
    private ArrayList<Excursions_TMP> excursionsTmpsLst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_excursion);
        activity = this;
        databaseManager = new DatabaseManager(activity);
        findViewById();
    }

    private void findViewById() {
        lstExportExcursion = (ListView) findViewById(R.id.lstExportExcursion);
        ibtnBackExportExcursion = (ImageButton) findViewById(R.id.ibtnBackExportExcursion);
        ibtnBackExportExcursion.setOnClickListener(this);
        ibtnAddExportExcursion = (ImageButton) findViewById(R.id.ibtnAddExportExcursion);
        ibtnAddExportExcursion.setOnClickListener(this);
        new ExportExcursionAsync().execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtnBackExportExcursion:
                startActivity(new Intent(activity, ManagementActivity.class));
                finishTheActivity();
                break;
            case R.id.ibtnAddExportExcursion:
                Intent intentGuest = new Intent(ExportExcursionActivity.this, MainActivity.class);
                intentGuest.putExtra(StaticAccess.CameFromExport, 1);
                startActivity(intentGuest);
                finishTheActivity();
                break;
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

    class ExportExcursionAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... params) {
            excursionsTmpsLst = new ArrayList<>();
            excursionsTmpsLst = databaseManager.excursionTempList();
            Excursions_TMP e=new Excursions_TMP();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (excursionsTmpsLst != null) {
                excursionsTmpsLst= sortIt(excursionsTmpsLst);
                exportExcursionAdapter = new ExportExcursionAdapter(activity, excursionsTmpsLst);
                lstExportExcursion.setAdapter(exportExcursionAdapter);
            }
            hideProgressDialog();

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


    private void finishTheActivity() {
        finish();
    }
}
