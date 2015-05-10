package com.clay.clay.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clay.clay.R;
import com.clay.clay.model.DoorLog;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by manuMohan on 15/05/10.
 */
public class LogListAdapter extends RecyclerView.Adapter<LogListAdapter.ViewHolder> {
    ArrayList<DoorLog> mDoorLogs;

    public LogListAdapter(ArrayList<DoorLog> doorLogs) {
        mDoorLogs = doorLogs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_log, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DoorLog doorLog = mDoorLogs.get(position);
        holder.userNameTextView.setText(doorLog.getUser().getName());
        holder.doorNameTextView.setText(doorLog.getDoor().getName());
        String status = doorLog.getStatus() == DoorLog.Status.OPENED? "Door Opened" : "Access Denied";
        holder.statusTextView.setText(status);
        DateTime dateTime = new DateTime(doorLog.getTimestamp());
        holder.timeStampTextView.setText(dateTime.toString());

    }

    @Override
    public int getItemCount() {
        return mDoorLogs == null ? 0 : mDoorLogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView;
        TextView doorNameTextView;
        TextView timeStampTextView;
        TextView statusTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            userNameTextView = (TextView) itemView.findViewById(R.id.user_name);
            doorNameTextView = (TextView) itemView.findViewById(R.id.door_name);
            timeStampTextView = (TextView) itemView.findViewById(R.id.time_stamp);
            statusTextView = (TextView) itemView.findViewById(R.id.status);
        }
    }
}
