package com.thundersoft.mi.example.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thundersoft.mi.example.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LeftFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LeftFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * @author TuYong
 * @create 19-7-4 *
 * @Email tuyong0125@thundersoft.com
 * @Describe
 * 问题：如果让fragment和fragment之间进行通信．
 * 答案：它的基本思路非常简单，首先在一个碎片中可以得到与它相关联的活动，然后再通过这个活动去获取另外一个碎片的实例，这样也就实现了不同碎片之间的通信功能，
 *
 *  　启动之后活动的生命周期：
 *   07-04 17:33:35.037  2021  2327 I ActivityTaskManager: START u0 {cmp=com.thundersoft.mi.example/.activity.FragmentActivity} from uid 10130
 *   07-04 17:33:35.060 12126 12126 D FragmentActivity: onCreate
 *   07-04 17:33:35.075 12126 12126 D LeftFragment: onAttach
 *   07-04 17:33:35.075 12126 12126 D LeftFragment: onCreate
 *   07-04 17:33:35.076 12126 12126 D LeftFragment: onCreateView
 *   07-04 17:33:35.110 12126 12126 D LeftFragment: onActivityCreated
 *   07-04 17:33:35.110 12126 12126 D LeftFragment: onStart
 *   07-04 17:33:35.110 12126 12126 D FragmentActivity: onStart
 *   07-04 17:33:35.110 12126 12126 D FragmentActivity: onResume
 *   07-04 17:33:35.111 12126 12126 D LeftFragment: onResume
 *
 *  退出之后的生命周期：
 *   07-04 17:35:58.588 12126 12126 D LeftFragment: onPause
 *   07-04 17:35:58.588 12126 12126 D FragmentActivity: onPause
 *   07-04 17:35:59.087 12126 12126 D LeftFragment: onStop
 *   07-04 17:35:59.087 12126 12126 D FragmentActivity: onStop
 *   07-04 17:35:59.088 12126 12126 D LeftFragment: onDestroyView
 *   07-04 17:35:59.090 12126 12126 D LeftFragment: onDestroy
 *   07-04 17:35:59.090 12126 12126 D LeftFragment: onDetach
 *   07-04 17:35:59.090 12126 12126 D FragmentActivity: onDestroy
 */
public class LeftFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "LeftFragment";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Button right_bt;

    public LeftFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeftFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeftFragment newInstance(String param1, String param2) {
        LeftFragment fragment = new LeftFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG,"onAttach");
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        /**
         * 通过此方法可以获取Fragment所依附的Activity对象
         */
        FragmentActivity activity = getActivity();
        /**
         * 通过此方法可以获取Fragment所依附的Context对象
         */
        Context context = getContext();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
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

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach");
        mListener = null;
    }

    @Override
    public void onClick(View v) {

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }





    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
