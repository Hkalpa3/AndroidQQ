package com.example.zed.androidqq.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zed.androidqq.R;
import com.example.zed.androidqq.XmppCls.XmppConnection;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{
    String acc, pwd;
    EditText etact, etpswd;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags
                (WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

       // bindService();

        initView();
        openConnection();


    }

//    private void bindService() {
//        //开启服务获得与服务器的连接
//        Intent intent=new Intent(this,XmppConnection.class);
//        bindService(intent, new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName name, IBinder service) {
//                XmppConnection.getInstance().LocalBinder
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//
//            }
//        },BIND_AUTO_CREATE);
//        //
//        startService(intent)
//    }


    void initView() {
        etact = (EditText) findViewById(R.id.etAccount);
        etpswd = (EditText) findViewById(R.id.etPswd);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
//        btnLogin.setClickable(false);
        findViewById(R.id.btnToRegist).setOnClickListener(this);
    }

    void openConnection(){
        final Handler conHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        btnLogin.setBackgroundColor(Color.parseColor("#377ff4"));
                        btnLogin.setClickable(true);
                        Toast.makeText(LoginActivity.this, "已连接到openfire server", Toast.LENGTH_SHORT).show();
                        Log.i("msg", "handleMessage: 已连接到openfire server");
                        break;
                    case 1:
                        Toast.makeText(LoginActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        //开启线程 连接服务器
        //成功handler msg.what=0  失败=1
        new Thread(new Runnable() {
            @Override
            public void run() {
               if(XmppConnection.getInstance().createConnection()) {
                   //Log.i("msg", "已连接到 server");
                   conHandler.sendEmptyMessage(0);
               }
               else {
                    Log.i("msg", "连接server失败 ");
                    conHandler.sendEmptyMessage(1);
                }
}
        }).start();
    }



    // 监听点击
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
               // doLogin();

                Log.i("msg", "btnlogin_click: 点击");
                acc = etact.getText().toString();
                pwd = etpswd.getText().toString();
                if(acc.isEmpty()||pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this, "账号或者密码为空。", Toast.LENGTH_SHORT).show();
                }
                new LoginAsyncTask().execute(acc,pwd);
                break;

            case R.id.btnToRegist:
                Intent itn = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(itn);
                break;
        }
    }


    /*
        登录的异步类
     */

    class LoginAsyncTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
          //  Boolean result = false;
            boolean result = XmppConnection.getInstance().login(params[0], params[1]);
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!result) {
                Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent itn=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(itn);
                finish();
            }
        }
    }

}
