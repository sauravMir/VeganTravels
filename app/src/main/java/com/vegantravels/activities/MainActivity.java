package com.vegantravels.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vegantravels.R;
import com.vegantravels.adapter.CruisesAdapter;
import com.vegantravels.model.Cruises;
import com.vegantravels.retroapi.APIClient;
import com.vegantravels.retroapi.APIInterface;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends BaseActivity {

    private ListView lvCruises;
    private CruisesAdapter cruisesAdapter;
    private ArrayList<Cruises> cruisesList;
    MainActivity activity;
    // retro Call back Interface
    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        lvCruises = (ListView) findViewById(R.id.lvCruises);
        //Connection Https or http Instances
        APIClient.getClient().create(APIInterface.class);
        fillDummmyData();

    }
void parsingCruisesList(){
    Call<Cruises> call = apiInterface.getCruizeList();
    call.enqueue(new Callback<Cruises>() {
        @Override
        public void onResponse(Call<Cruises> call, Response<Cruises> response) {

        Log.d("TAG",response.code()+"");
            Cruises resource = response.body();
  /*  
            Integer text = resource.page;
            Integer total = resource.total;
            Integer totalPages = resource.totalPages;
            List<MultipleResource.Datum> datumList = resource.data;

            displayResponse += text + " Page\n" + total + " Total\n" + totalPages + " Total Pages\n";

            for (MultipleResource.Datum datum : datumList) {
                displayResponse += datum.id + " " + datum.name + " " + datum.pantoneValue + " " + datum.year + "\n";
            }

            responseText.setText(displayResponse);*/

        }

        @Override
        public void onFailure(Call<Cruises> call, Throwable t) {
            call.cancel();
        }
    });

}
    private void fillDummmyData() {

        Cruises cruises = new Cruises(String.valueOf(1), "cruise 1", "ship 1", "2-2-2017", "12.00pm");
        Cruises cruises1 = new Cruises(String.valueOf(2), "cruise 2", "ship 2", "2-2-2017", "12.00pm");
        Cruises cruises2 = new Cruises(String.valueOf(3), "cruise 3", "ship 3", "2-2-2017", "12.00pm");
        Cruises cruises3 = new Cruises(String.valueOf(4), "cruise 4", "ship 4", "2-2-2017", "12.00pm");
        Cruises cruises4 = new Cruises(String.valueOf(5), "cruise 5", "ship 5", "2-2-2017", "12.00pm");
        Cruises cruises5 = new Cruises(String.valueOf(6), "cruise 6", "ship 6", "2-2-2017", "12.00pm");
        Cruises cruises6 = new Cruises(String.valueOf(7), "cruise 7", "ship 7", "2-2-2017", "12.00pm");
        Cruises cruises7 = new Cruises(String.valueOf(8), "cruise 8", "ship 8", "2-2-2017", "12.00pm");
        Cruises cruises8 = new Cruises(String.valueOf(9), "cruise 9", "ship 9", "2-2-2017", "12.00pm");
        Cruises cruises9 = new Cruises(String.valueOf(10), "cruise 10", "ship 10", "2-2-2017", "12.00pm");

        cruisesList = new ArrayList<>();
        cruisesList.add(cruises);
        cruisesList.add(cruises1);
        cruisesList.add(cruises2);
        cruisesList.add(cruises3);
        cruisesList.add(cruises4);
        cruisesList.add(cruises5);
        cruisesList.add(cruises6);
        cruisesList.add(cruises7);
        cruisesList.add(cruises8);
        cruisesList.add(cruises9);
        cruisesAdapter = new CruisesAdapter(this, cruisesList);
        lvCruises.setAdapter(cruisesAdapter);
        lvCruises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(MainActivity.this, GuestListActivity.class));
                finish();
            }
        });
    }
}
