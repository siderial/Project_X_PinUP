package com.example.undine.project_ooad;

import android.app.Notification;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

/**
 * Created by Administrator on 15/11/2558.
 */
public class CommentFreament extends Fragment {

    public CommentFreament(){
    }
    private  int topicID=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.activity_detail, container, false);
        mListView = (ListView) rootView.findViewById(R.id.listView);
        ArrayList<Comment> listContact ;
        //  ListView lv = (ListView)rootView.findViewById(R.id.listView);
        //lv.setAdapter(new CustomAdapter(getActivity(), listContact));

//        Bundle extra = getIntent().getExtras();
//        topicID = extra.getInt("Topic_ID");
        new SimpleTask().execute(URL + "getCommentByTopicID?topicID=80");

//        Bundle extra = getIntent().getExtras();
//        String forBackend = extra.getString("DATE");




        return rootView;

    }



    public static final String URL = "http://203.151.92.175:8080/";
    private ListView mListView;
    private CommentAdapterFragment mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
        JsonParser parser = new JsonParser();
        JsonArray jArray = parser.parse(jsonString).getAsJsonArray();
        ArrayList<Comment> comments = new ArrayList<>();

        for (JsonElement obj : jArray){
            Comment comment = gson.fromJson(obj,Comment.class);
            comments.add(comment);
        }

        mAdapter = new CommentAdapterFragment(getActivity(), comments);
        mListView.setAdapter(mAdapter);

    }
}
