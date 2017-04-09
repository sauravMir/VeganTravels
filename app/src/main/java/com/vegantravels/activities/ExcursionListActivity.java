package com.vegantravels.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.vegantravels.R;
import com.vegantravels.adapter.ExcursionAdapter;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;

import java.util.ArrayList;

public class ExcursionListActivity extends BaseActivity implements View.OnClickListener {
    ExcursionListActivity activity;
    ImageButton ibtnBackExcursionList, ibtnAddExcursion;
    ListView lvExcursion;
    ExcursionAdapter excursionAdapter;
    private ArrayList<Excursions_TMP> excursionLst;
    IDatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion_list);
        activity = this;
        databaseManager = new DatabaseManager(activity);
        excursionLst = new ArrayList<>();
        excursionLst = databaseManager.excursionTempList();
        findViewById();

    }

    private void findViewById() {
        ibtnBackExcursionList = (ImageButton) findViewById(R.id.ibtnBackExcursionList);
        ibtnAddExcursion = (ImageButton) findViewById(R.id.ibtnAddExcursion);
        lvExcursion = (ListView) findViewById(R.id.lvExcursion);
        excursionAdapter = new ExcursionAdapter(activity, excursionLst);
        lvExcursion.setAdapter(excursionAdapter);
        ibtnBackExcursionList.setOnClickListener(this);
        ibtnAddExcursion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtnBackExcursionList:
                startActivity(new Intent(activity, FinanceActivity.class));
                finishTheActivity();
                break;

            case R.id.ibtnAddExcursion:
                Intent exIntent = new Intent(activity, AddExcursionActivity.class);
                startActivity(exIntent);
                finishTheActivity();
                break;

        }
    }

    private void finishTheActivity() {
        finish();
    }
}
