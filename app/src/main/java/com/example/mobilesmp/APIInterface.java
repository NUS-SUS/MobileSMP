package com.example.mobilesmp;

import com.example.retrofit.smp.*;

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
    Call<InfluencerContent> doGetInfluenceResources(@Query("EMAIL") String email);

    @GET("/nussmp/classifications")
    Call<ClassificationsResource> doGetClassificationsResources();

    @POST("/nussmp/influencer")
    Call<InfluencerContent> submitInfluencer(@Body InfluencerContent influencer);

    @POST("/nussmp/company")
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