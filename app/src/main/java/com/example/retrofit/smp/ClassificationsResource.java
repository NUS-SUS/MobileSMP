package com.example.retrofit.smp;

import android.util.Log;

import com.example.mobilesmp.APIClient;
import com.example.mobilesmp.APIInterface;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassificationsResource {

    APIInterface apiInterface;
    public static Set<String> category;

    @SerializedName("classifications")
    public static List<ClassificationContent> classifications = new ArrayList<>();

    public void getClassificationsAPI(){
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<ClassificationsResource> call1 = apiInterface.doGetClassificationsResources();
        call1.enqueue(new Callback<ClassificationsResource>() {
            @Override
            public void onResponse(Call<ClassificationsResource> call, Response<ClassificationsResource> response) {
                Log.d("ClassificationsAPI",response.code()+" => response code");
                ClassificationsResource classificationsResource = response.body();
                List<ClassificationContent> classificationContentList = classificationsResource.classifications;
                category = new HashSet<String>();
                for (ClassificationContent cc : classificationContentList){
                    if (cc.c_parent.equals(""))
                        category.add(cc.c_value);
                }
            }

            @Override
            public void onFailure(Call<ClassificationsResource> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
