package com.vegantravels.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

/**
 * Created by ibrar on 4/10/2017.
 */

public class ExportExcursionAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Excursions_TMP> excursionsTmpsList;
    private LayoutInflater inflater;
    private IDatabaseManager databaseManager;
    Criuzes_TMP criuzes_tmp;

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

            convertView.setTag(holder);

        } else {
            holder = (ExportExcursionAdapter.ViewHolder) convertView.getTag();
        }
        criuzes_tmp = databaseManager.getCruizeByCruizeUniqueID(excursionsTmpsList.get(i).getCruzeId());

        if(criuzes_tmp != null)
        holder.tvExportExcursionDate.setText(String.valueOf(excursionsTmpsList.get(i).getFrom()));
        holder.tvExportExcursionTime.setText(String.valueOf(excursionsTmpsList.get(i).getTime()));
        holder.tvExportExcursionName.setText(String.valueOf(excursionsTmpsList.get(i).getTitle()));
        holder.tvExportExcursionPPP.setText(String.valueOf(excursionsTmpsList.get(i).getPrice()));
        holder.tvExportExcursionCruizeName.setText(String.valueOf(criuzes_tmp.getName()));

        final int position = i;

        holder.ibtnExportExcursionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentGuest = new Intent(context, GuestListThreeActivity.class);
                intentGuest.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentGuest.putExtra(StaticAccess.KEY_CRUISES_ID, criuzes_tmp.getId());
                intentGuest.putExtra(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID, excursionsTmpsList.get(position).getCruzeId());
                intentGuest.putExtra(StaticAccess.KEY_INTENT_DATE, "From :" + criuzes_tmp.getFrom() + "\n To :" + criuzes_tmp.getTo());
                context.startActivity(intentGuest);

            }
        });
        holder.ibtnExportExcursionPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return convertView;
    }

}
