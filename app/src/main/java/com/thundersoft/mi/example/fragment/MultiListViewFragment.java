package com.thundersoft.mi.example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.thundersoft.mi.example.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MultiListViewFragment extends Fragment {

    public static final String TAG="MultiListViewFragment";
    private ListView fragment_multi_lv;
    private ModeCallback modeCallback;
    private List<String> list;
    private ArrayAdapter mAdapter;

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
        list = new ArrayList<String>();
        for (int i=0; i<60; i++){
            list.add("item"+i);
        }
        //MyMultiListViewAdapter mulAdapter = new MyMultiListViewAdapter(this.getActivity() , list);
        mAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,list);
        fragment_multi_lv.setAdapter(mAdapter);
        modeCallback = new ModeCallback();
        fragment_multi_lv.setMultiChoiceModeListener(modeCallback);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.normal_mode_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private class ModeCallback implements ListView.MultiChoiceModeListener {


        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            //这是多选模式下特有的方法，监听列表项的选中状态变化，比如可以在里面进行CheckBox的UI更新，也可以是标题栏中选择的项的数量更新。getCheckedItemCount()
            Log.i(TAG,"onItemCheckedStateChanged");
            mode.setTitle(getString(R.string.selected_num, fragment_multi_lv.getCheckedItemCount()));
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            //可以进行多选模式下的菜单栏布局更新，但是只在多选模式创建时调用一次。
            Log.i(TAG,"onCreateActionMode");
            MenuInflater menuInflater = mode.getMenuInflater();
            menuInflater.inflate(R.menu.multi_choice_mode_menu,menu);
            mAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_multiple_choice,list);
            fragment_multi_lv.setAdapter(mAdapter);
            mode.setTitle(getString(R.string.selected_num,fragment_multi_lv.getCheckedItemCount()));
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            //在创建完成后，每当需要进行菜单栏布局的更新，可以在这个方法中进行。
            Log.i(TAG,"onPrepareActionMode");
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            //监听多选模式下的菜单栏项的点击事件。
            Log.i(TAG,"onActionItemClicked");
            if (item.getItemId() == R.id.menu_item_delete) {
                SparseBooleanArray array = fragment_multi_lv.getCheckedItemPositions();
                Log.i(TAG,"onActionItemClicked : array.size() ="+ array.size());
                List<String> toDelItems = new ArrayList<>();
                for (int i = 0; i < array.size(); ++i) {
                    Log.i(TAG,"onActionItemClicked : key ="+ array.keyAt(i) + "; value = "+ array.valueAt(i));
                    toDelItems.add(list.get(array.keyAt(i)));
                }
                for (String s : toDelItems) list.remove(s);
                mAdapter.notifyDataSetChanged();
                mode.finish();
            }

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            //在多选模式退出时调用，可以在里面完成还原正常模式下的菜单栏布局。
            Log.i(TAG,"onDestroyActionMode");
            fragment_multi_lv.clearChoices();
            mAdapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1, list);
            fragment_multi_lv.setAdapter(mAdapter);

        }
    }
    public class MyMultiListViewAdapter extends BaseAdapter{
        private Context mContext;
        private List<String> mData;
        public MyMultiListViewAdapter(Context context, List<String> data){
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
