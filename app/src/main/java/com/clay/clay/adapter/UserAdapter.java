package com.clay.clay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.clay.clay.R;
import com.clay.clay.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manuMohan on 15/05/10.
 */
public class UserAdapter extends BaseAdapter {
    private ArrayList<User> mUsers;
    UserAdapterListener mUserAdapterListener;

    public UserAdapter(List<User> users, UserAdapterListener userAdapterListener) {
        mUsers = (ArrayList<User>) users;
        mUserAdapterListener = userAdapterListener;
    }

    @Override
    public int getCount() {
        return mUsers == null ? 0 : mUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return mUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mUsers.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_user, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final User user = (User) getItem(i);
        viewHolder.userNameTextView.setText(user.getName());
        viewHolder.userSelectCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mUserAdapterListener.userAdded(user);
                } else {
                    mUserAdapterListener.userRemoved(user);
                }
            }
        });
        return view;
    }

    public static class ViewHolder {
        TextView userNameTextView;
        CheckBox userSelectCheckBox;

        public ViewHolder(View view) {
            userNameTextView = (TextView) view.findViewById(R.id.user_name);
            userSelectCheckBox = (CheckBox) view.findViewById(R.id.user_checkbox);
        }
    }

    public interface UserAdapterListener {
        void userAdded(User user);

        void userRemoved(User user);
    }
}
