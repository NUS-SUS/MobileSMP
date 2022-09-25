package com.example.mobilesmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.core.Amplify;
import com.example.mobilesmp.ui.discover.placeholder.CampaignContent;

import java.util.List;

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
    private void setAttribute(List<AuthUserAttribute> attr){
        Log.d("LOGIN", "State: After Fetch");
        for (AuthUserAttribute x : attr) {
            if (x.getKey().getKeyString().equals("email")){
                Log.d("AuthEmail", "Current User email = " + x.getValue());
            }else{
                Log.d("AuthEmail", "Other Keys = " + x.getKey().getKeyString());
                Log.d("AuthEmail", "Other Values = " + x.getValue());

            }
        }

        //Go to the callback screen
        Intent intent = new Intent(this, NavHomeActivity.class);
        intent.putExtra("Username", txtUsername.getText().toString());

        // call async Campaign API and store it inside first
        CampaignContent campaignContent = new CampaignContent();
        campaignContent.getAPICampaigns();


        startActivity(intent);

    }
    private void onLoginSuccess(AuthSignInResult authSignInResult) {
        Log.d("LOGIN", "State: Success");

        Amplify.Auth.fetchUserAttributes(
                attributes -> setAttribute(attributes),
                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
        );
    }
//    public void onJoinPressed(View view) {
//        Intent intent = new Intent(this, JoinActivity.class);
//        startActivity(intent);
//    }
}