package com.example.retrofit.smp;

import android.util.Log;

import com.example.mobilesmp.APIClient;
import com.example.mobilesmp.APIInterface;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassificationsResource {

    APIInterface apiInterface;

    // hashmap with key as "VALUE", values as "CLASSIFICATIONS_ID".
    public static HashMap<String,String> categories;
    // hashmap with key as "PARENT", values as "VALUE"
    public static HashMap<String,List<String>> tags;

    @SerializedName("CLASSIFICATIONS_ID")
    public String c_classificationsId;
    @SerializedName("TYPES")
    public String c_types;
    @SerializedName("PARENT")
    public String c_parent;
    @SerializedName("VALUE")
    public String c_value;

    @SerializedName("classifications")
    public List<ClassificationsResource> classifications = new ArrayList<>();

    public void getClassificationsAPI(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        categories = new HashMap<>();
        tags = new HashMap<>();

        Call<ClassificationsResource> call = apiInterface.doGetClassificationsResources();
        call.enqueue(new Callback<ClassificationsResource>() {
            @Override
            public void onResponse(Call<ClassificationsResource> call, Response<ClassificationsResource> response) {
                Log.d("ClassificationsAPI",response.code()+" => response code");
                ClassificationsResource classificationsResource = response.body();

                for (ClassificationsResource cr : classificationsResource.classifications) {
                    // if parent value is empty, means it is category
                    if (cr.c_parent.equals(""))
                        categories.put(cr.c_value,cr.c_classificationsId);
                    else{
                        List<String> tmp = new ArrayList<>();
                        if (tags.containsKey(cr.c_parent))
                            tmp = tags.get(cr.c_parent);
                        tmp.add(cr.c_value);
                        tags.put(cr.c_parent,tmp);
                    }
                }

                for(Map.Entry<String,String> entry: categories.entrySet()) {
                    Log.d("ClassificationsAPI",entry.getKey() + " => " + entry.getValue());
                }
                for(Map.Entry<String, List<String>> entry: tags.entrySet()) {
                    Log.d("ClassificationsAPI",entry.getKey() + " => " + entry.getValue());
                }

            }

            @Override
            public void onFailure(Call<ClassificationsResource> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
