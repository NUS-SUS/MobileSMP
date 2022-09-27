package com.example.mobilesmp;

import com.example.retrofit.smp.CampaignResource;
import com.example.retrofit.smp.CompanyResource;
import com.example.retrofit.smp.FeedbackContent;
import com.example.retrofit.smp.FeedbackResource;
import com.example.retrofit.smp.InfluencerResource;
import com.example.retrofit.smp.MultipleResource;
import com.example.retrofit.smp.PaymentContent;
import com.example.retrofit.smp.User;
import com.example.retrofit.smp.UserList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/nussmp/campaigns")
    Call<CampaignResource> doGetCampaignsResources();

    @GET("/nussmp/feedbacks")
    Call<FeedbackResource> doGetFeedbacksResources();

    @POST("/nussmp/feedback")
    Call<FeedbackContent> submitFeedback(@Body FeedbackContent feedback);

    @GET("/nussmp/payment")
    Call<PaymentContent> doGetPaymentResources(@Query("PAYMENTS_ID") String payment_id);

    @GET("/nussmp/company")
    Call<CompanyResource> doGetCompanyResources(@Query("EMAIL") String email);

    @GET("/nussmp/influencer")
    Call<InfluencerResource> doGetInfluenceResources(@Query("EMAIL") String email);

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