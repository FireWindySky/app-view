package com.example.a57258.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    //declare the widget
    private ListView contentlist;
    private Button btna;
    private EditText txta;
    private String num;
    private Button Next;
    private int[] imgArray = {R.drawable.a1, R.drawable.a2, R.drawable.a3,R.drawable.a4,R.drawable.a5,R.drawable.a6,R.drawable.a7,R.drawable.a8,R.drawable.a9,R.drawable.a10,R.drawable.a11,R.drawable.a12};

    //declare adapter
    private SimpleAdapter adapter;
    private List<HashMap<String, Object>> list;
    private int pnum=1;
    private String url = "http://data.zjzwfw.gov.cn/jdop_front/interfaces/cata_4368/get_data.do?pageNum=1&pageSize=15&appsecret=d2380c0c8844487b9a222bd74c2d005c";
    private String url1 = url;
    private RequestQueue requestQueue;
    String imageurl;

//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
////            super.handleMessage(msg);
//            Bundle bundle = msg.getData();
//            for(int i=0;i<3;i++) {
//                final int index = i;
//                imageurl = url + bundle.getString("im" + i);
//                try {
//                    ImageRequest imageRequest = new ImageRequest(imageurl,
//                            new Response.Listener<Bitmap>() {
//                                @Override
//                                public void onResponse(Bitmap bitmap) {
//                                    Log.e("imageurl",imageurl);
//                                    list.get(index).put("image",bitmap);
//                                    adapter.notifyDataSetChanged();
//                                }
//                            }, 0, 0, Bitmap.Config.RGB_565,
//                            new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError volleyError) {
//                                    Toast.makeText(MainActivity.this,
//                                            "loading Image Error.",Toast.LENGTH_LONG).show();
//                                }
//                            });
//                    requestQueue.add(imageRequest);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        contentlist = (ListView) findViewById(R.id.contentlist);
        TextView contentText = (TextView) findViewById(R.id.text);
        Next = (Button)findViewById(R.id.next);
        requestQueue = Volley.newRequestQueue(this);
        //initiate list
        list = new ArrayList<>();

        adapter = new SimpleAdapter(this, list, R.layout.item,
                new String[]{"image","title"},
                new int[]{R.id.img,R.id.txttitle});
        adapter.setViewBinder(new MyViewBinder());
        contentlist.setAdapter(adapter);


        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                pnum += 1;
                if(pnum>70){
                    pnum=1;
                }
                url1 = "http://data.zjzwfw.gov.cn/jdop_front/interfaces/cata_4368/get_data.do?pageNum="+pnum+"&pageSize=15&appsecret=d2380c0c8844487b9a222bd74c2d005c";

                JSONArray jsonArray = new JSONArray();

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonArray.put(0, "123456789");
                    jsonObject.put("data", jsonArray);
                    JsonObjectRequest request = new JsonObjectRequest(url1, jsonObject,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {

                                        if(true){
                                            JSONArray data = jsonObject.getJSONArray("data");
                                            for (int i = 0; i <data.length(); i++) {
                                                Random rand=new Random();
                                                int r=rand.nextInt(12);
                                                HashMap<String, Object> hash = new HashMap<>();
                                                JSONObject item = data.getJSONObject(i);
                                                String asd=(String)item.get("unitname");
                                                String address="景区地址："+(String)item.get("address");
                                                String level=(String)item.get("level");
                                                String tel="电话："+item.get("tel").toString();
                                                hash.put("image",imgArray[r]);
                                                hash.put("title",asd);
                                                hash.put("address",address);
                                                hash.put("level",level);
                                                hash.put("tel",tel);
                                                list.add(hash);
                                            }
                                            adapter.notifyDataSetChanged();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(SecondActivity.this, "error0", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(SecondActivity.this, "error", Toast.LENGTH_LONG).show();
                        }
                    });

                    requestQueue.add(request);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SecondActivity.this, "error1", Toast.LENGTH_LONG).show();
                }


//                contentlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//                    @Override
//                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i2, long l) {
//                        Intent ii=new Intent(SecondActivity.this,ThirdActivity.class);
//                        ii.putExtra("data",""+i2+pnum);
//                        startActivity(ii);
//                        return true;
//
//                    }
//                });


            }
        });

        contentlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
//                SecondActivity.this.startActivity(intent);
//                Toast.makeText(SecondActivity.this, "the item clicked!", Toast.LENGTH_LONG).show();
            }
        });

        contentlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent ii=new Intent(SecondActivity.this,ThirdActivity.class);
                ii.putExtra("data",list.get(i).get("title").toString());
                ii.putExtra("data1",list.get(i).get("address").toString());
                ii.putExtra("data2",list.get(i).get("level").toString());
                ii.putExtra("data3",list.get(i).get("tel").toString());
                startActivity(ii);
                return true;

            }
        });

//        btna=(Button)findViewById(R.id.btn);
//        txta=(EditText)findViewById(R.id.txt);
//        btna.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pnum=txta.getText().toString();
//                url = "http://data.zjzwfw.gov.cn/jdop_front/interfaces/cata_4368/get_data.do?pageNum="+pnum+"&pageSize=1&appsecret=d2380c0c8844487b9a222bd74c2d005c";
//
//            }
//        });
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonArray.put(0, "123456789");
            jsonObject.put("data", jsonArray);
            JsonObjectRequest request = new JsonObjectRequest(url1, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            try {

                                if(true){
                                    JSONArray data = jsonObject.getJSONArray("data");
                                    for (int i = 0; i <data.length(); i++) {
                                        Random rand=new Random();
                                        int r=rand.nextInt(12);
                                        HashMap<String, Object> hash = new HashMap<>();
                                        JSONObject item = data.getJSONObject(i);
                                        String asd=(String)item.get("unitname");
                                        String address="景区地址："+(String)item.get("address");
                                        String level=(String)item.get("level");
                                        String tel="电话："+item.get("tel").toString();
                                        hash.put("image",imgArray[r]);
                                        hash.put("title",asd);
                                        hash.put("address",address);
                                        hash.put("level",level);
                                        hash.put("tel",tel);
                                        list.add(hash);
                                    }
                                    adapter.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(SecondActivity.this, "error0", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(SecondActivity.this, "error", Toast.LENGTH_LONG).show();
                }
            });

            requestQueue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(SecondActivity.this, "error1", Toast.LENGTH_LONG).show();
        }

    }

}
