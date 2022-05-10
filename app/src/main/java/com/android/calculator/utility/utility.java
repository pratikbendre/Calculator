package com.android.calculator.utility;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class utility {


    //    Other Stuff
    public static String decodeEmail(String email) {
        return email.replace(",", ".");
    }

    public static String encode(String email) {
        return email.replace(".", ",");
    }
    public static String givetime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
        String date = df.format(c.getTime());
        return date;
    }
    public static String givedateandtime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, hh:mm a");
        String date = df.format(c.getTime());
        return date;
    }

    public static String givedate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd YYYY");
        String date = df.format(c.getTime());
        return date;
    }


    public static String getdatefromtimestamp(long time) {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();//get your local time zone.
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd YY, hh:mm a");
        sdf.setTimeZone(tz);//set time zone.
        String localTime = sdf.format(new Date(time * 1000));
        Log.d("check123", "getdatefromtimestamp: " + localTime);
        return localTime;
    }

    public static String datetostring(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("MMM dd YY, hh:mm a");
        String strDate = dateFormat.format(date);
        Log.d("check123", "Converted String: " + strDate);
        return strDate;
    }
    public static Date stringtodate(String date)throws Exception {

        Date date1=new SimpleDateFormat("MMM dd YYYY").parse(date);
        return date1;
    }
    public static  Long datetotimestamp(Date date){
        long epoch = date.getTime();
        Log.d("date123",""+date);
        return  epoch/1000;
    }

    public static Long givetimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static void maketoast(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }


    public static String capitalize(String str)
    {
        if(str == null) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String giveRandomStringForDP() {
        Random random = new Random();
        return random.nextInt(9 - 1 + 1) + 1 + "";
    }


    //    random alphanumericstring
    public static final String DATA = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_";
    public static Random RANDOM = new Random();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            sb.append(DATA.charAt(RANDOM.nextInt(DATA.length())));
        }

        return sb.toString().toLowerCase();
    }

}
