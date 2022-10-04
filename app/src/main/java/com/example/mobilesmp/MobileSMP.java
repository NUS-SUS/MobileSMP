package com.example.mobilesmp;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
//import com.stripe.android.PaymentConfiguration;

public class MobileSMP extends Application {
    public void onCreate() {
        super.onCreate();
/*        PaymentConfiguration.init(
                getApplicationContext(),
                "pk_test_51Lf1vhKeqC5GsAgqJPXct0k3qE2BHBegOictCp0vM9nSR9CUGcLz2zB2wVFHEnhQJKJih3yDla1VeNbWZUQv3nCA005eGX0OiI"
        );*/
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
