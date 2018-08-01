package com.vegantravels.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.activities.AddParticipantActivity;
import com.vegantravels.activities.GuestListFromExport;
import com.vegantravels.activities.GuestListThreeActivity;
import com.vegantravels.activities.ViewExcursionActivity;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.dialog.DialogNavBarHide;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;

public class GuestListExportAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Guests_TMP> guestList;
    private LayoutInflater layoutInflater;
    String fDate;
    private IDatabaseManager databaseManager;
    private GuestListFromExport activity;
    private String vtID;

    public GuestListExportAdapter(Context context, ArrayList<Guests_TMP> guestsList, String fDate) {
        this.context = context;
        this.guestList = guestsList;
        this.fDate = fDate;
        activity = (GuestListFromExport) context;
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

//        holder.ibtnEditGuest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intentEdit = new Intent(context, AddParticipantActivity.class);
//                intentEdit.putExtra(StaticAccess.KEY_GUEST_ID, guestList.get(position).getId());
//                intentEdit.putExtra(StaticAccess.KEY_INTENT_CRUISES_UNIQUE_ID, guestList.get(position).getGuestUniqueId());
//                intentEdit.putExtra(StaticAccess.KEY_INTENT_DATE, fDate);
//                context.startActivity(intentEdit);
//
//            }
//        });
//
        holder.ibtnAddGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogForOccupancy(guestList.get(position).getGuestUniqueId(),
                        guestList.get(position).getGuestVT_Id(),guestList.get(position).getCabinNumber());

            }
        });
//
//
//        holder.ibtnDeleteGuest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                deletePermissionDialog(position);
//            }
//        });

        holder.ibtnEditGuest.setVisibility(View.INVISIBLE);
        holder.ibtnDeleteGuest.setVisibility(View.INVISIBLE);

        return convertView;
    }



    public void dialogForOccupancy(final long guestUniq, final String  guestVtId, final int guestCabin) {
        final Dialog dialog = new Dialog(context, R.style.CustomAlertDialog);
        dialog.setContentView(R.layout.dialog_search);
        dialog.setCancelable(false);
        final EditText edtCabinNumber = (EditText) dialog.findViewById(R.id.edtCabinNumber);

        edtCabinNumber.setHint("Enter Number of People (Not greater than 4)");
        edtCabinNumber.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return false;
            }
        });
        edtCabinNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0){
                    try {

                        int j = Integer.valueOf(edtCabinNumber.getText().toString());

                        if (j>=1 && j <= 4) {

                        }else{
                            edtCabinNumber.setText("");
                            activity.makeToast("Please enter a number between 1-4");
                        }
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                        edtCabinNumber.setText("");
                    }
                }

            }
        });



        final EditText edtCabinName = (EditText) dialog.findViewById(R.id.edtCabinName);
        edtCabinName.setVisibility(View.GONE);

        ImageButton btnCancel = (ImageButton) dialog.findViewById(R.id.btnCancel);
        ImageButton btnOk = (ImageButton) dialog.findViewById(R.id.btnOk);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            try {
                if(!edtCabinNumber.getText().equals("")) {
                    int occupancy = Integer.parseInt(edtCabinNumber.getText().toString());
                    paymentDialog(guestUniq,guestVtId,guestCabin,occupancy);

                }else{
                    activity.makeToast("Please enter a number");
                }
            }catch (NumberFormatException e){
                e.printStackTrace();
            }

            dialog.dismiss();
            }
        });
        DialogNavBarHide.navBarHide(activity, dialog);
    }


    public void paymentDialog(final long guestUniq, final String  guestVtId, final int guestCabin, final int occupency) {

        final Dialog dialog = new Dialog(activity, R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.payment_dialog);
        dialog.setCancelable(true);

        ImageButton btnCancelPayment = (ImageButton) dialog.findViewById(R.id.btnCancelPayment);
        ImageButton btnOkPayment = (ImageButton) dialog.findViewById(R.id.btnOkPayment);

        final RadioGroup rdGrp = (RadioGroup) dialog.findViewById(R.id.myRadioGroup);
        RadioButton rbPaidAlReady = (RadioButton) dialog.findViewById(R.id.rbPaidAlReady);
        RadioButton rdCashBord = (RadioButton) dialog.findViewById(R.id.rbCashOnBoard);
        //RadioButton rdCaditCard = (RadioButton) dialog.findViewById(R.id.rbCreditCard);
        RadioButton rdComplementary = (RadioButton) dialog.findViewById(R.id.rbComplementary);


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
                    activity.bookedExcursion(StaticAccess.PAID_ALLREADY,guestUniq,guestVtId,guestCabin,occupency);
                    dialog.dismiss();

                } else if (selectedId == R.id.rbCashOnBoard) {
                    activity.bookedExcursion(StaticAccess.CASH_ON_BOARD,guestUniq,guestVtId,guestCabin,occupency);
                    dialog.dismiss();

                }
//                else if (selectedId == R.id.rbCreditCard) {
//                    activity.bookedExcursion(StaticAccess.CREDIT_CARD);
//                    dialog.dismiss();
//
//                }
                else if (selectedId == R.id.rbComplementary) {
                    activity.bookedExcursion(StaticAccess.COMPLEMENTARY,guestUniq,guestVtId,guestCabin,occupency);
                    dialog.dismiss();
                }else{
                    Toast.makeText(activity, "Select Payment Method", Toast.LENGTH_SHORT).show();
                }
            }
        });
        DialogNavBarHide.navBarHide(activity, dialog);
    }


}
