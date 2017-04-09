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
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

/**
 * Created by Rakib on 4/3/2017.
 */

public class GuestThreeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Guests_TMP> guestList;
    private LayoutInflater layoutInflater;
    String fDate;

    public GuestThreeAdapter(Context context, ArrayList<Guests_TMP> guestsList, String fDate) {
        this.context = context;
        this.guestList = guestsList;
        this.fDate = fDate;
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
        TextView tvFirstName;
        TextView tvCruiseDate;
        ImageButton ibtnEditGuest;
        ImageButton ibtnAddGuest;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        GuestThreeAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new GuestThreeAdapter.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.guest_cell_three, null);

            holder.tvCruiseDate = (TextView) convertView.findViewById(R.id.tvCruiseDate);
            holder.tvGuestName = (TextView) convertView.findViewById(R.id.tvGuestName);
            holder.tvFirstName = (TextView) convertView.findViewById(R.id.tvFirstName);
            holder.tvCabinNo = (TextView) convertView.findViewById(R.id.tvCabinNo);
            holder.ibtnEditGuest = (ImageButton) convertView.findViewById(R.id.ibtnEditGuest);
            holder.ibtnAddGuest = (ImageButton) convertView.findViewById(R.id.ibtnAddGuest);

            convertView.setTag(holder);

        } else {
            holder = (GuestThreeAdapter.ViewHolder) convertView.getTag();
        }

        holder.tvGuestName.setText(guestList.get(i).getLName());
        holder.tvFirstName.setText(guestList.get(i).getFname());
        holder.tvCabinNo.setText(String.valueOf(guestList.get(i).getCabinNumber()));
        holder.tvCruiseDate.setText(fDate);
        final int pos = i;
        holder.ibtnEditGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEdit = new Intent(context, AddParticipantActivity.class);
                intentEdit.putExtra(StaticAccess.KEY_GUEST_ID, guestList.get(pos).getId());
                intentEdit.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, guestList.get(pos).getGuestUniqueId());
                intentEdit.putExtra(StaticAccess.KEY_INTENT_DATE, fDate);
                
                context.startActivity(intentEdit);

            }
        });

        holder.ibtnAddGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        return convertView;
    }
}
