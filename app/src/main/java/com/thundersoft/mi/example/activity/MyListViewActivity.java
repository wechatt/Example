package com.thundersoft.mi.example.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.modle.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MyListViewActivity extends Activity {

    private ListView listview;
    private ArrayList<Fruit> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listview = findViewById(R.id.listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = dataList.get(position).getName();
                Toast.makeText(MyListViewActivity.this,name,Toast.LENGTH_SHORT).show();
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String name = dataList.get(position).getName();
                Toast.makeText(MyListViewActivity.this,"正在长按"+name,Toast.LENGTH_SHORT).show();
                //返回true则不会执行setOnItemClickListener中的onItemClick,返回false,则会执行
                return true;
            }
        });
        initData();
    }

    private void initData() {
        dataList = new ArrayList<Fruit>();
        dataList.add(new Fruit("苹果",R.drawable.apple));
        dataList.add(new Fruit("樱桃",R.drawable.cherry));
        dataList.add(new Fruit("猕猴桃",R.drawable.kiwi));
        dataList.add(new Fruit("柠檬",R.drawable.lemon));
        dataList.add(new Fruit("西瓜",R.drawable.watermelon));
        dataList.add(new Fruit("芒果",R.drawable.mango));
        MyAdapter myAdapter = new MyAdapter(this, dataList);
        listview.setAdapter(myAdapter);
    }


    class MyAdapter extends BaseAdapter{
        private Context mContext;
        private List<Fruit> datalist;
        private ImageView fruitImage;
        private TextView fruitName;

        public MyAdapter(Context context, List<Fruit> dataList) {
            this.mContext = context;
            this.datalist = dataList;
        }

        @Override
        public int getCount() {
            return datalist.size();
        }

        @Override
        public Object getItem(int position) {
            return datalist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder ;
            if (convertView == null){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview, null);
                holder = new ViewHolder();
                holder.picture = convertView.findViewById(R.id.fruitImage);
                holder.name = convertView.findViewById(R.id.fruitName);
                convertView.setTag(holder);

            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            holder.picture.setImageResource(datalist.get(position).getImageId());
            holder.name.setText(datalist.get(position).getName());
            return convertView;
        }
    }

    class ViewHolder {
        public ImageView picture;
        public TextView name;
    }

}
