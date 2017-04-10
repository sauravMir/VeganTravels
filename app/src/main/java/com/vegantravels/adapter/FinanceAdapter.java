package com.vegantravels.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.dao.Cabins_TMP;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;

import java.util.ArrayList;

/**
 * Created by ibrar on 4/10/2017.
 */

public class FinanceAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Cabins_TMP> cabinList;
    private LayoutInflater inflater;
    private IDatabaseManager databaseManager;

    public FinanceAdapter(Context context, ArrayList<Cabins_TMP> cabinList) {
        this.context = context;
        this.cabinList = cabinList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        databaseManager = new DatabaseManager(context);
    }

    @Override
    public int getCount() {
        return cabinList.size();
    }

    @Override
    public Object getItem(int i) {
        return cabinList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {

        TextView tvFinanceCabinNo;
        TextView tvFinanceLastName;
        TextView tvFinanceFirstName;
        TextView tvFinanceExcursionBooked;
        TextView tvFinanceExcursionDate;
        TextView tvFinancePPP;
        TextView tvFinanceTotal;
        TextView tvFinancePayment;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        FinanceAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new FinanceAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.finance_cell, null);

            holder.tvFinanceCabinNo = (TextView) convertView.findViewById(R.id.tvExcursionDate);
            holder.tvFinanceLastName = (TextView) convertView.findViewById(R.id.tvExcursionTime);
            holder.tvFinanceFirstName = (TextView) convertView.findViewById(R.id.tvExcursionName);
            holder.tvFinanceExcursionBooked = (TextView) convertView.findViewById(R.id.tvExcursionPPP);
            holder.tvFinanceExcursionDate = (TextView) convertView.findViewById(R.id.tvExcursionMaxGst);
            holder.tvFinancePPP = (TextView) convertView.findViewById(R.id.tvExcursionMaxGst);
            holder.tvFinanceTotal = (TextView) convertView.findViewById(R.id.tvExcursionMaxGst);
            holder.tvFinancePayment = (TextView) convertView.findViewById(R.id.tvExcursionMaxGst);

            convertView.setTag(holder);

        } else {
            holder = (FinanceAdapter.ViewHolder) convertView.getTag();
        }
        Guests_TMP guests_tmp = databaseManager.guestTempFromCabin(cabinList.get(i).getGuestVT_Id(), cabinList.get(i).getCruizeId());
        ArrayList<Excursions_TMP> excursions_tmpsLst = databaseManager.excursionTempListByExcursionId(cabinList.get(i).getExcursion());

       /* holder.tvFinanceCabinNo.setText(String.valueOf(cabinList.get(i).getCabinNumber()));
        holder.tvFinanceLastName.setText(String.valueOf(guests_tmp.getLName()));
        holder.tvFinanceFirstName.setText(String.valueOf(guests_tmp.getFname()));
        holder.tvFinanceExcursionBooked.setText(String.valueOf(excursions_tmpsLst.get(i).));
        holder.tvFinanceExcursionDate.setText(String.valueOf(excursions_tmpsLst.get(i).getFrom()));
        holder.tvFinancePPP.setText(String.valueOf(excursions_tmpsLst.get(i).getPrice()));
        holder.tvFinanceTotal.setText(String.valueOf(excursions_tmpsLst.get(i).));
        holder.tvFinancePayment.setText(String.valueOf(cabinList.get(i).getPaymentStatus()));*/

        final int position = i;

        return convertView;
    }

}
