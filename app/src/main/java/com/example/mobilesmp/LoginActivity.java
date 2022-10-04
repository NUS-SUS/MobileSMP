package com.example.mobilesmp;

import static com.example.mobilesmp.Constants.testEnvironment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

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
import com.example.retrofit.smp.ClassificationsResource;
import com.example.retrofit.smp.CompanyResource;
import com.example.retrofit.smp.CurrentUser;
import com.example.retrofit.smp.FeedbackResource;
import com.example.retrofit.smp.InfluencerContent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsername;
    EditText txtPassword;

    Boolean social = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onPressLogin(View view) {
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        if(testEnvironment){
            this.onTestAccount(view);
        }else{
            Amplify.Auth.signIn(
                    txtUsername.getText().toString(),
                    txtPassword.getText().toString(),
                    this::onLoginSuccess,
                    this::onLoginError

            );
        }
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
    private void setAttribute(List<AuthUserAttribute> attr){

        CurrentUser currentUser = new CurrentUser();
        //Go to the callback screen
        Intent intent = new Intent(this, NavHomeActivity.class);
            Log.d("LOGIN", "State: After Fetch");
            for (AuthUserAttribute x : attr) {
                if (x.getKey().getKeyString().equals("email")){
                    Log.d("AuthEmail", "Current User email = " + x.getValue());
                    intent.putExtra("Username", x.getValue());
                    intent.putExtra("UserEmail", x.getValue());

                    currentUser = new CurrentUser(x.getValue(),x.getValue());
                    //currentUser.getUserTypeAPI();
                }else{
                    Log.d("AuthEmail", "Other Keys = " + x.getKey().getKeyString());
                    Log.d("AuthEmail", "Other Values = " + x.getValue());

                }
            }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<InfluencerContent> call1 = apiInterface.doGetInfluenceResources(currentUser.getUserEmail());
        call1.enqueue(new Callback<InfluencerContent>() {
            @Override
            public void onResponse(Call<InfluencerContent> call, Response<InfluencerContent> response) {
                Log.d("CurrentUser",response.code()+" => response code");
                InfluencerContent influencerContent = response.body();
                influencerContent.setValues();
                Intent intent = new Intent("InfluencerEvent");
                Toast.makeText(getApplicationContext(),"Influencer Profile Retrieved",Toast.LENGTH_SHORT).show();
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
            }

            @Override
            public void onFailure(Call<InfluencerContent> call, Throwable t) {
                Log.d("CurrentUser","No Influencer");
                call.cancel();
            }
        });

        Call<CompanyResource> call2 = apiInterface.doGetCompanyResources(currentUser.getUserEmail());
        call2.enqueue(new Callback<CompanyResource>() {
            @Override
            public void onResponse(Call<CompanyResource> call, Response<CompanyResource> response) {
                Log.d("CurrentUser",response.code()+" => response code");
                CompanyResource companyResource = response.body();
                companyResource.setValues();
                Intent intent = new Intent("CompanyEvent");
                Toast.makeText(getApplicationContext(),"Company Profile Retrieved",Toast.LENGTH_SHORT).show();
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
            }

            @Override
            public void onFailure(Call<CompanyResource> call, Throwable t) {
                Log.d("CurrentUser","No Influencer");
                call.cancel();
            }
        });

        // call async Campaign API and store it inside first
        CampaignContent campaignContent = new CampaignContent();
        campaignContent.getAPICampaigns();

        // call async Feedback API and get count
        FeedbackResource feedbackResource = new FeedbackResource();
        feedbackResource.getCount();

        // call async Classification API and store it inside first
        ClassificationsResource classificationsResource = new ClassificationsResource();
        classificationsResource.getClassificationsAPI();


        startActivity(intent);

    }
    private void onLoginSuccess(AuthSignInResult authSignInResult) {
        Log.d("LOGIN", "State: Success");

        Amplify.Auth.fetchUserAttributes(
                attributes -> setAttribute(attributes),
                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
        );
    }

    public void onTestAccount(View view) {
        txtUsername.getText().toString();
        txtPassword.getText().toString();
        Intent intent = new Intent(this, NavHomeActivity.class);
        intent.putExtra("Username", txtUsername.getText().toString());
        intent.putExtra("UserEmail", "smp.marketing.nus.fb@gmail.com");
        CampaignContent campaignContent = new CampaignContent();
        campaignContent.getAPICampaigns();
        startActivity(intent);
    }
}