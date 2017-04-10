package com.vegantravels.activities;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vegantravels.R;
import com.vegantravels.utilities.StaticAccess;

import java.io.File;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
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




            Label m_idValue = new Label(0, 1, "1");
            Label m_idValue1 = new Label(1, 1, "Music");
            sheet.addCell(m_idValue);
            sheet.addCell(m_idValue1);
            m_workbook.write();
            m_workbook.close();
        } catch (Exception e) {

        }

    }
}
