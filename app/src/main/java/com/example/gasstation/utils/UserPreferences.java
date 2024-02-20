package com.example.gasstation.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gasstation.models.UserModel;

public class UserPreferences {
    private static SharedPreferences preferences;
    private static final String PREF_KEY = "key-data";
    private static final String USER_KEY = "key-user";

    public static void init(@NonNull Context context) {
        preferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
    }

    public static void setUser(@NonNull UserModel user) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_KEY, user.toJson());
        editor.apply();
    }

    @Nullable
    public static UserModel getUser() {
        String json = preferences.getString(USER_KEY, null);
        return json == null ? null : UserModel.fromJson(json);
    }
}
