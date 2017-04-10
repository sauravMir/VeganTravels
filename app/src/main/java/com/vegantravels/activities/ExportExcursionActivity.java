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

import java.util.ArrayList;

public class ExportExcursionActivity extends BaseActivity implements View.OnClickListener {

    ExportExcursionActivity activity;
    private ListView lstExportExcursion;
    private ImageButton ibtnBackExportExcursion;
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
        new ExportExcursionAsync().execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtnBackExportExcursion:
                startActivity(new Intent(activity, ExportActivity.class));
                finishTheActivity();
                break;
        }
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
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (excursionsTmpsLst != null) {
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
