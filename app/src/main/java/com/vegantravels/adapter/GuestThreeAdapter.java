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
import com.vegantravels.activities.AddParticipantActivity;
import com.vegantravels.activities.GuestListThreeActivity;
import com.vegantravels.activities.ViewExcursionActivity;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
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
private IDatabaseManager databaseManager;
private GuestListThreeActivity activity;
private String vtID;

public GuestThreeAdapter(Context context, ArrayList<Guests_TMP> guestsList, String fDate) {
        this.context = context;
        this.guestList = guestsList;
        this.fDate = fDate;
        activity = (GuestListThreeActivity) context;
        databaseManager = new DatabaseManager(context);
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
    ImageButton ibtnDeleteGuest;

}

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
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
            holder.ibtnDeleteGuest = (ImageButton) convertView.findViewById(R.id.ibtnDeleteGuest);

            convertView.setTag(holder);

        } else {
            holder = (GuestThreeAdapter.ViewHolder) convertView.getTag();

        }

        holder.tvGuestName.setText(guestList.get(position).getLName());
        holder.tvFirstName.setText(guestList.get(position).getFname());
        holder.tvCabinNo.setText(String.valueOf(guestList.get(position).getCabinNumber()));
        holder.tvCruiseDate.setText(fDate);
        holder.ibtnEditGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEdit = new Intent(context, AddParticipantActivity.class);
                intentEdit.putExtra(StaticAccess.KEY_GUEST_ID, guestList.get(position).getId());
                intentEdit.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, guestList.get(position).getGuestUniqueId());
                intentEdit.putExtra(StaticAccess.KEY_INTENT_DATE, fDate);
                context.startActivity(intentEdit);

            }
        });

        holder.ibtnAddGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ViewExcursionActivity.class);
                intent.putExtra(StaticAccess.INTENT_GUEST_ID_KEY, guestList.get(position).getId());
                intent.putExtra(StaticAccess.KEY_CRUISE_UNIQUE_ID, guestList.get(position).getGuestUniqueId());
                intent.putExtra(StaticAccess.KEY_INTENT_DATE, fDate);
                context.startActivity(intent);

            }
        });


        holder.ibtnDeleteGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                if (guestList != null) {
                    databaseManager.isDeleteSingleGuestTemp(guestList.get(pos).getGuestUniqueId(), guestList.get(pos).getGuestVT_Id());
                    activity.GuestListRefresh();
                    notifyDataSetChanged();
                }

                dialog.dismiss();
            }
        });
        DialogNavBarHide.navBarHide(activity, dialog);
    }
}
