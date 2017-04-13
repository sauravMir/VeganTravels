package com.vegantravels.utilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.dao.Cabins_TMP;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.model.CabinModel;
import com.vegantravels.model.CabinModelFinal;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Created by Rakib on 4/11/2017.
 */

public class ExportXls {


    private DatabaseManager databaseManager;
    private ArrayList<CabinModel> cabinList;
    private ArrayList<CabinModelFinal> finalList;
    private ProgressDialog progressDialog;
    private Context context;
    private long cruizeId = -1;

    public ExportXls(Context context, long cruizeId) {
        this.context = context;
        this.cruizeId = cruizeId;
        databaseManager = new DatabaseManager(context);
        cabinList = new ArrayList<>();
        finalList = new ArrayList<>();
        new CabinSetupAsyncTask().execute();
    }


    public String addspace(int i) {
        String res = "";
        res = String.valueOf(i) + ". ";
        return res;

    }


    boolean isgenerated = false;

    public String getDate() {
        long msTime = System.currentTimeMillis();
        Date curDateTime = new Date(msTime);
        SimpleDateFormat formatter = new SimpleDateFormat("MM'_'dd'_'y_hh:mm");
        String xlsDate = formatter.format(curDateTime);
        return xlsDate;
    }

    public void exportXls() {
        final String fileName = getDate() + "_PAYMENT_STATUS_PER_CABIN.xls";
        //Saving file in external storage
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/VeganTravel");

        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }

        //file path
        File file = new File(directory, fileName);
        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        wbSettings.setEncoding("Cp1252");
        WritableWorkbook m_workbook;

        try {
            m_workbook = Workbook.createWorkbook(file, wbSettings);

            // this will create new new sheet in workbook
            WritableSheet sheet = m_workbook.createSheet(StaticAccess.FINANCIAL_STATUS_PER_CABIN, 0);

            // this will add label in excel sheet
            Label label1 = new Label(0, 0, StaticAccess.KEY_CABIN_NUMBER);
            Label label2 = new Label(1, 0, StaticAccess.KEY_LAST_NAME);
            Label label3 = new Label(2, 0, StaticAccess.KEY_FIRST_NAME);
            Label label4 = new Label(3, 0, StaticAccess.KEY_EXC_BOOKED);
            Label label5 = new Label(4, 0, StaticAccess.KEY_EXC_DATE);
            Label label6 = new Label(5, 0, StaticAccess.KEY_PPL);
            Label label7 = new Label(6, 0, StaticAccess.KEY_TOTAL);
            Label label8 = new Label(7, 0, StaticAccess.KEY_PAYMENT);

            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);
            sheet.addCell(label6);
            sheet.addCell(label7);
            sheet.addCell(label8);

            int rowIndex = 1;

            for (int i = 0; i < finalList.size(); i++) {
                //if person did not add excursion then no calculations needed
                if (finalList.get(i).getExcursionName().size() > 0) {
                    Label m_idValue1 = new Label(0, rowIndex, String.valueOf(finalList.get(i).getCabinNum()));
                    Label m_idValue2 = new Label(1, rowIndex, finalList.get(i).getLName());
                    Label m_idValue3 = new Label(2, rowIndex, finalList.get(i).getFName());
                    sheet.addCell(m_idValue1);
                    sheet.addCell(m_idValue2);
                    sheet.addCell(m_idValue3);
                    float grandTotal = 0;
                    for (int j = 0; j < finalList.get(i).getExcursionName().size(); j++) {

                        Label excursionName = new Label(3, rowIndex, finalList.get(i).getExcursionName().get(j));
                        Label excursionDate = new Label(4, rowIndex, finalList.get(i).getExcursionDate().get(j));
                        Label people = new Label(5, rowIndex, finalList.get(i).getPeople().get(j).toString());
                        Label excursionPrice = new Label(6, rowIndex, String.valueOf((finalList.get(i).getExcursionPrice().get(j) * finalList.get(i).getPeople().get(j))));
                        Label payment = new Label(7, rowIndex, StaticAccess.getPaymentByName(finalList.get(i).getStatus().get(j)));

                        grandTotal += finalList.get(i).getExcursionPrice().get(j) * finalList.get(i).getPeople().get(j);

                        sheet.addCell(excursionName);
                        sheet.addCell(excursionDate);
                        sheet.addCell(people);
                        sheet.addCell(excursionPrice);
                        sheet.addCell(payment);

                        rowIndex++;
                    }
                    //adding total info
                    Label GTotal = new Label(3, rowIndex, "Grand Total");
                    Label total = new Label(6, rowIndex, String.valueOf(grandTotal) + StaticAccess.CURRENCY);
//                    Label paymentname = new Label(7, rowIndex, StaticAccess.getPaymentByName(finalList.get(i).getStatus().get(0)));

                    sheet.addCell(GTotal);
                    sheet.addCell(total);
//                    sheet.addCell(paymentname);

                    rowIndex++;
                }
            }


           /* Label m_idValue = new Label(0, 1, "1");
            Label m_idValue1 = new Label(1, 1, "Music");
            sheet.addCell(m_idValue);
            sheet.addCell(m_idValue1);*/
            m_workbook.write();
            share(directory.getAbsolutePath() + "/" + fileName);
            Toast.makeText(context, "XLS Generated Successfully", Toast.LENGTH_SHORT).show();
            m_workbook.close();
        } catch (Exception e) {
            isgenerated = false;
            Toast.makeText(context, "XLS Could not be Generated", Toast.LENGTH_SHORT).show();


        }

    }

    Comparator<Integer> comparator = new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    };

    HashMap<Integer, Float> cabinWiseCalculationmap = new HashMap<>();


    //step2
    public ArrayList<CabinModel> getAlltheRowsOfACabin(int cabin) {
        ArrayList<CabinModel> result = new ArrayList<>();
        for (int i = 0; i < cabinList.size(); i++) {
            if (cabinList.get(i).getCabinNum() == cabin) {
                result.add(cabinList.get(i));
                float calculation = 0;
                //if multiple rows
                if (cabinWiseCalculationmap.containsKey(cabin)) {
                    calculation = cabinWiseCalculationmap.get(cabin);
                }
                if (!cabinList.get(i).getExcursionPrice().equals("")) {
                    calculation = calculation + Float.parseFloat(cabinList.get(i).getExcursionPrice().trim())
                            * cabinList.get(i).getPeople();
                }
                cabinWiseCalculationmap.put(cabin, calculation);
            }

        }

        return result;
    }

    //step 1
    public ArrayList<Integer> getUniqCaBinNumber() {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> resList = new ArrayList<>();


        for (CabinModel m : cabinList) {
            map.put(m.getCabinNum(), m.getCabinNum());
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            resList.add(key);
        }

        //sorting by room number
        Collections.sort(resList, comparator);
        //SortArrayList
        return resList;
    }

    /////grouping the already sent cabin list
    public HashMap<Integer, ArrayList<CabinModel>> sortCabin() {
        HashMap<Integer, ArrayList<CabinModel>> sortCabin = new HashMap<>();

        ArrayList<Integer> uniqCabin = new ArrayList<>();
        //sorted uniq cabins
        uniqCabin = getUniqCaBinNumber();

        if (uniqCabin != null)
            for (int i = 0; i < uniqCabin.size(); i++) {
                ArrayList<CabinModel> list = getAlltheRowsOfACabin(uniqCabin.get(i));

                //this model contains same cabin and its multiple excursion
                CabinModelFinal finalmodel = new CabinModelFinal();
                for (CabinModel m : list) {
                    finalmodel.setCabinNum(m.getCabinNum());
                    finalmodel.setFName(m.getFName());
                    finalmodel.setLName(m.getLName());
                    finalmodel.setVTId(m.getVTId());
                    if (!m.getExcursionName().equals("") && m.getStatus() != -1) {
                        finalmodel.setExcursionName(m.getExcursionName());
                        finalmodel.setExcursionDate(m.getExcursionDate());
                        finalmodel.setExcursionPrice(Float.parseFloat(m.getExcursionPrice().trim()));
                        finalmodel.setPeople(m.getPeople());
                        finalmodel.setStatus(m.getStatus());
                    }

                }

                finalList.add(finalmodel);
            }


        return sortCabin;
    }


    /////// **************** cabin temp joing table data loading from database  REAZ *************************

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getResources().getString(R.string.pleaseWait));
        progressDialog.setCancelable(false);
        progressDialog.show();
        DialogNavBarHide.navBarHide((Activity) context, progressDialog);
    }

    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    /// for cabinModel
    ArrayList<CabinModel> arrCabinModel = new ArrayList<>();
    ArrayList<Cabins_TMP> arrCabinTemp;

    class CabinSetupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //// insert new Data Here,
            if (cruizeId != -1) {
                arrCabinTemp = databaseManager.cabinTempList(cruizeId);
                if (arrCabinTemp != null) {
                    for (Cabins_TMP cabins_tmp : arrCabinTemp) {
                        CabinModel cabinModel = new CabinModel();
                        cabinModel.setCabinNum(cabins_tmp.getCabinNumber());
                        cabinModel.setPeople(cabins_tmp.getOccupancy());
                        cabinModel.setStatus(cabins_tmp.getPaymentStatus());
                        Guests_TMP guests_tmp = databaseManager.guestTempFromCabin(cabins_tmp.getGuestVT_Id(), cabins_tmp.getCruizeId());
                        if (guests_tmp != null) {

                            cabinModel.setFName(guests_tmp.getFname());
                            cabinModel.setLName(guests_tmp.getLName());
                        } else {
                            cabinModel.setFName("");
                            cabinModel.setLName("");
                        }

                        Excursions_TMP excursions_tmp = databaseManager.getExcursionByExcursionUniqueId(cabins_tmp.getExcursion());
                        if (excursions_tmp != null) {
                            cabinModel.setExcursionDate(excursions_tmp.getFrom());
                            cabinModel.setExcursionPrice(excursions_tmp.getPrice());
                            cabinModel.setExcursionName(excursions_tmp.getTitle());
                        } else {
                            cabinModel.setExcursionDate("");
                            cabinModel.setExcursionPrice("");
                            cabinModel.setExcursionName("");
                        }
                        arrCabinModel.add(cabinModel);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            cabinList = arrCabinModel;
            sortCabin();
            exportXls();
            hideProgressDialog();
        }
    }

    void share(String fileLocation) {

        try {
            Log.e("papth:", fileLocation);
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_SUBJECT, "");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + fileLocation));
            intent.putExtra(Intent.EXTRA_TEXT, " ");
            intent.setData(Uri.parse("mailto:"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            System.out.println("is exception raises during sending mail" + e);
        }
    }
}


