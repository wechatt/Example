package com.thundersoft.mi.example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.thundersoft.mi.example.R;

import java.util.ArrayList;


public class MultiListViewFragment extends Fragment {

    private ListView fragment_multi_lv;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//在Fragment中设置Menu必须设置为true
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multi_listview_layout,null);
        fragment_multi_lv = view.findViewById(R.id.fragment_multi_lv);
        ArrayList<String> list = new ArrayList<String>();
        for (int i=0; i<60; i++){
            list.add("item"+i);
        }
        MyMultiListViewAdapter mulAdapter = new MyMultiListViewAdapter(this.getActivity() , list);
        fragment_multi_lv.setAdapter(mulAdapter);
        return view;
    }

    public class MyMultiListViewAdapter extends BaseAdapter{
        private Context mContext;
        private ArrayList<String> mData;
        public MyMultiListViewAdapter(Context context, ArrayList<String> data){
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
            ViewHolder vh;
            if(convertView==null){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_multi_listview,null);
                vh = new ViewHolder();
                vh.tv = convertView.findViewById(R.id.item_multi_tv);
                convertView.setTag(vh);
            }else{
                vh = (ViewHolder)convertView.getTag();
            }
            vh.tv.setText(mData.get(position));
            return convertView;
        }
    }

    class ViewHolder{
        public TextView tv;
    }
}
