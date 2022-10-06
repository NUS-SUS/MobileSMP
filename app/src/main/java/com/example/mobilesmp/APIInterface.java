package com.example.mobilesmp;


import android.util.Log;

import com.example.retrofit.smp.*;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/nussmpmobile/campaigns")
    Call<CampaignResource> doGetCampaignsResources();

    @GET("/nussmpmobile/feedbacks")
    Call<FeedbackResource> doGetFeedbacksResources();

    @POST("/nussmpmobile/feedback")
    Call<FeedbackContent> submitFeedback(@Header ("Authorization") String idToken,@Body FeedbackContent feedback);

    @GET("/nussmpmobile/payment")
    Call<PaymentContent> doGetPaymentResources(@Query("PAYMENTS_ID") String payment_id);

    @GET("/nussmpmobile/company")
    Call<CompanyResource> doGetCompanyResources(@Query("EMAIL") String email);

    @GET("/nussmpmobile/influencer")
    Call<InfluencerContent> doGetInfluenceResources(@Query("EMAIL") String email);

    @GET("/nussmpmobile/classifications")
    Call<ClassificationsResource> doGetClassificationsResources();

    @POST("/nussmpmobile/influencer")
    Call<InfluencerContent> submitInfluencer(@Body InfluencerContent influencer);

    @POST("/nussmpmobile/company")
    Call<CompanyResource> submitCompany(@Body CompanyResource company);

    @GET("/api/unknown")
    Call<MultipleResource> doGetListResources();

    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}