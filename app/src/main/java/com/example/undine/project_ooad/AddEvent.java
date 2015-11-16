package com.example.undine.project_ooad;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class AddEvent extends AppCompatActivity {
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

    private Toolbar toolbar;

    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private Button btnDisplay;
    private String parameter="";
    private String setDate2="";
    private Event eventInsert=new Event();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showDialogOnButtonClick();//start date
        showTimePickerDialog();//start time
        showDialogOnButtonClick2();//end date
        showTimePickerDialog2();//end time

        bp=(Button)findViewById(R.id.buttonPost);
        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AddEvent.this, MainActivity.class);
                startActivity(it);
                String ti="", sd="", st="", ed="", et="", lo="", de="", ty="", tg="";
                EditText title = (EditText) findViewById(R.id.editTitle);
                if (title.getText().toString().length() != 0) ti = ""+title.getText().toString();

                TextView startDate = (TextView) findViewById(R.id.textSd);
               if (startDate.getText().toString().length() != 0){
                   sd = ""+startDate.getText().toString();
                   sd=setDate(sd);
               }

                TextView startTime = (TextView) findViewById(R.id.textSt);
                if (startTime.getText().toString().length() != 0) {
                    st = ""+startTime.getText().toString();
                   // st=setTime(st);
                    st=ho+mi+"00";
                }

                TextView endDate = (TextView) findViewById(R.id.textEd);
                if (endDate.getText().toString().length() != 0) {
                    ed = ""+endDate.getText().toString();
                    ed=setDate(ed);
                }

                TextView endTime = (TextView) findViewById(R.id.textEt);
                if (endTime.getText().toString().length() != 0){
                    et = ""+endTime.getText().toString();
                    //et=setTime(et);
                    et=ho2+mi2+"00";
                }

                EditText location = (EditText) findViewById(R.id.editText);
                if (location.getText().toString().length() != 0) lo = ""+location.getText().toString();

                EditText desc = (EditText) findViewById(R.id.editText2);
                if (desc.getText().toString().length() != 0) de = ""+desc.getText().toString();
                addListenerOnButton();

//                RadioGroup types = (RadioGroup) findViewById(R.id.typeRadio);
//                switch (types.getCheckedRadioButtonId()){
//                    case  R.id.radioButton:
//                        eventInsert.setTag("private");
//                        break;
//                    case R.id.radioButton2:
//                        eventInsert.setType("public");
//                        break;
//                }


                if (result[0].length()!=0)
                    ty=""+result[0];

                TextView tagInsert = (TextView) findViewById(R.id.tag);
                if(tagInsert.getText().toString().length() != 0) tg=""+tagInsert.getText().toString();
                RadioButton private1 = (RadioButton) findViewById(R.id.radioButton);
                RadioButton public1 = (RadioButton) findViewById(R.id.radioButton);

                parameter=""+
                        ((ti.length()==0)? "":"title="+ti+"&") + getAnd(ti,sd)+
                        ((sd.length()==0)? "":"startDate="+sd+"&")+ getAnd(sd,st)+
                        ((st.length()==0)? "":"startTime="+st+"&")+ getAnd(st,ed)+
                        ((ed.length()==0)? "":"endDate="+ed+"&")+getAnd(ed,et)+
                        ((et.length()==0)? "":"endTime="+et+"&")+getAnd(et,lo)+
                        ((lo.length()==0)? "":"location="+lo+"&")+getAnd(lo,de)+
                        ((de.length()==0)? "":"desc="+de+"&")+getAnd(de,ty)+
                        ((ty.length()==0)? "":"type="+ty+"&")+getAnd(ty,tg)+
                        ((tg.length()==0)? "":"tag="+tg);

                new HttpTask().execute();
            }

        });




    }

    private String getAnd(String s1,String s2){
        String result="";
        if (s1.length()!=0 & s2.length()!=0)
            result="&";
        return result;
    }
    //-------------------------------------------------------------------------------------------
    @Override
    protected Dialog onCreateDialog(int id){
        if(id==idd)
            return new DatePickerDialog(this, dpickerListner ,d,m,y);
        if(id==idt)
            return new TimePickerDialog(AddEvent.this, kTimePickerListner ,ho,mi,true);
        if(id==idd2)
            return new DatePickerDialog(this, dpickerListner2 ,d2,m2,y2);
        if(id==idt2)
            return new TimePickerDialog(AddEvent.this, kTimePickerListner2 ,ho2,mi2,true);
        return null;
    }

    protected TimePickerDialog.OnTimeSetListener kTimePickerListner = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            ho=hourOfDay;
            mi=minute;
            st.setText(ho+ " : " +mi);
        }

    };

    protected TimePickerDialog.OnTimeSetListener kTimePickerListner2 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            ho2=hourOfDay;
            mi2=minute;
            st2.setText(ho2+ " : " +mi2);
        }

    };

    private DatePickerDialog.OnDateSetListener dpickerListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            y=year;
            m=monthOfYear+1;
            d=dayOfMonth;
            sd.setText(d +"/"+m+"/"+y);
            //Toast.makeText(AddEvent.this, y+" / "+m+" / "+d, Toast.LENGTH_LONG).show();
        }
    };

    private DatePickerDialog.OnDateSetListener dpickerListner2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            y2=year;
            m2=monthOfYear+1;
            d2=dayOfMonth;
            sd2.setText(d2 +"/"+m2+"/"+y2);
            //Toast.makeText(AddEvent.this, y+" / "+m+" / "+d, Toast.LENGTH_LONG).show();
        }
    };
    //------------------------------------------------------------------------------------------
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


    //------------------------------------------Send Post-----------------------------------

    private final String USER_AGENT = "Mozilla/5.0";
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public  String sendPost1() throws Exception {

       // String urlParameters  = "title=Mikasa&startDate=20151102&location=NewYork&desc=HelloBob&type=private&rate=4.5&tag=HELLO";
        String urlParameters=parameter;
        byte[] postData = urlParameters.getBytes("UTF-8");
        int postDataLength = postData.length;
        URL url;
        HttpURLConnection urlConn;
        DataOutputStream printout;
        DataInputStream input;
        url = new URL("http://203.151.92.175:8080/postTopic");
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

    //-------------------------radio group--------------------------

    final String[] result = {""};
    public void addListenerOnButton() {

        radioGroup = (RadioGroup) findViewById(R.id.radio);
        btnDisplay = (Button) findViewById(R.id.radioButton);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
               // int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
               // radioButton1 = (RadioButton) findViewById(selectedId);

                Toast.makeText(AddEvent.this,
                        radioButton1.getText(), Toast.LENGTH_SHORT).show();
                CharSequence s=radioButton1.getText();


                RadioGroup types = (RadioGroup) findViewById(R.id.typeRadio);
               switch (types.getCheckedRadioButtonId()){
                   case  R.id.radioButton:
                        result[0] ="private";
                       break;
                   case R.id.radioButton2:
                       result[0] ="public";
                       break;
               }

            }

        });

    }
    public String setDate(String date){
       // String result[]={""};
        ArrayList<String>  array=new ArrayList<>();
        ArrayList<String>  array2=new ArrayList<>();
        String sim="";
        for (int i=0;i<date.length();i++){
            //char s=date.charAt(i);

            if(!(date.substring(i,i+1).equals("/")))
                sim=sim+date.substring(i,i+1);

            if(date.substring(i,i+1).equals("/")){
                array.add(sim);
                sim="";
            }
            if(i==date.length()-1){
                array.add(sim);
            }
        }
        Iterator<String> irt=array.iterator();

        for(int i=0;i<2;i++){
            String s=irt.next();
            if(s.equals("D") | s.equals("M") ){
                array2.add("00");
            }
            else if (s.length()==1){
                array2.add("0"+s);
            }

            if(s.length()==2){
                array2.add(s);
            }
        }
        String ss=irt.next();
        if (ss.equals("Y")){
            array2.add("0000");
        }
        else array2.add(ss);

        String result="";
        Iterator<String> irt2=array2.iterator();
        result=""+array2.get(2)+array2.get(1)+array2.get(0);

        if (result.equals("00000000"))
            result="";
        return result;
    }
    public String setTime(String time){
        String result="";

        ArrayList<String>  array=new ArrayList<>();
        ArrayList<String>  array2=new ArrayList<>();
        String sim="";
        for (int i=0;i<time.length();i++){
            //char s=date.charAt(i);

            if(!(time.substring(i,i+1).equals(":")))
                sim=sim+time.substring(i,i+1);

            if(time.substring(i,i+1).equals(":")){
                array.add(sim);
                sim="";
            }
            if(i==time.length()-1){
                array.add(sim);
            }
        }
        Iterator<String> irt=array.iterator();

        for(int i=0;i<2;i++){
            String s=irt.next();
            if(s.equals("D") | s.equals("M") ){
                array2.add("00");
            }
            else if (s.length()==1){
                array2.add("0"+s);
            }

            if(s.length()==2){
                array2.add(s);
            }
        }
        String ss=irt.next();
        if (ss.equals("Y")){
            array2.add("0000");
        }
        else array2.add(ss);

        Iterator<String> irt2=array2.iterator();
        result=""+array2.get(2)+array2.get(1)+array2.get(0);

        if (result.equals("00000000"));
        result="";
        return result;

    }
}
