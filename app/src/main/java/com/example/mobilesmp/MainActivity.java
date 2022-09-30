package com.example.mobilesmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.core.Amplify;
import com.example.mobilesmp.ui.discover.placeholder.CampaignContent;
import com.example.retrofit.smp.ClassificationsResource;
import com.example.retrofit.smp.CurrentUser;
import com.example.retrofit.smp.FeedbackResource;

import java.util.List;

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
            Amplify.Auth.fetchUserAttributes(
                    attributes -> setAttribute(attributes),
                    error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
            );
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
            intent = new Intent(getApplicationContext(), NavHomeActivity.class);
       }

        // Start activity
        startActivity(intent);
        finish();
    }private void setAttribute(List<AuthUserAttribute> attr){


        //Go to the callback screen
        Intent intent = new Intent(this, NavHomeActivity.class);
        Log.d("LOGIN", "State: After Fetch");
        for (AuthUserAttribute x : attr) {
            if (x.getKey().getKeyString().equals("email")){
                Log.d("AuthEmail", "Current User email = " + x.getValue());
                intent.putExtra("Username", x.getValue());
                intent.putExtra("UserEmail", x.getValue());

                CurrentUser currentUser = new CurrentUser(x.getValue(),x.getValue());
                //currentUser.getUserTypeAPI();
            }else{
                Log.d("AuthEmail", "Other Keys = " + x.getKey().getKeyString());
                Log.d("AuthEmail", "Other Values = " + x.getValue());

            }
        }



        // call async Campaign API and store it inside first
        CampaignContent campaignContent = new CampaignContent();
        campaignContent.getAPICampaigns();

        // call async Feedback API and get count
        FeedbackResource feedbackResource = new FeedbackResource();
        feedbackResource.getCount();

        // call async Classification API and store it inside first
        ClassificationsResource classificationsResource = new ClassificationsResource();
        classificationsResource.getClassificationsAPI();

    }
}