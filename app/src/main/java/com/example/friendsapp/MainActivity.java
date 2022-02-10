package com.example.friendsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
AppCompatButton b1;
String getyname,getyfname,getyfnikname,getdyfriend;
String apiurl="https://dummyapifriends.herokuapp.com/adddata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText) findViewById(R.id.yname);
        ed2=(EditText) findViewById(R.id.yfname);
        ed3=(EditText) findViewById(R.id.yfnikname);
        ed4=(EditText) findViewById(R.id.dyfriend);
        b1=(AppCompatButton) findViewById(R.id.sub);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getyname=ed1.getText().toString();
               getyfname=ed2.getText().toString();
               getyfnikname=ed3.getText().toString();
               getdyfriend=ed4.getText().toString();
                StringRequest sr=new StringRequest(Request.Method.POST,
                        apiurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hm=new HashMap<>();
                        hm.put("name",getyname);
                        hm.put("friendName",getyfname);
                        hm.put("friendNickName",getyfnikname);
                        hm.put("DescribeYourFriend",getdyfriend);
                        return hm;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
                rq.add(sr);
            }
        });
    }
}