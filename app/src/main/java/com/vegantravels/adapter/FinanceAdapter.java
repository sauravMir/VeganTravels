package com.vegantravels.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.model.CabinModelFinal;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

/**
 * Created by ibrar on 4/10/2017.
 */

public class FinanceAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CabinModelFinal> cabinList;
    private LayoutInflater inflater;

    public FinanceAdapter(Context context, ArrayList<CabinModelFinal> cabinList) {
        this.context = context;
        this.cabinList = cabinList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        FinanceAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new FinanceAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.finance_cell, null);

            holder.tvFinanceCabinNo = (TextView) convertView.findViewById(R.id.tvFinanceCabinNo);
            holder.tvFinanceLastName = (TextView) convertView.findViewById(R.id.tvFinanceLastName);
            holder.tvFinanceFirstName = (TextView) convertView.findViewById(R.id.tvFinanceFirstName);
            holder.tvFinanceExcursionBooked = (TextView) convertView.findViewById(R.id.tvFinanceExcursionBooked);
            holder.tvFinanceExcursionDate = (TextView) convertView.findViewById(R.id.tvFinanceExcursionDate);
            holder.tvFinancePPP = (TextView) convertView.findViewById(R.id.tvFinancePPP);
            holder.tvFinanceTotal = (TextView) convertView.findViewById(R.id.tvFinanceTotal);
            holder.tvFinancePayment = (TextView) convertView.findViewById(R.id.tvFinancePayment);

            convertView.setTag(holder);

        } else {
            holder = (FinanceAdapter.ViewHolder) convertView.getTag();
        }

        String excursion = "";
        String excursionDate = "";
        String excursionPPl = "";
        String excursionTotal = "";
        String payment = "";
        int grandTotal = 0;

        // if (cabinList.get(i).getExcursionName().size() > 0) {
        holder.tvFinanceCabinNo.setText(String.valueOf(cabinList.get(i).getCabinNum()));
        holder.tvFinanceLastName.setText(String.valueOf(cabinList.get(i).getLName()));
        holder.tvFinanceFirstName.setText(String.valueOf(cabinList.get(i).getFName()));
        for (int j = 0; j < cabinList.get(i).getExcursionName().size(); j++) {
            excursion += addspace(j + 1) + cabinList.get(i).getExcursionName().get(j) + "\n";
            excursionDate += addspace(j + 1) + cabinList.get(i).getExcursionDate().get(j) + "\n";
            excursionPPl += addspace(j + 1) + cabinList.get(i).getPeople().get(j) + "\n";
            excursionTotal += addspace(j + 1) + cabinList.get(i).getPeople().get(j) * cabinList.get(i).getExcursionPrice().get(j) + "\n";
            payment += addspace(j + 1) + StaticAccess.getPaymentByName(cabinList.get(i).getStatus().get(j)) + "\n";

            grandTotal += cabinList.get(i).getExcursionPrice().get(j) * cabinList.get(i).getPeople().get(j);
        }


        // }

        holder.tvFinanceExcursionBooked.setText(String.valueOf(excursion) + "\n" +"Grand Total: "+ String.valueOf(grandTotal) + StaticAccess.CURRENCY);
        holder.tvFinanceExcursionDate.setText(String.valueOf(excursionDate));
        holder.tvFinancePPP.setText(String.valueOf(excursionPPl));
        holder.tvFinanceTotal.setText(String.valueOf(excursionTotal));
        holder.tvFinancePayment.setText(String.valueOf(payment));

        return convertView;
    }


    public String addspace(int i) {
        String res = "";
        res = String.valueOf(i) + ". ";
        return res;

    }

}
