package com.clay.clay.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.activeandroid.query.Select;
import com.clay.clay.R;
import com.clay.clay.adapter.DoorsAdapter;
import com.clay.clay.db.DatabaseManager;
import com.clay.clay.model.Door;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements OpenDoorDialogFragment.OnFragmentInteractionListener
        , EventsLogFragment.OnFragmentInteractionListener {


    private OnFragmentInteractionListener mListener;
    private DoorsAdapter mDoorsAdapter;


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseManager.createDummyDoors();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView doorsGridRecyclerView = (RecyclerView) view.findViewById(R.id.doors_grid);
        List<Door> doors = new Select().from(Door.class).execute();
        mDoorsAdapter = new DoorsAdapter((ArrayList<Door>) doors, new DoorsAdapter.DoorsAdapterListener() {
            @Override
            public void onDoorSelected(Door door) {
                showDoorOpenDialog(door);
            }
        });
        doorsGridRecyclerView.setAdapter(mDoorsAdapter);
        doorsGridRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        Button logsButton = (Button) view.findViewById(R.id.log);
        logsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventsLogFragment eventsLogFragment = EventsLogFragment.newInstance();
                eventsLogFragment.show(getChildFragmentManager(), "show-logs");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
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

    private void showDoorOpenDialog(Door door) {
        OpenDoorDialogFragment openDoorDialogFragment = OpenDoorDialogFragment.newInstance(door);
        openDoorDialogFragment.show(getChildFragmentManager(), "open-door");
    }

    public void updateViews() {
        List<Door> doors = new Select().from(Door.class).execute();
        mDoorsAdapter.setDoors((ArrayList<Door>) doors);
        mDoorsAdapter.notifyDataSetChanged();
    }


    public interface OnFragmentInteractionListener {
    }

}
