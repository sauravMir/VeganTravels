package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.adapter.ExportExcursionGuestAdapter;
import com.vegantravels.dao.Cabins_TMP;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.dialog.AllDialog;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.model.GuestExport;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

/**
 * Created by Rakib on 4/6/2017.
 */

public class ExportExcursionGuestListActivity extends BaseActivity implements View.OnClickListener {

    public ListView lstExportEcGuest;
    public ExportExcursionGuestAdapter adapterExportEc;
    public ArrayList<Guests_TMP> guestListExportEc;
    private ExportExcursionGuestListActivity activity;
    private ImageButton ibtnExportEcBack,ibtnAddGuestPerExc;
    private AllDialog allDialog;
    public long exUniqueId, cruiseId;
    public String date;
    ProgressDialog progressDialog;
    private IDatabaseManager databaseManager;
    private ArrayList<GuestExport> guestListExport;
    String excName="";
    TextView excTitle;
    String dateOfExc="";
    int maxguest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_excursion_guest_list);
        activity = this;
        lstExportEcGuest = (ListView) findViewById(R.id.lstExportEcGuest);
        allDialog = new AllDialog(activity);
        ibtnExportEcBack = (ImageButton) findViewById(R.id.ibtnExportEcBack);
        ibtnAddGuestPerExc = (ImageButton) findViewById(R.id.ibtnAddGuestPerExc);

        excTitle=(TextView) findViewById(R.id.ibtnExctitle);

        excName = getIntent().getExtras().getString(StaticAccess.KEY_EXCURSION_NAME,
                "Guest List per Excursion");
        cruiseId = getIntent().getExtras().getLong(StaticAccess.KEY_CRUISES_ID);
        exUniqueId = getIntent().getExtras().getLong(StaticAccess.KEY_EXCURSION_UNIQUE_ID);
        dateOfExc=getIntent().getExtras().getString(StaticAccess.KEY_INTENT_DATE,"");
        maxguest=getIntent().getExtras().getInt(StaticAccess.KEY_NUMBER_GUEST,0);

        ibtnExportEcBack.setOnClickListener(this);
        ibtnAddGuestPerExc.setOnClickListener(this);
        databaseManager = new DatabaseManager(activity);
        GuestListRefresh();

        excTitle.setText(excName);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ibtnExportEcBack:
                Intent intent = new Intent(activity, ExportExcursionActivity.class);
                startActivity(intent);
                finishTheActivity();
                break;

            case R.id.ibtnAddGuestPerExc:
                Intent intent2 = new Intent(activity, GuestListFromExport.class);
                intent2.putExtra(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID, cruiseId);
                intent2.putExtra(StaticAccess.KEY_EXCURSION_UNIQUE_ID, exUniqueId);
                intent2.putExtra(StaticAccess.KEY_INTENT_DATE, dateOfExc);
                intent2.putExtra(StaticAccess.KEY_EXCURSION_NAME, excName);
                intent2.putExtra(StaticAccess.KEY_NUMBER_GUEST, maxguest);
                startActivity(intent2);
                finishTheActivity();
                break;

        }
    }

    private void lvGuestLoad() {
        if (guestListExport != null) {
            int num=0;
            for(GuestExport temp:guestListExport){
                if(temp.getCabins_tmp()!=null)
                num+=temp.getCabins_tmp().getOccupancy();
            }
            excTitle.setText(String.valueOf(num)+" Guests in "+excName);
            adapterExportEc = new ExportExcursionGuestAdapter(activity, guestListExport);
            lstExportEcGuest.setAdapter(adapterExportEc);
            adapterExportEc.notifyDataSetChanged();
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

    private ArrayList<Cabins_TMP> cabins_tmpsList;

    class GuestSyncAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            cabins_tmpsList = new ArrayList<>();
            if (cruiseId != -1) {
//                guestList = databaseManager.listGuestByUniqueId(cruizeUniqID);
                guestListExport = new ArrayList<>();
                cabins_tmpsList = databaseManager.getGuestDeatilByExcursionCruizeID(exUniqueId, cruiseId);
                if (cabins_tmpsList != null) {
                    for (int i = 0; i < cabins_tmpsList.size(); i++) {
                        GuestExport model = new GuestExport();
                        Guests_TMP guests_tmp = databaseManager.guestTempFromCabin(cabins_tmpsList.get(i).getGuestVT_Id(),
                                cabins_tmpsList.get(i).getCruizeId());

                        model.setFirstName(guests_tmp.getFname());
                        model.setLastName(guests_tmp.getLName());
                        model.setCabins_tmp(cabins_tmpsList.get(i));

                        guestListExport.add(model);

                    }

                }


            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hideProgressDialog();
            lvGuestLoad();



        }
    }


}
