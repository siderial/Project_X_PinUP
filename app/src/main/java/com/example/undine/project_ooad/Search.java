package com.example.undine.project_ooad;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Search extends AppCompatActivity {

    private Toolbar toolbar;
    private String parameter="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);




        final ImageButton ib=(ImageButton)findViewById(R.id.searchButton);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(Search.this, Search.class);
               // startActivity(intent);

                EditText keyword=(EditText) findViewById(R.id.editText);
                parameter="?keyword="+keyword.getText().toString();

                new SimpleTask().execute(URL + "searchByKeyword"+parameter);

            }
        });

        final ImageView ibb=(ImageView)findViewById(R.id.backButton);
        ibb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Search.this, MainActivity.class);
                startActivity(intent2);
            }
        });

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
    public static final String URL = "http://203.151.92.175:8080/";
    private ListView mListView;
    private CustomAdapterActivity mAdapter;


    private class SimpleTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar
        }

        protected String doInBackground(String... urls) {
            String result = "";
            IOException ee;
            Exception ex;
            try {

                HttpGet httpGet = new HttpGet(urls[0]);
                HttpClient client = new DefaultHttpClient();

                HttpResponse response = client.execute(httpGet);

                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {
                ee=e;
            }catch (Exception e){
                ex=e;
            }
            return result;
        }

        protected void onPostExecute(String jsonString) {
            // Dismiss ProgressBar
            showData(jsonString);
        }


}

    private void showData(String jsonString) {
        Gson gson = new Gson();
        //Blog blog = gson.fromJson(jsonString, Blog.class);
        //Map map = gson.fromJson(jsonString, Map.class);
        JsonParser parser = new JsonParser();
        JsonArray jArray = parser.parse(jsonString).getAsJsonArray();
        ArrayList<Event> events = new ArrayList<Event>();

        for (JsonElement obj : jArray){
            Event event = gson.fromJson(obj,Event.class);
            events.add(event);
        }

        //StringBuilder builder = new StringBuilder();
        //builder.setLength(0);
        //List<Post> posts = blog.getPosts();
        //List<Event> events = map.getEvents();

        /*for (Event event : events) {
            builder.append(event.getDescription());
            builder.append("\n");
            builder.append(event.getLocation());
            builder.append("\n\n");
        }*/

        //mAdapter = new CustomAdapter(this, posts);
        //mListView.setAdapter(mAdapter);
        //Toast.makeText(this, jsonString, Toast.LENGTH_LONG).show();
        //Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show();
        /*
        try {
            JSONArray array = new JSONArray(jsonString);
            JSONObject title = array.getJSONObject(0);

            String ss= title.getString("nameTitle");
            Log.v("AAAAAAAAAA", ss);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        mAdapter = new CustomAdapterActivity(this, events);
        mListView.setAdapter(mAdapter);
    }

}

