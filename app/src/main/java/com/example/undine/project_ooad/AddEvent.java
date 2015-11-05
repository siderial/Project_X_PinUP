package com.example.undine.project_ooad;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddEvent extends AppCompatActivity {

    private Toolbar toolbar;

    //start date
    Button btsd;
    int y,m,d;
    static final int idd=0;
    TextView sd;

    //start time
    Button btst;
    int ho ,mi;
    static final int idt=1;
    TextView st;

    //end date
    Button btsd2;
    int y2,m2,d2;
    static final int idd2=2;
    TextView sd2;

    //end time
    Button btst2;
    int ho2 ,mi2;
    static final int idt2=3;
    TextView st2;

    Button bp;

    //------------------------------------------------------------------------------------------
    public void showDialogOnButtonClick(){
        btsd = (Button)findViewById(R.id.buttonSd);
        sd=(TextView)findViewById(R.id.textSd);
        btsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(idd);
            }
        });
    }
    public void showDialogOnButtonClick2(){
        btsd2 = (Button)findViewById(R.id.buttonEd);
        sd2=(TextView)findViewById(R.id.textEd);
        btsd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(idd2);
            }
        });
    }

    public  void  showTimePickerDialog(){
        btst=(Button)findViewById(R.id.buttonSt);
        st=(TextView)findViewById(R.id.textSt);
        btst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(idt);
            }
        });
    }

    public  void  showTimePickerDialog2(){
        btst2=(Button)findViewById(R.id.buttonEt);
        st2=(TextView)findViewById(R.id.textEt);
        btst2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(idt2);
            }
        });
    }
//------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        if (id == R.id.setting_action) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
