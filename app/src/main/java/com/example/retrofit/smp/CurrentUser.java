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
    private static String userEmail = "test@gmail.com";

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


    public void getUserTypeAPI(){
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<InfluencerContent> call1 = apiInterface.doGetInfluenceResources(userEmail);
        call1.enqueue(new Callback<InfluencerContent>() {
            @Override
            public void onResponse(Call<InfluencerContent> call, Response<InfluencerContent> response) {
                Log.d("CurrentUser",response.code()+" => response code");
                InfluencerContent influencerContent = response.body();
                influencerContent.setValues();
                type = "Influencer";
            }

            @Override
            public void onFailure(Call<InfluencerContent> call, Throwable t) {
                Log.d("CurrentUser","No Influencer");
                call.cancel();
            }
        });

        Call<CompanyResource> call2 = apiInterface.doGetCompanyResources(userEmail);
        call2.enqueue(new Callback<CompanyResource>() {
            @Override
            public void onResponse(Call<CompanyResource> call, Response<CompanyResource> response) {
                Log.d("CurrentUser",response.code()+" => response code");
                CompanyResource companyResource = response.body();
                type = "Company";
            }

            @Override
            public void onFailure(Call<CompanyResource> call, Throwable t) {
                Log.d("CurrentUser","No Company");
                call.cancel();
            }
        });

    }

}
