package com.example.a57258.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {
    private TextView T,T2,T3,T4,T5;
    private ImageView img;
    private RatingBar star;
    private String url;
    private RequestQueue requestQueue;
    private List<HashMap<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        requestQueue = Volley.newRequestQueue(this);
        list = new ArrayList<>();
        Intent i=getIntent();
        String st=i.getStringExtra("data");
        String st1=i.getStringExtra("data1");
        String st2=i.getStringExtra("data2");
        String st3=i.getStringExtra("data3");
        T=(TextView) findViewById(R.id.textView1);
        T2=(TextView) findViewById(R.id.textView2);
        T3=(TextView) findViewById(R.id.textView3);
        T4=(TextView) findViewById(R.id.textView4);
        img=(ImageView)findViewById(R.id.imgg);
        star=(RatingBar)findViewById(R.id.ratingBar);
        T5=(TextView)findViewById(R.id.starword);

        switch (st2){
            case "A":star.setRating(1);T5.setText("bad!");break;
            case "AA":star.setRating(2);T5.setText("Ordinary!");break;
            case "AAA":star.setRating(3);T5.setText("Good!");break;
            case "AAAA":star.setRating(4);T5.setText("Great!");break;
            case "AAAAA":{
                star.setRating(5);
                T5.setText("Perfect!");
            };
            break;
        }

        T.setText("景区名称："+st);
        T2.setText(st1);
        T3.setText("景区评级："+st2);
        T4.setText(st3);

        img.setOnClickListener(new View.OnClickListener() {
            boolean f=true;
            @Override
            public void onClick(View v) {
                if(f){
                    img.setImageResource(R.drawable.ball);
                    f=false;
                }
                else {
                    img.setImageResource(R.drawable.zhangsan);
                    f=true;
                }
            }
        });
    }
}
