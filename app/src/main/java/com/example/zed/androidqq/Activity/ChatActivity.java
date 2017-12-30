package com.example.zed.androidqq.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zed.androidqq.Activity.adapter.ChatMsgAdapter;
import com.example.zed.androidqq.Bean.ChatTextMsg;
import com.example.zed.androidqq.R;
import com.example.zed.androidqq.RxBus.RxBus;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/*//offlinemessage
* <message to="hjs@kalpa3pc" id="7a3vw-376" type="chat" from="lm@kalpa3pc/Spark">
    *   <thread>N9DSHp</thread>                                                             多个离线消息TheadID 一样
    *   <composing xmlns="http://jabber.org/protocol/chatstates"></composing>
    *   <delay xmlns="urn:xmpp:delay" stamp="2017-12-25T14:09:17.970Z" from="kalpa3pc"/>
*   </message>*/
public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton ib_back;                //顶部返回
    TextView title_chat;                //toolbar  自定义标题
    RecyclerView recyclerView_msg;      //消息recyclerView
    EditText et_msg;                    //消息框
    Button btnSendMsg;                  //发送按钮
    ImageButton ib_record,ib_pic,ib_location,ib_emj;
    Subscription subs;
    List<ChatTextMsg> msgList=new ArrayList<>();    //消息列表
    ChatMsgAdapter chatAdapter;                 //recyclerView adapter




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initViews();
//        //初始化 标题


        String name=getIntent().getStringExtra("somebody");
        title_chat.setText(name);
        //title_chat.setText("qqqqqq");
        initSomeMsg();

        recyclerView_msg=(RecyclerView)findViewById(R.id.recyclerView_chat_msg);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView_msg.setLayoutManager(layoutManager);
        chatAdapter=new ChatMsgAdapter(msgList);
        recyclerView_msg.setAdapter(chatAdapter);

        getMSGs();
    }

    void sendMSG(){

    }

    void initSomeMsg(){
        ChatTextMsg c1=new ChatTextMsg();
        c1.setBody("Hi ");
        c1.setWhoSend(1);
        msgList.add(c1);

        ChatTextMsg c2=new ChatTextMsg();
        c2.setBody(" :)");
        c2.setWhoSend(2);

        msgList.add(c2);
    }

    void getMSGs(){
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    subs= RxBus.getInstance().toObserverable(ChatTextMsg.class)
//                            //在主线程进行观察，可做UI更新操作
//                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<ChatTextMsg>() {
                        @Override
                        public void call(ChatTextMsg chatTextMsg) {
                            //更新数据？
                            msgList.add(chatTextMsg);
                            chatAdapter.notifyDataSetChanged();
                        }
                    });

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib__back_chat_toolb:      //返回
                finish();
                break;

            case R.id.btn_send_chat:

                break;

            case R.id.ib_record_chat:

                break;

            case R.id.ib_pic_chat:

                break;

            case R.id.ib_location_chat:

                break;

            case R.id.ib_emj_chat:

                break;


        }
    }

    void initList(){
//        recyclerView_msg=(RecyclerView)findViewById(R.id.recyclerView_chat_msg);

        //ChatTextMsg chatTextMsgME=new ChatTextMsg();
    }
    void initViews(){
        ib_back=(ImageButton)findViewById(R.id.ib__back_chat_toolb);
        title_chat=(TextView)findViewById(R.id.title_chat_toolb);
        et_msg=(EditText)findViewById(R.id.et_msg_chat);
        btnSendMsg=(Button)findViewById(R.id.btn_send_chat);
        ib_record=(ImageButton)findViewById(R.id.ib_record_chat);
        ib_pic=(ImageButton)findViewById(R.id.ib_pic_chat);
        ib_location=(ImageButton)findViewById(R.id.ib_location_chat);
        ib_emj=(ImageButton)findViewById(R.id.ib_emj_chat);
        /************************************/
        ib_back.setOnClickListener(this);
        btnSendMsg.setOnClickListener(this);
        ib_emj.setOnClickListener(this);
        ib_location.setOnClickListener(this);
        ib_pic.setOnClickListener(this);
        ib_record.setOnClickListener(this);
    }
}
