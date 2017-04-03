package com.vegantravels.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vegantravels.R;

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

        btnCancelPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnOkPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        DialogNavBarHide.navBarHide(activity, dialog);



    }



}
