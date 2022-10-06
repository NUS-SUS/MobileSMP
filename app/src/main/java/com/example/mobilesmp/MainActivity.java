package com.example.mobilesmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.core.Amplify;
import com.example.mobilesmp.ui.discover.placeholder.CampaignContent;
import com.example.retrofit.smp.ClassificationsResource;
import com.example.retrofit.smp.CompanyResource;
import com.example.retrofit.smp.CurrentUser;
import com.example.retrofit.smp.FeedbackResource;
import com.example.retrofit.smp.InfluencerContent;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AuthUser currentUser = Amplify.Auth.getCurrentUser();

        Intent intent;
        Log.d("MainActivity", "called first");
        if (currentUser == null) {
            Log.d("MainActivity", "current User null");
            // Go to the login screen
            intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        } else {
            Log.d("MainActivity", "current User not null");
            // call async Campaign API and store it inside first
            CampaignContent campaignContent = new CampaignContent();
            campaignContent.getAPICampaigns();

            // call async Feedback API and get count
            FeedbackResource feedbackResource = new FeedbackResource();
            feedbackResource.getCount();

            // call async Classification API and store it inside first
            ClassificationsResource classificationsResource = new ClassificationsResource();
            classificationsResource.getClassificationsAPI();

            // Go to the Chat screen
            currentAccountPopulate();
        }

        // Start activity
        finish();

    }

    public void currentAccountPopulate(){
        Log.d("LOGIN", "State: Success");

        Amplify.Auth.fetchUserAttributes(
                attributes -> setAttribute(attributes),
                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
        );
        Amplify.Auth.fetchAuthSession(
                result -> {
                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                    switch(cognitoAuthSession.getIdentityId().getType()) {
                        case SUCCESS:
                            String[] value = cognitoAuthSession.getUserPoolTokens().getValue().toString().split(",");
                            for (String x:value){
                                if (x.contains("idToken=")){
                                    CurrentUser userNow = new CurrentUser();
                                    userNow.setIdToken(x.substring(9,x.length()));
                                }
                            }
                            break;
                        case FAILURE:
                            Log.i("AuthQuickStart", "IdentityId not present because: " + cognitoAuthSession.getIdentityId().getError().toString());
                    }
                },
                error -> Log.e("AuthQuickStart", error.toString())
        );
    }

    private void setAttribute(List<AuthUserAttribute> attr){

        CurrentUser currentUser = new CurrentUser();
        //Go to the callback screen
        Intent intent = new Intent(this, NavHomeActivity.class);
        Log.d("LOGIN", "State: After Fetch");
        for (AuthUserAttribute x : attr) {
            if (x.getKey().getKeyString().equals("email")){
                Log.d("AuthEmail", "Current User email = " + x.getValue());
                intent.putExtra("Username", x.getValue());
                intent.putExtra("UserEmail", x.getValue());

                currentUser = new CurrentUser(x.getValue(),x.getValue());
                //currentUser.getUserTypeAPI();
            }else{
                Log.d("AuthEmail", "Other Keys = " + x.getKey().getKeyString());
                Log.d("AuthEmail", "Other Values = " + x.getValue());

            }
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<InfluencerContent> call1 = apiInterface.doGetInfluenceResources(currentUser.getUserEmail());
        CurrentUser finalCurrentUser = currentUser;
        call1.enqueue(new Callback<InfluencerContent>() {
            @Override
            public void onResponse(Call<InfluencerContent> call, Response<InfluencerContent> response) {
                Log.d("CurrentUser",response.code()+" => response code");
                InfluencerContent influencerContent = response.body();
                influencerContent.setValues();
                Intent intent = new Intent("InfluencerEvent");
                Toast.makeText(getApplicationContext(),"Influencer Profile Retrieved",Toast.LENGTH_SHORT).show();
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                finalCurrentUser.setType("Influencer");
            }

            @Override
            public void onFailure(Call<InfluencerContent> call, Throwable t) {
                Log.d("CurrentUser","No Influencer");
                call.cancel();
            }
        });

        Call<CompanyResource> call2 = apiInterface.doGetCompanyResources(currentUser.getUserEmail());
        call2.enqueue(new Callback<CompanyResource>() {
            @Override
            public void onResponse(Call<CompanyResource> call, Response<CompanyResource> response) {
                Log.d("CurrentUser",response.code()+" => response code");
                CompanyResource companyResource = response.body();
                companyResource.setValues();
                Intent intent = new Intent("CompanyEvent");
                Toast.makeText(getApplicationContext(),"Company Profile Retrieved",Toast.LENGTH_SHORT).show();
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                finalCurrentUser.setType("Company");
            }

            @Override
            public void onFailure(Call<CompanyResource> call, Throwable t) {
                Log.d("CurrentUser","No Influencer");
                call.cancel();
            }
        });

        // call async Campaign API and store it inside first
        CampaignContent campaignContent = new CampaignContent();
        campaignContent.getAPICampaigns();

        // call async Feedback API and get count
        FeedbackResource feedbackResource = new FeedbackResource();
        feedbackResource.getCount();

        // call async Classification API and store it inside first
        ClassificationsResource classificationsResource = new ClassificationsResource();
        classificationsResource.getClassificationsAPI();


        startActivity(intent);

    }

}