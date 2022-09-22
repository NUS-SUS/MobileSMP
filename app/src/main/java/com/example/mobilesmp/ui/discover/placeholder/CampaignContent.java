package com.example.mobilesmp.ui.discover.placeholder;

import android.util.Log;

import com.example.mobilesmp.APIClient;
import com.example.mobilesmp.APIInterface;
import com.example.retrofit.smp.CampaignResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */


public class CampaignContent{
    APIInterface apiInterface;

    public static List<CampaignContentItem> getItems() {
        return items;
    }

    public static List<CampaignContentItem> items = new ArrayList<CampaignContentItem>();
    public static Map<String, CampaignContentItem> item_map = new HashMap<String, CampaignContentItem>();

    public static int count = 0;

    private void addItem(CampaignContentItem item) {
        items.add(item);
        item_map.put(item.id, item);
    }

    private CampaignContentItem createPlaceholderItem(int position) {
        return new CampaignContentItem(String.valueOf(position), "c_description", "c_campaignsId", "c_category",
                                "c_campaignName", "c_companiesId", "c_startDate",
                                    "c_endDate","c_venue",new ArrayList<String>(),new ArrayList<String>());
    }

    public void getAPICampaigns(){

        count = 0;
        apiInterface = APIClient.getClient().create(APIInterface.class);

        /**
         GET List Resources
         **/
        Call<CampaignResource> call = apiInterface.doGetCampaignsResources();
        call.enqueue(new Callback<CampaignResource>() {
            @Override
            public void onResponse(Call<CampaignResource> call, Response<CampaignResource> response) {

                Log.d("CampaignItem",response.code()+" => response code");

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

                    if (c_status){
                        count++;
                        CampaignContentItem item = new CampaignContentItem(count+"",c_description,c_campaignsId,c_category,c_campaignName,
                                c_companiesId,c_startDate,c_endDate,
                                c_venue,c_tags,c_applied);
                        items.add(item);
                        item_map.put(count+"", item);
                    }
                }


            }

            @Override
            public void onFailure(Call<CampaignResource> call, Throwable t) {
                call.cancel();
            }
        });
    }


    /**
     * A placeholder item representing a piece of content.
     */
    public class CampaignContentItem {

        public final String id;
        public final String c_description;
        public final String c_campaignsId;
        public final String c_category;
        public final String c_campaignName;
        public final String c_companiesId;
        public final String c_startDate;
        public final String c_endDate;
        public final String c_venue;
        public final List<String> c_tags;
        public final List<String> c_applied;

        public CampaignContentItem(String id, String c_description, String c_campaignsId, String c_category, String c_campaignName,
                                 String c_companiesId, String c_startDate, String c_endDate,
                                    String c_venue, List<String> c_tags, List<String> c_applied) {
            this.id = id;
            this.c_description = c_description;
            this.c_campaignsId = c_campaignsId;
            this.c_category = c_category;
            this.c_campaignName = c_campaignName;
            this.c_companiesId = c_companiesId;
            this.c_startDate = c_startDate;
            this.c_endDate = c_endDate;
            this.c_venue = c_venue;
            this.c_tags = c_tags;
            this.c_applied = c_applied;
        }

        @Override
        public String toString() {
            return c_campaignName;
        }
    }


}