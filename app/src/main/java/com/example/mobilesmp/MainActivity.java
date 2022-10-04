package com.example.mobilesmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AuthUser currentUser = Amplify.Auth.getCurrentUser();

        Intent intent;

        if (currentUser == null) {
            // Go to the login screen
            // clear shared pref files
            File dir = new File(getApplicationContext().getFilesDir().getParent() + "/shared_prefs/");
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                // clear each preference file
                getApplicationContext().getSharedPreferences(children[i].replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit();
                //delete the file
                new File(dir, children[i]).delete();
            }


            intent = new Intent(getApplicationContext(), LoginActivity.class);
        } else {
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

    }

}