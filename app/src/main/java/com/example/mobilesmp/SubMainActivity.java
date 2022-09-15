package com.example.mobilesmp;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit.smp.Campaign;
import com.example.retrofit.smp.MultipleResource;
import com.example.retrofit.smp.User;
import com.example.retrofit.smp.UserList;

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

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());

        setContentView(R.layout.activity_main);
        responseText = (TextView) findViewById(R.id.responseText);
        apiInterface = APIClient.getClient().create(APIInterface.class);


        /**
         GET List Resources
         **/
        Call<Campaign> call = apiInterface.doGetCampaignsResources();
        call.enqueue(new Callback<Campaign>() {
            @Override
            public void onResponse(Call<Campaign> call, Response<Campaign> response) {


                Log.d("TAG",response.code()+"");

                String displayResponse = "";

                Campaign resource = response.body();
                List<Campaign.Campaigns> campaignList = resource.campaigns;

                for (Campaign.Campaigns campaign : campaignList) {
                    displayResponse += campaign.description + " " + campaign.campaignsId + " " + campaign.category + " " + campaign.campaignName + "\n";
                }

                responseText.setText(displayResponse);


            }

            @Override
            public void onFailure(Call<Campaign> call, Throwable t) {
                call.cancel();
            }
        });

    }
}
