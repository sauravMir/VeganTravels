package com.vegantravels.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.vegantravels.R;

public class ViewExcursionActivity extends BaseActivity implements View.OnClickListener {

    private Button btnAddExcursion;
    private Button btnConfirm;
    private TextView tvGuestDetail;
    private TextView tvGuestName;
    private TextView tvCabinNo;
    private Spinner spnExcursion;
    private Spinner spnGuestNumber;
    private ViewExcursionActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_excursion);
        activity = this;
        bindingViews();
    }

    private void bindingViews() {
        btnAddExcursion = (Button) findViewById(R.id.btnAddExcursion);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        tvGuestDetail = (TextView) findViewById(R.id.tvGuestDetail);
        tvGuestName = (TextView) findViewById(R.id.tvGuestName);
        tvCabinNo = (TextView) findViewById(R.id.tvCabinNo);
        spnExcursion = (Spinner) findViewById(R.id.spnExcursion);
        spnGuestNumber = (Spinner) findViewById(R.id.spnGuestNumber);


        btnAddExcursion.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);


        fillExcursionData();
        fillGuestNumberData();

    }

    private void fillGuestNumberData() {
        String[] GUEST_ARRAY = getResources().getStringArray(R.array.noOfGuest);

        ArrayAdapter<String> adapterGuest = new ArrayAdapter<String>(activity, R.layout.spinner_item, GUEST_ARRAY);
        spnGuestNumber.setAdapter(adapterGuest);

    }

    private void fillExcursionData() {
        String[] EXCURSION_ARRAY = getResources().getStringArray(R.array.excursions);

        ArrayAdapter<String> adapterExcursion = new ArrayAdapter<String>(activity, R.layout.spinner_item, EXCURSION_ARRAY);
        spnExcursion.setAdapter(adapterExcursion);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnAddExcursion:
                Intent intent = new Intent(activity, AddExcussionActivity.class);
                startActivity(intent);
                break;
            case R.id.btnConfirm:
                break;
        }
    }
}
