package com.vegantravels.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.activities.AddExcursionActivity;
import com.vegantravels.activities.ExportExcursionGuestListActivity;
import com.vegantravels.dao.Cabins_TMP;
import com.vegantravels.dao.Criuzes_TMP;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.model.GuestExport;
import com.vegantravels.utilities.StaticAccess;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private ArrayList<Guests_TMP> guestList = new ArrayList<>();

    private ArrayList<GuestExport> guestListExport = new ArrayList<>();

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

        LinearLayout tvExportExcursionDateln;
        LinearLayout tvExportExcursionTimeln;
        LinearLayout tvExportExcursionNameln;
        LinearLayout tvExportExcursionPPPln;
        LinearLayout tvExportExcursionCruizeNameln;

        ImageButton ibtnExportExcursionView;
        ImageButton ibtnExportExcursionPrint;
       // ImageButton ibtnExportExcursionDelete;
        ImageButton ibtnExportExcursionEdit;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ExportExcursionAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new ExportExcursionAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.export_excursion_cell, null);

            holder.tvExportExcursionDate = (TextView) convertView.findViewById(R.id.tvExportExcursionDate);
            holder.tvExportExcursionTime = (TextView) convertView.findViewById(R.id.tvExportExcursionTime);
            holder.tvExportExcursionName = (TextView) convertView.findViewById(R.id.tvExportExcursionName);
            holder.tvExportExcursionPPP = (TextView) convertView.findViewById(R.id.tvExportExcursionPPP);
            holder.tvExportExcursionCruizeName = (TextView) convertView.findViewById(R.id.tvExportExcursionCruizeName);

            holder.tvExportExcursionDateln = (LinearLayout) convertView.findViewById(R.id.tvExportExcursionDateln);
            holder.tvExportExcursionTimeln = (LinearLayout) convertView.findViewById(R.id.tvExportExcursionTimeln);
            holder.tvExportExcursionNameln = (LinearLayout) convertView.findViewById(R.id.tvExportExcursionNameln);
            holder.tvExportExcursionPPPln =  (LinearLayout) convertView.findViewById(R.id.tvExportExcursionPPPln);
            holder.tvExportExcursionCruizeNameln = (LinearLayout) convertView.findViewById(R.id.tvExportExcursionCruizeNameln);


            holder.ibtnExportExcursionView = (ImageButton) convertView.findViewById(R.id.ibtnExportExcursionView);
            holder.ibtnExportExcursionPrint = (ImageButton) convertView.findViewById(R.id.ibtnExportExcursionPrint);
           // holder.ibtnExportExcursionDelete = (ImageButton) convertView.findViewById(R.id.ibtnExportExcursionDelete);
            holder.ibtnExportExcursionEdit = (ImageButton) convertView.findViewById(R.id.ibtnExportExcursionEdit);
            convertView.setTag(holder);

        } else {
            holder = (ExportExcursionAdapter.ViewHolder) convertView.getTag();
        }
        criuzes_tmp = databaseManager.getCruizeByCruizeUniqueID(excursionsTmpsList.get(position).getCruzeId());


        holder.tvExportExcursionDate.setText(String.valueOf(excursionsTmpsList.get(position).getFrom()));
        holder.tvExportExcursionTime.setText(String.valueOf(excursionsTmpsList.get(position).getTime()));
        holder.tvExportExcursionName.setText(String.valueOf(excursionsTmpsList.get(position).getTitle()));
        holder.tvExportExcursionPPP.setText(String.valueOf(excursionsTmpsList.get(position).getPrice()));
        if (criuzes_tmp != null) {
            holder.tvExportExcursionCruizeName.setText(String.valueOf(criuzes_tmp.getName()));
        }

        final View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ExportExcursionGuestListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(StaticAccess.KEY_CRUISES_ID, excursionsTmpsList.get(position).getCruzeId());
                intent.putExtra(StaticAccess.KEY_EXCURSION_NAME, excursionsTmpsList.get(position).getTitle());
                intent.putExtra(StaticAccess.KEY_EXCURSION_UNIQUE_ID, excursionsTmpsList.get(position).getExcursionUniqueId());
                context.startActivity(intent);
            }
        };

        holder.tvExportExcursionDateln.setOnClickListener(listener);
        holder.tvExportExcursionTimeln.setOnClickListener(listener);
        holder.tvExportExcursionNameln.setOnClickListener(listener);
        holder.tvExportExcursionPPPln.setOnClickListener(listener);
        holder.tvExportExcursionCruizeNameln.setOnClickListener(listener);


        holder.ibtnExportExcursionView.setOnClickListener(listener);


        holder.ibtnExportExcursionPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuestSyncAsyncTask guestSyncAsyncTask = new GuestSyncAsyncTask(excursionsTmpsList.get(position).getCruzeId(), excursionsTmpsList.get(position).getExcursionUniqueId(), excursionsTmpsList.get(position).getTitle());
                guestSyncAsyncTask.execute();
            }
        });
//        holder.ibtnExportExcursionDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        holder.ibtnExportExcursionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddExcursionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //added in 2nd edition for editting from export
                intent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, excursionsTmpsList.get(position).getCruzeId());
                intent.putExtra(StaticAccess.KEY_EXCURSION_ID, excursionsTmpsList.get(position).getId());
                intent.putExtra("came",100);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private ArrayList<Cabins_TMP> cabins_tmpsList;

    class GuestSyncAsyncTask extends AsyncTask<Void, Void, Void> {
        private long cruizeUniqID = -1;
        private long excursionUniqID = -1;
        private String excursionName;
        private boolean isGenerated = false;

        public GuestSyncAsyncTask(long cruizeUniqID, long excursionUniqID, String excursionName) {
            this.cruizeUniqID = cruizeUniqID;
            this.excursionName = excursionName;
            this.excursionUniqID = excursionUniqID;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            guestList = new ArrayList<>();
            cabins_tmpsList = new ArrayList<>();
            if (cruizeUniqID != -1) {
//                guestList = databaseManager.listGuestByUniqueId(cruizeUniqID);
                cabins_tmpsList = databaseManager.getGuestDeatilByExcursionCruizeID(excursionUniqID, cruizeUniqID);
                if (cabins_tmpsList != null) {
                    for (int i = 0; i < cabins_tmpsList.size(); i++) {
                        GuestExport model = new GuestExport();
                        Guests_TMP guests_tmp = databaseManager.guestTempFromCabin(cabins_tmpsList.get(i).getGuestVT_Id(),
                                cabins_tmpsList.get(i).getCruizeId());

                        model.setFirstName(guests_tmp.getFname());
                        model.setLastName(guests_tmp.getLName());
                        model.setCabins_tmp(cabins_tmpsList.get(i));

                        guestListExport.add(model);

                    }
                    long msTime = System.currentTimeMillis();
                    Date curDateTime = new Date(msTime);
                    SimpleDateFormat formatter = new SimpleDateFormat("MM'_'dd'_'y_hh:mm");
                    String xlsDate = formatter.format(curDateTime);
                    if (guestListExport != null)
                        isGenerated = exportXls(xlsDate + "_" + excursionName, guestListExport);

                } else {
                    //please add a toast here
                    isGenerated = false;
                }


            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hideProgressDialog();
            if (isxlsGenerated) {
                Toast.makeText(context, "XLS Generated!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "No Booked Data Found", Toast.LENGTH_SHORT).show();
            }

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

    private boolean isxlsGenerated = false;
    private int nrCount = 0;

    public boolean exportXls(String xlsName, ArrayList<GuestExport> guestListExport) {
        final String fileName = xlsName + ".xls";
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
            WritableSheet sheet = m_workbook.createSheet(StaticAccess.GUEST_LIST_PER_EXCURSION, 0);

            // this will add label in excel sheet
            Label label1 = new Label(0, 0, StaticAccess.NR_NO);
            Label label2 = new Label(1, 0, StaticAccess.KEY_VT_ID);
            Label label3 = new Label(2, 0, StaticAccess.KEY_FIRST_NAME);
            Label label4 = new Label(3, 0, StaticAccess.KEY_LAST_NAME);
            Label label5 = new Label(4, 0, StaticAccess.KEY_CABIN_NUMBER);
            Label label6 = new Label(5, 0, StaticAccess.GUEST_IN_CABIN);


            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);
            sheet.addCell(label6);


            for (int i = 0; i < guestListExport.size(); i++) {
                nrCount++;
                int j = i + 1;
                Label m_idValue1 = new Label(0, j, String.valueOf(nrCount));
                Label m_idValue2 = new Label(1, j, String.valueOf(guestListExport.get(i).getCabins_tmp().getGuestVT_Id()));
                Label m_idValue3 = new Label(2, j, guestListExport.get(i).getFirstName());
                Label m_idValue4 = new Label(3, j, guestListExport.get(i).getLastName());
                Label m_idValue5 = new Label(4, j, String.valueOf(guestListExport.get(i).getCabins_tmp().getCabinNumber()));
                Label m_idValue6 = new Label(5, j, String.valueOf(guestListExport.get(i).getCabins_tmp().getOccupancy()));
                sheet.addCell(m_idValue1);
                sheet.addCell(m_idValue2);
                sheet.addCell(m_idValue3);
                sheet.addCell(m_idValue4);
                sheet.addCell(m_idValue5);
                sheet.addCell(m_idValue6);

            }

            m_workbook.write();
            m_workbook.close();
            nrCount = 0;
            isxlsGenerated = true;
            share(directory.getAbsolutePath() + "/" + fileName);
        } catch (Exception e) {

        }
        return isxlsGenerated;

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
