package com.vegantravels.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;

import com.vegantravels.dao.Criuzes;
import com.vegantravels.dialog.DialogNavBarHide;

/**
 * Created by Rakib on 4/5/2017.
 */

public class GenericsAsyncTask extends FrameWorkAsyncTask {
    private Activity activity;
    private ProgressDialog progressDialog;
    private DatabaseManager databaseManager;
    private String msg;
    private Criuzes cruises;

    public GenericsAsyncTask(Activity activity, Criuzes cruises, String progressMsg) {
        this.activity = activity;
        progressDialog = new ProgressDialog(activity);
        databaseManager = new DatabaseManager(activity);
        this.msg = progressMsg;
        this.cruises = cruises;
    }

    @Override
    void prepareHeavyWork() {
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        progressDialog.show();
        DialogNavBarHide.navBarHide(activity, progressDialog);
    }

    @Override
    void doHeavyWork() {
        databaseManager.insertCruises(cruises);
    }

    @Override
    void heavyWorkResult() {
        progressDialog.dismiss();
    }
}
