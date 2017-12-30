package com.example.zed.androidqq.Activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zed.androidqq.Bean.ChatTextMsg;
import com.example.zed.androidqq.R;

import java.util.List;

/**
 * Created by zed on 2017/12/26.
 */

public class ChatMsgAdapter extends RecyclerView.Adapter<ChatMsgAdapter.ViewHolder> {

    List<ChatTextMsg>msgList;
    public ChatMsgAdapter(List<ChatTextMsg>textMsgList){
        this.msgList=textMsgList;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftLinearLayout,rightLinearLayout;    //左右消息布局
        ImageView leftImage,rightImage;                     //左右头像
        TextView leftMsg,rightMsg;                          //
        ViewHolder(View view){
            super(view);
            leftLinearLayout=view.findViewById(R.id.left_layout_chat);
            rightLinearLayout=view.findViewById(R.id.right_layout_chat);
            leftImage=view.findViewById(R.id.iv_left_head_chat);
            rightImage=view.findViewById(R.id.iv_right_head_pic_chat);
            leftMsg=view.findViewById(R.id.tv_left_msg_chat);
            rightMsg=view.findViewById(R.id.tv_right_msg_chat);
        }
    }

    @Override
    public ChatMsgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_recyclerview_item_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatTextMsg chatTextMsg=msgList.get(position);
        if (chatTextMsg.getWhoSend()==1){   //自己发送
            holder.rightLinearLayout.setVisibility(View.VISIBLE);
            holder.leftLinearLayout.setVisibility(View.INVISIBLE);
            holder.rightMsg.setText(chatTextMsg.getBody());
        }else {
            holder.leftLinearLayout.setVisibility(View.VISIBLE);
            holder.rightLinearLayout.setVisibility(View.INVISIBLE);
            holder.leftMsg.setText(chatTextMsg.getBody());
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
