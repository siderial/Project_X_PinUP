package com.example.undine.project_ooad;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {
    private ViewHolder mViewHolder;
    private String parameter="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       final  String  title,
                date,
                location,
                startTime,
                description;
        double rate;
       final int topicID;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                title= null;
                description= null;
                date = null;
                location = null;
                startTime=null;
                rate = 0;
                topicID = 0;
            } else {
                title = extras.getString("EVENT_TITLE");
                date = extras.getString("EVENT_DATE");
                description = extras.getString("EVENT_DES");
                location = extras.getString("EVENT_LOCATE");
                rate = extras.getDouble("EVENT_RATE");
                topicID = extras.getInt("TOPIC_ID");
                startTime = extras.getString("START_TIME");
            }
        } else {
            title= (String)savedInstanceState.getSerializable("EVENT_TITLE");
            description= (String) savedInstanceState.getSerializable("EVENT_DES");
            date= (String) savedInstanceState.getSerializable("EVENT_DATE");
            location = (String) savedInstanceState.getSerializable("EVENT_LOCATE");
            rate = (Double) savedInstanceState.getSerializable("EVENT_RATE");
            topicID = (Integer)savedInstanceState.getSerializable("TOPIC_ID");
            startTime = (String)savedInstanceState.getSerializable("START_TIME");
        }
        //Toast.makeText(getApplicationContext(),title+date+description+location+rate,Toast.LENGTH_SHORT).show();
        TextView textTitle = (TextView)findViewById(R.id.textTitle);
        TextView textDate = (TextView)findViewById(R.id.textDate);
        TextView textLocate = (TextView)findViewById(R.id.DesLocate);
        TextView textDes = (TextView)findViewById(R.id.DesDes);
        TextView textRate = (TextView)findViewById(R.id.textRate);
        RatingBar ratebar = (RatingBar)findViewById(R.id.ratingBar);
        textTitle.setText(title);
        textDate.setText(date);
        textLocate.setText(location);
        textDes.setText(description);
        textRate.setText(Double.toString(rate));
        ratebar.setRating((float) rate);
       Toast.makeText(this,""+topicID+"   "+startTime+"   "+date, Toast.LENGTH_LONG).show();

        final Switch ib=(Switch)findViewById(R.id.switchPin);
//        if (ib.isChecked()) {
//            parameter = "accountID=111&datetime=" + date + "&topicID=" + topicID ;
//            new HttpTask().execute();
//            Toast.makeText(this,""+topicID, Toast.LENGTH_LONG).show();
//        }
        int i=111;

       // parameter="accountID=765&date=20151113&topicID="+topicID+"&time=23:27:00";
        ib.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parameter ="accountID=776&date="+date.substring(0,4)+date.substring(5,7)+date.substring(8,10)+"&topicID=" + topicID+"&time="+ startTime;
                new HttpTask().execute();
               // Toast.makeText(this,"topicID", Toast.LENGTH_LONG).show();
            }
        });
   }
    private static class ViewHolder{
        ImageView thumbnail;
        TextView title;
        TextView date;
        TextView location;
        TextView description;
        TextView rate;
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
        url = new URL("http://203.151.92.175:8080/storePinup");
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
