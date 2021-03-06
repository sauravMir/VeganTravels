package com.vegantravels.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.activities.FinanceActivity;
import com.vegantravels.activities.GuestListThreeActivity;
import com.vegantravels.dao.Criuzes_TMP;
import com.vegantravels.utilities.ExportXls;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

/**
 * Created by ibrar on 4/10/2017.
 */

public class ExcursionManagementAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Criuzes_TMP> cruisesManagementList;
    private LayoutInflater inflater;
    private boolean flag;

    public ExcursionManagementAdapter(Context context, ArrayList<Criuzes_TMP> cruisesManagementList, boolean flag) {
        this.context = context;
        this.cruisesManagementList = cruisesManagementList;
        this.flag = flag;
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
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ExcursionManagementAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new ExcursionManagementAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.excursion_management_cell, null);
            holder.tvManagementName = (TextView) convertView.findViewById(R.id.tvManagementName);
            holder.tvManagementShipName = (TextView) convertView.findViewById(R.id.tvManagementShipName);
            holder.tvManagementDate = (TextView) convertView.findViewById(R.id.tvManagementDate);
            holder.btnFinance = (Button) convertView.findViewById(R.id.btnFinance);
            if (!flag) {
                holder.btnFinance.setVisibility(View.INVISIBLE);
            } else {
                holder.btnFinance.setVisibility(View.VISIBLE);
            }
            convertView.setTag(holder);

        } else {
            holder = (ExcursionManagementAdapter.ViewHolder) convertView.getTag();
        }
        holder.tvManagementName.setText(cruisesManagementList.get(i).getName());
        holder.tvManagementShipName.setText(cruisesManagementList.get(i).getShipName());
        holder.tvManagementDate.setText(cruisesManagementList.get(i).getFrom() + "  -  " + cruisesManagementList.get(i).getTo());
        if (flag) {
            holder.tvManagementName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentExcursion2 = new Intent(context, FinanceActivity.class);
                    intentExcursion2.putExtra(StaticAccess.KEY_CRUISES_ID, cruisesManagementList.get(i).getCruizeUniqueId());
                    context.startActivity(intentExcursion2);
                }
            });
            holder.tvManagementShipName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentExcursion2 = new Intent(context, FinanceActivity.class);
                    intentExcursion2.putExtra(StaticAccess.KEY_CRUISES_ID, cruisesManagementList.get(i).getCruizeUniqueId());
                    context.startActivity(intentExcursion2);
                }
            });
            holder.tvManagementDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentExcursion2 = new Intent(context, FinanceActivity.class);
                    intentExcursion2.putExtra(StaticAccess.KEY_CRUISES_ID, cruisesManagementList.get(i).getCruizeUniqueId());
                    context.startActivity(intentExcursion2);
                }
            });
            holder.btnFinance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //// export finance xls
                    ExportXls exportXls = new ExportXls(context, cruisesManagementList.get(i).getCruizeUniqueId());
//                    exportXls.exportXls();
                /*  Intent intentExcursion2 = new Intent(context, ExportActivity.class);
                  intentExcursion2.putExtra(StaticAccess.KEY_CRUISES_ID, cruisesManagementList.get(i).getCruizeUniqueId());
                  context.startActivity(intentExcursion2);*/
                }
            });
        } else {
            holder.tvManagementName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentGuest = new Intent(context, GuestListThreeActivity.class);
                    intentGuest.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intentGuest.putExtra(StaticAccess.KEY_CRUISES_ID, cruisesManagementList.get(i).getId());
                    intentGuest.putExtra(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID, cruisesManagementList.get(i).getCruizeUniqueId());
                    intentGuest.putExtra(StaticAccess.KEY_INTENT_DATE, "From :" + cruisesManagementList.get(i).getFrom() + "\n To :" + cruisesManagementList.get(i).getTo());
                    context.startActivity(intentGuest);
                }
            });

            holder.tvManagementShipName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentGuest = new Intent(context, GuestListThreeActivity.class);
                    intentGuest.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intentGuest.putExtra(StaticAccess.KEY_CRUISES_ID, cruisesManagementList.get(i).getId());
                    intentGuest.putExtra(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID, cruisesManagementList.get(i).getCruizeUniqueId());
                    intentGuest.putExtra(StaticAccess.KEY_INTENT_DATE, "From :" + cruisesManagementList.get(i).getFrom() + "\n To :" + cruisesManagementList.get(i).getTo());
                    context.startActivity(intentGuest);
                }
            });
            holder.tvManagementDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentGuest = new Intent(context, GuestListThreeActivity.class);
                    intentGuest.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intentGuest.putExtra(StaticAccess.KEY_CRUISES_ID, cruisesManagementList.get(i).getId());
                    intentGuest.putExtra(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID, cruisesManagementList.get(i).getCruizeUniqueId());
                    intentGuest.putExtra(StaticAccess.KEY_INTENT_DATE, "From :" + cruisesManagementList.get(i).getFrom() + "\n To :" + cruisesManagementList.get(i).getTo());
                    context.startActivity(intentGuest);
                }
            });


        }

        return convertView;
    }


}
