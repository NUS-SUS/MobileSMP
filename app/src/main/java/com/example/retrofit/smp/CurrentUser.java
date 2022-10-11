package com.example.retrofit.smp;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.mobilesmp.APIClient;
import com.example.mobilesmp.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentUser {
    private static String userName;
    private static String userEmail = "test";

    public static String getIdToken() {
        return idToken;
    }

    public static void setIdToken(String idToken) {
        CurrentUser.idToken = idToken;
    }

    private static String idToken = "";

    public void setType(String type) {
        CurrentUser.type = type;
    }

    public String getType() {
        return type;
    }

    private static String type = "None";

    APIInterface apiInterface;

    public CurrentUser(){
    }


    public CurrentUser(String userName, String userEmail){
        this.userName = userName;
        this.userEmail = userEmail;
    }


    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userNameInput) {
        userName = userNameInput;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmailInput) {
        userEmail = userEmailInput;
    }


}
