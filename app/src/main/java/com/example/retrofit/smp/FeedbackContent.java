package com.example.retrofit.smp;

import com.google.gson.annotations.SerializedName;

public class FeedbackContent {

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


    public FeedbackContent(String message,String feedbackId,
                            Integer submittedDate,String influencersId){
        this.message = message;
        this.feedbackId = feedbackId;
        this.submittedDate = submittedDate;
        this.influencersId = influencersId;
    }
    public FeedbackContent(String message,String feedbackId,String companiesId,
                           Integer submittedDate){
        this.message = message;
        this.feedbackId = feedbackId;
        this.companiesId = companiesId;
        this.submittedDate = submittedDate;
    }
}
