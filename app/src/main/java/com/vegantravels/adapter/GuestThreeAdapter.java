package com.vegantravels.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.model.Guest;

import java.util.ArrayList;

/**
 * Created by Rakib on 4/3/2017.
 */

public class GuestThreeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Guest> guestList;
    private LayoutInflater layoutInflater;

    public GuestThreeAdapter(Context context, ArrayList<Guest> guestsList) {
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

        TextView tvCabinNo;
        TextView tvGuestName;
        TextView tvCruiseDate;
//        Button btnDelete;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        GuestThreeAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new GuestThreeAdapter.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.guest_cell_three, null);

            holder.tvGuestName = (TextView) convertView.findViewById(R.id.tvGuestName);
            holder.tvCabinNo = (TextView) convertView.findViewById(R.id.tvCabinNo);
            holder.tvCruiseDate = (TextView) convertView.findViewById(R.id.tvCruiseDate);
//            holder.btnDelete = (Button) convertView.findViewById(R.id.btnDelete);


            convertView.setTag(holder);

        } else {
            holder = (GuestThreeAdapter.ViewHolder) convertView.getTag();
        }

        holder.tvGuestName.setText(guestList.get(i).getGuestName());
        holder.tvCabinNo.setText(guestList.get(i).getCabinNo());
        holder.tvCruiseDate.setText(String.valueOf(guestList.get(i)));

//        holder.tvExcursionName.setText(guestList.get(i).getExcursion());
//        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        return convertView;
    }
}
