package com.example.undine.project_ooad;


import android.content.Intent;
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
                         monthString = plusZero(materialCalendarView.getSelectedDate().getMonth());
                         yearString = "" + materialCalendarView.getSelectedDate().getYear();
                         forBackend = yearString + monthString + dayString;
                         Toast.makeText(view.getContext(), forBackend,
                                 Toast.LENGTH_LONG).show();



                        startActivity(new Intent(view.getContext(), ChangePassword.class));
                     }
                 });

                materialCalendarView.setArrowColor(getResources().getColor(R.color.purpleA100));


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


}
