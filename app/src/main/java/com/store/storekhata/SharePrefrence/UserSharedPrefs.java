package com.store.storekhata.SharePrefrence;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class UserSharedPrefs {

    private Context context;

    public UserSharedPrefs(Context context) {
        this.context = context;
    }

    private SharedPreferences getUserPreference() {
        return context.getSharedPreferences("user", Activity.MODE_PRIVATE);
    }

    public void removeAllSharedPref()
    {
        getUserPreference().edit().clear().commit();

    }
    // For creating session
    public Boolean isLoggedInUser(){
        return getUserPreference().getBoolean("loggedin", false);
    }
    public void setLoggedInUser(boolean b){
        getUserPreference().edit().putBoolean("loggedin",b).apply();
    }

    public String getAID_forUserLogin() {
        return getUserPreference().getString("AID", "");
    }

    public void putAID_forUserLogin(String s) {
        getUserPreference().edit().putString("AID", s).apply();
    }
    public String getUID_forUserLogin() {
        return getUserPreference().getString("UID", "");
    }

    public void putUID_forUserLogin(String s) {
        getUserPreference().edit().putString("UID", s).apply();
    }

    public String getName_forUserLogin() {
        return getUserPreference().getString("name", "");
    }

    public void putName_forUserLogin(String s) {
        getUserPreference().edit().putString("name", s).apply();
    }
}
