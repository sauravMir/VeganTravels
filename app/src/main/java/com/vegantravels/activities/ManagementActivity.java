package com.vegantravels.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vegantravels.R;

public class ManagementActivity extends BaseActivity implements View.OnClickListener {
    TextView tvCruiseMngmnt, tvExcursionMngmnt, tvExport, tvFinancial;
    private ManagementActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        activity = this;
        findViewById();
    }

    private void findViewById() {
        tvCruiseMngmnt = (TextView) findViewById(R.id.tvCruiseMngmnt);
        tvExcursionMngmnt = (TextView) findViewById(R.id.tvExcursionMngmnt);
        tvExport = (TextView) findViewById(R.id.tvExport);
        tvFinancial = (TextView) findViewById(R.id.tvFinancial);

        tvCruiseMngmnt.setOnClickListener(this);
        tvExcursionMngmnt.setOnClickListener(this);
        tvExport.setOnClickListener(this);
        tvFinancial.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tvCruiseMngmnt:
                Intent intentGuest = new Intent(activity, MainActivity.class);
                startActivity(intentGuest);
                finishTheActivity();
                break;
            case R.id.tvExcursionMngmnt:
                Intent intentExcursion = new Intent(activity, ExcursionCruiseList.class);
                startActivity(intentExcursion);
                finishTheActivity();
                break;
            case R.id.tvExport:
                Intent intent = new Intent(activity, ExportActivity.class);
                startActivity(intent);
                finishTheActivity();
                break;
            case R.id.tvFinancial:
                startActivity(new Intent(activity, ExportExcursionActivity.class));
                finishTheActivity();
                break;

        }
    }


    void finishTheActivity() {
        finish();
    }
}

