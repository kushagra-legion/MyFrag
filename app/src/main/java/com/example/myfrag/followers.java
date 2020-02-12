package com.example.myfrag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myfrag.Adapter.RecyclerViewAdapter;
import com.example.myfrag.Adapter.example_item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class followers extends AppCompatActivity {
    private RecyclerView mrecyclerview;
    private RecyclerViewAdapter mrecycleadapter;
    private RecyclerView.LayoutManager layoutManager;



    private String url= "https://api.github.com/users/akashsarkar188/followers";
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        sendAndRequestResponse1();
    }

    private void sendAndRequestResponse1() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response!=null){
                            // Process the JSON
                            ArrayList<example_item> exampleList=new ArrayList<>();
                            try{
                                // Loop through the array elements
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject js = response.getJSONObject(i);
                                    String id = js.getString("id");


                                    String loginname = js.getString("login");

                                    String loginpic = js.getString("avatar_url");
                                    exampleList.add(new example_item(loginpic, loginname));

                                }

                                mrecyclerview = findViewById(R.id.recycler_view1);
                                mrecyclerview.setHasFixedSize(true);
                                layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                                mrecycleadapter = new RecyclerViewAdapter(exampleList);
                                mrecyclerview.setLayoutManager(layoutManager);
                                mrecyclerview.setAdapter(mrecycleadapter);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }}
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        mRequestQueue.add(jsonArrayRequest);

    }






}
