package com.store.storekhata.SharePrefrence;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharePrefs {

    private Context context;

    public SharePrefs(Context context) {
        this.context = context;
    }
    private SharedPreferences getUserPreference() {
        return context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }
    public String getAID() {
        return getUserPreference().getString("UID", "");
    }

    public void putAID(String s) {
        getUserPreference().edit().putString("UID", s).apply();
    }
}
