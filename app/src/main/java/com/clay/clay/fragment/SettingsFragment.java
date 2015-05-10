package com.clay.clay.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.clay.clay.R;
import com.clay.clay.adapter.UserAdapter;
import com.clay.clay.db.DatabaseManager;
import com.clay.clay.model.Door;
import com.clay.clay.model.User;
import com.clay.clay.model.UserDoorRelation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class SettingsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    ArrayList<User> mSelectedUsers;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseManager.createDummyUsers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        mSelectedUsers = new ArrayList<>();

        final EditText doorNameEditText = (EditText) view.findViewById(R.id.door_name_edittext);
        ListView usersListView = (ListView) view.findViewById(R.id.user_list);

        List<User> users = new Select().from(User.class).execute();
        UserAdapter userAdapter = new UserAdapter(users, new UserAdapter.UserAdapterListener() {
            @Override
            public void userAdded(User user) {
                mSelectedUsers.add(user);
            }

            @Override
            public void userRemoved(User user) {
                int index = 0;
                for (User u : mSelectedUsers) {
                    if (u.getUserId().equals(user.getUserId())) {
                        break;
                    }
                    index++;
                }
                if (index < mSelectedUsers.size()) {
                    mSelectedUsers.remove(index);
                }
            }
        });
        usersListView.setAdapter(userAdapter);

        Button addDoorButton = (Button) view.findViewById(R.id.add_door);
        addDoorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doorName = doorNameEditText.getText().toString().trim();
                if (TextUtils.isEmpty(doorName)) {
                    doorNameEditText.setError("Please enter a door name");
                    return;
                }
                if (mSelectedUsers.size() == 0) {
                    Toast.makeText(getActivity(), "At least select one user to open the door", Toast.LENGTH_SHORT).show();
                    return;
                }
                doorNameEditText.setError(null);
                Door door = new Door();
                door.setName(doorName);
                door.setDoorId(UUID.randomUUID().toString());
                door.save();
                for (User user : mSelectedUsers) {
                    UserDoorRelation userDoorRelation = new UserDoorRelation();
                    userDoorRelation.setDoor(door);
                    userDoorRelation.setUser(user);
                    userDoorRelation.save();
                }
                mListener.doorAdded(door);
            }
        });
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
        void doorAdded(Door door);
    }

}
