package com.example.mobilesmp.ui.discover;

import android.content.Intent;
import android.os.Bundle;

import com.example.mobilesmp.APIClient;
import com.example.mobilesmp.APIInterface;
import com.example.mobilesmp.databinding.ActivityDiscoverBinding;
import com.example.retrofit.smp.CampaignResource;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.mobilesmp.R;

public class DiscoverActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityDiscoverBinding binding;

    APIInterface apiInterface;
    TextView responseText;

    CampaignResource listOfCampaign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDiscoverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_discover);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        listOfCampaign = new ViewModelProvider(this).get(CampaignResource.class);

//        responseText = (TextView) findViewById(R.id.textview_first);

        /**
         GET List Resources
         **/
        Call<CampaignResource> call = apiInterface.doGetCampaignsResources();
        call.enqueue(new Callback<CampaignResource>() {
            @Override
            public void onResponse(Call<CampaignResource> call, Response<CampaignResource> response) {


                Log.d("TAG",response.code()+"");

                String displayResponse = "";

                CampaignResource resource = response.body();
                List<CampaignResource> campaignList = resource.campaigns;

//                CampaignResource listOfCampaign = new CampaignResource();

                for (CampaignResource campaign : campaignList) {
                    String c_description = campaign.description;
                    String c_campaignsId = campaign.campaignsId;
                    String c_category = campaign.category;
                    String c_campaignName = campaign.campaignName;
                    Boolean c_status = campaign.status;
                    String c_companiesId = campaign.companiesId;
                    String c_startDate = campaign.startDate;
                    String c_endDate = campaign.endDate;
                    String c_venue = campaign.venue;
                    List<String> c_tags = campaign.tags;
                    List<String> c_applied = campaign.applied;

                    if (c_status){
                        displayResponse += c_campaignName + " " + c_category
                                + " " + c_tags + " " + c_startDate +
                                " " + c_endDate +"\n\n\n";
                        listOfCampaign.campaigns.add(campaign);
                    }
                }

                Log.d("DiscoverAct", listOfCampaign.getCampaigns());
                responseText.setText(listOfCampaign.getCampaigns());

            }

            @Override
            public void onFailure(Call<CampaignResource> call, Throwable t) {
                call.cancel();
            }
        });


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_discover);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}