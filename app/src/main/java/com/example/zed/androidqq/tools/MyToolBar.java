package com.example.zed.androidqq.tools;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zed.androidqq.R;

/**
 * Created by zed on 2017/12/18.
 */

public class MyToolBar extends Toolbar {
    RoundImageView roundImage;
    ImageButton imgPlus;
    TextView tvTitle;


    public MyToolBar(Context context) {
        super(context);
    }

    public MyToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        roundImage = findViewById(R.id.iv_headPic_mytoolbar);
        imgPlus = findViewById(R.id.ib_plus_mytoolbar);
        tvTitle = findViewById(R.id.tv_title_mytoolbar);
    }

    //设置中间title的内容
    public void setMainTitle(String text) {
        this.setTitle(" ");
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(text);
    }

    //设置title左边点击事件
    public void setLeftTitleClickListener(OnClickListener onClickListener) {
        roundImage.setOnClickListener(onClickListener);
    }
//    //设置title左边图标
//    public void setLeftTitleDrawable(int res) {
//        Drawable dwLeft = ContextCompat.getDrawable(getContext(), res);
//        dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());
//        mTxtLeftTitle.setCompoundDrawables(dwLeft, null, null, null);
//    }


}
