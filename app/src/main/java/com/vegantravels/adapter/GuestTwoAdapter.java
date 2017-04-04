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

public class GuestTwoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Guest> guestList;
    private LayoutInflater layoutInflater;

    public GuestTwoAdapter(Context context, ArrayList<Guest> guestsList) {
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

        TextView tvGuestName;
        TextView tvGuestNo;
        TextView tvShipName;
        TextView tvExcursionName;
        Button btnDelete;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        GuestTwoAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new GuestTwoAdapter.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.guest_cell_two, null);

            holder.tvGuestName = (TextView) convertView.findViewById(R.id.tvGuestName);
            holder.tvGuestNo = (TextView) convertView.findViewById(R.id.tvGuestNo);
            holder.tvShipName = (TextView) convertView.findViewById(R.id.tvShipName);
            holder.tvExcursionName = (TextView) convertView.findViewById(R.id.tvExcursionName);
            holder.btnDelete = (Button) convertView.findViewById(R.id.btnDelete);


            convertView.setTag(holder);

        } else {
            holder = (GuestTwoAdapter.ViewHolder) convertView.getTag();
        }

        holder.tvGuestName.setText(guestList.get(i).getGuestName());
        holder.tvGuestNo.setText("Guest: " + guestList.get(i).getNumberOfGuest());
        holder.tvShipName.setText("Ship" + String.valueOf(i));
        holder.tvExcursionName.setText(guestList.get(i).getExcursion());
//        holder.btnDelete.setText(guestList.get(i).getPaymentStatus());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return convertView;
    }
}
