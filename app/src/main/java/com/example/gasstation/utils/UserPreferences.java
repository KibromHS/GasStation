package com.example.gasstation.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.gasstation.models.User;

public class UserPreferences {
    private static SharedPreferences preferences;
    private static final String PREF_KEY = "key-data";
    private static final String USER_KEY = "key-user";

    public static void init(Context context) {
        preferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
    }

    public static void setUser(User user) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_KEY, user.toJson());
        editor.apply();
    }

    public static User getUser() {
        String json = preferences.getString(USER_KEY, null);
        return User.fromJson(json);
    }
}
