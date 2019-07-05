package com.thundersoft.mi.example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.fragment.dummy.DummyContent;
import com.thundersoft.mi.example.fragment.dummy.DummyContent.DummyItem;


import static com.thundersoft.mi.example.fragment.MultiListViewFragment.TAG;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
/**
 * @author TuYong
 * @create 19-7-4 *
 * @Email tuyong0125@thundersoft.com
 * @Describe
 * 启动时的生命周期：
 * 07-04 17:39:05.504  2021  3598 I ActivityTaskManager: START u0 {cmp=com.thundersoft.mi.example/.activity.FragmentActivity} from uid 10130
 * 07-04 17:39:05.527 12126 12126 D FragmentActivity: onCreate
 * 07-04 17:39:05.537 12126 12126 D RightItemFragment: onAttach
 * 07-04 17:39:05.537 12126 12126 D RightItemFragment: onCreate
 * 07-04 17:39:05.538 12126 12126 D RightItemFragment: onCreateView
 * 07-04 17:39:05.539 12126 12126 D RightItemFragment: onActivityCreated
 * 07-04 17:39:05.540 12126 12126 D FragmentActivity: onStart
 * 07-04 17:39:05.540 12126 12126 D RightItemFragment: onStart
 * 07-04 17:39:05.540 12126 12126 D FragmentActivity: onResume
 * 07-04 17:39:05.540 12126 12126 D RightItemFragment: onResume
 *
 *　addToBackStack之后被replace时
 * 07-04 17:39:52.628 12126 12126 D RightItemFragment: onPause
 * 07-04 17:39:52.628 12126 12126 D RightItemFragment: onStop
 * 07-04 17:39:52.628 12126 12126 D RightItemFragment: onDestroyView
 *
 * 未执行addToBackStack之后被replace时
 * 07-04 18:33:38.706 13991 13991 D RightItemFragment: onPause
 * 07-04 18:33:38.706 13991 13991 D RightItemFragment: onStop
 * 07-04 18:33:38.710 13991 13991 D RightItemFragment: onDestroyView
 * 07-04 18:33:38.710 13991 13991 D RightItemFragment: onDestroy
 * 07-04 18:33:38.710 13991 13991 D RightItemFragment: onDetach
 */
public class RightItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private OnListFragmentInteractionListener mListener;
    private static final String TAG = "RightItemFragment";
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RightItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RightItemFragment newInstance(int columnCount) {
        RightItemFragment fragment = new RightItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 当碎片和活动建立关联的时候调用。
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG,"onAttach");
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
    }

    /**
     * 为碎片创建视图（加载布局）时调用。
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_item_list_right, container, false);

        return view;
    }

    /**
     * 确保与碎片相关联的活动一定已经创建完毕的时候调用。
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    /**
     * 当与碎片关联的视图被移除的时候调用。
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    /**
     * 当碎片和活动解除关联的时候调用。
     */
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach");
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
