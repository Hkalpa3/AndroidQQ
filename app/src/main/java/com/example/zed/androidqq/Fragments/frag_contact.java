package com.example.zed.androidqq.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zed.androidqq.Activity.ChatActivity;
import com.example.zed.androidqq.Activity.adapter.MyExpandableLVAdapter;
import com.example.zed.androidqq.Bean.User;
import com.example.zed.androidqq.R;
import com.example.zed.androidqq.XmppCls.XmppConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zed on 2017/12/14.
 */

public class frag_contact extends Fragment  {

    ExpandableListView exListView;
    List<String> groupName_list=new ArrayList<>();//分组名
    List<List<User>> groupFriends_list=new ArrayList<>();//分组下的好友
    MyExpandableLVAdapter adapter;


    //获取 expandablelist view数据源
    void getData(){
        groupName_list= XmppConnection.getInstance().getAllGroupName();
        for (String g:groupName_list) {
            List<User> usersList;     //一个分组下的所有 好友
            usersList=(XmppConnection.getInstance().getUsersByGroupName(g));
            groupFriends_list.add(usersList);


        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.frag_contact,container,false);
        exListView=view.findViewById(R.id.elv_contact);
        //获取数据
        getData();
        //getContext   getActivity 获取context
        adapter=new MyExpandableLVAdapter(getActivity(),groupName_list,groupFriends_list);
        exListView.setAdapter(adapter);
        //子项监听事件
        exListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //Toast.makeText(getActivity(), "dianji", Toast.LENGTH_SHORT).show();
                //Intent itn=new Intent()

                Log.i("msg", "onChildClick: groupPositon&&childPosition"+groupPosition+"&&"+childPosition);
                User selectedUser=new User();
                selectedUser =groupFriends_list.get(groupPosition).get(childPosition);
                Log.i("msg", "onChildClick: +click:"+selectedUser.getName()+":"+selectedUser.getGroup());

                //点击好友  跳转到聊天界面
                Intent i=new Intent(getActivity().getApplicationContext(), ChatActivity.class);
                // 传递点击好友名称
                Bundle b=new Bundle();
                b.putString("somebody",selectedUser.getName());
                i.putExtras(b);
                //使用getActivity传参 到另一个 activity
                getActivity().startActivity(i);

                /*******************************/
                //测试发送消息
                // Assume we've created an XMPPConnection name "connection"._

                // Presence presence=new Presence(Presence.Type.available);
//                ChatManager chatManager = ChatManager.getInstanceFor(XmppConnection.getInstance().getConnection());
//                chatManager.addIncomingListener(new IncomingChatMessageListener() {
//                    @Override
//                    public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
//                        Log.i("msg", "newIncomingMessage: from:"+from+"  msgBody:"+message.getBody());
//                    }
//                });

//                try {
//                    EntityBareJid jid= JidCreate.entityBareFrom("lm@kalpa3pc");
//                    Chat chat=chatManager.chatWith(jid);
////                    String mMsg="hiiii";
////                    chat.send(mMsg);
//                    //Log.i("msg", "onChildClick: 发送消息："+mMsg);
//                    //Message ms=new Message(jid, Message.Type.normal);
//
//                    Message ms=new Message(jid, Message.Type.chat);
//                    //Message Stanza [to=lm@kalpa3pc,id=wIxS8-9,type=chat,]
//
//                    chat.send(new Message(jid));
//                } catch (XmppStringprepException e) {
//                    e.printStackTrace();
//                } catch (SmackException.NotConnectedException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                /*******************************/
                return true;
            }
        });

        //添加长按事件
        exListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {

               // Log.i("msg", "onItemLongClick:  position="+position+" is :"+groupName_list.get(position));
               // Log.i("msg", "onItemLongClick:  position="+position+" is :"+groupName_list.get((int)view.getTag()));
               // String selectedGroupName_Long=view.getTag(R.layout.expand_listview_group_layout).toString();
                final String selectedGroupName_Long=view.getTag().toString();
                Log.i("msg", "onItemLongClick: "+selectedGroupName_Long);
             //   clickedGroupName=groupName_list.get()

                if((int)view.getTag(R.layout.expand_lv_group_detail)==-1){ //=-1表示 点击了 父项

                    //长按弹出 分组管理菜单
                    PopupMenu popupMenu=new PopupMenu(getContext(),view);
                    popupMenu.getMenuInflater().inflate(R.menu.contact_group_mng_menu,popupMenu.getMenu());
                    popupMenu.show();

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            //Toast.makeText(getActivity(), "click:"+item.getTitle(), Toast.LENGTH_SHORT).show();
                            switch (item.getItemId()){
                                case R.id.contact_group_add:    //添加分组
                                    Log.i("msg", "onMenuItemClick: add:");
                                    //显示弹窗
                                    GroupWindow window1=new GroupWindow("添加一个分组",1);
                                    window1.showAtBottom(view);



                                    break;
                                case R.id.contact_group_rename:
                                    Log.i("msg", "onMenuItemClick: 2");
                                    GroupWindow window2=new GroupWindow("更改组名",2,selectedGroupName_Long);
                                    window2.showAtBottom(view);
                                    window2.etName.setText(selectedGroupName_Long);


                                    break;
                                case R.id.contact_group_delete:
                                    Log.i("msg", "onMenuItemClick: 3");
                                    break;
                            }

                            return true;
                        }
                    });
                }
                return true;
            }
        });

        return view;
    }



    class GroupWindow extends PopupWindow implements View.OnClickListener,PopupWindow.OnDismissListener{

        private TextView tvTitle;
        private EditText etName;
        private Button btnOk,btnCancel;
        String window_Title;    //弹窗 标题
        int window_Type;        // 1:添加窗体  2：改名
        private OnDialogClickListener onDialogClickListener;
        String oldGroupName;    //更改组名用

//        MyExpandableLVAdapter adapter;      //刷新数据adapter

        public GroupWindow(String title, int window_type){
            this.window_Title =title;
            this.window_Type=window_type;
            initalize();
        }

        public GroupWindow(String title, int window_type,String oldGroupName){
            this.window_Title =title;
            this.window_Type=window_type;
            this.oldGroupName=oldGroupName;
            initalize();
        }

        private void initalize() {
            //from(getActivity());
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.group_pop_window, null);
            setContentView(view);
            initWindow();

            tvTitle = view.findViewById(R.id.tv_title_name_pop);
            etName = view.findViewById(R.id.et_group_pop);
            btnOk = view.findViewById(R.id.btn_ok_pop);
            btnCancel = view.findViewById(R.id.btn_cancel_pop);

            btnOk.setOnClickListener(this);
            btnCancel.setOnClickListener(this);

            //自定义标题
            tvTitle.setText(window_Title);
        }

        //确定  取消  按钮操作：
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_ok_pop:
                    // 编辑框 文本
                  final   String newGroupName=etName.getText().toString().trim();
                    if(window_Type==1) //添加分组窗体
                    {
                        if(XmppConnection.getInstance().addGroup(newGroupName)){
                            Log.i("msg", "添加分组成功"+newGroupName);
                            //添加到 数据源  刷新 数据
                            groupName_list.add(newGroupName);
                            adapter.notifyDataSetChanged();
                        }else{
                            Log.i("msg", "添加分组失败: ");
                            Toast.makeText(getActivity(), "添加分组失败", Toast.LENGTH_SHORT).show();
                        }

                    }else //window_Type==2  改名
                    {
//                        (Activity) getContext().ru(new Runnable() {
//                            @Override
//                            public void run() {
//                                //改名有问题
//                                if(XmppConnection.getInstance().renameGroup(oldGroupName,newGroupName)){
//                                    exListView.removeAllViews();
//                                    getData();      //重新 获取数据
//                                    exListView.setAdapter(adapter);
//                                    Log.i("msg", "onClick: 改名成功"+newGroupName);
//                                }else {
//                                    Log.i("msg", "onClick: 改名失败");
//                                }
//                            }
//                        });
                        Toast.makeText(getActivity(), "改名还没做完", Toast.LENGTH_SHORT).show();

                    }


                    dismiss();
                    if(onDialogClickListener != null){
                        onDialogClickListener.onOKClick();
                    }
                    break;

                case R.id.btn_cancel_pop:

                    dismiss();
                    if(onDialogClickListener != null){
                        onDialogClickListener.onCancelClick();
                    }
                    break;
            }
        }

        private void initWindow() {
            this.setBackgroundDrawable(new ColorDrawable(0xffffffff));
            DisplayMetrics d = getActivity().getResources().getDisplayMetrics();
            this.setWidth((int) (d.widthPixels * 0.8));
            this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            this.setFocusable(true);
            this.setOutsideTouchable(true);
            this.update();
            //设置关闭监听
            this.setOnDismissListener(this);


        }

        public void showAtBottom(View view){
            setbackgroundAlpha(0.4f);
            showAsDropDown(view, Math.abs((view.getWidth() - getWidth())/2), 20);
        }

        public void setOnDialogClickListener(OnDialogClickListener clickListener){
            onDialogClickListener = clickListener;
        }



        @Override
        public void onDismiss() {
            setbackgroundAlpha(1);
        }

        /**
         * 设置添加屏幕的背景透明度
         * @param bgAlpha
         */

        public void setbackgroundAlpha(float bgAlpha)
        {
         //   Activity curAvtivity=(Activity)context;
            Activity curActivity=(Activity)getContext();
            WindowManager.LayoutParams lp = curActivity.getWindow().getAttributes();
            lp.alpha = bgAlpha; //0.0-1.0
            curActivity.getWindow().setAttributes(lp);
        }



    }
    public interface OnDialogClickListener{
        void onOKClick();
        void onCancelClick();
    }

}
