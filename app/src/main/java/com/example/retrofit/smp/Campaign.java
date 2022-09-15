package com.example.retrofit.smp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Campaign {

    @SerializedName("DESCRIPTION")
    public String description;
    @SerializedName("CAMPAIGNS_ID")
    public String campaignsId;
    @SerializedName("CATEGORY")
    public String category;
    @SerializedName("CAMPAIGN_NAME")
    public String campaignName;
    @SerializedName("STATUS")
    public Boolean status;
    @SerializedName("COMPANIES_ID")
    public String companiesId;
    @SerializedName("START_DATE")
    public String startDate;
    @SerializedName("END_DATE")
    public String endDate;
    @SerializedName("VENUE")
    public String venue;
    @SerializedName("TAGS")
    public List<String> tags = null;
    @SerializedName("APPLIED")
    public List<Object> applied = null;

    @SerializedName("campaigns")
    public List<Campaigns> campaigns = null;

    public class Campaigns{
        @SerializedName("DESCRIPTION")
        public String description;
        @SerializedName("CAMPAIGNS_ID")
        public String campaignsId;
        @SerializedName("CATEGORY")
        public String category;
        @SerializedName("CAMPAIGN_NAME")
        public String campaignName;
        @SerializedName("STATUS")
        public Boolean status;
        @SerializedName("COMPANIES_ID")
        public String companiesId;
        @SerializedName("START_DATE")
        public String startDate;
        @SerializedName("END_DATE")
        public String endDate;
        @SerializedName("VENUE")
        public String venue;
        @SerializedName("TAGS")
        public List<String> tags = null;
        @SerializedName("APPLIED")
        public List<Object> applied = null;
    }
}
