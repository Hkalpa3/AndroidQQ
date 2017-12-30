package com.example.zed.androidqq.Activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zed.androidqq.R;
import com.example.zed.androidqq.Bean.User;

import java.util.List;

/**
 * Created by zed on 2017/12/19.
 */

public class MyExpandableLVAdapter extends BaseExpandableListAdapter {
    private List<String> groupList;//外层的数据源
    private List<List<User>> childList;//里层的数据源
    private Context context;

    public MyExpandableLVAdapter(Context context, List<String> groupList,List<List<User>> childList ){
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
    }


    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    /**
     * 这个返回的一定要是对应外层的item里面的List集合的size
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {

        //空项 点击报错
//        if(childList.get(groupPosition)==null)
//            return 0;
//        else
            return  childList.get(groupPosition).size();



//        if(childList.get(groupPosition)!=null&&childList.get(groupPosition).size()>0)
//            return childList.get(groupPosition).size();
//        else
//            return 0;


//            return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

//    public boolean reNameGroupName(String oldName,String newGroupName,int groupPosition){
//
//        if (groupList.contains(oldName))
//            //String oldName=groupList.get(groupPosition);
//            groupList.
//        groupList.
//        oldName.replace(oldName,newGroupName);
//    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        if (childList != null && childPosition < childList.size()) {
            return childList.get(groupPosition).get(childPosition);
        }
        return null;

        // return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //固定ID
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


//        if(convertView==null)
            convertView = View.inflate(context, R.layout.expand_listview_group_layout, null);
            //设置分组图片
            ImageView imgIndicator=convertView.findViewById(R.id.iv_group_elv);
            if(isExpanded)
                imgIndicator.setImageResource(R.drawable.contact_group_open);
            else
                imgIndicator.setImageResource(R.drawable.contact_group_close);

            String groupName=groupList.get(groupPosition);

            //分组名字
            TextView textView = convertView.findViewById(R.id.tv_group_name_elv);
            textView.setText(groupName);

            //设置 tag  长按事件中 判断
            //如果是父项，就设置R.layout.parent_item为第几个父项，设置R.layout.child_item为-1。
            //如果是子项，就设置R.layout.parent_item属于第几个父项，设置R.layout.child_item为该父项的第几个子项，
            // 这样就可以区分被长按的是父项还是子项了。
            convertView.setTag(R.layout.expand_listview_group_layout,groupPosition);
            convertView.setTag(R.layout.expand_lv_group_detail, -1);
            convertView.setTag(groupName);


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        //view = View.inflate(context, R.layout.expand_lv_group_detail, null);

        view=LayoutInflater.from(context).inflate(R.layout.expand_lv_group_detail,null);
        view.setFocusable(false);
        //用户名TextView
        String childName=childList.get(groupPosition).get(childPosition).getName();
        TextView textView = view.findViewById(R.id.tv_username_elv);
        textView.setText(childName);

        view.setTag(R.layout.expand_lv_group_detail,childPosition);
        view.setTag(R.layout.expand_listview_group_layout,groupPosition);
        view.setTag(childName);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
