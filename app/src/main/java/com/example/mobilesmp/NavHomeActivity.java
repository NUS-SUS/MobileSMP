package com.example.mobilesmp;

import static com.example.mobilesmp.Constants.testEnvironment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.core.Amplify;
import com.example.mobilesmp.ui.payment.PaymentExample;
import com.example.retrofit.smp.CurrentUser;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilesmp.databinding.ActivityNavHomeBinding;

import java.io.File;

public class NavHomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavHomeBinding binding;
    TextView testView;

    private String username = "Username";
    private String userEmail = "UserEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Payment ID example
        //PaymentExample paymentExample = new PaymentExample();
        Log.d("NavHomeActivity", "called first");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("Username");
            userEmail = extras.getString("UserEmail");
            //The key argument here must match that used in the other activity
            Log.d("Username Logging", username);
            Log.d("UserEmail Logging", userEmail);
            CurrentUser.setUserName(username);
            CurrentUser.setUserEmail(userEmail);
        }else{
            if (CurrentUser.getUserName()!=null) {
                username = CurrentUser.getUserName();
            }
            if (CurrentUser.getUserEmail()!=null) {
                userEmail = CurrentUser.getUserEmail();
            }
        }


        binding = ActivityNavHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavHome.toolbar);
        /*
        binding.appBarNavHome.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

         */
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.itemFragment, R.id.profileViewFragment, R.id.feedbackFragment)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_home, menu);

        testView = (TextView) findViewById(R.id.textView);
        testView.setText(CurrentUser.getUserEmail());


        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                // User chose the "logout" item, show the app settings UI...
                onPressLogout();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    public void onPressLogout() {
        if(testEnvironment){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else{
            Amplify.Auth.signOut(
                    this::onLogoutSuccess,
                    this::onLogoutError
            );
        }
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
        File dir = new File(getApplicationContext().getFilesDir().getParent() + "/shared_prefs/");
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++) {
            // clear each preference file
            getApplicationContext().getSharedPreferences(children[i].replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit();
            //delete the file
            new File(dir, children[i]).delete();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}