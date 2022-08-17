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
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.core.Amplify;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onPressLogin(View view) {
        EditText txtUsername = findViewById(R.id.txtUsername);
        EditText txtPassword = findViewById(R.id.txtPassword);

        Amplify.Auth.signIn(
                txtUsername.getText().toString(),
                txtPassword.getText().toString(),
                this::onLoginSuccess,
                this::onLoginError

        );
    }


    public void onPressFacebookLogin(View view) {
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
        //Go to the chat screen
        Intent intent = new Intent(this, CallbackActivity.class);
        startActivity(intent);
    }
//    public void onJoinPressed(View view) {
//        Intent intent = new Intent(this, JoinActivity.class);
//        startActivity(intent);
//    }
}