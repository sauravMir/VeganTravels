package com.vegantravels.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.vegantravels.R;

public class FinanceActivity extends BaseActivity implements View.OnClickListener {
    FinanceActivity activity;
    TextView tvCabinMain, tvPaymetMain;
    ImageButton ibtnBackFinace;
    ListView lvFinance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        activity = this;

        findViewById();
    }

    private void findViewById() {
        ibtnBackFinace = (ImageButton) findViewById(R.id.ibtnBackFinace);
        lvFinance = (ListView) findViewById(R.id.lvFinance);
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
