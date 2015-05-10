package com.clay.clay.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.clay.clay.R;
import com.clay.clay.adapter.DoorsAdapter;
import com.clay.clay.db.DatabaseManager;
import com.clay.clay.model.Door;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    private OnFragmentInteractionListener mListener;


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
        DoorsAdapter doorsAdapter = new DoorsAdapter((ArrayList<Door>) doors);
        doorsGridRecyclerView.setAdapter(doorsAdapter);
        doorsGridRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
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


    public interface OnFragmentInteractionListener {
    }

}
