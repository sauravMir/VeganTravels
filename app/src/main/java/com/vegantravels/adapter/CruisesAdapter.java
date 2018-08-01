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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.activities.AddCruizeActivity;
import com.vegantravels.activities.AddExcursionActivity;
import com.vegantravels.activities.ExcursionListActivity;
import com.vegantravels.activities.GuestListThreeActivity;
import com.vegantravels.activities.MainActivity;
import com.vegantravels.dao.Criuzes_TMP;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
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
    private IDatabaseManager databaseManager;
    MainActivity mainActivity;

    public CruisesAdapter(Context context, ArrayList<Criuzes_TMP> cruisesList, String flag) {
        this.context = context;
        this.cruisesList = cruisesList;
        this.flag = flag;
        mainActivity = (MainActivity) context;
        databaseManager = new DatabaseManager(context);
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

        LinearLayout tvCruiseNameln;
        LinearLayout tvShipNameln;
        LinearLayout tvDateln;

        ImageButton btnEdit;
        ImageButton ibtnAddCruize;
        ImageButton btnExCursionManager;
        ImageButton btnExCursionDelete;
        LinearLayout llbtnLst;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.cruise_cell, null);
            holder.tvCruiseName = (TextView) convertView.findViewById(R.id.tvCruiseName);
            holder.tvShipName = (TextView) convertView.findViewById(R.id.tvShipName);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);

            holder.tvCruiseNameln = (LinearLayout) convertView.findViewById(R.id.tvCruiseNameln);
            holder.tvShipNameln = (LinearLayout) convertView.findViewById(R.id.tvShipNameln);
            holder.tvDateln = (LinearLayout) convertView.findViewById(R.id.tvDateln);

            holder.btnEdit = (ImageButton) convertView.findViewById(R.id.btnEdit);
            holder.ibtnAddCruize = (ImageButton) convertView.findViewById(R.id.ibtnAddCruize);
            holder.btnExCursionManager = (ImageButton) convertView.findViewById(R.id.btnExCursionManager);
            holder.btnExCursionDelete = (ImageButton) convertView.findViewById(R.id.btnExCursionDelete);

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
        holder.tvCruiseName.setText(cruisesList.get(position).getName());
        holder.tvShipName.setText(cruisesList.get(position).getShipName());
        holder.tvDate.setText(cruisesList.get(position).getFrom() + "  -  " + cruisesList.get(position).getTo());

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

                Intent exIntent = new Intent(context.getApplicationContext(), ExcursionListActivity.class);
                exIntent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, cruisesList.get(position).getCruizeUniqueId());
                context.startActivity(exIntent);

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


        holder.btnExCursionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deletePermissionDialog(position);

            }
        });

        if(mainActivity.cameFromExport==1){
            holder.btnEdit.setVisibility(View.INVISIBLE);
            holder.ibtnAddCruize.setVisibility(View.INVISIBLE);
            holder.btnExCursionManager.setVisibility(View.INVISIBLE);
            holder.btnExCursionDelete.setVisibility(View.INVISIBLE);

            holder.tvCruiseNameln.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent exIntent = new Intent(mainActivity, AddExcursionActivity.class);
                    exIntent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, cruisesList.get(position).getCruizeUniqueId());
                    exIntent.putExtra(StaticAccess.CameFromExport, 1);
                    mainActivity.startActivity(exIntent);
                    mainActivity.finish();
                }
            });
            holder.tvShipNameln.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent exIntent = new Intent(mainActivity, AddExcursionActivity.class);
                    exIntent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, cruisesList.get(position).getCruizeUniqueId());
                    exIntent.putExtra(StaticAccess.CameFromExport, 1);
                    mainActivity.startActivity(exIntent);
                    mainActivity.finish();
                }
            });
            holder.tvDateln.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent exIntent = new Intent(mainActivity, AddExcursionActivity.class);
                    exIntent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, cruisesList.get(position).getCruizeUniqueId());
                    exIntent.putExtra(StaticAccess.CameFromExport, 1);
                    mainActivity.startActivity(exIntent);
                    mainActivity.finish();
                }
            });

        }


        return convertView;
    }


    private void deletePermissionDialog(final int pos) {

        final Dialog dialog = new Dialog(mainActivity, R.style.CustomAlertDialog);
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
                if (cruisesList != null) {
                    databaseManager.isDeleteCruiseTemp(cruisesList.get(pos).getCruizeUniqueId());
                    mainActivity.listRefresh();

                }

                dialog.dismiss();
            }
        });
        DialogNavBarHide.navBarHide(mainActivity, dialog);
    }

}
