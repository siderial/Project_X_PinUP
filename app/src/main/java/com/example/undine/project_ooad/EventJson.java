package com.example.undine.project_ooad;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
/**
 * Created by Administrator on 3/11/2558.
 */
public class EventJson extends AppCompatActivity {
    public static final String URL = "http://203.151.92.175:8080/";
    private ListView mListView;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);


        new SimpleTask().execute(URL + "getTopicAll");
    }

    private class SimpleTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar
        }

        protected String doInBackground(String... urls) {
            String result = "";
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
        mAdapter = new CustomAdapter(this, events);
        mListView.setAdapter(mAdapter);
    }
}
