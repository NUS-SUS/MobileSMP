package com.example.mobilesmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.HostedUIOptions;
import com.amazonaws.mobile.client.IdentityProvider;
import com.amazonaws.mobile.client.SignInUIOptions;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.core.Amplify;
import com.example.mobilesmp.ui.discover.placeholder.CampaignContent;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsername;
    EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onPressLogin(View view) {
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        Amplify.Auth.signIn(
                txtUsername.getText().toString(),
                txtPassword.getText().toString(),
                this::onLoginSuccess,
                this::onLoginError

        );
    }


    public void onPressFacebookLogin(View view) {
//// For Facebook
//        HostedUIOptions hostedUIOptions = HostedUIOptions.builder()
//                .scopes("openid", "email")
//                .identityProvider("Facebook")
//                .build();
//
//        SignInUIOptions signInUIOptions = SignInUIOptions.builder()
//                .hostedUIOptions(hostedUIOptions)
//                .build();
//// 'this' refers to the current active Activity
//        AWSMobileClient.getInstance().showSignIn(this, signInUIOptions, new Callback<UserStateDetails>() {
//            @Override
//            public void onResult(UserStateDetails details) {
//                Log.d("FB LOGIN", "onResult: " + details.getUserState());
//            }
//
//            @Override
//            public void onError(Exception e) {
//                Log.e("FB LOGIN", "onError: ", e);
//            }
//        });
        Amplify.Auth.signInWithSocialWebUI(
                AuthProvider.facebook(),
                this,
                this::onLoginSuccess,
                this::onLoginError
        );
    }

    public void onPressGoogleLogin(View view) {
        Amplify.Auth.signInWithSocialWebUI(
                AuthProvider.google(),
                this,
                this::onLoginSuccess,
                this::onLoginError
        );
    }


    private void onLoginError(AuthException e) {
        this.runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        });

    }

    private void onLoginSuccess(AuthSignInResult authSignInResult) {
        Log.d("LOGIN", "State: Success");

        //Go to the callback screen
        Intent intent = new Intent(this, NavHomeActivity.class);
        intent.putExtra("Username", txtUsername.getText().toString());
        startActivity(intent);
    }
//    public void onJoinPressed(View view) {
//        Intent intent = new Intent(this, JoinActivity.class);
//        startActivity(intent);
//    }
}