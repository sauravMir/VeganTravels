package com.vegantravels.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.vegantravels.R;
import com.vegantravels.activities.AddCruizeActivity;
import com.vegantravels.activities.AddExcursionActivity;
import com.vegantravels.activities.GuestListThreeActivity;
import com.vegantravels.activities.ViewExcursionActivity;
import com.vegantravels.adapter.GuestThreeAdapter;
import com.vegantravels.dao.Guests_TMP;
import com.vegantravels.manager.DatabaseManager;
import com.vegantravels.manager.IDatabaseManager;
import com.vegantravels.utilities.StaticAccess;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rakib on 4/3/2017.
 */

public class AllDialog {

    EditText edtCabinNumber, edtCabinName;
    IDatabaseManager databaseManager;
    GuestListThreeActivity guestListThreeActivity;
    AddCruizeActivity addCruizeActivity;
    ViewExcursionActivity viewExcursionActivity;
    AddExcursionActivity addExcursionActivity;

    public AllDialog(AddCruizeActivity activity) {
        addCruizeActivity = activity;
        databaseManager = new DatabaseManager(activity);
    }

    public AllDialog(ViewExcursionActivity activity) {
        viewExcursionActivity = activity;
        databaseManager = new DatabaseManager(activity);
    }

    public AllDialog(GuestListThreeActivity activity) {
        guestListThreeActivity = activity;
        databaseManager = new DatabaseManager(activity);
    }
    public AllDialog(AddExcursionActivity activity) {
        addExcursionActivity = activity;
        databaseManager = new DatabaseManager(activity);
    }

    ///confirm dialog
    public void confirmDialog(String title, final ViewExcursionActivity activity) {

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
                paymentDialog(activity);
                dialog.dismiss();
            }
        });
        DialogNavBarHide.navBarHide(activity, dialog);

    }

    /// payment dialog
    public void paymentDialog(final ViewExcursionActivity activity) {

        final Dialog dialog = new Dialog(activity, R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.payment_dialog);
        dialog.setCancelable(true);

        ImageButton btnCancelPayment = (ImageButton) dialog.findViewById(R.id.btnCancelPayment);
        ImageButton btnOkPayment = (ImageButton) dialog.findViewById(R.id.btnOkPayment);

        final RadioGroup rdGrp = (RadioGroup) dialog.findViewById(R.id.myRadioGroup);
        RadioButton rbPaidAlReady = (RadioButton) dialog.findViewById(R.id.rbPaidAlReady);
        RadioButton rdCashBord = (RadioButton) dialog.findViewById(R.id.rbCashOnBoard);
        RadioButton rdCaditCard = (RadioButton) dialog.findViewById(R.id.rbCreditCard);
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
                    activity.bookedExcursion(StaticAccess.PAID_ALLREADY);
                    dialog.dismiss();

                } else if (selectedId == R.id.rbCashOnBoard) {
                    activity.bookedExcursion(StaticAccess.CASH_ON_BOARD);
                    dialog.dismiss();

                } else if (selectedId == R.id.rbCreditCard) {
                    activity.bookedExcursion(StaticAccess.CREDIT_CARD);
                    dialog.dismiss();

                } else if (selectedId == R.id.rbComplementary) {
                    activity.bookedExcursion(StaticAccess.COMPLEMENTARY);
                    dialog.dismiss();


                }
            }
        });
        DialogNavBarHide.navBarHide(activity, dialog);


    }


    public void dialogForSearch(final long cruizeUniqID) {
        final Dialog dialog = new Dialog(guestListThreeActivity, R.style.CustomAlertDialog);
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
//
//                Intent intent = new Intent(activity, GuestListTwoActivity.class);
//                activity.startActivity(intent);

                if (edtCabinNumber.getText().length() > 0 && edtCabinName.getText().length() > 0) {
                    guestListThreeActivity.guestList.clear();
                    guestListThreeActivity.guestList = (ArrayList<Guests_TMP>) databaseManager.getSearchByNameCabin(edtCabinName.getText().toString(), edtCabinNumber.getText().toString(),cruizeUniqID);

                } else if (edtCabinNumber.getText().length() > 0 && edtCabinName.getText().length() <= 0) {
                    guestListThreeActivity.guestList.clear();
                    guestListThreeActivity.guestList = (ArrayList<Guests_TMP>) databaseManager.getSearchByCabin(edtCabinNumber.getText().toString(),cruizeUniqID);
                } else if (edtCabinNumber.getText().length() <= 0 && edtCabinName.getText().length() > 0) {
                    guestListThreeActivity.guestList.clear();
                    guestListThreeActivity.guestList = (ArrayList<Guests_TMP>) databaseManager.getSearchByName(edtCabinName.getText().toString(),cruizeUniqID);
                } else {
                    guestListThreeActivity.guestList.clear();
                    guestListThreeActivity.guestList = databaseManager.listGuestByUniqueId(guestListThreeActivity.uniqueId);
                }

                if (guestListThreeActivity.guestList != null) {
                    guestListThreeActivity.adapter = new GuestThreeAdapter(guestListThreeActivity, guestListThreeActivity.guestList, guestListThreeActivity.date);
                    guestListThreeActivity.lstGuest.setAdapter(guestListThreeActivity.adapter);
                }


                dialog.dismiss();
            }
        });

        DialogNavBarHide.navBarHide(guestListThreeActivity, dialog);
    }


    ///payment success dialog
    public void paymentCompletionDialog(String text) {

        final Dialog dialog = new Dialog(viewExcursionActivity, R.style.CustomAlertDialog);
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
        DialogNavBarHide.navBarHide(viewExcursionActivity, dialog);

    }


    public void setCustomDate(final TextView txt, final String flag) {

        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(addCruizeActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear) + "-" + String.valueOf(year);
                long time = calendar.getTimeInMillis();
                if (flag == StaticAccess.DATE_FROM) {
                    txt.setText(date);
                } else if (flag == StaticAccess.DATE_TO) {
                    txt.setText(date);
                }
            }
        }, yy, mm, dd);

        DialogNavBarHide.navBarHide(addCruizeActivity, datePicker);
    }

    public void setCustomTime(final TextView txt) {

        TimePickerDialog timePickerDialog = new TimePickerDialog(addCruizeActivity, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                final Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                Date millis = calendar.getTime();
                txt.setText(String.valueOf(new SimpleDateFormat("h:mm a").format(millis).toString()));
            }
        }, 10, 20, true);

        DialogNavBarHide.navBarHide(addCruizeActivity, timePickerDialog);
    }





    public void setCustomDateForEx(final TextView txt, final String flag) {

        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(addExcursionActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear) + "-" + String.valueOf(year);
                long time = calendar.getTimeInMillis();
                if (flag == StaticAccess.DATE_FROM) {
                    txt.setText(date);
                } else if (flag == StaticAccess.DATE_TO) {
                    txt.setText(date);
                }
            }
        }, yy, mm, dd);

        DialogNavBarHide.navBarHide(addExcursionActivity, datePicker);
    }

    public void setCustomTimeForEx(final TextView txt) {

        TimePickerDialog timePickerDialog = new TimePickerDialog(addExcursionActivity, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                final Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                Date millis = calendar.getTime();
                txt.setText(String.valueOf(new SimpleDateFormat("h:mm a").format(millis).toString()));
            }
        }, 10, 20, true);

        DialogNavBarHide.navBarHide(addExcursionActivity, timePickerDialog);
    }



    ///confirm dialog
    /*public void deletePermissionDialog() {

        final Dialog dialog = new Dialog(activity, R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.confirm_dialog);
        dialog.setCancelable(true);

        final TextView tvPermission = (TextView) dialog.findViewById(R.id.tvPermission);
        ImageButton btnCancelPermission = (ImageButton) dialog.findViewById(R.id.btnCancelPermission);
        ImageButton btnOkPermission = (ImageButton) dialog.findViewById(R.id.btnOkPermission);
        tvPermission.setText("Do you want to delete?");

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
*/

}
