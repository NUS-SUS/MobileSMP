package com.example.retrofit.smp;

import android.util.Log;

import com.example.mobilesmp.APIClient;
import com.example.mobilesmp.APIInterface;
import com.example.mobilesmp.ui.discover.placeholder.CampaignContent;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackResource {

    public static int count;
    APIInterface apiInterface;
    public static List<FeedbackResource> items = new ArrayList<FeedbackResource>();

    @SerializedName("MESSAGE")
    public String message;
    @SerializedName("ASSIGNED")
    public String assigned;
    @SerializedName("FEEDBACK_ID")
    public String feedbackId;
    @SerializedName("COMPANIES_ID")
    public String companiesId;
    @SerializedName("SUBMITTED_DATE")
    public Integer submittedDate;
    @SerializedName("INFLUENCERS_ID")
    public String influencersId;
    @SerializedName("CLOSE_DATE")
    public Integer closeDate;


    @SerializedName("feedbacks")
    public List<FeedbackResource> feedbacks = new ArrayList<>();

    public FeedbackResource(){

    }

    public void getCount(){

        count = 0;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        items.clear();

        /**
         GET List Resources
         **/
        Call<FeedbackResource> call = apiInterface.doGetFeedbacksResources();
        call.enqueue(new Callback<FeedbackResource>() {
            @Override
            public void onResponse(Call<FeedbackResource> call, Response<FeedbackResource> response) {

                Log.d("FeedbackResource",response.code()+" => response code");

                FeedbackResource resource = response.body();

                for (FeedbackResource feedback : resource.feedbacks) {
                    count++;
                }


            }

            @Override
            public void onFailure(Call<FeedbackResource> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
