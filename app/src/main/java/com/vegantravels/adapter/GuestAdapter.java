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

        TextView tvGstName;
        TextView tvCabinNo;
        TextView tvGstNumber;
        TextView tvExcursion;
        TextView tvPaymentStatus;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        GuestAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new GuestAdapter.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.guest_cell, null);

            holder.tvGstName = (TextView) convertView.findViewById(R.id.tvGstName);
            holder.tvCabinNo = (TextView) convertView.findViewById(R.id.tvCabinNo);
            holder.tvGstNumber = (TextView) convertView.findViewById(R.id.tvGstNumber);
            holder.tvExcursion = (TextView) convertView.findViewById(R.id.tvExcursion);
            holder.tvPaymentStatus = (TextView) convertView.findViewById(R.id.tvPaymentStatus);


            convertView.setTag(holder);

        } else {
            holder = (GuestAdapter.ViewHolder) convertView.getTag();
        }

        holder.tvGstName.setText(guestList.get(i).getGuestName());
        holder.tvCabinNo.setText("Cabin: "+guestList.get(i).getCabinNo());
        holder.tvGstNumber.setText("Guest: "+guestList.get(i).getNumberOfGuest());
        holder.tvExcursion.setText(guestList.get(i).getExcursion());
        holder.tvPaymentStatus.setText(guestList.get(i).getPaymentStatus());

        return convertView;
    }
}
