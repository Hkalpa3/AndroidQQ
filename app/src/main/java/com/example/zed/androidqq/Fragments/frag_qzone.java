package com.example.zed.androidqq.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.zed.androidqq.R;

/**
 * Created by zed on 2017/12/14.
 */

public class frag_qzone extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.frag_group,container,false);
        ListView lvGroup=(ListView)view.findViewById(R.id.lv_frag_group);
        //lvGroup.setOnItemClickListener();

        return view;
    }


}
