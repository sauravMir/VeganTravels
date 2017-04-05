package com.vegantravels.sync;

import android.content.Context;

import com.vegantravels.dao.Criuze;
import com.vegantravels.model.CruiseJson;
import com.vegantravels.model.Cruises;
import com.vegantravels.retroapi.APIClient;
import com.vegantravels.retroapi.APIInterface;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public CruiseTble(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    void parsingCruisesList() {
        cruisesList = new ArrayList<>();
//        showProgressDialog();
        Call<CruiseJson> call = apiInterface.getCruizeList();
        call.enqueue(new Callback<CruiseJson>() {
            @Override
            public void onResponse(Call<CruiseJson> call, Response<CruiseJson> response) {

                CruiseJson cruiseJson = response.body();

                for (Cruises cruises : cruiseJson.mCruiseList) {
                    System.out.println(cruises.toString());
                    Criuze criuze = new Criuze();
                    criuze.setCruizeKey(System.currentTimeMillis());
                    criuze.setCruizeName(cruises.getCruiseName());
//                    criuze.setDateFrom(cruises.getDateFrom());
                    criuze.setCruizeKey(System.currentTimeMillis());
                    criuze.setCruizeKey(System.currentTimeMillis());
                   /* Cruises cruises1 = new Cruises();
                    cruises1.setCruiseID(cruises.getCruiseID());
                    cruises1.setCruiseName();
                    cruises1.setShipName(cruises.getShipName());
                    cruises1.setDateFrom(cruises.getDateFrom());
                    cruises1.setDateTo(cruises.getDateTo());
                    cruisesList.add(cruises1);*/
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
