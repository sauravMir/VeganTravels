package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.adapter.SpinnerCustomAdapter;
import com.vegantravels.dao.Cabins_TMP;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.dialog.AllDialog;
import com.vegantravels.dialog.DialogNavBarHide;
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
    SpinnerCustomAdapter spinnerCustomAdapter;

    ArrayList<Excursions_TMP> arrExcursion;
    private long cruizeUniqueID = -1;
    private String fDate = "";
    private Cabins_TMP cabins_tmp;

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
        cruizeUniqueID = getIntent().getLongExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, -1);
        fDate = getIntent().getStringExtra(StaticAccess.KEY_INTENT_DATE);
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
        fillGuestNumberData();

    }


    public void showProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(getResources().getString(R.string.pleaseWait));
        progressDialog.setCancelable(false);
        progressDialog.show();
        DialogNavBarHide.navBarHide(activity, progressDialog);
    }

    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    private int numOfGuest = -1;

    private void fillGuestNumberData() {
        String[] GUEST_ARRAY = getResources().getStringArray(R.array.noOfGuest);
        final ArrayAdapter<String> adapterGuest = new ArrayAdapter<String>(activity, R.layout.spinner_item, GUEST_ARRAY);
        spnGuestNumber.setAdapter(adapterGuest);
        spnGuestNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0)
                    numOfGuest = Integer.parseInt(adapterGuest.getItem(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    

    private long excrusionId = -1;

    private void fillExcursionData() {
        spinnerCustomAdapter = new SpinnerCustomAdapter(activity, arrExcursion);
        spnExcursion.setAdapter(spinnerCustomAdapter);
        spnExcursion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (arrExcursion != null) {
                    excrusionId = arrExcursion.get(i).getExcursionUniqueId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnAddExcursion:
                if (cruizeUniqueID != -1) {
                    Intent intent = new Intent(activity, AddExcursionActivity.class);
                    intent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, cruizeUniqueID);
                    startActivity(intent);
                    finishTheActivity();
                }
                break;

            case R.id.btnConfirm:
                allDialog.confirmDialog("Are you sure? You want to confirm", this);
                break;
            case R.id.ibtnBack:
                if (cruizeUniqueID != -1) {
                    Intent guestintent = new Intent(activity, GuestListThreeActivity.class);
                    guestintent.putExtra(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID, cruizeUniqueID);
                    guestintent.putExtra(StaticAccess.KEY_INTENT_DATE, fDate);
                    startActivity(guestintent);
                    finishTheActivity();
                }
                break;
        }
    }

    public void bookedExcursion(int paymentStatus) {
        cabins_tmp = new Cabins_TMP();
        cabins_tmp.setCruizeId(tempGuestV.getGuestUniqueId());
        cabins_tmp.setGuestVT_Id(tempGuestV.getGuestVT_Id());
        cabins_tmp.setPaymentStatus(paymentStatus);
        if (numOfGuest != -1) {
            cabins_tmp.setOccupancy(numOfGuest);
        }

        if (excrusionId != -1) {
            cabins_tmp.setExcursion(excrusionId);
        }
        new BookedExcursionGuestAsyncTask().execute();

    }


    private void finishTheActivity() {
        finish();
    }

    ////////////////*****************  ASYNCTASK CLASS HERE   ****************************


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
            if (arrExcursion != null) {
                fillExcursionData();
            }

        }
    }

    long updateBookedID = -1;
    Cabins_TMP insertedCabin_Tmp;

    class BookedExcursionGuestAsyncTask extends AsyncTask<Void, Void, Void> {
        boolean success = false;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            //// insert new Data Here,
//            Cabins_TMP cabins_tmp_Update = databaseManager.hasGuestExcursion(tempGuestV.getGuestVT_Id());
//
//            if (cabins_tmp_Update != null) {
////                    update
//                cabins_tmp_Update.setPaymentStatus(cabins_tmp.getPaymentStatus());
//                cabins_tmp_Update.setOccupancy(cabins_tmp.getOccupancy());
//                cabins_tmp_Update.setExcursion(cabins_tmp.getExcursion());
//                updateBookedID = databaseManager.updateCabinTemp(cabins_tmp_Update);
//            } else {
//                // insert
//                if (cabins_tmp != null)
//                    insertedCabin_Tmp = databaseManager.insertCabinTemp(cabins_tmp);
//            }
            if (cabins_tmp != null)
                insertedCabin_Tmp = databaseManager.insertCabinTemp(cabins_tmp);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hideProgressDialog();
            if (updateBookedID != -1) {
                Toast.makeText(activity, "update", Toast.LENGTH_SHORT).show();
            }
            if (insertedCabin_Tmp != null) {
                Toast.makeText(activity, "Excursion Booked", Toast.LENGTH_SHORT).show();

            }

        }
    }


}
