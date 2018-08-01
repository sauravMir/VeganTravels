package com.vegantravels.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.adapter.GuestListExportAdapter;
import com.vegantravels.adapter.GuestThreeAdapter;
import com.vegantravels.dao.Cabins_TMP;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.dialog.AllDialog;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.model.Guest;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

public class GuestListFromExport extends Activity implements View.OnClickListener{
    public ListView lstGuest;
    public GuestListExportAdapter adapter;
    public ArrayList<Guests_TMP> guestList;
    private Guest guests;
    private GuestListFromExport activity;
    private ImageButton ibtnBack, ibtnSearch, ibtnAddGuest;
    private AllDialog allDialog;
    public long uniqueId, cruiseUniqId,excUniqID;
    public String date,excName;
    ProgressDialog progressDialog;
    IDatabaseManager databaseManager;
    int maxguest;
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

        ibtnAddGuest.setVisibility(View.INVISIBLE);
        maxguest=getIntent().getExtras().getInt(StaticAccess.KEY_NUMBER_GUEST,0);
        excName = getIntent().getExtras().getString(StaticAccess.KEY_EXCURSION_NAME,
                "Guest List per Excursion");
        cruiseUniqId = uniqueId=getIntent().getExtras().getLong(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID);
       // uniqueId = getIntent().getExtras().getLong(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID);
        date = getIntent().getExtras().getString(StaticAccess.KEY_INTENT_DATE,"");
        excUniqID= getIntent().getExtras().getLong(StaticAccess.KEY_EXCURSION_UNIQUE_ID);

        ibtnSearch.setOnClickListener(this);
        ibtnBack.setOnClickListener(this);
        ibtnAddGuest.setOnClickListener(this);
        databaseManager = new DatabaseManager(activity);
        GuestListRefresh();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ibtnBack:
                Intent intent = new Intent(activity, ExportExcursionGuestListActivity.class);
                intent.putExtra(StaticAccess.KEY_CRUISES_ID, cruiseUniqId);
                intent.putExtra(StaticAccess.KEY_EXCURSION_UNIQUE_ID, excUniqID);
                intent.putExtra(StaticAccess.KEY_INTENT_DATE,date);
                intent.putExtra(StaticAccess.KEY_EXCURSION_NAME, excName);
                intent.putExtra(StaticAccess.KEY_NUMBER_GUEST, maxguest);
                startActivity(intent);
                finishTheActivity();
                break;
            case R.id.ibtnSearch:
                dialogForSearch(uniqueId);
                break;
            case R.id.ibtnAddGuest:

                break;

        }
    }

    private void lvGuestLoad() {
        if (guestList != null)
            adapter = new GuestListExportAdapter(activity, guestList, date);
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


    public void GuestListRefresh() {
        new GuestSyncAsyncTask().execute();
    }

    private void finishTheActivity() {
        finish();
    }


    public void dialogForSearch(final long cruizeUniqID) {
        final Dialog dialog = new Dialog(this, R.style.CustomAlertDialog);
        dialog.setContentView(R.layout.dialog_search);
        dialog.setCancelable(false);
        final EditText edtCabinNumber = (EditText) dialog.findViewById(R.id.edtCabinNumber);
        final EditText edtCabinName = (EditText) dialog.findViewById(R.id.edtCabinName);
        ImageButton btnCancel = (ImageButton) dialog.findViewById(R.id.btnCancel);
        ImageButton btnOk = (ImageButton) dialog.findViewById(R.id.btnOk);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                Intent intent = new Intent(activity, GuestListTwoActivity.class);
//                activity.startActivity(intent);

                if (edtCabinNumber.getText().length() > 0 && edtCabinName.getText().length() > 0) {
                    guestList.clear();
                    guestList = (ArrayList<Guests_TMP>) databaseManager.getSearchByNameCabin(edtCabinName.getText().toString(), edtCabinNumber.getText().toString(),cruizeUniqID);

                } else if (edtCabinNumber.getText().length() > 0 && edtCabinName.getText().length() <= 0) {
                    guestList.clear();
                    guestList = (ArrayList<Guests_TMP>) databaseManager.getSearchByCabin(edtCabinNumber.getText().toString(),cruizeUniqID);
                } else if (edtCabinNumber.getText().length() <= 0 && edtCabinName.getText().length() > 0) {
                    guestList.clear();
                    guestList = (ArrayList<Guests_TMP>) databaseManager.getSearchByName(edtCabinName.getText().toString(),cruizeUniqID);
                } else {
                    guestList.clear();
                    guestList = databaseManager.listGuestByUniqueId(uniqueId);
                }

                if (guestList != null) {
                    adapter = new GuestListExportAdapter(GuestListFromExport.this, guestList,date);
                    lstGuest.setAdapter(adapter);
                }


                dialog.dismiss();
            }
        });

        DialogNavBarHide.navBarHide(GuestListFromExport.this, dialog);
    }

    private int isExcursionAvailable(int maxOccupancyPerExc){
        int res=-1;
        if(excUniqID!=-1) {
            ArrayList<Cabins_TMP> cabins = databaseManager.cabinByCruiseAndExcursionId2(cruiseUniqId, excUniqID);
            int occupancy=0;
            if(cabins!=null)
                for(Cabins_TMP cab: cabins){
                    occupancy+=cab.getOccupancy();
                }

            if(occupancy<maxOccupancyPerExc){
                res=maxOccupancyPerExc-occupancy;
            }else{
                makeToast("This excursion is full, can't add more guests");
            }
        }

        return res;
    }


    Cabins_TMP cabins_tmp;
    public void bookedExcursion(int paymentStatus, long guestUniq,String  guestVtId,int guestCabin, int numOfGuest) {
        int available=isExcursionAvailable(maxguest);

        if(numOfGuest<available) {
            cabins_tmp = new Cabins_TMP();
            cabins_tmp.setCruizeId(guestUniq);
            cabins_tmp.setGuestVT_Id(guestVtId);
            cabins_tmp.setCabinNumber(guestCabin);
            cabins_tmp.setPaymentStatus(paymentStatus);
            cabins_tmp.setCabinUniqueId(System.currentTimeMillis());
            if (numOfGuest != -1) {
                cabins_tmp.setOccupancy(numOfGuest);
            }

            if (excUniqID != -1) {
                cabins_tmp.setExcursion(excUniqID);
            }
            if (numOfGuest != -1 && excUniqID != -1) {
                new BookedExcursionGuestAsyncTask().execute();
            }
        }else{
            if(available!=-1)
            makeToast("You can add only "+String.valueOf(available)+" more people in this excursion");
        }
    }


    class BookedExcursionGuestAsyncTask extends AsyncTask<Void, Void, Void> {
        boolean success = false;
        Cabins_TMP insertedCabin_Tmp;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            if (cabins_tmp != null)
                insertedCabin_Tmp = databaseManager.insertCabinTemp(cabins_tmp);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hideProgressDialog();
//            if (updateBookedID != -1) {
//                Toast.makeText(activity, "update", Toast.LENGTH_SHORT).show();
//            }
            if (insertedCabin_Tmp != null) {
                //Toast.makeText(activity, "Excursion Booked", Toast.LENGTH_SHORT).show();
                makeToast("Excursion Booked Successfully");
            }

        }
    }



    public void makeToast(String str){
        Toast.makeText(GuestListFromExport.this, str,Toast.LENGTH_LONG).show();
    }





}
