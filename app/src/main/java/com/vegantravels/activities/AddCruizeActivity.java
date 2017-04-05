package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.pdf.codec.Base64;
import com.vegantravels.R;
import com.vegantravels.asynctask.GenericsAsyncTask;
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
    private Button btnCabinUpload;
    private TextView tvCabinUpload;
    EditText edtCruzeName;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cruize);
        activity = this;
        progressDialog = new ProgressDialog(activity);
        databaseManager = new DatabaseManager(activity);
        btnCabinUpload = (Button) findViewById(R.id.btnCabinUpload);
        tvCabinUpload = (TextView) findViewById(R.id.tvCabinUpload);
        edtCruzeName = (EditText) findViewById(R.id.edtCruzeName);
        btnCabinUpload.setOnClickListener(this);
        //findViewById();
//        GenericsAsyncTask cruisesGenericsAsyncTask = new GenericsAsyncTask(activity, null, "inserting");
    }

   /* private void findViewById() {
        btnCabinUpload = (Button) findViewById(R.id.btnCabinUpload);
        tvCabinUpload = (TextView) findViewById(R.id.tvCabinUpload);
        btnCabinUpload.setOnClickListener(this);

    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCabinUpload:
                Toast.makeText(activity, "Toast", Toast.LENGTH_SHORT).show();
                setXLS();
                break;
        }
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
            tvCabinUpload.setText(values);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class NewCruiseAsyncTask extends AsyncTask<Void, Void, Void> {

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
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.show();
            super.onPostExecute(aVoid);
        }
    }
}
