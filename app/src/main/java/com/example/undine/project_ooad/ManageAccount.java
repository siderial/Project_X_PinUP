package com.example.undine.project_ooad;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManageAccount extends Fragment {

    private TextView changePass;
    private TextView deleteAcc;
    private TextView myEvents;
    private TextView logOut;
    private TextView helpAndFeed;

    public ManageAccount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_manage_account, container, false);


        deleteAcc = (TextView) view.findViewById(R.id.go_delete_account);
        deleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAcc.setBackground(getResources().getDrawable(R.drawable.background_select_on_click));
                startActivity(new Intent(view.getContext(), ChangePassword.class));

            }
        });

        myEvents = (TextView) view.findViewById(R.id.go_my_event);
        myEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myEvents.setBackground(getResources().getDrawable(R.drawable.background_select_on_click));
                startActivity(new Intent(view.getContext(), ChangePassword.class));

            }
        });

        logOut = (TextView) view.findViewById(R.id.go_log_out);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut.setBackground(getResources().getDrawable(R.drawable.background_select_on_click));
                startActivity(new Intent(view.getContext(), ChangePassword.class));

            }
        });

        helpAndFeed = (TextView)view.findViewById(R.id.go_help);
        helpAndFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpAndFeed.setBackground(getResources().getDrawable(R.drawable.background_select_on_click));
                startActivity(new Intent(view.getContext(), ChangePassword.class));

            }
        });


        return view;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }


}
