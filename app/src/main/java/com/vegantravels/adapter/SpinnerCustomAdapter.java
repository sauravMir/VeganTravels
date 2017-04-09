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
import com.vegantravels.activities.AddParticipantActivity;
import com.vegantravels.activities.ViewExcursionActivity;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

/**
 * Created by ibrar on 4/9/2017.
 */

public class SpinnerCustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Excursions_TMP> excursionList;
    private LayoutInflater layoutInflater;
    String fDate;

    public SpinnerCustomAdapter(Context context, ArrayList<Excursions_TMP> excursionList, String fDate) {
        this.context = context;
        this.excursionList = excursionList;
        this.fDate = fDate;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        TextView tvCellDate;
        TextView tvCellName;
        TextView tvCellPrice;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        SpinnerCustomAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new SpinnerCustomAdapter.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.spinner_custom_cell, null);

            holder.tvCellDate = (TextView) convertView.findViewById(R.id.tvCellDate);
            holder.tvCellName = (TextView) convertView.findViewById(R.id.tvCellName);
            holder.tvCellPrice = (TextView) convertView.findViewById(R.id.tvCellPrice);

            convertView.setTag(holder);

        } else {
            holder = (SpinnerCustomAdapter.ViewHolder) convertView.getTag();
        }

        holder.tvCellDate.setText(excursionList.get(i).getFrom());
        holder.tvCellName.setText(excursionList.get(i).getTitle());
        holder.tvCellPrice.setText(String.valueOf(excursionList.get(i).getPrice()));
        return convertView;
    }
}
