package com.clay.clay.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by manuMohan on 15/05/09.
 */
public final class PreferenceUtil {
    public static class Session {
        private static final String ARG_SESSION = "session";
        private static final String ARG_USER_ID = "user_id";

        public static void setUserId(Context context, String userId) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(ARG_SESSION, Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(ARG_USER_ID, userId).apply();
        }

        public static String getUserId(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(ARG_SESSION, Context.MODE_PRIVATE);
            return sharedPreferences.getString(ARG_USER_ID, "");
        }
    }
}
