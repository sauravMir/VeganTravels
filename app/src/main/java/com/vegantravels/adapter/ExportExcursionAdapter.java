package com.vegantravels.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.activities.GuestListThreeActivity;
import com.vegantravels.dao.Criuzes_TMP;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.utilities.StaticAccess;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Created by ibrar on 4/10/2017.
 */

public class ExportExcursionAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Excursions_TMP> excursionsTmpsList;
    private LayoutInflater inflater;
    private IDatabaseManager databaseManager;
    Criuzes_TMP criuzes_tmp;
    private ArrayList<Guests_TMP> guestList;
    private ProgressDialog progressDialog;

    public ExportExcursionAdapter(Context context, ArrayList<Excursions_TMP> excursionsTmpsList) {
        this.context = context;
        this.excursionsTmpsList = excursionsTmpsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        databaseManager = new DatabaseManager(context);
    }

    @Override
    public int getCount() {
        return excursionsTmpsList.size();
    }

    @Override
    public Object getItem(int i) {
        return excursionsTmpsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {

        TextView tvExportExcursionDate;
        TextView tvExportExcursionTime;
        TextView tvExportExcursionName;
        TextView tvExportExcursionPPP;
        TextView tvExportExcursionCruizeName;
        ImageButton ibtnExportExcursionView;
        ImageButton ibtnExportExcursionPrint;
        ImageButton ibtnExportExcursionDelete;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ExportExcursionAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new ExportExcursionAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.export_excursion_cell, null);

            holder.tvExportExcursionDate = (TextView) convertView.findViewById(R.id.tvExportExcursionDate);
            holder.tvExportExcursionTime = (TextView) convertView.findViewById(R.id.tvExportExcursionTime);
            holder.tvExportExcursionName = (TextView) convertView.findViewById(R.id.tvExportExcursionName);
            holder.tvExportExcursionPPP = (TextView) convertView.findViewById(R.id.tvExportExcursionPPP);
            holder.tvExportExcursionCruizeName = (TextView) convertView.findViewById(R.id.tvExportExcursionCruizeName);
            holder.ibtnExportExcursionView = (ImageButton) convertView.findViewById(R.id.ibtnExportExcursionView);
            holder.ibtnExportExcursionPrint = (ImageButton) convertView.findViewById(R.id.ibtnExportExcursionPrint);
            holder.ibtnExportExcursionDelete = (ImageButton) convertView.findViewById(R.id.ibtnExportExcursionDelete);

            convertView.setTag(holder);

        } else {
            holder = (ExportExcursionAdapter.ViewHolder) convertView.getTag();
        }
        criuzes_tmp = databaseManager.getCruizeByCruizeUniqueID(excursionsTmpsList.get(i).getCruzeId());


        holder.tvExportExcursionDate.setText(String.valueOf(excursionsTmpsList.get(i).getFrom()));
        holder.tvExportExcursionTime.setText(String.valueOf(excursionsTmpsList.get(i).getTime()));
        holder.tvExportExcursionName.setText(String.valueOf(excursionsTmpsList.get(i).getTitle()));
        holder.tvExportExcursionPPP.setText(String.valueOf(excursionsTmpsList.get(i).getPrice()));
        if (criuzes_tmp != null) {
            holder.tvExportExcursionCruizeName.setText(String.valueOf(criuzes_tmp.getName()));
        }
        final int position = i;

        holder.ibtnExportExcursionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (criuzes_tmp != null) {
                    Intent intentGuest = new Intent(context, GuestListThreeActivity.class);
                    intentGuest.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intentGuest.putExtra(StaticAccess.KEY_CRUISES_ID, criuzes_tmp.getId());
                    intentGuest.putExtra(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID, excursionsTmpsList.get(position).getCruzeId());
                    intentGuest.putExtra(StaticAccess.KEY_INTENT_DATE, "From :" + criuzes_tmp.getFrom() + "\n To :" + criuzes_tmp.getTo());
                    context.startActivity(intentGuest);
                }

            }
        });
        holder.ibtnExportExcursionPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuestSyncAsyncTask guestSyncAsyncTask = new GuestSyncAsyncTask(excursionsTmpsList.get(position).getCruzeId());
                guestSyncAsyncTask.execute();
            }
        });
        holder.ibtnExportExcursionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    class GuestSyncAsyncTask extends AsyncTask<Void, Void, Void> {
        private long cruizeUniqID = -1;

        public GuestSyncAsyncTask(long cruizeUniqID) {
            this.cruizeUniqID = cruizeUniqID;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            guestList = new ArrayList<>();
            if (cruizeUniqID != -1) {
                guestList = databaseManager.listGuestByUniqueId(cruizeUniqID);
                exportXls("_guest_list", guestList);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            hideProgressDialog();
            super.onPostExecute(aVoid);

        }
    }

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getResources().getString(R.string.pleaseWait));
        progressDialog.show();
        DialogNavBarHide.navBarHide((Activity) context, progressDialog);
    }

    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    public void exportXls(String xlsName, ArrayList<Guests_TMP> guestList) {
        final String fileName = xlsName + ".xls";
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
            Label label1 = new Label(0, 0, StaticAccess.KEY_VT_ID);
            Label label2 = new Label(1, 0, StaticAccess.KEY_LAST_NAME);
            Label label3 = new Label(2, 0, StaticAccess.KEY_FIRST_NAME);
            Label label4 = new Label(3, 0, StaticAccess.KEY_CABIN_NUMBER);


            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);


            for (int i = 0; i < guestList.size(); i++) {
                int j = i + 1;
                Label m_idValue1 = new Label(0, j, String.valueOf(guestList.get(i).getGuestVT_Id()));
                Label m_idValue2 = new Label(1, j, guestList.get(i).getLName());
                Label m_idValue3 = new Label(2, j, guestList.get(i).getFname());
                Label m_idValue4 = new Label(3, j, String.valueOf(guestList.get(i).getCabinNumber()));
                sheet.addCell(m_idValue1);
                sheet.addCell(m_idValue2);
                sheet.addCell(m_idValue3);
                sheet.addCell(m_idValue4);

            }

            m_workbook.write();
            m_workbook.close();
        } catch (Exception e) {

        }

    }

}
