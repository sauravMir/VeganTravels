package com.vegantravels.sync;

import android.content.Context;

import com.vegantravels.dao.Criuzes;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.model.CruiseJson;
import com.vegantravels.model.Cruises;
import com.vegantravels.retroapi.APIClient;
import com.vegantravels.retroapi.APIInterface;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RAFI on 4/5/2017.
 */

public class CruiseTble {
    Context context;
    ArrayList<Cruises> cruisesList;
    APIInterface apiInterface;
    IDatabaseManager databaseManager;
    public CruiseTble(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        databaseManager = new DatabaseManager(context);
    }

   public void parsingCruisesList() {
        cruisesList = new ArrayList<>();
//        showProgressDialog();
        Call<CruiseJson> call = apiInterface.getCruizeList();
        call.enqueue(new Callback<CruiseJson>() {
            @Override
            public void onResponse(Call<CruiseJson> call, Response<CruiseJson> response) {

                CruiseJson cruiseJson = response.body();

                for (Cruises cruises : cruiseJson.mCruiseList) {
                    System.out.println(cruises.toString());
                    Criuzes criuze = new Criuzes();
                    //criuze.setCruizeKey(System.currentTimeMillis());
                    criuze.setName(cruises.getCruiseName());
                    criuze.setShipName(cruises.getShipName());
                    criuze.setTo(cruises.getDateTo());
                    criuze.setFrom(cruises.getDateFrom());
                    databaseManager.insertCruises(criuze);
                }
            }

            @Override
            public void onFailure(Call<CruiseJson> call, Throwable t) {

                System.out.println(t.getMessage());
            }
        });
    }

    String getDateFormat(String checkDate) {
/*//        Convert input string into a date
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        Date date = inputFormat.parse(checkDate);*/
// Format date into output format
        DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        String outputString = outputFormat.format(checkDate);
        return outputString;
    }
}
