package com.clay.clay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.clay.clay.R;
import com.clay.clay.model.User;
import com.clay.clay.util.PreferenceUtil;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        final EditText usernameEditText = (EditText) findViewById(R.id.username);
        final EditText passwordEditText = (EditText) findViewById(R.id.password);
        final Button loginButton = (Button) findViewById(R.id.sign_in);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    usernameEditText.setError("Please enter your username");
                    return;
                }
                String password = passwordEditText.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    passwordEditText.setError("Please enter your password");
                    return;
                }
                login(username, password);
            }
        });
    }

    private void login(String username, String password) {
        User user = new Select().from(User.class).where("username = ?", username).executeSingle();
        if (user == null) {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!user.getPassword().equals(password)) {
            Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
            return;
        }
        PreferenceUtil.Session.setUserId(this, user.getUserId());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
