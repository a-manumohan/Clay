package com.clay.clay.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.clay.clay.R;
import com.clay.clay.model.Door;
import com.clay.clay.model.DoorLog;
import com.clay.clay.model.User;
import com.clay.clay.model.UserDoorRelation;
import com.clay.clay.util.PreferenceUtil;


public class OpenDoorDialogFragment extends DialogFragment {
    private static final String ARG_DOOR = "arg_door";

    private OnFragmentInteractionListener mListener;
    private Door mDoor;
    private ProgressBar mProgressBar;
    private View mMainPanel;

    public static OpenDoorDialogFragment newInstance(Door door) {
        OpenDoorDialogFragment fragment = new OpenDoorDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DOOR, door);
        fragment.setArguments(args);
        fragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        return fragment;
    }

    public OpenDoorDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDoor = (Door) getArguments().getSerializable(ARG_DOOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_door_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mMainPanel = view.findViewById(R.id.main_panel);

        TextView doorNameTextView = (TextView) view.findViewById(R.id.door_name);
        TextView doorIdTextView = (TextView) view.findViewById(R.id.door_id);

        doorNameTextView.setText(mDoor.getName());
        doorIdTextView.setText(mDoor.getDoorId());

        Button openDoorButton = (Button) view.findViewById(R.id.open_door);
        openDoorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDoor();
            }
        });
    }

    private void openDoor() {
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setIndeterminate(true);
        mMainPanel.setVisibility(View.INVISIBLE);
        setCancelable(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        mMainPanel.setVisibility(View.VISIBLE);
                        setCancelable(true);
                        checkAndOpenDoor();
                    }
                });
            }
        }, 3000);


    }

    private void checkAndOpenDoor() {
        User user = new Select().from(User.class).where("userid = ?", PreferenceUtil.Session.getUserId(getActivity())).executeSingle();

        UserDoorRelation userDoorRelation = new Select()
                .from(UserDoorRelation.class)
                .where("user = ?", user.getId())
                .and("door = ?", mDoor.getId())
                .executeSingle();
        DoorLog doorLog = new DoorLog();
        doorLog.setUser(user);
        doorLog.setDoor(mDoor);
        doorLog.setTimestamp(System.currentTimeMillis() + "");
        if (userDoorRelation == null) {
            Toast.makeText(getActivity(), "Access Denied!", Toast.LENGTH_SHORT).show();
            doorLog.setStatus(DoorLog.Status.ACCESS_DENIED);
        } else {
            Toast.makeText(getActivity(), "Door Opened", Toast.LENGTH_SHORT).show();
            doorLog.setStatus(DoorLog.Status.OPENED);
        }
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
