package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.vegantravels.R;
import com.vegantravels.adapter.CruisesAdapter;
import com.vegantravels.dao.Criuzes_TMP;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.model.CruiseJson;
import com.vegantravels.model.Cruises;
import com.vegantravels.retroapi.APIClient;
import com.vegantravels.retroapi.APIInterface;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends BaseActivity {

    private ListView lvCruises;
    private CruisesAdapter cruisesAdapter;
    // Criuze model for dao class
    private ArrayList<Criuzes_TMP> cruisesList;
    MainActivity activity;
    // retro Call back Interface
    APIInterface apiInterface;
    ProgressDialog progressDialog;
    private ImageButton ibtnBack, ibtnAddCruize, ibtnSync;
    IDatabaseManager databaseManager;
    public int cameFromExport=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        lvCruises = (ListView) findViewById(R.id.lvCruises);
        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);
        ibtnAddCruize = (ImageButton) findViewById(R.id.ibtnAddCruize);
//        ibtnSync = (ImageButton) findViewById(R.id.ibtnSync);
        //Connection Https or http Instances
        apiInterface = APIClient.getClient().create(APIInterface.class);
        databaseManager = new DatabaseManager(activity);
        listRefresh();

        if(getIntent().getExtras()!=null)
        cameFromExport = getIntent().getExtras().getInt(StaticAccess.CameFromExport,0);

        if(cameFromExport==1){
            ibtnAddCruize.setVisibility(View.INVISIBLE);
        }

        ibtnAddCruize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, AddCruizeActivity.class));
                finishActivity();
            }
        });
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cameFromExport==1){
                    Intent intentGuest = new Intent(MainActivity.this, ExportExcursionActivity.class);
                    intentGuest.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentGuest);
                    finish();
                }else {
                    Intent intent = new Intent(activity, ManagementActivity.class);
                    startActivity(intent);
                    finishActivity();
                }
            }
        });
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

    //fill cruize data
    private void fillData() {
        if (cruisesList != null ) {
            cruisesAdapter = new CruisesAdapter(this, cruisesList, StaticAccess.CRIUZE_MANAGEMENT);
            lvCruises.setAdapter(cruisesAdapter);
            cruisesAdapter.notifyDataSetChanged();
        }
        lvCruises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentGuest = new Intent(activity, GuestListThreeActivity.class);
                intentGuest.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentGuest.putExtra(StaticAccess.KEY_CRUISES_ID, cruisesList.get(i).getId());
                startActivity(intentGuest);
                finishActivity();
            }
        });


    }

    public void finishActivity() {
        finish();
    }

    /////****************** sync asyctask ***********************///////
    class CruizeSyncAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //// insert new Data Here,

            cruisesList = new ArrayList<>();
            cruisesList = databaseManager.listCriuzeTemporary();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            fillData();
            progressDialog.dismiss();

        }
    }
    /////****************** sync asyctask end here ***********************///////


/////************** unused Method for future debugging ********************////////

    void parsingCruisesList() {
        cruisesList = new ArrayList<>();
        showProgressDialog();
        Call<CruiseJson> call = apiInterface.getCruizeList();
        call.enqueue(new Callback<CruiseJson>() {
            @Override
            public void onResponse(Call<CruiseJson> call, Response<CruiseJson> response) {
                hideProgressDialog();
                CruiseJson cruiseJson = response.body();

                for (Cruises cruises : cruiseJson.mCruiseList) {
                    System.out.println(cruises.toString());
                    Cruises cruises1 = new Cruises();
                    cruises1.setCruiseID(cruises.getCruiseID());
                    cruises1.setCruiseName(cruises.getCruiseName());
                    cruises1.setShipName(cruises.getShipName());
                    cruises1.setDateFrom(cruises.getDateFrom());
                    cruises1.setDateTo(cruises.getDateTo());
//                    cruisesList.add(cruises1);
                }
            }

            @Override
            public void onFailure(Call<CruiseJson> call, Throwable t) {
                hideProgressDialog();
                System.out.println(t.getMessage());
            }
        });
    }

    public void listRefresh(){
        new CruizeSyncAsyncTask().execute();
    }
}
