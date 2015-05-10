package com.clay.clay.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.clay.clay.R;
import com.clay.clay.adapter.LogListAdapter;
import com.clay.clay.model.DoorLog;

import java.util.ArrayList;
import java.util.List;

public class EventsLogFragment extends DialogFragment {


    private OnFragmentInteractionListener mListener;


    public static EventsLogFragment newInstance() {

        return new EventsLogFragment();
    }

    public EventsLogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events_log, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.log_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<DoorLog> doorLogs = new Select().from(DoorLog.class).orderBy("timestamp DESC").execute();
        LogListAdapter logListAdapter = new LogListAdapter((ArrayList<DoorLog>) doorLogs);
        recyclerView.setAdapter(logListAdapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

    }

}
