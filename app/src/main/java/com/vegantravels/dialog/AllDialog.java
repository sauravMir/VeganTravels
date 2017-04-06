package com.vegantravels.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.vegantravels.R;
import com.vegantravels.activities.GuestListThreeActivity;
import com.vegantravels.activities.GuestListTwoActivity;
import com.vegantravels.adapter.GuestThreeAdapter;
import com.vegantravels.model.Guest;
import com.vegantravels.utilities.StaticAccess;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Rakib on 4/3/2017.
 */

public class AllDialog {
    Activity activity;
    EditText edtCabinNumber, edtCabinName;
    GuestListThreeActivity guestListThreeActivity;

    public AllDialog(Activity activity) {
        this.activity = activity;
        guestListThreeActivity = new GuestListThreeActivity();
    }

    ///confirm dialog
    public void confirmDialog(String title) {

        final Dialog dialog = new Dialog(activity, R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.confirm_dialog);
        dialog.setCancelable(true);

        final TextView tvPermission = (TextView) dialog.findViewById(R.id.tvPermission);
        ImageButton btnCancelPermission = (ImageButton) dialog.findViewById(R.id.btnCancelPermission);
        ImageButton btnOkPermission = (ImageButton) dialog.findViewById(R.id.btnOkPermission);
        tvPermission.setText(title);

        btnCancelPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnOkPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentDialog();
                dialog.dismiss();
            }
        });
        DialogNavBarHide.navBarHide(activity, dialog);

    }

    /// payment dialog
    public void paymentDialog() {

        final Dialog dialog = new Dialog(activity, R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.payment_dialog);
        dialog.setCancelable(true);

        ImageButton btnCancelPayment = (ImageButton) dialog.findViewById(R.id.btnCancelPayment);
        ImageButton btnOkPayment = (ImageButton) dialog.findViewById(R.id.btnOkPayment);

       /* final RadioGroup rdGrp=(RadioGroup)dialog.findViewById(R.id.rdGrp);
        RadioButton rdPaid = (RadioButton) dialog.findViewById(R.id.rdPaid);
        RadioButton rdCashBord = (RadioButton) dialog.findViewById(R.id.rdCashBord);
        RadioButton rdCaditCard = (RadioButton) dialog.findViewById(R.id.rdCaditCard);
        RadioButton rdComplementary = (RadioButton) dialog.findViewById(R.id.rdComplementary);*/


        btnCancelPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnOkPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int selectedId=rdGrp.getCheckedRadioButtonId();
                //RadioButton radioButton =(RadioButton)dialog.findViewById(selectedId);
            }
        });
        DialogNavBarHide.navBarHide(activity, dialog);


    }


    public void dialogForSearch() {
        final Dialog dialog = new Dialog(activity, R.style.CustomAlertDialog);
        dialog.setContentView(R.layout.dialog_search);
        dialog.setCancelable(false);
        edtCabinNumber = (EditText) dialog.findViewById(R.id.edtCabinNumber);
        edtCabinName = (EditText) dialog.findViewById(R.id.edtCabinName);
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
                Intent intent = new Intent(activity, GuestListTwoActivity.class);
                activity.startActivity(intent);

                dialog.dismiss();
            }
        });

        DialogNavBarHide.navBarHide(activity, dialog);
    }


    /*public void searchGuestList() {
        edtCabinNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               *//* if (selectedPackType.equals(TaskType.All)) {
                    guestListThreeActivity.guestList = (ArrayList<Guest>) databaseManager.getTaskPacksByName(edtCabinNumber.getText().toString());
                } else {
                    guestListThreeActivity.guestList = (ArrayList<Guest>) databaseManager.getTaskPacksByName(edtCabinNumber.getText().toString(), selectedPackType);
                }*//*
                guestListThreeActivity.guestList = (ArrayList<Guest>) databaseManager.getTaskPacksByName(edtCabinNumber.getText().toString(), selectedPackType);

                if (guestListThreeActivity.guestList  != null) {
                    guestListThreeActivity.adapter = new GuestThreeAdapter(activity, guestListThreeActivity.guestList);
                    guestListThreeActivity.lstGuest.setAdapter(guestListThreeActivity.adapter);
                    currentSelectedPack = null;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }*/


    ///payment success dialog
    public void paymentCompletionDialog(String text) {

        final Dialog dialog = new Dialog(activity, R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.confirm_dialog);
        dialog.setCancelable(true);

        final TextView tvPermission = (TextView) dialog.findViewById(R.id.tvPermission);
        ImageButton btnCancelPermission = (ImageButton) dialog.findViewById(R.id.btnCancelPermission);
        ImageButton btnOkPermission = (ImageButton) dialog.findViewById(R.id.btnOkPermission);
        tvPermission.setText(text);

        btnCancelPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnOkPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        DialogNavBarHide.navBarHide(activity, dialog);

    }


    public void setDate(final TextView txt, final String flag) {

        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear) + "-" + String.valueOf(year);
                if (flag == StaticAccess.DATE_FROM) {
                    txt.setText("Date From: " + date);
                } else if (flag == StaticAccess.DATE_TO) {
                    txt.setText("Date To: " + date);
                }
            }
        }, yy, mm, dd);

        DialogNavBarHide.navBarHide(activity, datePicker);
    }

}
