package com.vegantravels.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vegantravels.R;

public class ManagementActivity extends BaseActivity implements View.OnClickListener {
    TextView tvCruiseMngmnt, tvExcursionMngmnt, tvExport, tvFinancial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
    }

    private void findViewById() {
        //TextView tvCruiseMngmnt, tvExcursionMngmnt, tvExport, tvFinancial;
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

    }
}
