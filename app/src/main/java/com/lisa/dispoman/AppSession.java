package com.lisa.dispoman;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.firebase.firestore.auth.User;
import com.google.gson.Gson;
import com.lisa.dispoman.Model.Users;
import com.lisa.dispoman.Model.apiDispen;
import com.lisa.dispoman.Model.apiGuru;

public class AppSession {
    private SharedPreferences prefs;

    public AppSession(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void clearAll() {
        prefs.edit().clear().commit();
    }

    public void setGuru(apiGuru data) {
        Gson gson = new Gson();
        String dataJson = gson.toJson(data);
        prefs.edit().putString("apiguru", dataJson).commit();
    }

    public apiGuru getGuru() {
        String dataJson = prefs.getString("apiguru", "");
        Gson gson = new Gson();
        apiGuru data = gson.fromJson(dataJson, apiGuru.class);
        return data;
    }

    public void setUsers(Users data) {
        Gson gson = new Gson();
        String dataJson = gson.toJson(data);
        prefs.edit().putString("apiuser", dataJson).commit();
    }

    public Users getUser() {
        String dataJson = prefs.getString("apiuser", "");
        Gson gson = new Gson();
        Users data = gson.fromJson(dataJson, Users.class);
        return data;
    }
    public void setDispen(apiDispen data) {
        Gson gson = new Gson();
        String dataJson = gson.toJson(data);
        prefs.edit().putString("apiuser", dataJson).commit();
    }

    public apiDispen getDispen() {
        String dataJson = prefs.getString("apiuser", "");
        Gson gson = new Gson();
        apiDispen data = gson.fromJson(dataJson, apiDispen.class);
        return data;
    }
}
