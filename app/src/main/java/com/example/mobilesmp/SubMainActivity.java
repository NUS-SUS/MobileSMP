package com.example.mobilesmp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit.smp.CampaignResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubMainActivity extends AppCompatActivity {
    TextView responseText;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        responseText = (TextView) findViewById(R.id.responseText);
        apiInterface = APIClient.getClient().create(APIInterface.class);


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

                    if (c_status)
                        displayResponse += c_campaignName + " " + c_category
                                + " " + c_tags + " " + c_startDate +
                                " " + c_endDate +"\n\n\n";
                }

                responseText.setText(displayResponse);


            }

            @Override
            public void onFailure(Call<CampaignResource> call, Throwable t) {
                call.cancel();
            }
        });

    }
}
