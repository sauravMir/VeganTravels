package com.vegantravels.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.model.Guest;

import java.util.ArrayList;

/**
 * Created by Rakib on 4/3/2017.
 */

public class GuestAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Guest> guestList;
    private LayoutInflater layoutInflater;

    public GuestAdapter(Context context, ArrayList<Guest> guestsList) {
        this.context = context;
        this.guestList = guestsList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return guestList.size();
    }

    @Override
    public Object getItem(int i) {
        return guestList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        CruisesAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new CruisesAdapter.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.guest_cell, null);
            holder.tvCruiseName = (TextView) convertView.findViewById(R.id.tvCruiseName);
            holder.tvShipName = (TextView) convertView.findViewById(R.id.tvShipName);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            convertView.setTag(holder);

        } else {
            holder = (CruisesAdapter.ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
