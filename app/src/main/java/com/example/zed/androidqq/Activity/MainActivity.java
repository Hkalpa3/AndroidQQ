package com.example.zed.androidqq.Activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zed.androidqq.Fragments.adapter.MyFragmentpagerAdapter;
import com.example.zed.androidqq.Fragments.frag_contact;
import com.example.zed.androidqq.Fragments.frag_msg;
import com.example.zed.androidqq.Fragments.frag_qzone;
import com.example.zed.androidqq.R;
import com.example.zed.androidqq.XmppCls.LogMe;
import com.example.zed.androidqq.XmppCls.XmppConnection;
import com.example.zed.androidqq.XmppCls.XmppIncomingChatMsgListener;
import com.example.zed.androidqq.tools.RoundImageView;

import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.EntityBareJid;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener,
        IncomingChatMessageListener,
        ChatManagerListener{

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    TabLayout tabLayout;
    ViewPager viewPager;
    String curUserName;

//    Subscription subscription;


    //不相关
    private long lastTime;

    TextView myTitle;   //自定义标题
    RoundImageView imghead; //圆形头像
    ExpandableListView expListView;
    //必须实例化  不能直接添加
    List<Fragment>fragments_list=new ArrayList<>();   //碎片列表
    //FragmentManager fragmentManager;  getSupportManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setStatusBarUpperAPI21();

        findViews();
        initToolbar();
        initTabLayout();
        initViewPager();

        //默认选中第二个
        viewPager.setCurrentItem(1);
        tabLayout.getTabAt(1).select();

        initChatListener();

//        subscription= (Subscription) RxBus.getInstance().toObserverable(StudentEvent.class).subscribe(new Action1<StudentEvent>() {
//            @Override
//            public void call(StudentEvent studentEvent) {
//                //Toast.makeText(MainActivity.this, studentEvent.getId()+studentEvent.getName(), Toast.LENGTH_SHORT).show();
//                Log.i("msg", "call: "+studentEvent.getId()+studentEvent.getName());
//            }
//        });

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//
//        toggle.syncState();

        //

    }

//    void zhuoseMode(){
//        Window window = this.getWindow();
////取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
////需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
////设置状态栏颜色
//        window.setStatusBarColor(statusColor);
//
//        ViewGroup mContentView = (ViewGroup) this.findViewById(Window.ID_ANDROID_CONTENT);
//        View mChildView = mContentView.getChildAt(0);
//        if (mChildView != null) {
//            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
//            ViewCompat.setFitsSystemWindows(mChildView, true);
//        }
//    }

//    void getData(){
//        List<String> groupName_list;//分组名
//        List<List<User>> groupFriends_list;//分组下的好友
//        groupName_list= XmppConnection.getInstance().getAllGroupName();
//        for (String g:groupName_list) {
//            Log.i("me", "getData: 组名"+g);
//            List<User> usersList;     //一个分组下的所有 好友
//            usersList=(XmppConnection.getInstance().getUsersByGroupName(g));
//            for (User u:usersList) {
//                Log.i("me", "getData: 好友"+u.getName()+" jid:"+u.getId()+u.getGroup()+u.getType());
//            }
//
//        }
//    }

    void initChatListener(){
        ChatManager cm= ChatManager.getInstanceFor(XmppConnection.getInstance().getConnection());

        cm.addIncomingListener(new XmppIncomingChatMsgListener());
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_headPic_toolbar_main:  //点击头像  打开drawerLayout
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
    }

    void findViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        viewPager=(ViewPager)findViewById(R.id.vp_main);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout_main);


        imghead=(RoundImageView)findViewById(R.id.iv_headPic_toolbar_main);
        imghead.setOnClickListener(this);


        //expListView=fragments_list.get(1).getView().findViewById(R.id.elv_contact);


        myTitle=(TextView)findViewById(R.id.tv_myTitle_toolbar_main);

    }




    void initToolbar(){
        setSupportActionBar(toolbar);
        //取消 title
        if (myTitle != null) {   //自定义 title  id
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        //启动时 设置 title
       myTitle.setText( tabLayout.getTabAt( tabLayout.getSelectedTabPosition()).getText());
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setTitle("");

        //代码设置navigation 不管用
        //toolbar.setNavigationIcon(R.drawable.ic_menu_share);

        //设置左侧NavigationIcon点击事件
//        mNormalToolbar.setNavigationOnClickListener(new View.OnClickListener() {
    }

    void initTabLayout(){

        //tabLayout.selected
        //TabLayout ItemSelected监听事件
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //设置标题
                myTitle.setText(tab.getText());
                //设置图标
                switch (tab.getPosition()){
                    case 0:tab.setIcon(R.drawable.skin_tab_msg_selected);break;
                    case 1:tab.setIcon(R.drawable.skin_tab_contact_selected);break;
                    case 2:tab.setIcon(R.drawable.skin_tab_qzone_selected);break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //设置图标
                switch (tab.getPosition()){
                    case 0:tab.setIcon(R.drawable.skin_tab_msg_normal);break;
                    case 1:tab.setIcon(R.drawable.skin_tab_contact_normal);break;
                    case 2:tab.setIcon(R.drawable.skin_tab_qzone_normal);break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //与ViewPager关联
        tabLayout.setupWithViewPager(viewPager);
    }

    void initViewPager(){

        fragments_list.add(new frag_msg());
        fragments_list.add(new frag_contact());
        fragments_list.add(new frag_qzone());
        MyFragmentpagerAdapter adapter=new MyFragmentpagerAdapter(getSupportFragmentManager(),fragments_list);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            long currentTime= System.currentTimeMillis();
            if(currentTime-lastTime>=2*1000){   //间隔 大于2秒
                Toast.makeText(this, "再按一次退出程序。", Toast.LENGTH_SHORT).show();
                lastTime=System.currentTimeMillis();
            }else {
                super.onBackPressed();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        switch (item.getItemId()){
            case R.id.addFriend:
                Log.i("msg", "onOptionsItemSelected: click:add");

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
//        drawer.closeDrawer(GravityCompat.START);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setStatusBarUpperAPI21() {
        Window window = getWindow();
        //设置透明状态栏,这样才能让 ContentView 向上
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
    }


    @Override
    protected void onDestroy() {
//        if (!subscription.isUnsubscribed()){
//            subscription.unsubscribe();
//        }
        //断开连接 移除监听
        XmppConnection.getInstance().disConnect();

        super.onDestroy();
    }

    @Override
    public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
        LogMe.logI("Main Incoming::"+message.getBody());
    }

    @Override
    public void chatCreated(org.jivesoftware.smack.chat.Chat chat, boolean createdLocally) {

    }
}
