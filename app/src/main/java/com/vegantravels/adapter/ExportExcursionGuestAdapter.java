package com.vegantravels.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.activities.ExportExcursionGuestListActivity;
import com.vegantravels.dao.Cabins_TMP;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.model.GuestExport;
import com.vegantravels.utilities.StaticAccess;

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
        ImageButton ibtnExportEcEdit;
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
            holder.ibtnExportEcEdit = (ImageButton) convertView.findViewById(R.id.ibtnExportEcEdit);
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
        holder.ibtnExportEcEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEdit(position);

            }
        });

        return convertView;
    }

    int occ, paymentStatus;

    private void dialogEdit(int position) {

        final Dialog dialog = new Dialog(activity, R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.payment_dialog);
        dialog.setCancelable(true);

       final GuestExport guestExport = guestListExportEc.get(position);

        ImageButton btnCancelPayment = (ImageButton) dialog.findViewById(R.id.btnCancelPayment);
        ImageButton btnOkPayment = (ImageButton) dialog.findViewById(R.id.btnOkPayment);
        Spinner spnGuestNo = (Spinner) dialog.findViewById(R.id.spnGuestNo);

        fillGuestNumberData(spnGuestNo, guestExport.getCabins_tmp().getOccupancy());

        final RadioGroup rdGrp = (RadioGroup) dialog.findViewById(R.id.myRadioGroup);
        RadioButton rbPaidAlReady = (RadioButton) dialog.findViewById(R.id.rbPaidAlReady);
        RadioButton rdCashBord = (RadioButton) dialog.findViewById(R.id.rbCashOnBoard);
        RadioButton rdCaditCard = (RadioButton) dialog.findViewById(R.id.rbCreditCard);
        RadioButton rdComplementary = (RadioButton) dialog.findViewById(R.id.rbComplementary);
        if (guestExport.getCabins_tmp().getPaymentStatus() == 1) {
            rdGrp.check(R.id.rbPaidAlReady);
        } else if (guestExport.getCabins_tmp().getPaymentStatus() == 2) {
            rdGrp.check(R.id.rbCashOnBoard);
        } else if (guestExport.getCabins_tmp().getPaymentStatus() == 3) {
            rdGrp.check(R.id.rbCreditCard);
        } else if (guestExport.getCabins_tmp().getPaymentStatus() == 4) {
            rdGrp.check(R.id.rbComplementary);
        }

        btnCancelPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnOkPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rdGrp.getCheckedRadioButtonId();
                if (selectedId == R.id.rbPaidAlReady) {
                    paymentStatus = StaticAccess.PAID_ALLREADY;


                } else if (selectedId == R.id.rbCashOnBoard) {
                    paymentStatus = StaticAccess.CASH_ON_BOARD;


                } else if (selectedId == R.id.rbCreditCard) {
                    paymentStatus = StaticAccess.CREDIT_CARD;


                } else if (selectedId == R.id.rbComplementary) {
                    paymentStatus = StaticAccess.COMPLEMENTARY;

                } else {
                    Toast.makeText(activity, "Select Payment Method", Toast.LENGTH_SHORT).show();
                }
                if(occ!=-1)
                guestExport.getCabins_tmp().setOccupancy(occ);

                if(paymentStatus!=-1)
                guestExport.getCabins_tmp().setPaymentStatus(paymentStatus);
                databaseManager.updateCabinTemp(guestExport.getCabins_tmp());

                dialog.dismiss();

            }
        });
        DialogNavBarHide.navBarHide(activity, dialog);
    }

    private void fillGuestNumberData(Spinner spnGuestNo, int occupancy) {

        final String[] GUEST_ARRAY = context.getResources().getStringArray(R.array.noOfGuest);
        final ArrayAdapter<String> adapterGuest = new ArrayAdapter<String>(activity, R.layout.spinner_item, GUEST_ARRAY);
        spnGuestNo.setAdapter(adapterGuest);
        spnGuestNo.setSelection(occupancy);
        spnGuestNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                occ = Integer.parseInt(GUEST_ARRAY[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                    databaseManager.deleteCabinByVTIdAndExcursionId(guestListExportEc.get(pos).getCabins_tmp().getId(), guestListExportEc.get(pos).getCabins_tmp().getExcursion());
                    activity.GuestListRefresh();
                }

                dialog.dismiss();
            }
        });
        DialogNavBarHide.navBarHide(activity, dialog);
    }

}
