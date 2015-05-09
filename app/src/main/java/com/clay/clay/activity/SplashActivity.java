package com.clay.clay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.clay.clay.R;
import com.clay.clay.util.PreferenceUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                proceedToHomeIfLoggedIn();
            }
        },3000);
    }

    private void proceedToHomeIfLoggedIn() {
        String userId = PreferenceUtil.Session.getUserId(this);
        Intent intent;
        if (TextUtils.isEmpty(userId)) {
            intent = new Intent(this, LoginActivity.class);
        } else {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }


}
