package com.example.retrofit.smp;

import android.util.Log;

import com.example.mobilesmp.APIClient;
import com.example.mobilesmp.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentUser {
    private static String userName;
    private static String userEmail = "test@gmail.com";
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

    public String getType() {
        return type;
    }

    public void getUserTypeAPI(){
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<InfluencerResource> call1 = apiInterface.doGetInfluenceResources(userEmail);
        call1.enqueue(new Callback<InfluencerResource>() {
            @Override
            public void onResponse(Call<InfluencerResource> call, Response<InfluencerResource> response) {
                Log.d("CurrentUser",response.code()+" => response code");
                InfluencerResource influencerResource = response.body();
                type = "Influencer";
                Log.d("ProfileViewFrag","ThreadID in Curre -> " +Thread.currentThread().getId());
            }

            @Override
            public void onFailure(Call<InfluencerResource> call, Throwable t) {
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
                Log.d("ProfileViewFrag","ThreadID in Curre -> " +Thread.currentThread().getId());
            }

            @Override
            public void onFailure(Call<CompanyResource> call, Throwable t) {
                Log.d("CurrentUser","No Company");
                call.cancel();
            }
        });

    }

}
