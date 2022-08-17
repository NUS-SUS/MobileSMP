package com.example.mobilesmp;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class MobileSMP extends Application {
    public void onCreate() {
        super.onCreate();

        try {
            // Add this line, to include the Auth plugin.
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MobileSMP", "Initialized Amplify");

        } catch (AmplifyException error) {
            Log.e("MobileSMP", "Could not initialize Amplify", error);
        }
    }
}
