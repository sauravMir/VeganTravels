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
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

/**
 * Created by Rakib on 4/3/2017.
 */

public class CruisesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Criuzes_TMP> cruisesList;
    private LayoutInflater inflater;
    private String flag;

    public CruisesAdapter(Context context, ArrayList<Criuzes_TMP> cruisesList, String flag) {
        this.context = context;
        this.cruisesList = cruisesList;
        this.flag = flag;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cruisesList.size();
    }

    @Override
    public Object getItem(int i) {
        return cruisesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {

        TextView tvCruiseName;
        TextView tvShipName;
        TextView tvDate;
        ImageButton btnEdit;
        ImageButton ibtnAddCruize;
        ImageButton btnExCursionManager;
        LinearLayout llbtnLst;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.cruise_cell, null);
            holder.tvCruiseName = (TextView) convertView.findViewById(R.id.tvCruiseName);
            holder.tvShipName = (TextView) convertView.findViewById(R.id.tvShipName);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            holder.btnEdit = (ImageButton) convertView.findViewById(R.id.btnEdit);
            holder.ibtnAddCruize = (ImageButton) convertView.findViewById(R.id.ibtnAddCruize);
            holder.btnExCursionManager = (ImageButton) convertView.findViewById(R.id.btnExCursionManager);
            holder.llbtnLst = (LinearLayout) convertView.findViewById(R.id.llbtnLst);
            if (flag == StaticAccess.EXCURSION_MANAGEMENT) {
                holder.llbtnLst.setVisibility(View.GONE);
            } else if (flag == StaticAccess.CRIUZE_MANAGEMENT) {
                holder.llbtnLst.setVisibility(View.VISIBLE);
            }

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvCruiseName.setText(cruisesList.get(i).getName());
        holder.tvShipName.setText(cruisesList.get(i).getShipName());
        holder.tvDate.setText(cruisesList.get(i).getFrom() + "  -  " + cruisesList.get(i).getTo());
        final int position = i;
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edtiIntent = new Intent(context, AddCruizeActivity.class);
                edtiIntent.putExtra(StaticAccess.KEY_CRUISES_ID, cruisesList.get(position).getId());
                edtiIntent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, cruisesList.get(position).getCruizeUniqueId());
                context.startActivity(edtiIntent);
            }
        });
        holder.ibtnAddCruize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* Intent exIntent = new Intent(context.getApplicationContext(), AddExcursionActivity.class);
                exIntent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, cruisesList.get(position).getCruizeUniqueId());
                context.startActivity(exIntent);*/

               /* Intent intentGuest = new Intent(context, GuestListThreeActivity.class);
                intentGuest.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentGuest.putExtra(StaticAccess.KEY_CRUISES_ID, cruisesList.get(position).getId());
                intentGuest.putExtra(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID, cruisesList.get(position).getCruizeUniqueId());
                intentGuest.putExtra(StaticAccess.KEY_INTENT_DATE, "From :" + cruisesList.get(position).getFrom() + "\n To :" + cruisesList.get(position).getTo());
                context.startActivity(intentGuest);*/
            }
        });

        holder.btnExCursionManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   Intent exIntent = new Intent(context.getApplicationContext(), AddExcursionActivity.class);
                exIntent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, cruisesList.get(position).getCruizeUniqueId());
                context.startActivity(exIntent);*/
                Intent intentGuest = new Intent(context, GuestListThreeActivity.class);
                intentGuest.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentGuest.putExtra(StaticAccess.KEY_CRUISES_ID, cruisesList.get(position).getId());
                intentGuest.putExtra(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID, cruisesList.get(position).getCruizeUniqueId());
                intentGuest.putExtra(StaticAccess.KEY_INTENT_DATE, "From :" + cruisesList.get(position).getFrom() + "\n To :" + cruisesList.get(position).getTo());
                context.startActivity(intentGuest);
            }
        });
        return convertView;
    }

}
