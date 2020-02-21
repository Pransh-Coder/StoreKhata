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

    public void removeAllSP()
    {
        getUserPreference().edit().clear().commit();

    }
    // For creating session
    public Boolean isLoggedIn(){
        return getUserPreference().getBoolean("loggedin", false);
    }
    public void setLoggedIn(boolean b){
        getUserPreference().edit().putBoolean("loggedin",b).apply();
    }

    public String getAID() {
        return getUserPreference().getString("AID", "");
    }

    public void putAID(String s) {
        getUserPreference().edit().putString("AID", s).apply();
    }

    public String getName() {
        return getUserPreference().getString("name", "");
    }

    public void putName(String name) {
        getUserPreference().edit().putString("name", name).apply();
    }
}
