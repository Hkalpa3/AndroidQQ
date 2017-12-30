package com.example.zed.androidqq.Fragments.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zed on 2017/12/14.
 */

public class MyFragmentpagerAdapter extends FragmentPagerAdapter {

    String[]titles={"消息","联系人","空间"};
    List<Fragment>fragmentList;
    

    public MyFragmentpagerAdapter(FragmentManager manager,List<Fragment> list){
        super(manager);
        fragmentList=list;
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    //重新设置 每个tab标题
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
