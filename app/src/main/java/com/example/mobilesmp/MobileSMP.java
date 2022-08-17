package com.example.mobilesmp;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;

public class MobileSMP extends Application {
    public void onCreate() {
        super.onCreate();

        try {
            Amplify.configure(getApplicationContext());
            Log.i("MobileSMP", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MobileSMP", "Could not initialize Amplify", error);
        }
    }
}
