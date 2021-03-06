package com.vegantravels.utilities;

/**
 * Created by RAFI on 4/3/2017.
 */

public class StaticAccess {
    //Root Url
    public static final String RootUrl = "http://dmaverick.com/cruize/public/";

    public static final String CURRENCY = " EUR";
    public static final String CameFromExport = " export";
    // Cruises Keys
    public static final String KEY_CRUISES_ID = "id";
    public static final String KEY_EXCURSION_UNIQUE_ID = "excursion_id";
    public static final String KEY_INTENT_DATE = "date";
    public static final String KEY_INTENT_CRUISES_UNIQUE_ID = "UniqueId";

    public static final String KEY_CRUISES_JSON_ARRAY = "cruizes";
    public static final String KEY_CRUISES_NAME = "name";
    public static final String KEY_SHIP_NAME = "ship_name";
    public static final String KEY_DATE_FROM = "from";
    public static final String KEY_DATE_TO = "to";
    public static final String KEY_CRUISE_UNIQUE_ID = "uniq_id";
    public static final String KEY_Activity_flag = "flag";

    public static final String CRIUZE_MANAGEMENT = "CruizeManagement";
    public static final String EXCURSION_MANAGEMENT = "ExcursionManagement";
    public static final String FINANCE_MANAGEMENT = "ExcursionManagement";

    /// Date picker
    public static final String DATE_FROM = "Date from";
    public static final String DATE_TO = "Date to";
    public static final String TIME = "Time";

    //Guest Key
//    public static final String KEY_CRUISES_ID = "cruiseId";
    public static final String KEY_GUEST_ID = "guestID";
    public static final String KEY_GUEST_NAME = "guestName";
    public static final String KEY_NUMBER_GUEST = "numberOfGuest";
    public static final String KEY_EXCURSION = "excursion";
    public static final String KEY_CABIN_NO = "cabinNo";
    public static final String KEY_PAYMENT_STATUS = "paymentStatus";


    public static final String INTENT_GUEST_ID_KEY = "IntentGuestID";

    //Guest detail Key
//    public static final String KEY_CRUISES_ID = "cruiseId";


    //excursion  Key
    public static final String KEY_EXCURSION_ID = "id";
    public static final String KEY_EXCURSION_NAME = "excursionName";

    //Number0fGuest  Key
    public static final String KEY_GUEST_NUMBER_ID = "id";
    public static final String KEY_GUEST_NUMBER_NAME = "guestNumberName";

    //Participant

    public static final String KEY_PARTICIPANT_GUEST_ID = "id";
    public static final String KEY_PARTICIPANT_GUEST_NAME = "guestName";
    public static final String KEY_PARTICIPANT_GUEST_FIRST_NAME = "guestFName";
    public static final String KEY_PARTICIPANT_GUEST_LAST_NAME = "guestLName";
    public static final int PAID_ALLREADY = 1;
    public static final int CASH_ON_BOARD = 2;
   // public static final int CREDIT_CARD = 3;
    public static final int COMPLEMENTARY = 3;

    public static String getPaymentByName(int payment) {
        String paymentValue = "";
        switch (payment) {
            case PAID_ALLREADY:
                paymentValue = "Paid Already";
                break;
            case CASH_ON_BOARD:
                paymentValue = "Cash on Board";
                break;
//            case CREDIT_CARD:
//                paymentValue = "Credit Card";
//                break;
            case COMPLEMENTARY:
                paymentValue = "Complimentary";
                break;
        }
        return paymentValue;
    }

    //// financial xls columns names
    public static final String FINANCIAL_STATUS_PER_CABIN = "FINANCIAL STATUS PER CABIN";
    public static final String KEY_CABIN_NUMBER = "CABIN NO";
    public static final String KEY_LAST_NAME = "LAST NAME";
    public static final String KEY_FIRST_NAME = "FIRST NAME";
    public static final String KEY_EXC_BOOKED = "EXC BOOKED";
    public static final String KEY_EXC_DATE = "EXC DATE";
    public static final String KEY_PPL = "PPL";
    public static final String KEY_TOTAL = "TOTAL";
    public static final String KEY_PAYMENT = "PAYMENT";




    //// FOR GENERATING GUSET LIST BY EXCURSION
    public static final String KEY_VT_ID= "VT ID NO";
    public static final String GUEST_LIST_PER_EXCURSION = "GUEST LIST PER EXCURSION";
    public static final String NR_NO = "Nr.";
    public static final String GUEST_IN_CABIN ="GUESTS IN CABIN" ;




    public static String dateString(String date){
        String res="";
        if(date!=null){
            String[] dateAr=new String[3];

            dateAr=date.split("-");

            String month=dateAr[1];

            if(month!=null){
                if(month.equals("0")){
                    month="Jan";
                }else if(month.equals("1")){
                    month="Feb";
                }else if(month.equals("2")){
                    month="Mar";
                }else if(month.equals("3")){
                    month="Apr";
                }else if(month.equals("4")){
                    month="May";
                }else if(month.equals("5")){
                    month="Jun";
                }else if(month.equals("6")){
                    month="Jul";
                }else if(month.equals("7")){
                    month="Aug";
                }else if(month.equals("8")){
                    month="Sep";
                }else if(month.equals("9")){
                    month="Oct";
                }else if(month.equals("10")){
                    month="Nov";
                }else if(month.equals("11")){
                    month="Dec";
                }

                res= dateAr[0]+"-"+month+"-"+dateAr[2];
            }
        }

        return  res;
    }


}
