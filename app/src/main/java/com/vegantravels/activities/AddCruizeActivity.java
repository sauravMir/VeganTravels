package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.vegantravels.R;
import com.vegantravels.asynctask.GenericsAsyncTask;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;

public class AddCruizeActivity extends BaseActivity {

    private ProgressDialog progressDialog;
    private AddCruizeActivity activity;
    private DatabaseManager databaseManager;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cruize);
        activity = this;
        progressDialog = new ProgressDialog(activity);
        databaseManager = new DatabaseManager(activity);
//        GenericsAsyncTask cruisesGenericsAsyncTask = new GenericsAsyncTask(activity, null, "inserting");
    }


    class NewCruiseAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            DialogNavBarHide.navBarHide(activity, progressDialog);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //// insert new Data Here,
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.show();
            super.onPostExecute(aVoid);
        }
    }
}
