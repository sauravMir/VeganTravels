package com.vegantravels.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.activities.AddExcursionActivity;
import com.vegantravels.activities.ExcursionListActivity;
import com.vegantravels.dao.Excursions_TMP;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

/**
 * Created by ibrar on 4/9/2017.
 */

public class ExcursionAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Excursions_TMP> excursionList;
    private LayoutInflater inflater;
    private String flag;
    private long cruizeUniqueID = -1;
    private IDatabaseManager databaseManager;
    private ExcursionListActivity activity;

    public ExcursionAdapter(Context context, ArrayList<Excursions_TMP> excursionList, long cruizeUniqueID) {
        this.context = context;
        activity = (ExcursionListActivity) context;
        this.excursionList = excursionList;
        this.cruizeUniqueID = cruizeUniqueID;
        databaseManager = new DatabaseManager(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        TextView tvExcursionDate;
        TextView tvExcursionTime;
        TextView tvExcursionName;
        TextView tvExcursionPPP;
        TextView tvExcursionMaxGst;
        ImageButton ibtnExcursionEdit;
        ImageButton ibtnExcursionDelete;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ExcursionAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new ExcursionAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.excursion_cell, null);
            holder.tvExcursionDate = (TextView) convertView.findViewById(R.id.tvExcursionDate);
            holder.tvExcursionTime = (TextView) convertView.findViewById(R.id.tvExcursionTime);
            holder.tvExcursionName = (TextView) convertView.findViewById(R.id.tvExcursionName);
            holder.tvExcursionPPP = (TextView) convertView.findViewById(R.id.tvExcursionPPP);
            holder.tvExcursionMaxGst = (TextView) convertView.findViewById(R.id.tvExcursionMaxGst);
            holder.ibtnExcursionEdit = (ImageButton) convertView.findViewById(R.id.ibtnExcursionEdit);
            holder.ibtnExcursionDelete = (ImageButton) convertView.findViewById(R.id.ibtnExcursionDelete);

            convertView.setTag(holder);

        } else {
            holder = (ExcursionAdapter.ViewHolder) convertView.getTag();
        }
        holder.tvExcursionDate.setText(excursionList.get(position).getFrom());
        holder.tvExcursionTime.setText(excursionList.get(position).getTime());
        holder.tvExcursionName.setText(excursionList.get(position).getTitle());
        holder.tvExcursionPPP.setText(excursionList.get(position).getPrice());
        holder.tvExcursionMaxGst.setText(String.valueOf(excursionList.get(position).getMaxNumberOfGuest()));


        holder.ibtnExcursionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edtiIntent = new Intent(context, AddExcursionActivity.class);
                edtiIntent.putExtra(StaticAccess.KEY_EXCURSION_ID, excursionList.get(position).getId());
                edtiIntent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, cruizeUniqueID);
                context.startActivity(edtiIntent);
            }
        });


        holder.ibtnExcursionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //deletePermissionDialog();
            }
        });


        return convertView;
    }

   /* private void deletePermissionDialog() {

        final Dialog dialog = new Dialog(activity, R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.confirm_dialog);
        dialog.setCancelable(true);

        final TextView tvPermission = (TextView) dialog.findViewById(R.id.tvPermission);
        ImageButton btnCancelPermission = (ImageButton) dialog.findViewById(R.id.btnCancelPermission);
        ImageButton btnOkPermission = (ImageButton) dialog.findViewById(R.id.btnOkPermission);
        tvPermission.setText(R.string.delete_permission);

        btnCancelPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnOkPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (excursionList != null) {
                    databaseManager.isDeleteCruiseTemp(excursionList.get(position).getCruizeUniqueId());
                    activity.ExcursionListRefresh();
                    notifyDataSetChanged();
                }

                dialog.dismiss();
            }
        });
        DialogNavBarHide.navBarHide(activity, dialog);
    }*/

}
