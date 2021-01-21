package com.lisa.dispoman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lisa.dispoman.Model.apiDispen;
import com.lisa.dispoman.Model.apiGuru;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoriActivity extends AppCompatActivity {

    private AppSession appSession;
    RecyclerView mRecycleView;
    RecyclerView.LayoutManager layoutManager;



    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori);

        appSession = new AppSession(getApplicationContext());

        mRecycleView = findViewById(R.id.RcView);
        mRecycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);


        show();

    }

    private void show() {
        apiGuru apiguru = appSession.getGuru();
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://dispo-web.herokuapp.com/api/dispensasi?guru_id=" + apiguru.id;
        Log.d(":DEBUG", "url: " + url);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: "+ response.substring(0,500));
                        Log.d("DEBUG", "onResponse: " + response);

                        try {
                            JSONObject myJson = new JSONObject(response);
                            String myresp = myJson.get("data").toString();
                            Gson gson = new Gson();
                            List<apiDispen> dispens = gson.fromJson(myresp, new TypeToken<List<apiDispen>>(){}.getType());

                            RecyclerView.Adapter mAdapter = new MyAdapter(getApplicationContext(), dispens);
                            mRecycleView.setAdapter(mAdapter);
                        } catch (JSONException e) {
                            Log.d(":DEBUG", "url: " + e.getMessage().toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                progressDialog.hide();
                Log.d(":DEBUG", "url: " + error.getMessage());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }


}

