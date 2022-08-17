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
import com.amazonaws.mobile.client.SignOutOptions;
import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.core.Amplify;

public class CallbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callback);
    }

    public void onPressLogout(View view) {
        Amplify.Auth.signOut(
                this::onLogoutSuccess,
                this::onLogoutError
        );
//        AWSMobileClient.getInstance().signOut(SignOutOptions.builder().invalidateTokens(true).build(), new Callback<Void>() {
//            public void onResult(Void result) {
//                Log.d("TAG", "onResult: ");
//            }
//
//            public void onError(Exception e) {
//                Log.e("TAG", "onError: ", e);
//            }
//        });
    }

    private void onLogoutError(AuthException e) {
        this.runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        });

    }

    private void onLogoutSuccess() {
        //Go to the chat screen
        Log.d("LOGOUT", "State: Success");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}