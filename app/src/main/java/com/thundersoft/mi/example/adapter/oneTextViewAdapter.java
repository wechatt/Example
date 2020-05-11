package com.thundersoft.mi.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.modle.Operator;

import java.util.List;

/**
 * @author TuYong
 * @create 19-11-6
 * @Describe
 */
public class oneTextViewAdapter extends BaseAdapter {

    private Context mContext ;
    private List<Operator> mData;
    public oneTextViewAdapter(List<Operator> data , Context context){
        mContext = context;
        mData = data;

    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview_one_textview,null);
            holder.textview = convertView.findViewById(R.id.item_listview_one_textview);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textview.setText(mData.get(position).getName());
        return convertView;
    }
    class ViewHolder{
        TextView textview;

    }
}
