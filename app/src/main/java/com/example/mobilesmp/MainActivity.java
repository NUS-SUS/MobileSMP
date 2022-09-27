package com.example.mobilesmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.example.mobilesmp.ui.discover.placeholder.CampaignContent;
import com.example.retrofit.smp.FeedbackResource;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        Amplify.Auth.fetchAuthSession(
//                result -> Log.i("AmplifyQuickstart", result.toString()),
//                error -> Log.e("AmplifyQuickstart", error.toString())
//        );
//        Amplify.Auth.signInWithSocialWebUI(AuthProvider.facebook(), this,
//                result -> Log.i("AuthQuickstart", result.toString()),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
        AuthUser currentUser = Amplify.Auth.getCurrentUser();

        Intent intent;

        if(currentUser == null){
            // Go to the login screen
            intent = new Intent(getApplicationContext(), LoginActivity.class);
        }else {

            // call async Campaign API and store it inside first
            CampaignContent campaignContent = new CampaignContent();
            campaignContent.getAPICampaigns();

            // call async Feedback API and get count
            FeedbackResource feedbackResource = new FeedbackResource();
            feedbackResource.getCount();

            // Go to the Chat screen
            intent = new Intent(getApplicationContext(), NavHomeActivity.class);
       }

        // Start activity
        startActivity(intent);
        finish();
    }
}