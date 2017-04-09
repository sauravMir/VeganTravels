package com.vegantravels.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.vegantravels.R;

public class FinancePerCabinActivity extends BaseActivity implements View.OnClickListener {
    FinancePerCabinActivity activity;
    ImageButton ibtnBackFinacePerCabin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_per_cabin);
        activity = this;


    }

    private void findViewById() {
        ibtnBackFinacePerCabin = (ImageButton) findViewById(R.id.ibtnBackFinacePerCabin);
        ibtnBackFinacePerCabin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtnBackFinacePerCabin:
                startActivity(new Intent(activity, FinanceActivity.class));
                finishTheActivity();
                break;
        }
    }

    private void finishTheActivity() {
        finish();
    }
}
