package com.vegantravels.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.adapter.FinanceAdapter;
import com.vegantravels.dao.Cabins_TMP;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.model.CabinModel;
import com.vegantravels.model.CabinModelFinal;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FinanceActivity extends BaseActivity implements View.OnClickListener {
    FinanceActivity activity;
    TextView tvCabinMain, tvPaymetMain;
    ImageButton ibtnBackFinace;
    ListView lvFinance;
    FinanceAdapter financeAdapter;
    private long cruizeId = -1;
    private DatabaseManager databaseManager;
    private ArrayList<CabinModel> cabinList;
    private ArrayList<CabinModelFinal> finalList;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        activity = this;
        cruizeId = getIntent().getLongExtra(StaticAccess.KEY_CRUISES_ID, -1);
        databaseManager = new DatabaseManager(activity);
        cabinList = new ArrayList<>();
        finalList = new ArrayList<>();
        findViewById();
    }

    private void findViewById() {
        ibtnBackFinace = (ImageButton) findViewById(R.id.ibtnBackFinace);
        lvFinance = (ListView) findViewById(R.id.lvFinance);
        ibtnBackFinace.setOnClickListener(this);
        new CabinSetupAsyncTask().execute();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtnBackFinace:
                Intent intentExcursion2 = new Intent(activity, ExcursionCruiseList.class);
                intentExcursion2.putExtra(StaticAccess.KEY_Activity_flag,2);
                startActivity(intentExcursion2);
                finishTheActivity();
                break;
        }
    }

    private void finishTheActivity() {
        finish();
    }


    /////**************************   finance data from database using async and mir bro code  *****************************

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
                    if (!m.getExcursionName().equals("") && m.getStatus()!=-1) {
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
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(getResources().getString(R.string.pleaseWait));
        progressDialog.setCancelable(false);
        progressDialog.show();
        DialogNavBarHide.navBarHide(activity, progressDialog);
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
            if (finalList != null) {
                financeAdapter = new FinanceAdapter(activity, modifyList(finalList));
                lvFinance.setAdapter(financeAdapter);
            }
            hideProgressDialog();
            //Toast.makeText(activity, String.valueOf(arrCabinModel.size()), Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<CabinModelFinal> modifyList(ArrayList<CabinModelFinal> list){
        ArrayList<CabinModelFinal> resultList=new ArrayList<>();

        for(CabinModelFinal model: list){
            if(model.getExcursionName().size()>0 )
                resultList.add(model);
        }
        return  resultList;
    }
}
