package com.example.retrofit.smp;

import androidx.lifecycle.ViewModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CampaignResource extends ViewModel {
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
    public List<String> applied = null;

    @SerializedName("campaigns")
    public List<CampaignResource> campaigns = new ArrayList<>();

    public String getCampaigns(){
        String msg = "";
        for (CampaignResource c : campaigns){
            msg+= c.campaignName + " " + c.category + " " + c.tags + " " + c.startDate + " " + c.endDate + "\n\n";
        }
        return msg;
    }
}
