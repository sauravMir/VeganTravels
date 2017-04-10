package com.vegantravels.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.vegantravels.R;

import com.vegantravels.activities.MainActivity;
import com.vegantravels.dao.Criuzes_TMP;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;

import java.util.ArrayList;

/**
 * Created by ibrar on 4/10/2017.
 */

public class ExcursionManagementAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Criuzes_TMP> cruisesManagementList;
    private LayoutInflater inflater;

    public ExcursionManagementAdapter(Context context, ArrayList<Criuzes_TMP> cruisesManagementList) {
        this.context = context;
        this.cruisesManagementList = cruisesManagementList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cruisesManagementList.size();
    }

    @Override
    public Object getItem(int i) {
        return cruisesManagementList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {

        TextView tvManagementName;
        TextView tvManagementShipName;
        TextView tvManagementDate;
        Button btnFinance;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ExcursionManagementAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new ExcursionManagementAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.excursion_management_cell, null);
            holder.tvManagementName = (TextView) convertView.findViewById(R.id.tvManagementName);
            holder.tvManagementShipName = (TextView) convertView.findViewById(R.id.tvManagementShipName);
            holder.tvManagementDate = (TextView) convertView.findViewById(R.id.tvManagementDate);
            holder.btnFinance = (Button) convertView.findViewById(R.id.btnFinance);
            convertView.setTag(holder);

        } else {
            holder = (ExcursionManagementAdapter.ViewHolder) convertView.getTag();
        }
        holder.tvManagementName.setText(cruisesManagementList.get(i).getName());
        holder.tvManagementShipName.setText(cruisesManagementList.get(i).getShipName());
        holder.tvManagementDate.setText(cruisesManagementList.get(i).getFrom() + "  -  " + cruisesManagementList.get(i).getTo());

        holder.btnFinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

}
