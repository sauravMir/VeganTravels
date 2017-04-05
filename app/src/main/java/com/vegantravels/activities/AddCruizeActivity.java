package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.dao.CriuzeTemporary;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;

import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class AddCruizeActivity extends BaseActivity implements View.OnClickListener {

    private ProgressDialog progressDialog;
    private AddCruizeActivity activity;
    private DatabaseManager databaseManager;
    private Button btnCabinUpload, btnDone;
    private TextView tvCabinUpload;
    private EditText edtCruzeName, edtShipName, edtDateFrom, edtDateTo;
    private CriuzeTemporary aCruize;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cruize);
        activity = this;
        progressDialog = new ProgressDialog(activity);
        databaseManager = new DatabaseManager(activity);

        findViewById();
    }

    private void findViewById() {
        //button
        btnCabinUpload = (Button) findViewById(R.id.btnCabinUpload);
        tvCabinUpload = (TextView) findViewById(R.id.tvCabinUpload);
        btnDone = (Button) findViewById(R.id.btnDone);
        //edtext
        edtCruzeName = (EditText) findViewById(R.id.edtCruzeName);
        edtShipName = (EditText) findViewById(R.id.edtShipName);
        edtDateFrom = (EditText) findViewById(R.id.edtDateFrom);
        edtDateTo = (EditText) findViewById(R.id.edtDateTo);

        //listner
        btnCabinUpload.setOnClickListener(this);
        btnDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCabinUpload:
                //Toast.makeText(activity, "Toast", Toast.LENGTH_SHORT).show();
                setXLS();
                break;
            case R.id.btnDone:
                if (edtCruzeName.length() > 0 && edtShipName.length() > 0 && edtDateFrom.length() > 0 && edtDateTo.length() > 0) {
                    addNewCruize();
                } else {
                    Toast.makeText(activity, "Fill Properly", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void addNewCruize() {
        /// insert new cruize tempo here....
        long cruizeKey = System.currentTimeMillis();
        aCruize = new CriuzeTemporary();
        aCruize.setCruizeName(edtCruzeName.getText().toString());
        aCruize.setShipName(edtShipName.getText().toString());
        aCruize.setDateFrom(edtDateFrom.getText().toString());
        aCruize.setDateTo(edtDateTo.getText().toString());
        aCruize.setCruizeKey(cruizeKey);
        // trigger asynctask to insert cruize in temo table
        new NewCruiseAsyncTask().execute();

    }


    public void setXLS() {
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("abc.xls");
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(0);
            int row = sheet.getRows();
            int col = sheet.getColumns();
            String values = "";

            for (int i = 0; i < row; i++) {
                for (int j = 0; i < col; j++) {
                    Cell cell = sheet.getCell(j, i);
                    values = values + cell.getContents();
                }
                values = values + "\n";
            }
            Log.d("values", values);
            Toast.makeText(activity, values, Toast.LENGTH_SHORT).show();

            tvCabinUpload.setText(values);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class NewCruiseAsyncTask extends AsyncTask<Void, Void, Void> {
        boolean success = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            DialogNavBarHide.navBarHide(activity, progressDialog);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //// insert new Data Here,
            if (aCruize != null) {
                databaseManager.insertCriuzeTemporary(aCruize);
                success = true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            Toast.makeText(activity, "insert Success", Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }
    }
}
