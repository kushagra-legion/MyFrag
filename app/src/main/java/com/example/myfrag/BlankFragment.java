package com.example.myfrag;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


public class BlankFragment extends Fragment{
    Button button,button1;
    View root;
    Fragment frag;
    private RequestQueue mQueue;
    TextView t1,t2,t3;
    ImageView imageView;
    String id;
    Button b1,b2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_blank, container, false);
        t1 = root.findViewById(R.id.textView2);
        t2 = root.findViewById(R.id.textView3);
        t3 = root.findViewById(R.id.textView4);
        imageView = root.findViewById(R.id.imageView);
        b1=(Button)root.findViewById(R.id.button6);
        b2=(Button)root.findViewById(R.id.textView5);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),followers.class));
            }
        });
        mQueue = Volley.newRequestQueue(getActivity());
        jsonParse();
        btn();
        return root;

    }

    public void btn(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+id));
                startActivity(browserIntent);
            }
        });
    }
    private void jsonParse() {

        String url = "https://api.github.com/users/akashsarkar188";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String firstName = response.getString("name");
                            String age = response.getString("location");
                            String mail = response.getString("bio");
                            id = response.getString("blog");
                            String image=response.getString("avatar_url");
                            Picasso.get().load(image).into(imageView);
                            t1.setText(firstName);
                            t2.setText(age);
                            t3.setText(mail);
                            b1.setText(id);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

}

