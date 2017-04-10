package com.vegantravels.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.adapter.FinanceAdapter;
import com.vegantravels.dao.Cabins_TMP;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;

import java.util.ArrayList;

public class FinanceActivity extends BaseActivity implements View.OnClickListener {
    FinanceActivity activity;
    TextView tvCabinMain, tvPaymetMain;
    ImageButton ibtnBackFinace;
    ListView lvFinance;
    FinanceAdapter financeAdapter;
    private IDatabaseManager databaseManager;
    private ArrayList<Cabins_TMP> cabinList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        activity = this;

        databaseManager = new DatabaseManager(activity);
        cabinList = new ArrayList<>();
        cabinList = databaseManager.cabinTempList(1);
        findViewById();
    }

    private void findViewById() {
        ibtnBackFinace = (ImageButton) findViewById(R.id.ibtnBackFinace);
        lvFinance = (ListView) findViewById(R.id.lvFinance);
        financeAdapter = new FinanceAdapter(activity, cabinList);
        lvFinance.setAdapter(financeAdapter);
        ibtnBackFinace.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtnBackFinace:
                startActivity(new Intent(activity, ManagementActivity.class));
                finishTheActivity();
                break;
        }
    }

    private void finishTheActivity() {
        finish();
    }
}
