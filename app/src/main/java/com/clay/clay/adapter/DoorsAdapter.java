package com.clay.clay.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clay.clay.R;
import com.clay.clay.model.Door;

import java.util.ArrayList;

/**
 * Created by manuMohan on 15/05/10.
 */
public class DoorsAdapter extends RecyclerView.Adapter<DoorsAdapter.DoorViewHolder> {
    private ArrayList<Door> mDoors;

    public DoorsAdapter(ArrayList<Door> doors) {
        mDoors = doors;
    }

    @Override
    public DoorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View doorView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_door, parent,false);
        return new DoorViewHolder(doorView);
    }

    @Override
    public void onBindViewHolder(DoorViewHolder holder, int position) {
        holder.doorNameTextView.setText(mDoors.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mDoors == null ? 0 : mDoors.size();
    }

    public static class DoorViewHolder extends RecyclerView.ViewHolder {
        TextView doorNameTextView;
        public DoorViewHolder(View itemView) {
            super(itemView);
            doorNameTextView = (TextView) itemView.findViewById(R.id.door_name);
        }
    }
}
