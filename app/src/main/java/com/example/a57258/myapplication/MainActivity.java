package com.example.a57258.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edUsername, edPassword;
    private CheckBox isSave;
    private Button btnSubmit,back;
    private ImageView img1;

    private String username,password;
    private String toastInfo = "";

    private View loginLL,loadingRel,html;
    private boolean FLAG=true;
    private ProgressBar horizontalPBar;
    private int mProgressBarStatus=0;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        edUsername = (EditText) findViewById(R.id.edtname);
        edPassword = (EditText) findViewById(R.id.edtpass);
        isSave = (CheckBox) findViewById(R.id.chkpass);
        btnSubmit = (Button) findViewById(R.id.btnok);
        loginLL=(View)findViewById(R.id.login_ll);
        loadingRel=(View)findViewById(R.id.loading_rel);
        html=(View)findViewById(R.id.html);
        horizontalPBar=(ProgressBar)findViewById(R.id.horizontal_pbar);
        back=(Button)findViewById(R.id.back);
        img1=(ImageView)findViewById(R.id.im1);

//        img1.setOnClickListener(new View.OnClickListener() {
//            boolean f=true;
//            @Override
//            public void onClick(View v) {
//                if(f){
//                    img1.setImageResource(R.drawable.zhangsan);
//                    f=false;
//                }
//                else {
//                    img1.setImageResource(R.drawable.im1);
//                    f=true;
//                }
//            }
//        });

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loginLL.setVisibility(View.VISIBLE);
//
//                loadingRel.setVisibility(View.GONE);
//
//                html.setVisibility(View.GONE);
//                Toast.makeText(getApplicationContext(),"成功退出\n请重新登录",Toast.LENGTH_SHORT).show();
//
//            }
//        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edUsername.getText().toString();
                password = edPassword.getText().toString();
                if(username.equals("null")&&password.equals("666")){
                    loginLL.setVisibility(View.GONE);
                    loadingRel.setVisibility(View.VISIBLE);
                    FLAG=true;
                    Toast.makeText(MainActivity.this,"账号密码正确", Toast.LENGTH_LONG).show();
                    mProgressBarStatus=0;
                    startThread(username,password);
                }else{
                    Toast.makeText(MainActivity.this,"账号密码错误", Toast.LENGTH_LONG).show();
                }
            }
            private void startThread(final String username,final String password){
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        while(FLAG){
                            mProgressBarStatus=addNum();
                            handler.post(new Runnable(){
                                @Override
                                public void run(){
                                    if(mProgressBarStatus<100){
                                        horizontalPBar.setProgress(mProgressBarStatus);
                                    }else{
                                        Toast.makeText(getApplicationContext(),"登陆成功!",Toast.LENGTH_SHORT).show();
                                        FLAG=false;
                                        loadingRel.setVisibility(View.GONE);
                                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                        MainActivity.this.startActivity(intent);
                                    }
                                }
                            });
                        }
                    }
                    private int addNum(){
                        mProgressBarStatus+=4;
                        try{
                            Thread.sleep(100);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                        return mProgressBarStatus;
                    }
                }).start();
            }

        });

    }
}
