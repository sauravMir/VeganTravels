package com.vegantravels.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.activities.AddCruizeActivity;
import com.vegantravels.activities.AddExcursionActivity;
import com.vegantravels.activities.GuestListThreeActivity;
import com.vegantravels.dao.Criuzes_TMP;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

/**
 * Created by ibrar on 4/9/2017.
 */

public class ExcursionAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Excursions_TMP> excursionList;
    private LayoutInflater inflater;
    private String flag;

    public ExcursionAdapter(Context context, ArrayList<Excursions_TMP> excursionList) {
        this.context = context;
        this.excursionList = excursionList;
        this.flag = flag;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return excursionList.size();
    }

    @Override
    public Object getItem(int i) {
        return excursionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {

        TextView tvExcursionDate;
        TextView tvExcursionTime;
        TextView tvExcursionName;
        TextView tvExcursionPPP;
        TextView tvExcursionMaxGst;
        ImageButton ibtnExcursionEdit;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ExcursionAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new ExcursionAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.excursion_cell, null);
            holder.tvExcursionDate = (TextView) convertView.findViewById(R.id.tvExcursionDate);
            holder.tvExcursionTime = (TextView) convertView.findViewById(R.id.tvExcursionTime);
            holder.tvExcursionName = (TextView) convertView.findViewById(R.id.tvExcursionName);
            holder.tvExcursionPPP = (TextView) convertView.findViewById(R.id.tvExcursionPPP);
            holder.tvExcursionMaxGst = (TextView) convertView.findViewById(R.id.tvExcursionMaxGst);
            holder.ibtnExcursionEdit = (ImageButton) convertView.findViewById(R.id.ibtnExcursionEdit);

            convertView.setTag(holder);

        } else {
            holder = (ExcursionAdapter.ViewHolder) convertView.getTag();
        }
        holder.tvExcursionDate.setText(excursionList.get(i).getFrom());
        holder.tvExcursionTime.setText(excursionList.get(i).getTime());
        holder.tvExcursionName.setText(excursionList.get(i).getTitle());
        holder.tvExcursionPPP.setText(excursionList.get(i).getPrice());
        holder.tvExcursionMaxGst.setText(String.valueOf(excursionList.get(i).getMaxNumberOfGuest()));

        final int position = i;

        holder.ibtnExcursionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edtiIntent = new Intent(context, AddExcursionActivity.class);
                edtiIntent.putExtra(StaticAccess.KEY_EXCURSION_ID, excursionList.get(position).getId());
                edtiIntent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, excursionList.get(position).getExcursionUniqueId());
                context.startActivity(edtiIntent);
            }
        });


        return convertView;
    }

}
