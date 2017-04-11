package com.vegantravels.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.activities.ExportExcursionGuestListActivity;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.model.GuestExport;

import java.util.ArrayList;

/**
 * Created by ibrar on 4/11/2017.
 */

public class ExportExcursionGuestAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<GuestExport> guestListExportEc;
    private LayoutInflater layoutInflater;
    String fDate;
    private IDatabaseManager databaseManager;
    private ExportExcursionGuestListActivity activity;
    private String vtID;

    public ExportExcursionGuestAdapter(Context context, ArrayList<GuestExport> guestsListExportEc) {
        this.context = context;
        this.guestListExportEc = guestsListExportEc;
        this.fDate = fDate;
        activity = (ExportExcursionGuestListActivity) context;
        databaseManager = new DatabaseManager(context);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return guestListExportEc.size();
    }

    @Override
    public Object getItem(int i) {
        return guestListExportEc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {


        TextView tvExportEcCruiseVTID;
        TextView tvExportEcFirstName;
        TextView tvExportEcGuestName;
        TextView tvExportEcCabinNo;
        TextView tvExportEcCruiseGuestInTable;
        ImageButton ibtnExportEcDelete;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ExportExcursionGuestAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new ExportExcursionGuestAdapter.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.export_excursion_guest_cell, null);

            holder.tvExportEcCruiseVTID = (TextView) convertView.findViewById(R.id.tvExportEcCruiseVTID);
            holder.tvExportEcFirstName = (TextView) convertView.findViewById(R.id.tvExportEcFirstName);
            holder.tvExportEcGuestName = (TextView) convertView.findViewById(R.id.tvExportEcGuestName);
            holder.tvExportEcCabinNo = (TextView) convertView.findViewById(R.id.tvExportEcCabinNo);
            holder.tvExportEcCruiseGuestInTable = (TextView) convertView.findViewById(R.id.tvExportEcCruiseGuestInTable);
            holder.ibtnExportEcDelete = (ImageButton) convertView.findViewById(R.id.ibtnExportEcDelete);

            convertView.setTag(holder);

        } else {
            holder = (ExportExcursionGuestAdapter.ViewHolder) convertView.getTag();

        }
        holder.tvExportEcCruiseVTID.setText(String.valueOf(guestListExportEc.get(position).getCabins_tmp().getGuestVT_Id()));
        holder.tvExportEcFirstName.setText(guestListExportEc.get(position).getFirstName());
        holder.tvExportEcGuestName.setText(guestListExportEc.get(position).getLastName());
        holder.tvExportEcCabinNo.setText(String.valueOf(guestListExportEc.get(position).getCabins_tmp().getCabinNumber()));
        holder.tvExportEcCruiseGuestInTable.setText(String.valueOf(guestListExportEc.get(position).getCabins_tmp().getOccupancy()));
        holder.ibtnExportEcDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePermissionDialog(position);

            }
        });

        return convertView;
    }

    private void deletePermissionDialog(final int pos) {

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
                if (guestListExportEc != null) {
                    databaseManager.deleteCabinByVTIdAndExcursionId(guestListExportEc.get(pos).getCabins_tmp().getGuestVT_Id(), guestListExportEc.get(pos).getCabins_tmp().getExcursion());
                    activity.GuestListRefresh();
                }

                dialog.dismiss();
            }
        });
        DialogNavBarHide.navBarHide(activity, dialog);
    }

}
