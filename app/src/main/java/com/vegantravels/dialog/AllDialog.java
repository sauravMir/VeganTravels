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
import android.widget.Toast;

import com.vegantravels.R;
import com.vegantravels.activities.GuestListThreeActivity;
import com.vegantravels.activities.GuestListTwoActivity;
import com.vegantravels.adapter.GuestThreeAdapter;
import com.vegantravels.dao.Guests;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
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
    IDatabaseManager databaseManager;

    public AllDialog(Activity activity) {
        this.activity = activity;
        guestListThreeActivity = new GuestListThreeActivity();
        databaseManager = new DatabaseManager(activity);
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


   /* public void searchGuestList() {
        edtCabinNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (selectedPackType.equals(TaskType.All)) {
                    guestListThreeActivity.guestList = (ArrayList<Guest>) databaseManager.getTaskPacksByName(edtCabinNumber.getText().toString());
                } else {
                    guestListThreeActivity.guestList = (ArrayList<Guest>) databaseManager.getTaskPacksByName(edtCabinNumber.getText().toString(), selectedPackType);
                }

                int cabinName = edtCabinName.getText().toString().length();
                int cabinNumber = edtCabinNumber.getText().toString().length();

               if(cabinName>0 && cabinNumber ==0){
                   guestListThreeActivity.guestList = databaseManager.getSearchByNameCabin(edtCabinName.getText().toString())
               }else if(cabinNumber>0 && cabinName==0){
                   guestListThreeActivity.guestList = databaseManager.getSearchByNameCabin(edtCabinNumber.getText().toString())
               }else if(cabinName>0 && cabinNumber>0){
                   guestListThreeActivity.guestList = databaseManager.getSearchByNameCabin(edtCabinNumber.getText().toString())
               }else if(cabinName==0 && cabinNumber==0){
                   Toast.makeText(activity, "Please write something for search", Toast.LENGTH_SHORT).show();
               }

                guestListThreeActivity.guestList = (ArrayList<Guests>) databaseManager.getSearchByNameCabin(edtCabinNumber.getText().toString());

                if (guestListThreeActivity.guestList  != null) {
                    guestListThreeActivity.adapter = new GuestThreeAdapter(activity, guestListThreeActivity.guestList);
                    guestListThreeActivity.lstGuest.setAdapter(guestListThreeActivity.adapter);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
*/

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


    public void setCustomDate(final TextView txt, final String flag) {

        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear) + "-" + String.valueOf(year);
                long time = calendar.getTimeInMillis();
                if (flag == StaticAccess.DATE_FROM) {
                    txt.setText("Date From: " + date);
                } else if (flag == StaticAccess.DATE_TO) {
                    txt.setText("Date To: " + date);
                }else if (flag == StaticAccess.TIME) {
                    txt.setText("Time: " + time);
                }
            }
        }, yy, mm, dd);

        DialogNavBarHide.navBarHide(activity, datePicker);
    }

}
