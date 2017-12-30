package com.example.zed.androidqq.tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.zed.androidqq.Activity.adapter.MyExpandableLVAdapter;
import com.example.zed.androidqq.R;

/**
 * Created by zed on 2017/12/24.
 */

public class Group_window extends PopupWindow implements View.OnClickListener,PopupWindow.OnDismissListener {
    private Context context;
    private TextView tvTitle;
    private EditText etName;
    private Button btnOk,btnCancel;
    String window_Title;    //弹窗 标题
    int window_Type;        // 1:添加窗体  2：改名  3:查找好友
    private OnDialogClickListener dialogClickListener;

    MyExpandableLVAdapter adapter;      //刷新数据adapter

    public Group_window(Context c, String title, int window_type, MyExpandableLVAdapter adapter){
        this.context=c;
        this.window_Title =title;
        this.window_Type=window_type;
        this.adapter=adapter;
        initalize();


    }

    private void initalize() {
        LayoutInflater inflater = LayoutInflater.from(context);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok_pop:

                whichType(window_Type);
                //***************************//
                dismiss();
                if(dialogClickListener != null){
                    dialogClickListener.onOKClick();
                }
                //***************************//
                break;
            case R.id.btn_cancel_pop:

                //***************************//
                dismiss();
                if(dialogClickListener != null){
                    dialogClickListener.onCancelClick();
                }
                //***************************//
                break;
        }
    }

    void whichType(int window_Type){
        //                if(window_Type==1){     //添加分组窗体
//                    String newGroupName=etName.getText().toString().trim();
//                    if(XmppConnection.getInstance().addGroup(newGroupName)){
//                        Log.i("msg", "添加分组成功"+newGroupName);
//                        //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
//                        //添加成功 刷新 数据？
//                        adapter.notifyAll();
//                    }else{
//                        Log.i("msg", "添加失败: ");
//                        Toast.makeText(context, "添加分组失败", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else { //window_Type==2  改名
//                       // if(XmppConnection.getInstance().renameGroup())
//                }
//
//

        //***************************//
        switch (window_Type){
            case 1:

                break;
            case 2:

                break;
            case 3: //搜索朋友

                break;
        }
    }

    private void initWindow() {
        this.setBackgroundDrawable(new ColorDrawable(0xffffffff));
        DisplayMetrics d = context.getResources().getDisplayMetrics();
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
        dialogClickListener = clickListener;
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
        Activity curAvtivity=(Activity)context;

        WindowManager.LayoutParams lp = curAvtivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        curAvtivity.getWindow().setAttributes(lp);
    }

    public interface OnDialogClickListener{
        void onOKClick();
        void onCancelClick();
    }

}
