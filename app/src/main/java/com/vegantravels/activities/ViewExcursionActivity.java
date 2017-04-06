package com.vegantravels.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.dialog.AllDialog;

public class ViewExcursionActivity extends BaseActivity implements View.OnClickListener {

    private Button btnAddExcursion;
    private Button btnConfirm;
    private TextView tvGuestDetail;
    private TextView tvGuestName;
    private TextView tvCabinNo;
    private Spinner spnExcursion;
    private Spinner spnGuestNumber;
    private ViewExcursionActivity activity;
    private AllDialog allDialog;
    private ImageButton ibtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_excursion);
        activity = this;
        allDialog = new AllDialog(activity);
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
        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);


        btnAddExcursion.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        ibtnBack.setOnClickListener(this);


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
                Intent intent = new Intent(activity, AddExcursionActivity.class);
                startActivity(intent);
                finishTheActivity();
                
                break;
            case R.id.btnConfirm:
                allDialog.confirmDialog("Are you sure? You want to confirm");
                break;
            case R.id.ibtnBack:
                Intent guestintent = new Intent(activity, GuestListActivity.class);
                startActivity(guestintent);
                finishTheActivity();
                break;
        }
    }


    private void finishTheActivity() {
        finish();
    }
}
