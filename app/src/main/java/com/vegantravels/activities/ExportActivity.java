package com.vegantravels.activities;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vegantravels.R;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.model.CabinModel;
import com.vegantravels.model.CabinModelFinal;
import com.vegantravels.utilities.StaticAccess;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportActivity extends BaseActivity {

    private DatabaseManager databaseManager;
    private ArrayList<CabinModel> cabinList;
    private ArrayList<CabinModelFinal> finalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        databaseManager = new DatabaseManager(ExportActivity.this);

        finalList=new ArrayList<>();

    }

    public void exportXls(View view) {
        final String fileName = "test.xls";

        //Saving file in external storage
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/veganT");

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

            for (int i = 0; i < cabinList.size(); i++) {



                Label m_idValue1 = new Label(0, i, String.valueOf(cabinList.get(i).getCabinNum()));
                Label m_idValue2 = new Label(1, i, cabinList.get(i).getLName());
                Label m_idValue3 = new Label(2, i, cabinList.get(i).getFName());
                Label m_idValue4 = new Label(3, i, cabinList.get(i).getExcursionName());
                Label m_idValue5 = new Label(4, i, cabinList.get(i).getExcursionDate());
                Label m_idValue6 = new Label(5, i, String.valueOf(cabinList.get(i).getPeople()));
                Label m_idValue7 = new Label(6, i, cabinList.get(i).getExcursionPrice());
                Label m_idValue8 = new Label(7, i, StaticAccess.getPaymentByName(cabinList.get(i).getStatus()));

                sheet.addCell(m_idValue1);
                sheet.addCell(m_idValue2);
                sheet.addCell(m_idValue3);
                sheet.addCell(m_idValue4);
                sheet.addCell(m_idValue5);
                sheet.addCell(m_idValue6);
                sheet.addCell(m_idValue7);
                sheet.addCell(m_idValue8);
            }


           /* Label m_idValue = new Label(0, 1, "1");
            Label m_idValue1 = new Label(1, 1, "Music");
            sheet.addCell(m_idValue);
            sheet.addCell(m_idValue1);*/
            m_workbook.write();
            m_workbook.close();
        } catch (Exception e) {

        }

    }

    Comparator<Integer> comparator = new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    };

    HashMap<Integer ,Integer> cabinWiseCalculationmap=new HashMap<>();


    //step2
    public ArrayList<CabinModel> getAlltheRowsOfACabin(int cabin){
        ArrayList<CabinModel>  result=new ArrayList<>();
        for(int i=0;i<cabinList.size();i++){
            if(cabinList.get(i).getCabinNum()==cabin){
                result.add(cabinList.get(i));
                int calculation=0;
                //if multiple rows
                if(cabinWiseCalculationmap.containsKey(cabin)){
                    calculation=cabinWiseCalculationmap.get(cabin);
                }
                if(cabinList.get(i).getExcursionPrice()!=null){
                    calculation=calculation+Integer.valueOf(cabinList.get(i).getExcursionPrice().trim())
                                            *cabinList.get(i).getPeople();
                }
                cabinWiseCalculationmap.put(cabin,calculation);
            }

        }

        return result;
    }

    //step 1
    public ArrayList<Integer> getUniqCaBinNumber(){
        HashMap<Integer,Integer> map=new HashMap<>();
        ArrayList<Integer> resList=new ArrayList<>();


        for(CabinModel m: cabinList){
            map.put(m.getCabinNum(),m.getCabinNum());
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            resList.add(key);
        }

        //sorting by room number
        Collections.sort(resList, comparator);
        //SortArrayList
        return  resList;
    }

    /////grouping the already sent cabin list
    public HashMap<Integer, ArrayList<CabinModel>> sortCabin(){
        HashMap<Integer, ArrayList<CabinModel>> sortCabin= new HashMap<>();

        ArrayList<Integer> uniqCabin=new ArrayList<>();
        //sorted uniq cabins
        uniqCabin=getUniqCaBinNumber();

        if(uniqCabin!=null)
        for(int i=0;i<uniqCabin.size();i++){
            ArrayList<CabinModel> list=getAlltheRowsOfACabin(uniqCabin.get(i));

            //this model contains same cabin and its multiple excursion
            CabinModelFinal finalmodel=new CabinModelFinal();
            for(CabinModel m: list){
                finalmodel.setCabinNum(m.getCabinNum());
                finalmodel.setFName(m.getFName());
                finalmodel.setLName(m.getLName());
                finalmodel.setVTId(m.getVTId());
                if(m.getExcursionName()!=null) {
                    finalmodel.setExcursionName(m.getExcursionName());
                    finalmodel.setExcursionDate(m.getExcursionDate());
                    finalmodel.setExcursionPrice(Integer.parseInt(m.getExcursionPrice().trim()));
                }
                finalmodel.setPeople(m.getPeople());
                finalmodel.setStatus(m.getStatus());
            }

            finalList.add(finalmodel);
        }



        return sortCabin;
    }

}
