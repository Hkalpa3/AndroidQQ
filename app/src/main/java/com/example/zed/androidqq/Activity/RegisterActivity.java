package com.example.zed.androidqq.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.zed.androidqq.R;
import com.example.zed.androidqq.XmppCls.XmppConnection;

public class RegisterActivity extends AppCompatActivity {
    String acc,pwd;
    RelativeLayout rlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }


    void init(){

        //返回layout点击
        rlayout=(RelativeLayout)findViewById(R.id.relativelayoutRegistBack);
        //不能之直接使用findViewById(R.id.relativelayoutRegistBack)..setOnClickListener(new View.OnClickListener(){
        rlayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent itn=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(itn);
                finish();
            }
        });
        //注册btn点击
        findViewById(R.id.btnRegist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册的账号密码
                acc=((EditText)findViewById(R.id.etRegistacc)).getText().toString();
                pwd=((EditText)findViewById(R.id.etRegistapwd)).getText().toString();
                new myAsyncTask().execute(acc,pwd);
            }
        });
    }

//    void Toregist()  {
//        //注册的账号密码
//        acc=((EditText)findViewById(R.id.etRegistacc)).getText().toString();
//        pwd=((EditText)findViewById(R.id.etRegistapwd)).getText().toString();
//        //
//        try {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    if(XmppConnection.getInstance().regist(acc,pwd)){
//                        Toast.makeText(RegisterActivity.this, acc+" 注册成功", Toast.LENGTH_SHORT).show();
//
////                        Intent itn=new Intent(RegisterActivity.this,LoginActivity.class);
////                        startActivity(itn);
//                    }else {
//                        Toast.makeText(RegisterActivity.this, acc+" 失败。。", Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//            }).start();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }


    class myAsyncTask extends AsyncTask<String,Integer,Boolean>{
        @Override
        protected Boolean doInBackground(String... params) {
            boolean result=XmppConnection.getInstance().register(params[0],params[1]);
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){//注册成功
                Toast.makeText(RegisterActivity.this, acc+" 注册成功!", Toast.LENGTH_SHORT).show();
                Intent itn=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(itn);
                finish();
            }else {
                Toast.makeText(RegisterActivity.this, "注册失败。。", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
//        if(!sub.isUnsubscribed())
//            sub.unsubscribe();
        super.onDestroy();
    }
}
