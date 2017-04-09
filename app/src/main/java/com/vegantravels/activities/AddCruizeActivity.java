package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.dao.Cabins_TMP;
import com.vegantravels.dao.Criuzes_TMP;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.dialog.AllDialog;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.model.XlsModel;
import com.vegantravels.utilities.FileUtils;
import com.vegantravels.utilities.StaticAccess;

import java.io.File;
import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import static android.content.ContentValues.TAG;

public class AddCruizeActivity extends BaseActivity implements View.OnClickListener {

    private ProgressDialog progressDialog;
    private AddCruizeActivity activity;
    private DatabaseManager databaseManager;
    private Button btnCabinUpload, btnDone;
    private TextView tvCabinUpload;
    private EditText edtCruzeName, edtShipName;
    private TextView tvDateFrom, tvDateTo;
    private Criuzes_TMP aCruize, upCruize;
    private ImageButton ibtnBackCruize;
    AllDialog allDialog;

    private ArrayList<XlsModel> xlsDataList;
    private XlsModel aXls;
    private long cruizeID = -1;
    private long cruizeUniqueID = -1;
    private Criuzes_TMP criuzes_tmp;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cruize);
        activity = this;
        progressDialog = new ProgressDialog(activity);
        databaseManager = new DatabaseManager(activity);
        allDialog = new AllDialog(activity);
        cruizeID = getIntent().getLongExtra(StaticAccess.KEY_CRUISES_ID, -1);
        cruizeUniqueID = getIntent().getLongExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, -1);
        findViewById();
        if (cruizeID != -1 && cruizeUniqueID != -1) {
            fillEditableData();
        }


    }

    /// for editing
    private void fillEditableData() {
        new EditCruiseAsyncTask().execute();

    }

    private void findViewById() {
        //button
        btnCabinUpload = (Button) findViewById(R.id.btnCabinUpload);
        tvCabinUpload = (TextView) findViewById(R.id.tvCabinUpload);
        btnDone = (Button) findViewById(R.id.btnDone);
        //edtext
        edtCruzeName = (EditText) findViewById(R.id.edtCruzeName);
        edtShipName = (EditText) findViewById(R.id.edtShipName);
        tvDateFrom = (TextView) findViewById(R.id.tvDateFrom);
        tvDateTo = (TextView) findViewById(R.id.tvDateTo);
        ibtnBackCruize = (ImageButton) findViewById(R.id.ibtnBackCruize);

        //listner
        btnCabinUpload.setOnClickListener(this);
        btnDone.setOnClickListener(this);
        ibtnBackCruize.setOnClickListener(this);
        tvDateFrom.setOnClickListener(this);
        tvDateTo.setOnClickListener(this);


        xlsDataList = new ArrayList<>();
    }

    boolean goForEdit = false; /// for controlling edit post or get cruize

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCabinUpload:
                showFileChooser();
                break;
            case R.id.btnDone:
                if (cruizeUniqueID != -1 && cruizeUniqueID != -1) {
                    /// edit
                    if (edtCruzeName.length() > 0 && edtShipName.length() > 0 && tvDateFrom.length() > 0 && tvDateTo.length() > 0) {
                        goForEdit = true;
                        updateCruize();
                    } else {
                        Toast.makeText(activity, "Fill Properly", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    /// add
                    if (edtCruzeName.length() > 0 && edtShipName.length() > 0 && tvDateFrom.length() > 0 && tvDateTo.length() > 0 && xlsDataList.size() > 0) {
                        addNewCruize();
                    } else {
                        Toast.makeText(activity, "Fill Properly", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.ibtnBackCruize:
                startActivity(new Intent(activity, MainActivity.class));
                finishTheActivity();
                break;
            case R.id.tvDateFrom:
                allDialog.setCustomDate(tvDateFrom, StaticAccess.DATE_FROM);
                break;

            case R.id.tvDateTo:
                allDialog.setCustomDate(tvDateTo, StaticAccess.DATE_TO);
                break;

        }
    }

    private void updateCruize() {
        upCruize = new Criuzes_TMP();
        upCruize.setId(cruizeID);
        upCruize.setName(edtCruzeName.getText().toString());
        upCruize.setShipName(edtShipName.getText().toString());
        upCruize.setFrom(tvDateFrom.getText().toString());
        upCruize.setCruizeUniqueId(cruizeUniqueID);
        upCruize.setTo(tvDateTo.getText().toString());
        new EditCruiseAsyncTask().execute();
    }

    private void addNewCruize() {
        /// insert new cruize tempo here....
        long cruizeKey = System.currentTimeMillis();
        aCruize = new Criuzes_TMP();
        aCruize.setName(edtCruzeName.getText().toString());
        aCruize.setShipName(edtShipName.getText().toString());
        aCruize.setFrom(tvDateFrom.getText().toString());
        aCruize.setCruizeUniqueId(cruizeKey);
        aCruize.setTo(tvDateTo.getText().toString());
        // aCruize.setCruizeKey(cruizeKey);
        // trigger asynctask to insert cruize in temo table
        new NewCruiseAsyncTask().execute();

    }


    //// reading xls file per row 9 column fixed ,
    public void setXLS(String path) {
        try {
            if (path != null && path != "") {
                File file = new File(path);
                FileInputStream fileInputStream = new FileInputStream(file);
                xlsDataList = new ArrayList<>();
//            AssetManager assetManager = getAssets();
//            InputStream inputStream = assetManager.open("client.xls");
                WorkbookSettings ws = new WorkbookSettings();
                ws.setEncoding("Cp1252");
                Workbook workbook = Workbook.getWorkbook(fileInputStream, ws);
                Sheet sheet = workbook.getSheet(0);
                int row = sheet.getRows();///xls doc total row
                int col = sheet.getColumns(); /// xls doc total columns

                for (int i = 1; i < row; i++) {
                    aXls = new XlsModel();
                    aXls.setVTID(sheet.getCell(1, i).getContents());
                    aXls.setFirstNameGuestOne(sheet.getCell(2, i).getContents());
                    aXls.setLastNameGuestOne(sheet.getCell(3, i).getContents());
                    aXls.setSexGuestOne(sheet.getCell(4, i).getContents());
                    aXls.setFirstNameGuestTwo(sheet.getCell(5, i).getContents());
                    aXls.setLastNameGuestTwo(sheet.getCell(6, i).getContents());
                    aXls.setSexGuestTwo(sheet.getCell(7, i).getContents());
                    aXls.setCabinNo(sheet.getCell(8, i).getContents());
                    aXls.setGuestInCabin(sheet.getCell(9, i).getContents());
                    xlsDataList.add(aXls);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void finishTheActivity() {
        finish();
    }

    ///// ************ FILE PICKER SHITS ************////
    private static final int FILE_SELECT_CODE = 0;

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    String path = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d(TAG, "File Uri: " + uri.toString());
                    // Get the path

                    try {
                        path = FileUtils.getPath(this, uri);
                        tvCabinUpload.setText(path);
                        setXLS(path); // read data and fill arraylist
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                        Log.d(TAG, "File Path: " + e.toString());
                    }
                    Log.d(TAG, "File Path: " + path);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
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
                Criuzes_TMP insertCruise = new Criuzes_TMP();
                Guests_TMP insertGuest = new Guests_TMP();
                insertCruise = databaseManager.insertCriuzeTemporary(aCruize);
                if (xlsDataList.size() > 0)
                    for (int i = 0; i < xlsDataList.size(); i++) {
                        Cabins_TMP insertCabinPayment = new Cabins_TMP();
                        Guests_TMP mGuest = new Guests_TMP();
                        mGuest.setFname(xlsDataList.get(i).getFirstNameGuestOne());
                        mGuest.setLName(xlsDataList.get(i).getLastNameGuestOne());
                        mGuest.setCabinNumber(Integer.valueOf(xlsDataList.get(i).getCabinNo()));
                        mGuest.setGuestVT_Id(xlsDataList.get(i).getVTID());
                        mGuest.setGuestUniqueId(insertCruise.getCruizeUniqueId());
                        insertGuest = databaseManager.insertGuestTemporary(mGuest);

                        if (insertGuest != null)
                            insertCabinPayment.setCabinNumber(insertGuest.getCabinNumber());
                        insertCabinPayment.setGuestVT_Id(insertGuest.getGuestVT_Id());
                        insertCabinPayment.setOccupancy(Integer.valueOf(xlsDataList.get(i).getGuestInCabin()));
//                        insertCabinPayment.setNumberOfGuest(Integer.valueOf(xlsDataList.get(i).getGuestInCabin()));
                        // here is CruiseId
                        insertCabinPayment.setCruizeId(insertCruise.getCruizeUniqueId());
                        insertCabinPayment.setCabinUniqueId(insertCruise.getCruizeUniqueId());
//                        insertCabinPayment.setDeviceDate("d");

                        databaseManager.insertCabinTemp(insertCabinPayment);

//                  insertGuest.setGuestUniqueId(insertCruise.getCruizeUniqueId());
//                    insertGuest.setCabinNumber(insertCruise.getCruizeUniqueId());

                    }


            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            Toast.makeText(activity, "insert Success", Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
            startActivity(new Intent(activity, MainActivity.class));
            finishTheActivity();
        }
    }


    class EditCruiseAsyncTask extends AsyncTask<Void, Void, Void> {
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
            if (goForEdit) {
                /// update cruize
                databaseManager.updateCriuzeTemporary(upCruize);

            } else {
                // get cruize
                if (cruizeID != -1 && cruizeUniqueID != -1) {
                    criuzes_tmp = databaseManager.getCruiseTempById(cruizeID);

                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!goForEdit) {
                if (criuzes_tmp != null) {
                    /// fill data for edit
                    edtCruzeName.setText(String.valueOf(criuzes_tmp.getName()));
                    edtShipName.setText(String.valueOf(criuzes_tmp.getShipName()));
                    tvDateFrom.setText(String.valueOf(criuzes_tmp.getFrom()));
                    tvDateTo.setText(String.valueOf(criuzes_tmp.getTo()));
                    tvCabinUpload.setText("");
                    btnCabinUpload.setVisibility(View.GONE);

                }
            } else {
                Toast.makeText(activity, "Update done", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(activity, MainActivity.class));
                finishTheActivity();
            }
            progressDialog.dismiss();
        }
    }

}
