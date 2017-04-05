package com.vegantravels.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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
import com.vegantravels.activities.GuestListTwoActivity;

import java.util.Calendar;

/**
 * Created by Rakib on 4/3/2017.
 */

public class AllDialog {
    Activity activity;

    public AllDialog(Activity activity) {
        this.activity = activity;
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

        EditText edtCabinNumber = (EditText) dialog.findViewById(R.id.edtCabinNumber);
        EditText edtCabinName = (EditText) dialog.findViewById(R.id.edtCabinName);
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


    public void setDate(final TextView txt) {

        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = "Date: " + String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear) + "-" + String.valueOf(year);
                txt.setText(date);
            }
        }, yy, mm, dd);

        DialogNavBarHide.navBarHide(activity, datePicker);
    }

}
