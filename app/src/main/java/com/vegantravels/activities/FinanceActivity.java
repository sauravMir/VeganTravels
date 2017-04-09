package com.vegantravels.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vegantravels.R;

public class FinanceActivity extends BaseActivity {
    FinanceActivity activity;
    TextView tvCabinMain, tvPaymetMain;
    ImageButton ibtnBackFinace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        activity = this;
        tvCabinMain = (TextView) findViewById(R.id.tvCabinMain);
        tvPaymetMain = (TextView) findViewById(R.id.tvPaymetMain);
        ibtnBackFinace = (ImageButton) findViewById(R.id.ibtnBackFinace);

        tvCabinMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, FinancePerCabinActivity.class));
                finishTheActivity();

            }
        });


        tvPaymetMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ibtnBackFinace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, ManagementActivity.class));
                finishTheActivity();
            }
        });

    }

    private void finishTheActivity() {
        finish();
    }
}
