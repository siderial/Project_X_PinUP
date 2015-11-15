package com.example.undine.project_ooad;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;


/**
 * A simple {@link Fragment} subclass.
 */
public class Schedule extends Fragment {


    MaterialCalendarView materialCalendarView;
    View view;
    String dayString;
    String monthString;
    String yearString;
    String forBackend;
   public static String parameterSchedule;


    public Schedule() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            view = inflater.inflate(R.layout.fragment_schedule, container, false);

                materialCalendarView = (MaterialCalendarView)view.findViewById(R.id.calendarView);
                 materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
                     @Override
                     public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
                         dayString = plusZero(materialCalendarView.getSelectedDate().getDay());
                         monthString = plusZero(materialCalendarView.getSelectedDate().getMonth()+1);
                         yearString = "" + materialCalendarView.getSelectedDate().getYear();
                         forBackend = yearString + monthString + dayString;

                         Toast.makeText(view.getContext(), forBackend,
                                 Toast.LENGTH_LONG).show();


                       Intent intent = new Intent(getActivity(), Day.class);
                      intent.putExtra("DATE",forBackend);
                         startActivity(intent);

                     }
                 });

                materialCalendarView.setArrowColor(getResources().getColor(R.color.purpleA100));

        parameterSchedule="accountID=123&date="+yearString+monthString+dayString;
     //   new HttpTask().execute();

        return view;

    }


    String plusZero(int number){
       String plusezero;
        if(number<10){
          plusezero = "0"+number;
        }
        else plusezero = "" + number;


        return  plusezero;
    }


    //------------------------------------------Send Post-----------------------------------

    private final String USER_AGENT = "Mozilla/5.0";
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public  String sendPost1() throws Exception {

        // String urlParameters  = "title=Mikasa&startDate=20151102&location=NewYork&desc=HelloBob&type=private&rate=4.5&tag=HELLO";
        String urlParameters=parameterSchedule;
        byte[] postData = urlParameters.getBytes("UTF-8");
        int postDataLength = postData.length;
        URL url;
        HttpURLConnection urlConn;
        DataOutputStream printout;
        DataInputStream input;
        url = new URL("http://203.151.92.175:8080/getScheduleOne");
        urlConn = (HttpURLConnection) url.openConnection();
        urlConn.setDoInput(true);
        urlConn.setDoOutput(true);
        urlConn.setUseCaches(false);
        urlConn.setInstanceFollowRedirects(false);
        urlConn.setRequestMethod("POST");
        urlConn.setRequestProperty("charset", "utf-8");
        urlConn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        urlConn.setRequestProperty("User-Agent", USER_AGENT);
        urlConn.connect();
        DataOutputStream wr = new DataOutputStream(urlConn.getOutputStream());
        wr.write(postData);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(urlConn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        urlConn.disconnect();
        return response.toString();



    }

    private class HttpTask extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... params)   {
            try {
                sendPost1();
                // postData();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "result";
        }

        protected void onProgressUpdate(Integer... values) {

        }

        protected void onPostExecute(String result)  {

        }
    }


}
