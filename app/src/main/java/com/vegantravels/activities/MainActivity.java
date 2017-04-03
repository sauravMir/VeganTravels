package com.vegantravels.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.vegantravels.R;
import com.vegantravels.adapter.CruisesAdapter;
import com.vegantravels.model.Cruises;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ListView lvCruises;
    private CruisesAdapter cruisesAdapter;
    private ArrayList<Cruises> cruisesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvCruises = (ListView) findViewById(R.id.lvCruises);
        fillDummmyData();

    }

    private void fillDummmyData() {

        Cruises cruises = new Cruises(1, "cruise 1", "ship 1", "2-2-2017", "12.00pm");
        Cruises cruises1 = new Cruises(2, "cruise 2", "ship 2", "2-2-2017", "12.00pm");
        Cruises cruises2 = new Cruises(3, "cruise 3", "ship 3", "2-2-2017", "12.00pm");
        Cruises cruises3 = new Cruises(4, "cruise 4", "ship 4", "2-2-2017", "12.00pm");
        Cruises cruises4 = new Cruises(5, "cruise 5", "ship 5", "2-2-2017", "12.00pm");
        Cruises cruises5 = new Cruises(6, "cruise 6", "ship 6", "2-2-2017", "12.00pm");

        cruisesList = new ArrayList<>();
        cruisesList.add(cruises);
        cruisesList.add(cruises1);
        cruisesList.add(cruises2);
        cruisesList.add(cruises3);
        cruisesList.add(cruises4);
        cruisesList.add(cruises5);
        cruisesAdapter=new CruisesAdapter(this,cruisesList);
        lvCruises.setAdapter(cruisesAdapter);
    }
}
