package com.vegantravels.sync;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.vegantravels.dao.Criuzes;
import com.vegantravels.dao.Criuzes_TMP;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.model.CruiseJson;
import com.vegantravels.model.Cruises;
import com.vegantravels.model.GuestDetails;
import com.vegantravels.retroapi.APIClient;
import com.vegantravels.retroapi.APIInterface;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    ArrayList<Criuzes_TMP> criuzes_tmps;

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

    void addCruise() {
        /**
         GET List Resources
         **/

        criuzes_tmps = databaseManager.listCriuzeTemporary();
        List<Cruises> criuzes_tmps_json = new ArrayList<Cruises>();
        for (int i = 0; i > criuzes_tmps.size(); i++) {
            Cruises cruises = new Cruises();
            cruises.setShipName(criuzes_tmps.get(i).getShipName());
            cruises.setCruiseName(criuzes_tmps.get(i).getName());
            cruises.setDateTo(criuzes_tmps.get(i).getTo());
            cruises.setDateFrom(criuzes_tmps.get(i).getFrom());
            criuzes_tmps_json.add(cruises);

        }
        CruiseJson cruiseJson = new CruiseJson(criuzes_tmps_json);
        Call<CruiseJson> call = apiInterface.addCruise(cruiseJson);
        call.enqueue(new Callback<CruiseJson>() {
            @Override
            public void onResponse(Call<CruiseJson> call, Response<CruiseJson> response) {


  /*             Log.d("TAG", response.code() + "");

                String displayResponse = "";

                CruiseJson resource = response.body();
                String cabinNumber = resource.cabinNumber;
                String guestName = resource.guestName;
                String guestId = resource.GuestId;

                List<GuestDetails.Excursion> excursionList = resource.excursions;
                List<GuestDetails.NumberOfGuest> numberOfGuestList = resource.numberOfGuests;

                for (GuestDetails.Excursion excursion : excursionList) {
                    displayResponse += excursion.id + " " + excursion.excursionName;
                }

                for (GuestDetails.NumberOfGuest numberOfGuest : numberOfGuestList) {
                    displayResponse += numberOfGuest.id + " " + numberOfGuest.guestName;
                }*/


            }

            @Override
            public void onFailure(Call<CruiseJson> call, Throwable t) {
                call.cancel();
            }
        });

    }
}
