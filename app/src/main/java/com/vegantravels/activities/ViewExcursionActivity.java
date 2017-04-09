package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.dialog.AllDialog;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

public class ViewExcursionActivity extends BaseActivity implements View.OnClickListener {

    private Button btnAddExcursion;
    private Button btnConfirm;
    private TextView tvGuestDetail;
    private TextView tvGuestName;
    private TextView tvCabinNo;
    private Spinner spnExcursion;
    private Spinner spnGuestNumber;
    private ViewExcursionActivity activity;
    private AllDialog allDialog;
    private ImageButton ibtnBack;
    long getGuestId;
    IDatabaseManager databaseManager;
    Guests_TMP tempGuestV;
    ProgressDialog progressDialog;

    ArrayList<Excursions_TMP> arrExcursion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_excursion);
        activity = this;
        allDialog = new AllDialog(activity);

        databaseManager = new DatabaseManager(activity);
        bindingViews();
        new ExCursionGuestAsyncTask().execute();
    }

    private void bindingViews() {

        getGuestId = getIntent().getExtras().getLong(StaticAccess.INTENT_GUEST_ID_KEY, -1);

        btnAddExcursion = (Button) findViewById(R.id.btnAddExcursion);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        tvGuestDetail = (TextView) findViewById(R.id.tvGuestDetail);
        tvGuestName = (TextView) findViewById(R.id.tvGuestName);
        tvCabinNo = (TextView) findViewById(R.id.tvCabinNo);
        spnExcursion = (Spinner) findViewById(R.id.spnExcursion);
        spnGuestNumber = (Spinner) findViewById(R.id.spnGuestNumber);
        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);


        btnAddExcursion.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        ibtnBack.setOnClickListener(this);
        tempGuestV = new Guests_TMP();
        arrExcursion = new ArrayList<>();

//        fillExcursionData();
//        fillGuestNumberData();

    }

    
    class ExCursionGuestAsyncTask extends AsyncTask<Void, Void, Void> {
        boolean success = false;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //// insert new Data Here,
            if (getGuestId != -1) {
                /// update cruize
                tempGuestV = databaseManager.getGuestTempById(getGuestId);
                if (tempGuestV != null)
                    arrExcursion = databaseManager.excursionTempListByCruiseUniqueId(tempGuestV.getGuestUniqueId());

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
                tvGuestName.setText(tempGuestV.getLName() + ",  " + tempGuestV.getFname());
                tvCabinNo.setText(String.valueOf(tempGuestV.getCabinNumber()));
            }
            if (arrExcursion != null) ;

        }
    }


    public void showProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(getResources().getString(R.string.pleaseWait));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }


    private void fillGuestNumberData() {
        String[] GUEST_ARRAY = getResources().getStringArray(R.array.noOfGuest);
        ArrayAdapter<String> adapterGuest = new ArrayAdapter<String>(activity, R.layout.spinner_item, GUEST_ARRAY);
        spnGuestNumber.setAdapter(adapterGuest);


    }

    private void fillExcursionData() {
        String[] EXCURSION_ARRAY = getResources().getStringArray(R.array.excursions);

        ArrayAdapter<String> adapterExcursion = new ArrayAdapter<String>(activity, R.layout.spinner_item, EXCURSION_ARRAY);
        spnExcursion.setAdapter(adapterExcursion);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnAddExcursion:
                Intent intent = new Intent(activity, AddExcursionActivity.class);
                startActivity(intent);
                finishTheActivity();

                break;

            case R.id.btnConfirm:
                allDialog.confirmDialog("Are you sure? You want to confirm");
                break;
            case R.id.ibtnBack:
                Intent guestintent = new Intent(activity, GuestListActivity.class);
                startActivity(guestintent);
                finishTheActivity();
                break;
        }
    }


    private void finishTheActivity() {
        finish();
    }
}
